package ch.hearc.turbospin.prototype1.tridimensional;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class TurboApplet extends JFrame {

	public TurboApplet() {
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		
		TurboCanvas canvas = new TurboCanvas(config);
		
		//buttonControl = new ButtonControl(canvas);
		
		add("North", new Label("Spinny spinny"));
		add("Center", canvas);
		add("South",new Label("Spinny spinny"));
	}
	
	private ButtonControl buttonControl;
}