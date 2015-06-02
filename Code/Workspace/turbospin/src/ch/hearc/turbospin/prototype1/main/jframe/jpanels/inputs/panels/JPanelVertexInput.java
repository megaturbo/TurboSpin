
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;

public class JPanelVertexInput extends JPanel
	{

	public JPanelVertexInput()
		{
		pointAInput = new JPanelPointInput();
		pointBInput = new JPanelPointInput();

		add(new JLabel("Point de départ: "));
		add(pointAInput);
		add(new JLabel("Point d'arrivée: "));
		add(pointBInput);
		}

	public Vertex3D getVertex3D() throws UserIsAnIdiotException
		{
		return new Vertex3D(pointAInput.getPoint3D(), pointBInput.getPoint3D());
		}

	private JPanelPointInput pointAInput;
	private JPanelPointInput pointBInput;

	}
