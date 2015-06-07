
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.Arrays;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.RotationItem;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

public class TurboCanvas extends Canvas3D
	{
	
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	
	public TurboCanvas(GraphicsConfiguration arg0, List<Shape3D> shapes)
		{
		super(arg0);
		
		this.shapes = shapes;
		
		// Universe
		universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();
		
		// Groups capabilities
		shapesBG.setCapability(BranchGroup.ALLOW_DETACH);
		mainTG.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		mainTG.setCapability(Group.ALLOW_CHILDREN_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		setBackgroundColor(TurboColors.WHITE);
		createAxisSystem();
		
		// Controls
		createMouseNavigation();
		
		// camera position at start
		Transform3D rotate = new Transform3D();
		rotate.lookAt(new Point3d(8, 8, 8), new Point3d(2, 2, 0), new Vector3d(-0.5, 5, 0));
		mainTG.setTransform(rotate);
		
		// add the branch to the universe
		mainBG.addChild(mainTG);
		// mainTG.addChild(vectorsBG);
		
		// Faster rendering
		// mainBG.compile(); //this makes everything crash, for some reason
		
		universe.addBranchGraph(mainBG);
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	
	/**
	 * Add a vector starting at origin, with a parallelepiped showing its 3D
	 * components
	 *
	 * @param v
	 *            End location
	 */
	public void addParallelepiped(Vector3D vector)
		{
		shapesBG.detach();
		
		// colored vertices
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(0, vector.getB(), vector.getC()), TurboColors.RED, 1));
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(vector.getA(), 0, vector.getC()), TurboColors.GREEN, 1));
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(vector.getA(), vector.getB(), 0), TurboColors.BLUE, 1));
		
		// black vertices
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, vector.getC()), new Point3D(0, 0, vector.getC()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), 0), new Point3D(0, vector.getB(), 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), 0), new Point3D(vector.getA(), 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), vector.getC()), new Point3D(0, 0, vector.getC()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		shapesBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), vector.getC()), new Point3D(0, vector.getB(), 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, vector.getC()), new Point3D(vector.getA(), 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		// black vertices that may be in the axes
		if (vector.getA() < 0)
			{
			shapesBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, 0), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}
		if (vector.getB() < 0)
			{
			shapesBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), 0), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}
		if (vector.getC() < 0)
			{
			shapesBG.addChild(new Vertex3D(new Point3D(0, 0, vector.getC()), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}
		
		mainTG.addChild(shapesBG);
		}
	
	public void addParallelepiped(Vertex3D vertex)
		{
		Point3D A = vertex.getA();
		Point3D B = vertex.getB();
		
		shapesBG.detach();
		
		// colored vertices
		shapesBG.addChild(new Vertex3D(A, new Point3D(B.getX(), A.getY(), A.getZ()), TurboColors.RED, 1));
		shapesBG.addChild(new Vertex3D(A, new Point3D(A.getX(), B.getY(), A.getZ()), TurboColors.GREEN, 1));
		shapesBG.addChild(new Vertex3D(A, new Point3D(A.getX(), A.getY(), B.getZ()), TurboColors.BLUE, 1));
		
		// black vertices
		shapesBG.addChild(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(B.getX(), A.getY(), B.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		shapesBG.addChild(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(A.getX(), B.getY(), B.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		shapesBG.addChild(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), A.getY(), B.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(A.getX(), B.getY(), B.getZ()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		// black vertices that may be in the axes
		shapesBG.addChild(new Vertex3D(new Point3D(A.getX(), B.getY(), B.getZ()), B, TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(B.getX(), A.getY(), B.getZ()), B, TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		shapesBG.addChild(new Vertex3D(new Point3D(B.getX(), B.getY(), A.getZ()), B, TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		
		mainTG.addChild(shapesBG);
		}
	
	public void addParallelepiped(Shape3D shape)
		{
		if (shape instanceof Point3D)
			{
			addParallelepiped(new Vertex3D(new Point3D(), (Point3D)shape));
			}
		else if (shape instanceof Vector3D)
			{
			addParallelepiped(new Vertex3D(new Vector3D(), (Vector3D)shape));
			}
		else if (shape instanceof Vertex3D)
			{
			addParallelepiped((Vertex3D)shape);
			}
		}
	
	public void createTrail()
		{
		if (selectedRotation != null && selectedShape != null)
			{
			if (selectedRotation instanceof Quaternion)
				{
				createTrail((Quaternion)selectedRotation, (Vector3D)selectedShape);
				}
			}
		}
	
	private void createTrail(Quaternion q, Vector3D v)
		{
		// LineStripArray
		double theta = QuaternionTools.getAngle(q);
		Vector3D axis = QuaternionTools.getAxis(q);
		Quaternion qslow = QuaternionTools.createRotationQuaternion(2.0 * Math.PI / 360.0, axis);
		theta *= 180.0 / Math.PI;
		Vector3D tmp = new Vector3D(v);
		
		LineStripArray lsa = new LineStripArray((int)theta + 2, GeometryArray.COORDINATES | GeometryArray.COLOR_3, new int[] { (int)theta + 2 });
		
		Point3d pointTmp = new Point3d(tmp.getA(), tmp.getB(), tmp.getC());
		Point3d[] pointsInit = new Point3d[(int)theta + 1];
		pointsInit[0] = new Point3d(pointTmp);
		
		for(int i = 0; i < (int)theta; i++)
			{
			tmp = QuaternionTools.rotation(tmp, qslow);
			pointTmp = new Point3d(tmp.getA(), tmp.getB(), tmp.getC());
			lsa.setCoordinate(i, pointTmp);
			pointsInit[i + 1] = new Point3d(pointTmp);
			}
		lsa.setCoordinate((int)theta, new Point3d(0.0, 0.0, 0.0));
		lsa.setCoordinate((int)theta + 1, new Point3d(v.getA(), v.getB(), v.getC()));
		
		Color3f[] colors = new Color3f[(int)theta + 2];
		Arrays.fill(colors, TurboColors.PINK);
		lsa.setColors(0, colors);
		trailLines = new Shape3D(lsa);
		
		// polygons
		Point3d[] points = new Point3d[2 * pointsInit.length];
		for(int i = 0; i < pointsInit.length; i++)
			{
			points[i] = new Point3d(pointsInit[i]);
			points[points.length - 1 - i] = new Point3d(pointsInit[i]);
			}
		
		TriangleArray geometryArray = new TriangleArray(points.length * 3, GeometryArray.COORDINATES);
		for(int i = 0; i < points.length - 1; i++)
			{
			geometryArray.setCoordinate(3 * i, new Point3d(0.0f, 0.0f, 0.0f));
			geometryArray.setCoordinate(3 * i + 1, points[i]);
			geometryArray.setCoordinate(3 * i + 2, points[i + 1]);
			}
		
		GeometryInfo gi = new GeometryInfo(geometryArray);
		
		NormalGenerator normalGenerator = new NormalGenerator();
		normalGenerator.generateNormals(gi);
		GeometryArray polygons = gi.getIndexedGeometryArray();
		
		// Appearance
		Appearance ap = new Appearance();
		Material material = new Material();
		material.setEmissiveColor(0.8f, 0.8f, 0.8f);
		material.setSpecularColor(0.8f, 0.5f, 0.5f);
		material.setColorTarget(Material.SPECULAR);
		ap.setMaterial(material);
		
		TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST, 0.5f);
		ap.setTransparencyAttributes(ta);
		
		trail = new Shape3D(polygons, ap);
		}
	
	public void addShape(Shape3D shape)
		{
		shapesBG.detach();
		shapesBG.addChild(shape);
		mainTG.addChild(shapesBG);
		}
	
	public void removeShape(Shape3D shape)
		{
		shapesBG.detach();
		shapesBG.removeChild(shape);
		mainTG.addChild(shapesBG);
		}
	
	/**
	 * Removes all vectors from the scene
	 */
	public void clear()
		{
		shapesBG.detach();
		shapesBG.removeAllChildren();
		mainTG.addChild(shapesBG);
		createAxisSystem();
		}
	
	/**
	 * redraws vectors, useful if they have been changed
	 */
	public void refresh()
		{
		shapesBG.detach();
		shapesBG.removeAllChildren();
		for(Shape3D shape:shapes)
			{
			addShape(shape);
			}
		addParallelepiped(selectedShape);
		addShape(trail);
		addShape(trailLines);
		createAxisSystem();
		}
	
	public void setSelected(Shape3D shape)
		{
		this.selectedShape = shape;
		createTrail();
		this.refresh();
		}
	
	public void setSelected(RotationItem rotationItem)
		{
		this.selectedRotation = rotationItem;
		createTrail();
		this.refresh();
		}
	
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void createMouseNavigation()
		{
		// Mouse rotation rotates the view
		MouseRotate mouseRotate = new MouseRotate(mainTG);
		MouseZoom mouseZoom = new MouseZoom(mainTG);
		MouseTranslate mouseTranslate = new MouseTranslate(mainTG);
		
		BoundingSphere bs = new BoundingSphere();
		
		mouseRotate.setSchedulingBounds(bs);
		mouseZoom.setSchedulingBounds(bs);
		mouseTranslate.setSchedulingBounds(bs);
		
		mainBG.addChild(mouseRotate);
		mainBG.addChild(mouseZoom);
		mainBG.addChild(mouseTranslate);
		}
	
	private void setBackgroundColor(Color3f color)
		{
		Background background = new Background(color);
		BoundingSphere sphere = new BoundingSphere(new Point3d(0, 0, 0), 100000);
		background.setApplicationBounds(sphere);
		mainBG.addChild(background);
		}
	
	private void createAxisSystem()
		{
		shapesBG.detach();
		shapesBG.addChild(new Vector3D(1, 0, 0, TurboColors.RED, 3));
		shapesBG.addChild(new Vector3D(0, 1, 0, TurboColors.GREEN, 3));
		shapesBG.addChild(new Vector3D(0, 0, 1, TurboColors.BLUE, 3));
		shapesBG.addChild(new Vector3D(100, 0, 0, TurboColors.RED, 1));
		shapesBG.addChild(new Vector3D(0, 100, 0, TurboColors.GREEN, 1));
		shapesBG.addChild(new Vector3D(0, 0, 100, TurboColors.BLUE, 1));
		mainTG.addChild(shapesBG);
		}
	
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	
	private BranchGroup mainBG = new BranchGroup();
	private BranchGroup shapesBG = new BranchGroup();
	private TransformGroup mainTG = new TransformGroup();
	private List<Shape3D> shapes;
	private SimpleUniverse universe;
	private Shape3D selectedShape;
	private RotationItem selectedRotation;
	private Shape3D trail;
	private Shape3D trailLines;
	
	}
