package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class TridimensionalTests extends JPanel {

	public TridimensionalTests() {
		setLayout(new BorderLayout());
		
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(config);
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        // SimpleUniverse is a Convenience Utility class
        SimpleUniverse u = new SimpleUniverse(canvas3D);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(scene);
	}
	
	public BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();
		
		Background background = new Background(new Color3f(1f,0,0));
		BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
		background.setApplicationBounds(sphere);
		objRoot.addChild(background);

		// Create X axis
		LineArray axisXLines = new LineArray(2, LineArray.COORDINATES);
		objRoot.addChild(new Shape3D(axisXLines));

		axisXLines.setCoordinate(0, new Point3f(-1.0f, 0.0f, 0.0f));
		axisXLines.setCoordinate(1, new Point3f(1.0f, 0.0f, 0.0f));

		// Create Y axis
		LineArray axisYLines = new LineArray(2, LineArray.COORDINATES
				| LineArray.COLOR_3);
		objRoot.addChild(new Shape3D(axisYLines));

		axisYLines.setCoordinate(0, new Point3f(0.0f, -1.0f, 0.0f));
		axisYLines.setCoordinate(1, new Point3f(0.0f, 1.0f, 0.0f));

		// Create Z axis with arrow
		Point3f z1 = new Point3f(0.0f, 0.0f, -1.0f);
		Point3f z2 = new Point3f(0.0f, 0.0f, 1.0f);

		LineArray axisZLines = new LineArray(10, LineArray.COORDINATES
				| LineArray.COLOR_3);
		objRoot.addChild(new Shape3D(axisZLines));

		axisZLines.setCoordinate(0, z1);
		axisZLines.setCoordinate(1, z2);
		axisZLines.setCoordinate(2, z2);
		axisZLines.setCoordinate(3, new Point3f(0.1f, 0.1f, 0.9f));
		axisZLines.setCoordinate(4, z2);
		axisZLines.setCoordinate(5, new Point3f(-0.1f, 0.1f, 0.9f));
		axisZLines.setCoordinate(6, z2);
		axisZLines.setCoordinate(7, new Point3f(0.1f, -0.1f, 0.9f));
		axisZLines.setCoordinate(8, z2);
		axisZLines.setCoordinate(9, new Point3f(-0.1f, -0.1f, 0.9f));

		return objRoot;
	}
}