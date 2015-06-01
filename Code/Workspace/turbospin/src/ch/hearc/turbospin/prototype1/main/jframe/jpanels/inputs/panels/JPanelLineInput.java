package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelLineInput extends JPanel{
	
	public JPanelLineInput()
	{

		String lineTypes[] = { "Paramètrique", "A l'aide de 2 points" };
		lineComboBox = new JComboBox<String>(lineTypes);

		this.add(new JLabel("Comment voulez-vous entrer la droite?"));
		this.add(lineComboBox);
	}
	
	public int getInputType(){
		return lineComboBox.getSelectedIndex();
	}
	
	private JComboBox<String> lineComboBox;
	public static final int PARAMETRIC = 0;
	public static final int TWOPOINTS = 1;

}
