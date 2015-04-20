
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class JPanelMain2D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMain2D()
		{
		geometry();
		control();
		appearance();
		//		resize2DPanels();
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
		panelIJ = new JPanel2D();
		panelJK = new JPanel2D();
		panelKI = new JPanel2D();
		//			// Layout : Specification
		//			{
		//			BorderLayout borderLayout = new BorderLayout();
		//			setLayout(borderLayout);
		//			// flowlayout.setHgap(20);
		//			// flowlayout.setVgap(20);
		//			}
		//
		//		// JComponent : add
		//		add(panelIJ, BorderLayout.NORTH);
		//		add(panelJK, BorderLayout.CENTER);
		//		add(panelKI, BorderLayout.SOUTH);

		GridLayout gridLayout = new GridLayout(0, 1);
		setLayout(gridLayout);

		add(panelIJ);
		add(panelJK);
		add(panelKI);

		//		boxV = Box.createVerticalBox();

		//		boxV.add(Box.createVerticalStrut(10));
		//		boxV.add(panelIJ);
		//		boxV.add(Box.createVerticalStrut(10));
		//		boxV.add(panelJK);
		//		boxV.add(Box.createVerticalStrut(10));
		//		boxV.add(panelKI);
		//		boxV.add(Box.createVerticalStrut(10));

		//		add(boxV);
		}

	private void control()
		{
		// rien
		addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{
					resize2DPanels();
					}

			});
		}

	private void resize2DPanels()
		{
		panelIJ.setPreferredSize(new Dimension(panelIJ.getHeight(), panelIJ.getHeight()));
		}

	private void appearance()
		{
		// rien
		this.setBackground(Color.BLUE);
		setBorder(BorderFactory.createTitledBorder("2D"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	JPanel2D panelIJ;
	JPanel2D panelJK;
	JPanel2D panelKI;

	//	Box boxV;
	}
