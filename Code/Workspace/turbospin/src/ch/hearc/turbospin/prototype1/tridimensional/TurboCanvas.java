package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.GraphicsConfiguration;
import java.util.Vector;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;

import ch.hearc.turbospin.prototype1.mathtools.Line3D;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class TurboCanvas extends Canvas3D {
	
	BranchGroup contents = new BranchGroup();
	 Appearance app = new Appearance();

	public TurboCanvas(GraphicsConfiguration arg0) {
		super(arg0);

		SimpleUniverse universe = new SimpleUniverse(this);
		universe.getViewingPlatform().setNominalViewingTransform();

		contents.addChild(new ColorCube(0.3));

		universe.addBranchGraph(contents);
	}
	
	
	public void addAxis(Line3D axis)
	{
		//TODO Add the Line3D to the branch group
		// Nothing is working of the code below
		Vector<Double> axisDirection = axis.getVectorDirection();
		
		Point3f[] plaPts = new Point3f[2];
	    plaPts[0] = new Point3f(-0.9f, -0.7f, 0.0f);
	    plaPts[1] = new Point3f(-0.5f, 0.7f, 0.0f);
	    LineArray pla = new LineArray(2, LineArray.COORDINATES);
	    pla.setCoordinates(0, plaPts);
	    Shape3D plShape = new Shape3D(pla, app);
	    
	    TransformGroup objTrans = new TransformGroup();
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

	    contents.addChild(objTrans);
		
	}
}
