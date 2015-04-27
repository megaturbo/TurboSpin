package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JPanelVectorInput extends JPanel {

	public JPanelVectorInput(JPanelHandling panelHandling) {
		
		this.panelHandling = panelHandling;
		
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField zField = new JTextField(5);

		this.add(new JLabel("x:"));
		this.add(xField);
		this.add(new JLabel("y:"));
		this.add(yField);
		this.add(new JLabel("z:"));
		this.add(zField);
		
		int result = JOptionPane.showConfirmDialog(null, this, "Please Enter x and y shits", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			this.panelHandling.addVector(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()), Double.parseDouble(zField.getText()));
		}
	}
	
	private JPanelHandling panelHandling;

}
