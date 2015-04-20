
package ch.hearc.turbospin.prototype1.quaternion.jframe;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.mathtools.Point3D;
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
		labelPoint = new JLabel("Point before rotation: ");
		labelAxis = new JLabel("Axis of rotation: ");
		textAngle = new JTextField(5);
		textPoint = new JTextField[3];
		textAxis = new JTextField[3];
		button = new JButton("Rotate!");
		for(int i = 0; i < textAxis.length; i++)
			{
			textPoint[i] = new JTextField(5);
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
		add(labelPoint);
		for(int i = 0; i < textAxis.length; i++)
			{
			add(textPoint[i]);
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
						double pointA = Double.parseDouble(textPoint[0].getText());
						double pointB = Double.parseDouble(textPoint[1].getText());
						double pointC = Double.parseDouble(textPoint[2].getText());
						Point3D p = QuaternionTools.rotation(new Point3D(pointA, pointB, pointC), angle, new Vector3D(axisA, axisB, axisC));
						labelOutput.setText("Point after rotation: " + p);
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
	private JLabel labelPoint;
	private JTextField textAngle;
	private JTextField textAxis[];
	private JTextField textPoint[];
	private JButton button;
	}
