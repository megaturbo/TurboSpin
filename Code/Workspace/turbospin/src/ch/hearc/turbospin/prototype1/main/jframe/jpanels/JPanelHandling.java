
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

	public void addVector(double a, double b, double c)
		{
		Vector3D vector = new Vector3D(a, b, c, TurboColors.PINK);
		vectors.add(vector);
		canvas.addVector(vector);
		panelView.repaint();

		model.addElement(vector.toString());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometry()
		{
		// JComponents
		buttonAddVector = new JButton("Add vector");
		spinthisshit = new JButton("Spin by 60° C");
		model = new DefaultListModel<String>();
		listVector = new JList<String>(model);
		JScrollPane listPane = new JScrollPane(listVector);

		// Layout
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		// JComponent : add
		add(spinthisshit);
		add(buttonAddVector);

		add(listPane);
		}

	private void control()
		{
		buttonAddVector.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					new JPanelVectorInput(JPanelHandling.this);
					}
			});
		spinthisshit.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					//vQuaternion
					Vector3D axis = new Vector3D(1, 0, 0);
					Quaternion rotation = QuaternionTools.createRotationQuaternion(Math.PI / 3, axis);
					for(Vector3D vector:vectors)
						{
						vector.set(QuaternionTools.rotation(vector, rotation));
						}

					//vMatrix
					//					Vector3D axis = new Vector3D(1, 0, 0);
					//					Matrix rotation = MatrixTools.createRotationMatrix(Math.PI / 3, 0, 0);
					//					for(Vector3D vector:vectors)
					//						{
					//						vector.set(MatrixTools.rotate(vector, rotation));
					//						}

					canvas.refresh();
					panelView.repaint();
					}
			});
		}

	private void appearance()
		{
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

	private JList<String> listVector;
	private DefaultListModel<String> model;

	private List<Vector3D> vectors;
	private TurboCanvas canvas;
	private JPanelView panelView;

	}
