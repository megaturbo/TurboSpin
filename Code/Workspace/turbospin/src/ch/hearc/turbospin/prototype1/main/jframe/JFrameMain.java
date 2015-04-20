
package ch.hearc.turbospin.prototype1.main.jframe;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
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

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		turboCanvas = new TurboCanvas(SimpleUniverse.getPreferredConfiguration());
		// JComponent : Instanciation
		panelHandling = new JPanelHandling(turboCanvas);
		panelView = new JPanelView(turboCanvas);
		panelRotationInfo = new JPanelRotationInfo();
			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// borderLayout.setHgap(20);
			// borderLayout.setVgap(20);
			}

		// JComponent : add
		add(panelHandling, BorderLayout.WEST);
		add(panelView, BorderLayout.CENTER);
		add(panelRotationInfo, BorderLayout.SOUTH);

		panelView.setBorder(BorderFactory.createTitledBorder("View"));
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
	JPanelHandling panelHandling;
	JPanelView panelView;
	JPanelRotationInfo panelRotationInfo;
	
	private TurboCanvas turboCanvas;
	}
