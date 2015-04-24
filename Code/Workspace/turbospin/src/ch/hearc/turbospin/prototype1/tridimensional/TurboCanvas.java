
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.ArrayList;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class TurboCanvas extends Canvas3D
	{

	BranchGroup mainBG = new BranchGroup();
	TransformGroup mainTG = new TransformGroup();
	ArrayList<Node> elements = new ArrayList<Node>();

	public TurboCanvas(GraphicsConfiguration arg0)
		{
		super(arg0);

		// Universe
		SimpleUniverse universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		// Capability to read/write a Transform
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mainTG.setCapability(Group.ALLOW_CHILDREN_EXTEND);

		setBackgroundColor(TurboColors.WHITE);
		createAxisSystem();

		// Controls
		createMouseNavigation();

		Transform3D rotate = new Transform3D();
		rotate.rotX(Math.PI / 4);
		mainTG.setTransform(rotate);

		// add the branch to the universe
		mainBG.addChild(mainTG);

		// Faster rendering
		mainBG.compile();

		universe.addBranchGraph(mainBG);
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
		addVector(new Vector3D(1, 0, 0), TurboColors.RED);
		addVector(new Vector3D(0, 1, 0), TurboColors.GREEN);
		addVector(new Vector3D(0, 0, 1), TurboColors.BLUE);
		}

	public void addText(String text, Color3f color, Vector3D location)
		{
		BranchGroup bg = new BranchGroup();

		}

	/**
	 * Add a vector starting at origin
	 * @param v End location
	 * @param color
	 */
	public void addVector(Vector3D v, Color3f color)
		{
		addVector(new Vector3D(), v, color);
		}

	/**
	 * Add a vector with start location and end location
	 * @param v0 Start location
	 * @param v1 End location
	 * @param color
	 */
	public void addVector(Vector3D v0, Vector3D v1, Color3f color)
		{

		BranchGroup tempGroup = new BranchGroup();
		Group lineGroup = new Group();

		// LineArray
		Point3d[] pointLineArrayPoints = new Point3d[2];
		pointLineArrayPoints[0] = new Point3d(v0.getA(), v0.getB(), v0.getC());
		pointLineArrayPoints[1] = new Point3d(v1.getA(), v1.getB(), v1.getC());
		LineArray pointLineArray = new LineArray(2, GeometryArray.COORDINATES);
		pointLineArray.setCoordinates(0, pointLineArrayPoints);

		// Color
		Appearance appearance = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(color, 0);
		appearance.setColoringAttributes(ca);

		//Width
		LineAttributes la = new LineAttributes();
		la.setLineWidth(3);
		appearance.setLineAttributes(la);

		// Shape
		Shape3D plShape = new Shape3D(pointLineArray, appearance);

		elements.add(lineGroup);
		lineGroup.addChild(plShape);

		tempGroup.addChild(lineGroup);
		mainTG.addChild(tempGroup);
		}

	public void clear()
		{
		mainTG.removeAllChildren();
		mainBG.removeAllChildren();
		elements.clear();
		createAxisSystem();
		}
	}
