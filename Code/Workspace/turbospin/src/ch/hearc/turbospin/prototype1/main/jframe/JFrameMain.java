package ch.hearc.turbospin.prototype1.main.jframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Shape3D;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelHandling;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelRotationInfo;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.views.JPanelView;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JFrameMain extends JFrame {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain() {
		geometry();
		control();
		appearance();
		this.setSize(new Dimension(this.getWidth() + 1, this.getHeight()));
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refreshCanvas()
		{
		panelHandling.refreshCanvas();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry() {
		// Instantiate list
		List<Shape3D> shapes = new ArrayList<Shape3D>();

		// Instantiate canvas
		turboCanvas = new TurboCanvas(
				SimpleUniverse.getPreferredConfiguration(), shapes);

		// Instantiate components
		panelView = new JPanelView(turboCanvas, shapes);
		panelRotationInfo = new JPanelRotationInfo(this);
		panelHandling = new JPanelHandling(turboCanvas, shapes, panelView,
				panelRotationInfo);

		// Layout specifications
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// Adding components
		add(panelHandling, BorderLayout.WEST);
		add(panelView, BorderLayout.CENTER);
		add(panelRotationInfo, BorderLayout.SOUTH);
	}

	private void control() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void appearance() {
		setSize(1200, 900);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelHandling panelHandling;
	private JPanelView panelView;
	private JPanelRotationInfo panelRotationInfo;
	private TurboCanvas turboCanvas;

	// Inputs
	private Quaternion infoQuaternion;
	private Matrix infoMatrix;
}
