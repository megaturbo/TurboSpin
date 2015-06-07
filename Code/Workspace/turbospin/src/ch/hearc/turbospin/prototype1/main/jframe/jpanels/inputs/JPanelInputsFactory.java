
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.media.j3d.Shape3D;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelParametricLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelPointInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelTwoPointsLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelVectorInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelVertexInput;
import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.mathtools.Line3D;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

/**
 * Inputs factory.
 *
 * Generate dialog to enter new Shape3D in the interface.
 * <hr>
 *
 * <b>DONE</b>:
 * <ul>
 * <li>Vector3D</li>
 * <li>Point3D</li>
 * <li>Parametric Line</li>
 * <li>2 points Line</li>
 * </ul>
 *
 * TODO:
 * <ul>
 * <li>Check Quaternions input</li>
 * <li>Check Matrix input</li>
 * </ul>
 *
 * @author thomas.roulin
 *
 */
public class JPanelInputsFactory
	{

	public static Vector3D showVector3DInput() throws UserIsAnIdiotException
		{

		JPanel mainPanel = new JPanel();
		JPanelVectorInput vectorInput = new JPanelVectorInput();

		mainPanel.add(new JLabel("Entrez le vecteur: "));
		mainPanel.add(vectorInput);

		int result = JOptionPane.showConfirmDialog(null, mainPanel, "Vector input", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION)
			{
			return vectorInput.getVector3D();
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Point3D showPoint3DInput() throws UserIsAnIdiotException
		{

		JPanel mainPanel = new JPanel();
		JPanelPointInput pointInput = new JPanelPointInput();

		mainPanel.add(new JLabel("Entrez le point: "));
		mainPanel.add(pointInput);

		int result = JOptionPane.showConfirmDialog(null, mainPanel, "Vector input", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION)
			{
			return pointInput.getPoint3D();
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Shape3D showVertexInput() throws UserIsAnIdiotException
		{
		JPanelVertexInput vertexInput = new JPanelVertexInput();
		int result = JOptionPane.showConfirmDialog(null, vertexInput, "Vertex input", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION)
			{
			return vertexInput.getVertex3D();
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Line3D showLineInput() throws UserIsAnIdiotException
		{
		JPanelLineInput lineInput = new JPanelLineInput();
		int result = JOptionPane.showConfirmDialog(null, lineInput, "Line Input", JOptionPane.OK_CANCEL_OPTION);

		// Pro
		if (result == JOptionPane.OK_OPTION)
			{
			if (lineInput.getInputType() == JPanelLineInput.PARAMETRIC)
				{
				JPanelParametricLineInput parametricInput = new JPanelParametricLineInput();
				result = JOptionPane.showConfirmDialog(null, parametricInput, "Vector input", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION)
					{
					return parametricInput.getLine3D();
					}
				else
					{
					throw new UserIsAnIdiotException("Canceled");
					}
				}
			else if (lineInput.getInputType() == JPanelLineInput.TWOPOINTS)
				{
				JPanelTwoPointsLineInput twoPointsInput = new JPanelTwoPointsLineInput();
				result = JOptionPane.showConfirmDialog(null, twoPointsInput, "Vector input", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION)
					{
					return twoPointsInput.getLine3D();
					}
				else
					{
					throw new UserIsAnIdiotException("Canceled");
					}
				}
			else
				{
				throw new UserIsAnIdiotException("Is that even possible ?");
				}
			}
		else
			{
			return null;
			}
		}

	public static Quaternion showQuaternionInput() throws UserIsAnIdiotException
		{
		JPanel panel = new JPanel();

		JTextField aField = new JTextField(5);
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		ButtonGroup buttonGroupRadio = new ButtonGroup();
		JRadioButton radioButtonDegrees = new JRadioButton("Degr�s");
		radioButtonDegrees.setSelected(true);
		JRadioButton radioButtonRadians = new JRadioButton("Radians");
		buttonGroupRadio.add(radioButtonDegrees);
		buttonGroupRadio.add(radioButtonRadians);

		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints co = new GridBagConstraints();
		panel.setLayout(gridBagLayout);

		co.fill = GridBagConstraints.HORIZONTAL;
		co.gridx = 0;
		co.gridy = 0;
		co.gridwidth = 6;
		co.insets = new Insets(0, 0, 20, 0);
		panel.add(new JLabel("Veuillez ins�rer un angle et un axe de rotation."), co);

		co.gridx = 0;
		co.gridy = 1;
		co.insets = new Insets(0, 0, 0, 50);
		co.gridwidth = 1;
		panel.add(radioButtonDegrees, co);

		co.gridx = 0;
		co.gridy = 2;
		panel.add(radioButtonRadians, co);
		co.insets = new Insets(0, 0, 0, 0);


		co.gridx = 4;
		co.gridy = 1;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel(Hexacodes.THETA_LOWER + ":"),co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(aField, co);


		co.gridx = 4;
		co.gridy = 2;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel("x:"),co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(xField, co);

		co.gridx = 4;
		co.gridy = 3;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel("y:"), co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(yField, co);

		co.gridx = 4;
		co.gridy = 4;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel("z:"), co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(zField, co);

		int result = JOptionPane.showConfirmDialog(null, panel, "Entrez l'angle et l'axe de rotation", JOptionPane.OK_CANCEL_OPTION);
		aField.requestFocus();

		if (result == JOptionPane.OK_OPTION)
			{
			double a;
			double x;
			double y;
			double z;

			try
				{
				a = Double.parseDouble(aField.getText());
				x = Double.parseDouble(xField.getText());
				y = Double.parseDouble(yField.getText());
				z = Double.parseDouble(zField.getText());
				}
			catch (NumberFormatException e)
				{
				throw new UserIsAnIdiotException("Not enough information");
				}
			if(radioButtonDegrees.isSelected())
				{
				a = a * Math.PI/180;
				}
			return QuaternionTools.createRotationQuaternion(a, new Vector3D(x, y, z));
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Matrix showMatrixInput() throws UserIsAnIdiotException
		{
		JPanel panel = new JPanel();

		JTextField aField = new JTextField(5);
		JTextField bField = new JTextField(5);
		JTextField cField = new JTextField(5);
		aField.setText("0");
		aField.requestFocusInWindow();
		bField.setText("0");
		cField.setText("0");

		ButtonGroup buttonGroupRadio = new ButtonGroup();
		JRadioButton radioButtonDegrees = new JRadioButton("Degr�s");
		radioButtonDegrees.setSelected(true);
		JRadioButton radioButtonRadians = new JRadioButton("Radians");
		buttonGroupRadio.add(radioButtonDegrees);
		buttonGroupRadio.add(radioButtonRadians);

		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints co = new GridBagConstraints();
		panel.setLayout(gridBagLayout);

		co.fill = GridBagConstraints.HORIZONTAL;
		co.gridx = 0;
		co.gridy = 0;
		co.gridwidth = 6;
		co.insets = new Insets(0, 0, 20, 0);
		panel.add(new JLabel("Veuillez ins�rer 3 angles pour cr�er une matrice."), co);

		co.gridx = 0;
		co.gridy = 1;
		co.insets = new Insets(0, 0, 0, 50);
		co.gridwidth = 1;
		panel.add(radioButtonDegrees, co);

		co.gridx = 0;
		co.gridy = 2;
		panel.add(radioButtonRadians, co);
		co.insets = new Insets(0, 0, 0, 0);

		co.gridx = 4;
		co.gridy = 1;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel(Hexacodes.ALPHA_LOWER + ":"),co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(aField, co);


		co.gridx = 4;
		co.gridy = 2;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel(Hexacodes.BETA_LOWER+ ":"),co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(bField, co);

		co.gridx = 4;
		co.gridy = 3;
		co.insets = new Insets(0, 0, 0, 20);
		panel.add(new JLabel(Hexacodes.GAMMA_LOWER+ ":"), co);
		co.insets = new Insets(0, 0, 0, 30);
		co.gridx = 5;
		panel.add(cField, co);

		int result = JOptionPane.showConfirmDialog(null, panel, "Entrez les angles", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION)
			{
			double a;
			double b;
			double c;

			try
				{
				a = Double.parseDouble(aField.getText());
				b = Double.parseDouble(bField.getText());
				c = Double.parseDouble(cField.getText());
				}
			catch (NumberFormatException e)
				{
				throw new UserIsAnIdiotException("Not enough information");
				}
			if(radioButtonDegrees.isSelected())
				{
				a = a * Math.PI/180;
				b = b * Math.PI/180;
				c = c * Math.PI/180;
				}
			return MatrixTools.createRotationMatrix(a, b, c);
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}
	}
