
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.sun.j3d.utils.universe.SimpleUniverse;

import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JPanel3D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanel3D()
		{
		geometry();
		control();
		appearance();
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
		// JComponent : Instanciation
		turboCanvas = new TurboCanvas(SimpleUniverse.getPreferredConfiguration());
			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(turboCanvas);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien¨
		this.setBackground(Color.GREEN);
		setBorder(BorderFactory.createTitledBorder("3D"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	TurboCanvas turboCanvas;
	}
