
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.exceptions.NotAVectorException;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class JPanelInputsFactory
	{

	public static Vector3D showVectorInput() throws NotAVectorException
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
				throw new NotAVectorException("Not enough information");
				}
			return new Vector3D(x, y, z);
			}
		else
			{
			throw new NotAVectorException("Canceled");
			}
		}

	public static Vector3D showLineInput()
		{

		JPanel panel = new JPanel();

		String lineTypes[] = { "Paramétrique", "A l'aide de 2 points" };
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

	public static Point3D showPointInput()
		{
		JPanel panel = new JPanel();

		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		panel.add(new JLabel("Veuillez inserer un point."));

		panel.add(new JLabel("x:"));
		panel.add(xField);
		panel.add(new JLabel("y:"));
		panel.add(yField);
		panel.add(new JLabel("z:"));
		panel.add(zField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Entrez les coordonnées", JOptionPane.OK_CANCEL_OPTION);
		yField.requestFocus();

		if (result == JOptionPane.OK_OPTION)
			{
			double x;
			double y;
			double z;

			x = Double.parseDouble(xField.getText());
			y = Double.parseDouble(yField.getText());
			z = Double.parseDouble(zField.getText());

			return new Point3D(x, y, z);
			}
		else
			{
			return null;
			}

		}
	}
