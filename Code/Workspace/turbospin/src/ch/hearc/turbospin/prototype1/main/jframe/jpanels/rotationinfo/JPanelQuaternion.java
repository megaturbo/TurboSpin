
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class JPanelQuaternion extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelQuaternion(Quaternion quaternion)
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refresh(Quaternion quaternion)
	{
		quaternionLabel.setText(quaternion.toString());
	}
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
		quaternionInfoLabel = new JLabel("Quaternion generated from this rotation:");
		quaternionLabel = new JLabel("");
			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
			add(quaternionInfoLabel);
			add(quaternionLabel);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Quaternion"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JLabel quaternionLabel;
	private JLabel quaternionInfoLabel;
	// Inputs

	}
