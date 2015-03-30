package ch.hearc.turbospin.prototype1.tridimensional;

import javax.swing.JFrame;

public class UseTest3D {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("wow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new TridimensionalTests());
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

}
