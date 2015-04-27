package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.exceptions.NotAVectorException;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class JPanelVectorInput {

	public static Vector3D showVectorInput() throws NotAVectorException{
		
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
		
		if(result == JOptionPane.OK_OPTION) {
			double x;
			double y;
			double z;
			try{
				x = Double.parseDouble(xField.getText());
				y = Double.parseDouble(yField.getText());
				z = Double.parseDouble(zField.getText());
			}catch (NumberFormatException e){
				throw new NotAVectorException("Not enough information");
			}
			return new Vector3D(x, y, z);
		}else{
			throw new NotAVectorException("Canceled");
		}
	}

}
