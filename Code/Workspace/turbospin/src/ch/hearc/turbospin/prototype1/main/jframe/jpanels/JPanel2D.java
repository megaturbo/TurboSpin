
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JPanel2D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanel2D()
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

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add

		}

	private void control()
		{
		// rien
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{

					}

			});
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("IJ"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools

	}
