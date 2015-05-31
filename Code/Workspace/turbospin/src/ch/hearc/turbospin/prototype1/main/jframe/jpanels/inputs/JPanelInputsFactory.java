package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelParametricLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelPointInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelTwoPointsLineInput;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels.JPanelVectorInput;
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
 * </ul>
 * 
 * TODO:
 * <ul>
 * <li>Parametric Line</li>
 * <li>2 points Line</li>
 * </ul>
 * 
 * @author thomas.roulin
 *
 */
public class JPanelInputsFactory {

	public static Vector3D showVector3DInput() throws UserIsAnIdiotException {

		JPanelVectorInput vectorInput = new JPanelVectorInput();
		int result = JOptionPane.showConfirmDialog(null, vectorInput,
				"Vector input", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return vectorInput.getVector3D();
		} else {
			throw new UserIsAnIdiotException("Canceled");
		}
	}

	public static Point3D showPoint3DInput() throws UserIsAnIdiotException {

		JPanelPointInput pointInput = new JPanelPointInput();
		int result = JOptionPane.showConfirmDialog(null, pointInput,
				"Vector input", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			return pointInput.getPoint3D();
		} else {
			throw new UserIsAnIdiotException("Canceled");
		}
	}

	public static Line3D showLineInput() throws UserIsAnIdiotException {
		JPanelLineInput lineInput = new JPanelLineInput();
		int result = JOptionPane.showConfirmDialog(null, lineInput,
				"Line Input", JOptionPane.OK_CANCEL_OPTION);

		// Pro
		if (result == JOptionPane.OK_OPTION) {
			if(lineInput.getInputType() == JPanelLineInput.PARAMETRIC){
				JPanelParametricLineInput parametricInput = new JPanelParametricLineInput();
				result = JOptionPane.showConfirmDialog(null, parametricInput,
						"Vector input", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					return parametricInput.getLine3D();
				} else {
					throw new UserIsAnIdiotException("Canceled");
				}
			}else if(lineInput.getInputType() == JPanelLineInput.TWOPOINTS){
				JPanelTwoPointsLineInput twoPointsInput = new JPanelTwoPointsLineInput();
				result = JOptionPane.showConfirmDialog(null, twoPointsInput,
						"Vector input", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					return twoPointsInput.getLine3D();
				} else {
					throw new UserIsAnIdiotException("Canceled");
				}
			}else{
				throw new UserIsAnIdiotException("Is that even possible ?");
			}
		} else {
			return null;
		}
	}

	public static Line3D showParametricLineInput() {
		JPanel panel = new JPanel();
		return null;
	}

	public static Quaternion showQuaternionInput()
			throws UserIsAnIdiotException {
		JPanel panel = new JPanel();

		JTextField aField = new JTextField(5);
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		panel.add(new JLabel("Veuillez insérer un angle et un axe de rotation."));

		panel.add(new JLabel(Hexacodes.THETA + ":"));
		panel.add(aField);
		panel.add(new JLabel("x:"));
		panel.add(xField);
		panel.add(new JLabel("y:"));
		panel.add(yField);
		panel.add(new JLabel("z:"));
		panel.add(zField);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Entrez l'angle et l'axe de rotation",
				JOptionPane.OK_CANCEL_OPTION);
		aField.requestFocus();

		if (result == JOptionPane.OK_OPTION) {
			double a;
			double x;
			double y;
			double z;

			try {
				a = Double.parseDouble(aField.getText());
				x = Double.parseDouble(xField.getText());
				y = Double.parseDouble(yField.getText());
				z = Double.parseDouble(zField.getText());
			} catch (NumberFormatException e) {
				throw new UserIsAnIdiotException("Not enough information");
			}
			return QuaternionTools.createRotationQuaternion(a, new Vector3D(x,
					y, z));
		} else {
			throw new UserIsAnIdiotException("Canceled");
		}
	}

	public static Matrix showMatrixInput() throws UserIsAnIdiotException {
		JPanel panel = new JPanel();

		JTextField aField = new JTextField(5);
		JTextField bField = new JTextField(5);
		JTextField cField = new JTextField(5);

		panel.add(new JLabel(
				"Veuillez insérer 3 angles pour créer une matrice."));

		panel.add(new JLabel(Hexacodes.ALPHA));
		panel.add(aField);
		panel.add(new JLabel(Hexacodes.BETA));
		panel.add(bField);
		panel.add(new JLabel(Hexacodes.GAMMA));
		panel.add(cField);

		int result = JOptionPane.showConfirmDialog(null, panel,
				"Entrez les angles", JOptionPane.OK_CANCEL_OPTION);
		aField.requestFocus();

		if (result == JOptionPane.OK_OPTION) {
			double a;
			double b;
			double c;

			try {
				a = Double.parseDouble(aField.getText());
				b = Double.parseDouble(bField.getText());
				c = Double.parseDouble(cField.getText());
			} catch (NumberFormatException e) {
				throw new UserIsAnIdiotException("Not enough information");
			}
			return MatrixTools.createRotationMatrix(a, b, c);
		} else {
			throw new UserIsAnIdiotException("Canceled");
		}
	}
}
