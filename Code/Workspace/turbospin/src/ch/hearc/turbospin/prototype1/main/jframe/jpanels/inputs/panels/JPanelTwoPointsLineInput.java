package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels;

import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.mathtools.Line3D;

public class JPanelTwoPointsLineInput extends JPanel {

	public JPanelTwoPointsLineInput(){
		pointAInput = new JPanelPointInput();
		pointBInput = new JPanelPointInput();
		
		add(pointAInput);
		add(pointBInput);
	}
	
	public Line3D getLine3D() throws UserIsAnIdiotException {
		return new Line3D(pointAInput.getPoint3D(), pointBInput.getPoint3D());
	}
	
	private JPanelPointInput pointAInput;
	private JPanelPointInput pointBInput;
}
