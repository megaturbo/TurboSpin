package ch.hearc.turbospin.prototype1.tridimensional;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class ButtonControl extends JPanel{

	public ButtonControl(TurboCanvas view3d){
		this.view3d = view3d;
		
		buttonAdd = new JButton("Add");
		vX = new JTextField();
		vY = new JTextField();
		vZ = new JTextField();
		
		buttonAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view3d.addVector(new Vector3D(Double.valueOf(vX.getText()), Double.valueOf(vY.getText()), Double.valueOf(vZ.getText())), TurboColors.PINK);
			}
		});
	}
	
	private TurboCanvas view3d;
	private JButton buttonAdd;
	private JTextField vX, vY, vZ;
	
}
