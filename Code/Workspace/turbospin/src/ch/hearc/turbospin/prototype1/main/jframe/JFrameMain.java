
package ch.hearc.turbospin.prototype1.main.jframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelHandling;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelRotationInfo;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelView;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain()
		{
		geometry();
		control();
		appearance();
		this.setSize(new Dimension(this.getWidth() + 1, this.getHeight()));
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// Instantiate canvas
		turboCanvas = new TurboCanvas(SimpleUniverse.getPreferredConfiguration());

		// Instantiate components
		List<Vector3D> vectors = new ArrayList<Vector3D>();
		panelView = new JPanelView(turboCanvas, vectors);
		panelHandling = new JPanelHandling(turboCanvas, vectors, panelView);
		panelRotationInfo = new JPanelRotationInfo();

		// Layout specifications
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);

		// Adding components
		add(panelHandling, BorderLayout.WEST);
		add(panelView, BorderLayout.CENTER);
		add(panelRotationInfo, BorderLayout.SOUTH);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(799, 600);
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
	}
