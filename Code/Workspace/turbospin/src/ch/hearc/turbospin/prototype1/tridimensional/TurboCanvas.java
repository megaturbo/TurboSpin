
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.Arrays;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.TriangleArray;
import javax.swing.JButton;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.mathtools.Matrix;
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

		//parallepipedusorz init
		para = new Vertex3D[12];
		// colored vertices
		para[0] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.RED, 1);
		para[1] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.GREEN, 1);
		para[2] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLUE, 1);
		// black vertices
		para[3] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[4] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[5] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[6] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[7] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[8] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		// black vertices that may be in the axes
		para[9] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[10] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		para[11] = new Vertex3D(new Point3D(), new Point3D(), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH);
		for(int i = 0; i < para.length; i++)
			{
			paraBG.addChild(para[i]);
			}
		quaternionAxis = new Vertex3D(new Point3D(), new Point3D(), TurboColors.LIGHTGRAY, 2);
		addShape(trail);
		addShape(trailLines);
		addShape(quaternionAxis);

		// Universe
		universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		// Groups capabilities
		shapesBG.setCapability(BranchGroup.ALLOW_DETACH);
		paraBG.setCapability(BranchGroup.ALLOW_DETACH);
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
		mainTG.addChild(paraBG);
		mainBG.addChild(mainTG);

		// Faster rendering
		//		 mainBG.compile(); //this makes everything crash, for some reason

		universe.addBranchGraph(mainBG);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addParallelepiped(Vertex3D vertex)
		{
		Point3D A = vertex.getA();
		Point3D B = vertex.getB();
		// colored vertices
		para[0].set(new Vertex3D(A, new Point3D(B.getX(), A.getY(), A.getZ())));
		para[1].set(new Vertex3D(A, new Point3D(A.getX(), B.getY(), A.getZ())));
		para[2].set(new Vertex3D(A, new Point3D(A.getX(), A.getY(), B.getZ())));
		// black vertices
		para[3].set(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ())));
		para[4].set(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(B.getX(), A.getY(), B.getZ())));
		para[5].set(new Vertex3D(new Point3D(A.getX(), A.getY(), B.getZ()), new Point3D(A.getX(), B.getY(), B.getZ())));
		para[6].set(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), B.getY(), A.getZ())));
		para[7].set(new Vertex3D(new Point3D(B.getX(), A.getY(), A.getZ()), new Point3D(B.getX(), A.getY(), B.getZ())));
		para[8].set(new Vertex3D(new Point3D(A.getX(), B.getY(), A.getZ()), new Point3D(A.getX(), B.getY(), B.getZ())));
		// black vertices that may be in the axes
		para[9].set(new Vertex3D(new Point3D(A.getX(), B.getY(), B.getZ()), B));
		para[10].set(new Vertex3D(new Point3D(B.getX(), A.getY(), B.getZ()), B));
		para[11].set(new Vertex3D(new Point3D(B.getX(), B.getY(), A.getZ()), B));
		}

	public void addParallelepiped()
		{
		Shape3D shape = selectedShape;
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
			addParallelepiped(new Vertex3D(new Point3D(), new Point3D((Vector3D)shape)));
			}
		}

	public void createTrail()
		{
		removeTrail();
		if (selectedRotation != null && selectedShape != null)
			{
			if (selectedRotation instanceof Quaternion)
				{
				Quaternion q = (Quaternion)selectedRotation;
				Vector3D axis = QuaternionTools.getAxis(q);
				quaternionAxis.set(new Vertex3D(new Point3D(axis.multiply(-50.0)), new Point3D(axis.multiply(50.0))));
				createTrail((Quaternion)selectedRotation, (Vector3D)selectedShape);
				}
			if (selectedRotation instanceof Matrix)
				{
				createTrail((Matrix)selectedRotation, (Vector3D)selectedShape);
				}
			}
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
		//		shapesBG.detach();
		//		shapesBG.removeAllChildren();
		//		for(Shape3D shape:shapes)
		//			{
		//			addShape(shape);
		//			}
		repaint();
		addParallelepiped();
		//				addShape(trail);
		//		addShape(trailLines);
		//		createAxisSystem();
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
		GeometryArray polygons = gi.getIndexedGeometryArray();

		// Appearance
		Appearance ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(TurboColors.LIGHTGRAY, 1);
		ap.setColoringAttributes(ca);
		TransparencyAttributes ta = new TransparencyAttributes(TransparencyAttributes.NICEST, 0.5f);
		ap.setTransparencyAttributes(ta);

		trail = new Shape3D(polygons, ap);
		addShape(trail);
		addShape(trailLines);
		refresh();
		}

	public void removeTrail()
		{
		shapesBG.detach();
		quaternionAxis.set(new Point3D(), new Point3D());
		shapesBG.removeChild(trail);
		shapesBG.removeChild(trailLines);
		trail = null;
		trailLines = null;
		mainTG.addChild(shapesBG);
		refresh();
		}

	private void createTrail(Matrix selectedRotation2, Vector3D selectedShape2)

		{
		// TODO Auto-generated method stub

		}

	public void rotate(JButton buttonRotate)
		{
		if (!isRunning)
			{
			isRunning = true;
			buttonRotate.setEnabled(false);
			Thread thread = new Thread(new Runnable()
				{

					@Override
					public void run()
						{
						double theta = QuaternionTools.getAngle((Quaternion)selectedRotation);
						theta *= 180.0 / Math.PI;
						for(int i = 0; i < (int)theta; i++)
							{
							try
								{
								Thread.sleep(1000 / 27);
								}
							catch (InterruptedException e)
								{
								// NOP
								}
							slowRotate();
							repaint();
							addParallelepiped();
							}
						createTrail();
						refresh();
						isRunning = false;
						buttonRotate.setEnabled(true);
						}

					private void slowRotate()
						{
						Vector3D axis = QuaternionTools.getAxis((Quaternion)selectedRotation);
						Quaternion qslow = QuaternionTools.createRotationQuaternion(2.0 * Math.PI / 360.0, axis);
						((Vector3D)selectedShape).set(QuaternionTools.rotation((Vector3D)selectedShape, qslow));
						}
				});
			thread.start();
			}
		}

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
	private BranchGroup paraBG = new BranchGroup();
	private TransformGroup mainTG = new TransformGroup();
	private List<Shape3D> shapes;
	private SimpleUniverse universe;
	private Shape3D selectedShape;
	private RotationItem selectedRotation;
	private Shape3D trail;
	private Shape3D trailLines;
	private Vertex3D quaternionAxis;
	private Vertex3D[] para;
	private boolean isRunning = false;
	}
