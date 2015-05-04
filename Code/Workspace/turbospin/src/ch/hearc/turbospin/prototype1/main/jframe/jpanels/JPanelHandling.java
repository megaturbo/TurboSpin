
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.turbospin.prototype1.exceptions.NotAVectorException;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;
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

	public void addVector(Vector3D vector)
		{
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
					try
						{
						addVector(JPanelVectorInput.showVectorInput());
						}
					catch (NotAVectorException e)
						{
						//NOP
						}
					}
			});
		spinthisshit.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					//vQuaternion
					//					Vector3D axis = new Vector3D(1, 0, 0);
					//					Quaternion rotation = QuaternionTools.createRotationQuaternion(Math.PI / 3, axis);
					//					for(Vector3D vector:vectors)
					//						{
					//						vector.set(QuaternionTools.rotation(vector, rotation));
					//						}

					//vMatrix
					//					Matrix rotation = MatrixTools.createRotationMatrix(-0.1, 2, Math.PI / 3);
					Matrix rotation1 = MatrixTools.createRotationRxMatrix(Math.PI / 3);
					Matrix rotation2 = MatrixTools.createRotationRyMatrix(Math.PI / 3);
					Matrix rotation3 = MatrixTools.createRotationRzMatrix(Math.PI / 3);

					ArrayList<Vector3D> vectorsTmp = new ArrayList<Vector3D>();
					for(Vector3D vector:vectors)
						{
						//						vector.set(MatrixTools.rotate(vector, rotation));
						Vector3D vector1 = MatrixTools.rotate(vector, rotation1);
						Vector3D vector2 = MatrixTools.rotate(vector1, rotation2);
						Vector3D vector3 = MatrixTools.rotate(vector2, rotation3);
						vectorsTmp.add(new Vector3D(vector1, TurboColors.RED));
						vectorsTmp.add(new Vector3D(vector2, TurboColors.GREEN));
						vectorsTmp.add(new Vector3D(vector3, TurboColors.BLUE));
						}
					vectors.addAll(vectorsTmp);
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
