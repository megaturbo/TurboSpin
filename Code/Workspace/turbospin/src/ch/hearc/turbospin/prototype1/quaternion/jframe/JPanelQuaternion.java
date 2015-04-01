
package ch.hearc.turbospin.prototype1.quaternion.jframe;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;

public class JPanelQuaternion extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelQuaternion()
		{
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

	private void geometry()
		{
		// JComponent : Instanciation
		labelOutput = new JLabel("Point after rotation: ");
		labelAngle = new JLabel("Angle of rotation(°) ");
		labelVector = new JLabel("Point before rotation: ");
		labelAxis = new JLabel("Axis of rotation: ");
		textAngle = new JTextField(5);
		textVector = new JTextField[3];
		textAxis = new JTextField[3];
		button = new JButton("Rotate!");
		for(int i = 0; i < textAxis.length; i++)
			{
			textVector[i] = new JTextField(5);
			textAxis[i] = new JTextField(5);
			}
			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add
		add(labelAngle);
		add(textAngle);
		add(labelAxis);
		for(int i = 0; i < textAxis.length; i++)
			{
			add(textAxis[i]);
			}
		add(labelVector);
		for(int i = 0; i < textAxis.length; i++)
			{
			add(textVector[i]);
			}
		add(button);
		add(labelOutput);
		}

	private void control()
		{
		button.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					try
						{
						double angle = Math.toRadians(Double.parseDouble(textAngle.getText()));
						double axisA = Double.parseDouble(textAxis[0].getText());
						double axisB = Double.parseDouble(textAxis[1].getText());
						double axisC = Double.parseDouble(textAxis[2].getText());
						double vectorA = Double.parseDouble(textVector[0].getText());
						double vectorB = Double.parseDouble(textVector[1].getText());
						double vectorC = Double.parseDouble(textVector[2].getText());
						Vector3D v = QuaternionTools.rotation(new Vector3D(vectorA, vectorB, vectorC), angle, new Vector3D(axisA, axisB, axisC));
						labelOutput.setText("Point after rotation: " + v);
						}
					catch (NumberFormatException textException)
						{
						labelOutput.setText("enter real numbers");
						System.err.println("JPanelQuaternion: parsing double exception");
						textException.printStackTrace();
						}
					}

			});
		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools

	private JLabel labelOutput;
	private JLabel labelAxis;
	private JLabel labelAngle;
	private JLabel labelVector;
	private JTextField textAngle;
	private JTextField textAxis[];
	private JTextField textVector[];
	private JButton button;
	}
