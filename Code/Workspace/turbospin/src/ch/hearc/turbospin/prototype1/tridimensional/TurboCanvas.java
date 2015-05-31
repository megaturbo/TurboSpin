
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.List;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;

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
		vectorsBG.setCapability(BranchGroup.ALLOW_DETACH);
		mainTG.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		mainTG.setCapability(Group.ALLOW_CHILDREN_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		setBackgroundColor(TurboColors.WHITE);
		createAxisSystem();

		// Controls
		createMouseNavigation();

		Transform3D rotate = new Transform3D();
		rotate.rotX(Math.PI / 4);
		mainTG.setTransform(rotate);

		// add the branch to the universe
		mainBG.addChild(mainTG);
		//		mainTG.addChild(vectorsBG);

		// Faster rendering
		//				mainBG.compile(); //this makes everything crash, for some reason

		universe.addBranchGraph(mainBG);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/**
	 * Add a vector starting at origin, with a parallelepiped showing its 3D components
	 * @param v End location
	 */
	public void addParallelepiped(Vector3D vector)
		{
		vectorsBG.detach();

		//adding the vector
		//		vectorsBG.addChild(vector);

		//adding a cube
		//colored vertices
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(0, vector.getB(), vector.getC()), TurboColors.RED, 1));
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(vector.getA(), 0, vector.getC()), TurboColors.GREEN, 1));
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), vector.getC()), new Point3D(vector.getA(), vector.getB(), 0), TurboColors.BLUE, 1));

		//black vertices
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, vector.getC()), new Point3D(0, 0, vector.getC()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), 0), new Point3D(0, vector.getB(), 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));

		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), vector.getB(), 0), new Point3D(vector.getA(), 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		vectorsBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), vector.getC()), new Point3D(0, 0, vector.getC()), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));

		vectorsBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), vector.getC()), new Point3D(0, vector.getB(), 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
		vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, vector.getC()), new Point3D(vector.getA(), 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));

		//black vertices that may be in the axes
		if (vector.getA() < 0)
			{
			vectorsBG.addChild(new Vertex3D(new Point3D(vector.getA(), 0, 0), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}
		if (vector.getB() < 0)
			{
			vectorsBG.addChild(new Vertex3D(new Point3D(0, vector.getB(), 0), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}
		if (vector.getC() < 0)
			{
			vectorsBG.addChild(new Vertex3D(new Point3D(0, 0, vector.getC()), new Point3D(0, 0, 0), TurboColors.BLACK, 1, LineAttributes.PATTERN_DASH));
			}

		mainTG.addChild(vectorsBG);
		}

	public void addParallelepiped(Point3D point)
		{
		addParallelepiped(new Vector3D(point));
		}

	/**
	 * Add a vector starting at origin
	 * @param v End location
	 */
	public void addVector(Vector3D vector)
		{
		vectorsBG.detach();
		vectorsBG.addChild(vector);
		mainTG.addChild(vectorsBG);
		}

	public void addPoint(Point3D point)
		{
		vectorsBG.detach();
		vectorsBG.addChild(point);
		mainTG.addChild(vectorsBG);
		}

	/**
	 * Removes all vectors from the scene
	 */
	public void clear()
		{
		vectorsBG.detach();
		vectorsBG.removeAllChildren();
		mainTG.addChild(vectorsBG);
		createAxisSystem();
		}

	/**
	 * redraws vectors, useful if they have been changed
	 */
	public void refresh()
		{
		vectorsBG.detach();
		vectorsBG.removeAllChildren();
		for(Shape3D shape:shapes)
			{
			if (shape instanceof Vector3D)
				{
					addVector((Vector3D)shape);
					addParallelepiped((Vector3D)shape);
				}
			if (shape instanceof Point3D)
				{
				addPoint((Point3D)shape);
				addParallelepiped((Point3D)shape);
				}
			}
		createAxisSystem();
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
		addVector(new Vector3D(1, 0, 0, TurboColors.RED, 3));
		addVector(new Vector3D(0, 1, 0, TurboColors.GREEN, 3));
		addVector(new Vector3D(0, 0, 1, TurboColors.BLUE, 3));
		addVector(new Vector3D(100, 0, 0, TurboColors.RED, 1));
		addVector(new Vector3D(0, 100, 0, TurboColors.GREEN, 1));
		addVector(new Vector3D(0, 0, 100, TurboColors.BLUE, 1));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private BranchGroup mainBG = new BranchGroup();
	private BranchGroup vectorsBG = new BranchGroup();
	private TransformGroup mainTG = new TransformGroup();
	private List<Shape3D> shapes;
	private SimpleUniverse universe;
	}
