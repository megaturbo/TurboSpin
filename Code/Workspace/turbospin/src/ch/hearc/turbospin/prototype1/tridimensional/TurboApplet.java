package ch.hearc.turbospin.prototype1.tridimensional;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Label;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class TurboApplet extends Applet {

	public TurboApplet() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		
		TurboCanvas canvas = new TurboCanvas(config);
		
		add("North",new Label("Nice menu bro"));
		add("Center", canvas);
		add("South",new Label("Spinny spinny"));
	}
}