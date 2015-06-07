
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

public class JPanelSettingsQuaternion extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSettingsQuaternion(JPanelQuaternion parent)
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
		Vector3D vector = QuaternionTools.getAxis(quaternion);
		double theta = QuaternionTools.getAngle(quaternion);

		updateSlider(sliderTheta, theta * 1000.0);
		updateSlider(sliderX, vector.getA() * 1000.0);
		updateSlider(sliderY, vector.getB() * 1000.0);
		updateSlider(sliderZ, vector.getC() * 1000.0);
		}

	private void updateSlider(JSlider slider, double value) {
		int intVal = (int)value;
		if(value > slider.getMaximum()) {
			slider.setMaximum(intVal * 2);
		}
		slider.setValue(intVal);
	}

	private void sliderChangedRecalculateTheQuaternion()
		{
		double theta = sliderTheta.getValue() / 1000.0;
		double x = sliderX.getValue() / 1000.0;
		double y = sliderY.getValue() / 1000.0;
		double z = sliderZ.getValue() / 1000.0;

		quaternion.set(QuaternionTools.createRotationQuaternion(theta, new Vector3D(x, y, z)));

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

	private void updateLabels()
		{
		labelTheta.setText(Hexacodes.THETA_LOWER + " = " + sliderTheta.getValue() / 1000.0);
		labelX.setText("X = " + sliderX.getValue() / 1000.0);
		labelY.setText("Y = " + sliderY.getValue() / 1000.0);
		labelZ.setText("Z = " + sliderZ.getValue() / 1000.0);
		}

	private void geometry()
		{
		// JComponent : Instanciation
		labelTheta = new JLabel();
		labelX = new JLabel();
		labelY = new JLabel();
		labelZ = new JLabel();
		sliderTheta = new JSlider();
		sliderX = new JSlider();
		sliderY = new JSlider();
		sliderZ = new JSlider();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		add(labelTheta, c);

		c.gridy = 1;
		add(sliderTheta, c);

		c.gridy = 2;
		add(labelX, c);

		c.gridy = 3;
		add(sliderX, c);

		c.gridy = 4;
		add(labelY, c);

		c.gridy = 5;
		add(sliderY, c);

		c.gridy = 6;
		add(labelZ, c);

		c.gridy = 7;
		add(sliderZ, c);

		}

	private void control()
		{
		ChangeListener changeListener = new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent arg0)
					{
					updateLabels();
					sliderChangedRecalculateTheQuaternion();
					}
			};

		sliderTheta.addChangeListener(changeListener);
		sliderX.addChangeListener(changeListener);
		sliderY.addChangeListener(changeListener);
		sliderZ.addChangeListener(changeListener);
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
	private JLabel labelTheta;
	private JLabel labelX;
	private JLabel labelY;
	private JLabel labelZ;
	private JSlider sliderTheta;
	private JSlider sliderX;
	private JSlider sliderY;
	private JSlider sliderZ;

	}
