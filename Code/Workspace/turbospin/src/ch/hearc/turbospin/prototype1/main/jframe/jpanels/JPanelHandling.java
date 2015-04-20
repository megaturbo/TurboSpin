package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;
import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class JPanelHandling extends JPanel {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHandling(TurboCanvas canvas) {
		this.canvas = canvas;
		geometry();
		control();
		appearance();
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry() {
		// JComponent : Instanciation
		buttonAddVector = new JButton("Add vector");
		vX = new JTextField();
		vY = new JTextField();
		vZ = new JTextField();

		// Layout : Specification
		{
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			setLayout(boxLayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
		}

		// JComponent : add
		add(buttonAddVector);
		add(vX);
		add(vY);
		add(vZ);

	}

	private void control() {
		buttonAddVector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double a = Double.parseDouble(vX.getText());
				double b = Double.parseDouble(vY.getText());
				double c = Double.parseDouble(vZ.getText());
				canvas.addVector(new Vector3D(a, b, c), TurboColors.PINK);
			}
		});
	}

	private void appearance() {
		// rien
		this.setBackground(Color.CYAN);
		setBorder(BorderFactory.createTitledBorder("Handling"));
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private TurboCanvas view3d;
	private JButton buttonAddVector;
	private JTextField vX, vY, vZ;

	private TurboCanvas canvas;

}
