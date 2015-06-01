
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.mathtools.Line3D;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

public class JPanelInputsFactory
	{

	public static Vector3D showVectorInput() throws UserIsAnIdiotException
		{

		JPanel panel = new JPanel();

		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		panel.add(new JLabel("x:"));
		panel.add(xField);
		panel.add(new JLabel("y:"));
		panel.add(yField);
		panel.add(new JLabel("z:"));
		panel.add(zField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Vector coords", JOptionPane.OK_CANCEL_OPTION);
		yField.requestFocus();

		if (result == JOptionPane.OK_OPTION)
			{
			double x;
			double y;
			double z;
			try
				{
				x = Double.parseDouble(xField.getText());
				y = Double.parseDouble(yField.getText());
				z = Double.parseDouble(zField.getText());
				}
			catch (NumberFormatException e)
				{
				throw new UserIsAnIdiotException("Not enough information");
				}
			return new Vector3D(x, y, z);
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Line3D showLineInput()
		{

		JPanel panel = new JPanel();

		String lineTypes[] = { "Param�trique", "A l'aide de 2 points" };
		JComboBox<String> lineComboBox = new JComboBox<String>(lineTypes);

		panel.add(new JLabel("Comment voulez-vous entrer la droite?"));
		panel.add(lineComboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Vector coords", JOptionPane.OK_CANCEL_OPTION);

		//Pro
		if (result == JOptionPane.OK_OPTION)
			{
			return null;
			}
		else
			{
			return null;
			}
		}

	public static Point3D showPointInput() throws UserIsAnIdiotException
		{
		JPanel panel = new JPanel();

		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		panel.add(new JLabel("Veuillez ins�rer un point."));

		panel.add(new JLabel("x:"));
		panel.add(xField);
		panel.add(new JLabel("y:"));
		panel.add(yField);
		panel.add(new JLabel("z:"));
		panel.add(zField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Entrez les coordonn�es", JOptionPane.OK_CANCEL_OPTION);
		yField.requestFocus();

		if (result == JOptionPane.OK_OPTION)
			{
			double x;
			double y;
			double z;

			try
				{
				x = Double.parseDouble(xField.getText());
				y = Double.parseDouble(yField.getText());
				z = Double.parseDouble(zField.getText());
				}
			catch (NumberFormatException e)
				{
				throw new UserIsAnIdiotException("Not enough information");
				}
			return new Point3D(x, y, z);
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}

	public static Quaternion showQuaternionInput() throws UserIsAnIdiotException
		{
		JPanel panel = new JPanel();

		JTextField aField = new JTextField(5);
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		panel.add(new JLabel("Veuillez ins�rer un angle et un axe de rotation."));

		panel.add(new JLabel("α:"));
		panel.add(aField);
		panel.add(new JLabel("x:"));
		panel.add(xField);
		panel.add(new JLabel("y:"));
		panel.add(yField);
		panel.add(new JLabel("z:"));
		panel.add(zField);

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

		panel.add(new JLabel("Veuillez insérer 3 angles pour cr�er une matrice."));

		panel.add(new JLabel(Hexacodes.ALPHA));
		panel.add(aField);
		panel.add(new JLabel(Hexacodes.BETA));
		panel.add(bField);
		panel.add(new JLabel(Hexacodes.GAMMA));
		panel.add(cField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Entrez les angles", JOptionPane.OK_CANCEL_OPTION);
		aField.requestFocus();

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
			return MatrixTools.createRotationMatrix(a, b, c);
			}
		else
			{
			throw new UserIsAnIdiotException("Canceled");
			}
		}
	}
