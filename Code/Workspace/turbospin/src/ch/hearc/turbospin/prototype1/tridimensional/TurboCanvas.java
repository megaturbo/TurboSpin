package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import ch.hearc.turbospin.prototype1.mathtools.Line3D;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class TurboCanvas extends Canvas3D {
	
	BranchGroup contents = new BranchGroup();

	public TurboCanvas(GraphicsConfiguration arg0) {
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
		Vector<Double> axisDirection = axis.getVectorDirection();

		Appearance appearance = new Appearance();
		
		
		Point3f[] plaPts = new Point3f[2];
	    plaPts[0] = new Point3f(-0.9f, -0.7f, 0.0f);
	    plaPts[1] = new Point3f(-0.5f, 0.7f, 0.0f);
	    LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    pla.setColor(0, new Color3f(0.2f,0f,0f));
	    pla.setCoordinates(0, plaPts);
	    Shape3D plShape = new Shape3D(pla, appearance);
	    
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
