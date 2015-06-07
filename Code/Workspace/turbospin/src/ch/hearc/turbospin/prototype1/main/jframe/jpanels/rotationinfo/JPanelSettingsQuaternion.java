
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class JPanelSettingsQuaternion extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSettingsQuaternion(JPanelQuaternion parent, Quaternion quaternion)
		{
		this.parent = parent;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void updateQuaternion(Quaternion quaternion)
		{
		this.quaternion = quaternion;

		sliderR.setValue((int)quaternion.getReal());
		sliderI.setValue((int)quaternion.getI());
		sliderJ.setValue((int)quaternion.getJ());
		sliderK.setValue((int)quaternion.getK());
		}

	private void sliderChangedRecalculateTheQuaternion() {
		quaternion.setReal(sliderR.getValue());
		quaternion.setI(sliderI.getValue());
		quaternion.setJ(sliderJ.getValue());
		quaternion.setK(sliderK.getValue());

		parent.refreshCanvas();
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

	private void updateLabels() {
		labelR.setText("R = " + sliderR.getValue());
		labelI.setText("I = " + sliderI.getValue());
		labelJ.setText("J = " + sliderJ.getValue());
		labelK.setText("K = " + sliderK.getValue());
	}

	private void geometry()
		{
		// JComponent : Instanciation
		labelR = new JLabel();
		labelI = new JLabel();
		labelJ = new JLabel();
		labelK = new JLabel();
		sliderR = new JSlider();
		sliderI = new JSlider();
		sliderJ = new JSlider();
		sliderK = new JSlider();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		add(labelR, c);

		c.gridy = 1;
		add(sliderR, c);

		c.gridy = 2;
		add(labelI, c);

		c.gridy = 3;
		add(sliderI, c);

		c.gridy = 4;
		add(labelJ, c);

		c.gridy = 5;
		add(sliderJ, c);

		c.gridy = 6;
		add(labelK, c);

		c.gridy = 7;
		add(sliderK, c);

		}

	private void control()
		{
		ChangeListener changeListener = new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent arg0)
				{
				System.out.println("state changed");
				updateLabels();
				sliderChangedRecalculateTheQuaternion();
				}
		};

		sliderR.addChangeListener(changeListener);
		sliderI.addChangeListener(changeListener);
		sliderJ.addChangeListener(changeListener);
		sliderK.addChangeListener(changeListener);
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JPanelQuaternion parent;
	private Quaternion quaternion;

	// Tools
	private JLabel labelR;
	private JLabel labelI;
	private JLabel labelJ;
	private JLabel labelK;
	private JSlider sliderR;
	private JSlider sliderI;
	private JSlider sliderJ;
	private JSlider sliderK;

	}
