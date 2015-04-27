
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;
import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class JPanelHandling extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHandling(TurboCanvas canvas, List<Vector3D> vectors, JPanelView panelView)
		{
		this.canvas = canvas;
		this.vectors = vectors;
		this.panelView = panelView;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		buttonAddVector = new JButton("Add vector");
		spinthisshit = new JButton("Spin by 60° C");
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
		add(spinthisshit);
		add(buttonAddVector);
		add(vX);
		add(vY);
		add(vZ);

		}

	private void control()
		{
		buttonAddVector.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					double a = Double.parseDouble(vX.getText());
					double b = Double.parseDouble(vY.getText());
					double c = Double.parseDouble(vZ.getText());
					Vector3D vector = new Vector3D(a, b, c, TurboColors.PINK);
					vectors.add(vector);
					canvas.addVector(vector);
					panelView.repaint();
					}
			});
		spinthisshit.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					//TODO : better this shit
					//					Vector3D axis = new Vector3D(1, 0, 0);
					//					List<Vector3D> tmp = new ArrayList<Vector3D>();
					//					for(Vector3D vector:vectors)
					//						{
					//						vector = QuaternionTools.rotation(vector, Math.PI / 3, axis);
					//						tmp.add(new Vector3D(vector));
					//						}
					//					vectors.clear();
					//					canvas.clear();
					//					for(int i = 0; i < tmp.size(); i++)
					//						{
					//						vectors.add(tmp.get(i));
					//						canvas.addVector(tmp.get(i), TurboColors.PINK);
					//						}
					Vector3D axis = new Vector3D(1, 0, 0);
					Quaternion rotation = QuaternionTools.createRotationQuaternion(Math.PI / 3, axis);
					for(Vector3D vector:vectors)
						{
						vector.set(QuaternionTools.rotation(vector, rotation));
						}
					canvas.refresh();
					panelView.repaint();
					}
			});
		}

	private void appearance()
		{
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
	private JButton spinthisshit;
	private JTextField vX, vY, vZ;

	private List<Vector3D> vectors;
	private TurboCanvas canvas;
	private JPanelView panelView;

	}
