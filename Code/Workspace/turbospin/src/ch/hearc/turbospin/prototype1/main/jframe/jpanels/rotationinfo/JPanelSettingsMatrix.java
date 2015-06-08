
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;

public class JPanelSettingsMatrix extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelSettingsMatrix(JPanelMatrix parent)
		{
		this.parent = parent;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void updateMatrix(Matrix matrix, Matrix matrixRz, Matrix matrixRy, Matrix matrixRx)
		{
		this.matrix = matrix;
		this.matrixRz = matrixRz;
		this.matrixRy = matrixRy;
		this.matrixRx = matrixRx;


		double a = MatrixTools.getAlpha(matrixRz);
		double b = MatrixTools.getBeta(matrixRy);
		double c = MatrixTools.getGamma(matrixRx);

		sliderX.setEnabled(true);
		sliderY.setEnabled(true);
		sliderZ.setEnabled(true);

		updateSlider(sliderZ, a * 1000.0);
		updateSlider(sliderY, b * 1000.0);
		updateSlider(sliderX, c * 1000.0);

		}

	private void updateSlider(JSlider slider, double value) {
		int intVal = (int)(value);
		if(value > slider.getMaximum()) {
			slider.setMaximum(intVal + (int)(2000.0 * Math.PI));
		}
		slider.setValue(intVal);
	}

	private void sliderChangedRecalculateTheMatrix()
		{
		double a = sliderZ.getValue() / 1000.0;
		double b = sliderY.getValue() / 1000.0;
		double c = sliderX.getValue() / 1000.0;

		matrixRx.set(MatrixTools.createRotationRxMatrix(c));
		matrixRy.set(MatrixTools.createRotationRyMatrix(b));
		matrixRz.set(MatrixTools.createRotationRzMatrix(a));
		matrix.set(MatrixTools.createRotationMatrix(a, b, c));

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
		labelAlpha.setText(Hexacodes.ALPHA_LOWER + " = " + sliderZ.getValue() / 1000.0);
		labelBeta.setText(Hexacodes.BETA_LOWER + " = " + sliderY.getValue() / 1000.0);
		labelGamma.setText(Hexacodes.GAMMA_LOWER + " = " + sliderX.getValue() / 1000.0);
		}

	private void geometry()
		{
		// JComponent : Instanciation
		labelAlpha = new JLabel();
		labelBeta = new JLabel();
		labelGamma = new JLabel();
		sliderZ = new JSlider();
		sliderY = new JSlider();
		sliderX = new JSlider();
		sliderX.setEnabled(false);
		sliderY.setEnabled(false);
		sliderZ.setEnabled(false);
		updateLabels();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		add(labelAlpha, c);

		c.gridy = 1;
		add(sliderZ, c);

		c.gridy = 2;
		add(labelBeta, c);

		c.gridy = 3;
		add(sliderY, c);

		c.gridy = 4;
		add(labelGamma, c);

		c.gridy = 5;
		add(sliderX, c);
		}

	private void control()
		{
		ChangeListener changeListener = new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent arg0)
					{
					updateLabels();
					sliderChangedRecalculateTheMatrix();
					}
			};

		sliderZ.addChangeListener(changeListener);
		sliderY.addChangeListener(changeListener);
		sliderX.addChangeListener(changeListener);
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private JPanelMatrix parent;
	private Matrix matrixRz;
	private Matrix matrixRy;
	private Matrix matrixRx;
	private Matrix matrix;

	// Tools
	private JLabel labelAlpha;
	private JLabel labelBeta;
	private JLabel labelGamma;
	private JSlider sliderZ;
	private JSlider sliderY;
	private JSlider sliderX;

	}
