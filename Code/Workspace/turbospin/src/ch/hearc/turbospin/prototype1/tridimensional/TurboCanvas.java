
package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.mathtools.Line3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class TurboCanvas extends Canvas3D
	{

	BranchGroup contents = new BranchGroup();
	Appearance app = new Appearance();

	public TurboCanvas(GraphicsConfiguration arg0)
		{
		super(arg0);

		SimpleUniverse universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		contents.addChild(new ColorCube(0.3));

		addAxis(new Line3D());

		universe.addBranchGraph(contents);
		}

	public void addAxis(Line3D axis)
		{

		Group lineGroup = new Group();
		//TODO Add the Line3D to the branch group
		// Nothing is working of the code below
		Vector3D axisDirection = axis.getVectorDirection();

		Point3f[] plaPts = new Point3f[2];
		plaPts[0] = new Point3f(-0.9f, -0.7f, 0.0f);
		plaPts[1] = new Point3f(-0.5f, 0.7f, 0.0f);
		LineArray pla = new LineArray(2, GeometryArray.COORDINATES);
		pla.setCoordinates(0, plaPts);
		Shape3D plShape = new Shape3D(pla, app);
		lineGroup.addChild(plShape);

		TransformGroup objScale = new TransformGroup();
		Transform3D scaleTrans = new Transform3D();
		objScale.setTransform(scaleTrans);
		contents.addChild(objScale);

		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

		objScale.addChild(objTrans);
		objTrans.addChild(lineGroup);
		}
	}
