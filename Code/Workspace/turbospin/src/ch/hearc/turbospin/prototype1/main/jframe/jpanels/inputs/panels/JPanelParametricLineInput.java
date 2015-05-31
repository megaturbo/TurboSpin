package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels;

import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.mathtools.Line3D;

public class JPanelParametricLineInput extends JPanel{

	public JPanelParametricLineInput(){
		pointInput = new JPanelPointInput();
		vectorInput = new JPanelVectorInput();
		
		add(pointInput);
		add(vectorInput);
	}
	
	public Line3D getLine3D() throws UserIsAnIdiotException {
		return new Line3D(pointInput.getPoint3D(), vectorInput.getVector3D());
	}
	
	private JPanelPointInput pointInput;
	private JPanelVectorInput vectorInput;

}
