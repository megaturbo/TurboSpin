package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class TurboCanvas extends Canvas3D {

	BranchGroup branchGroup = new BranchGroup();
	TransformGroup viewGroup = new TransformGroup();

	public TurboCanvas(GraphicsConfiguration arg0) {
		super(arg0);

		SimpleUniverse universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		setBackgroundColor(TurboColors.WHITE);
		createAxisSystem();
		addVector(new Vector3D(0, 1, 0), new Vector3D(0.5, 0, 0), TurboColors.PINK);
		
		// Capability to read/write a Transform
	    viewGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    viewGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

	    branchGroup.addChild(viewGroup);
	    
	    // Mouse rotation rotates the view
		MouseRotate myMouseRotate = new MouseRotate();
		myMouseRotate.setTransformGroup(viewGroup);
		myMouseRotate.setSchedulingBounds(new BoundingSphere());
		branchGroup.addChild(myMouseRotate);

		// add the branch to the universe
		universe.addBranchGraph(branchGroup);
	}

	private void setBackgroundColor(Color3f color) {
		Background background = new Background(color);
		BoundingSphere sphere = new BoundingSphere(new Point3d(0, 0, 0), 100000);
		background.setApplicationBounds(sphere);
		branchGroup.addChild(background);
	}

	private void createAxisSystem() {
		Vector3D origin = new Vector3D(0, 0, 0);
		addVector(origin, new Vector3D(1, 0, 0), TurboColors.RED);
		addVector(origin, new Vector3D(0, 1, 0), TurboColors.GREEN);
		addVector(origin, new Vector3D(0, 0, 1), TurboColors.BLUE);
	}

	public void addVector(Vector3D v0, Vector3D v1, Color3f color) {

		Group lineGroup = new Group();

		// LineArray
		Point3d[] plaPts = new Point3d[2];
		plaPts[0] = new Point3d(v0.getA(), v0.getB(), v0.getC());
		plaPts[1] = new Point3d(v1.getA(), v1.getB(), v1.getC());
		LineArray pla = new LineArray(2, LineArray.COORDINATES);
		pla.setCoordinates(0, plaPts);

		// Color
		Appearance appearance = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(color, 0);
		appearance.setColoringAttributes(ca);

		// Shape
		Shape3D plShape = new Shape3D(pla, appearance);

		lineGroup.addChild(plShape);

		viewGroup.addChild(lineGroup);
	}
}
