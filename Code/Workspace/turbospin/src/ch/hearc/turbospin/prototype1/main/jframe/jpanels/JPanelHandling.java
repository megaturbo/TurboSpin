
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.media.j3d.Shape3D;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.hearc.turbospin.prototype1.exceptions.NotAVectorException;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.JPanelInputs;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JPanelHandling extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHandling(TurboCanvas canvas, List<Shape3D> shapes, JPanelView panelView)
		{
		this.canvas = canvas;
		this.shapes = shapes;
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
		shapes.add(vector);
		canvas.refresh();

		panelView.repaint();
		model.addElement(vector);
		}

	public void addPoint(Point3D point)
		{
		shapes.add(point);
		canvas.refresh();

		panelView.repaint();
		model.addElement(point);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometry()
		{
		// JComponents
		initComponents();

		JScrollPane listPane = new JScrollPane(listVector);

		// Layout
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		// JComponent : add
		add(buttonRotateVector);
		add(buttonAddVector);
		add(buttonAddLine);
		add(buttonAddPoint);
		add(listPane);
		}

	private void control()
		{
		initListeners();

		buttonRotateVector.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					//vQuaternion
					Vector3D axis = new Vector3D(1, 0, 0);
					Quaternion rotation = QuaternionTools.createRotationQuaternion(Math.PI / 3, axis);
					for(Shape3D shape:shapes)
						{
						if (shape instanceof Vector3D)
							{
							Vector3D vector = (Vector3D)shape;
							vector.set(QuaternionTools.rotation(vector, rotation));
							}
						else if (shape instanceof Point3D)
							{
							Point3D point = (Point3D)shape;
							point.set(QuaternionTools.rotation(point, rotation));
							}
						}

					canvas.refresh();
					panelView.repaint();

					//vMatrix
					//					//					Matrix rotation = MatrixTools.createRotationMatrix(-0.1, 2, Math.PI / 3);
					//					Matrix rotation1 = MatrixTools.createRotationRxMatrix(Math.PI / 3);
					//					Matrix rotation2 = MatrixTools.createRotationRyMatrix(Math.PI / 3);
					//					Matrix rotation3 = MatrixTools.createRotationRzMatrix(Math.PI / 3);
					//
					//					ArrayList<Vector3D> vectorsTmp = new ArrayList<Vector3D>();
					//					for(Vector3D vector:vectors)
					//						{
					//						//						vector.set(MatrixTools.rotate(vector, rotation));
					//						Vector3D vector1 = MatrixTools.rotate(vector, rotation1);
					//						Vector3D vector2 = MatrixTools.rotate(vector1, rotation2);
					//						Vector3D vector3 = MatrixTools.rotate(vector2, rotation3);
					//						vectorsTmp.add(new Vector3D(vector1, TurboColors.RED));
					//						vectorsTmp.add(new Vector3D(vector2, TurboColors.GREEN));
					//						vectorsTmp.add(new Vector3D(vector3, TurboColors.BLUE));
					//						}
					//					vectors.addAll(vectorsTmp);
					//					canvas.refresh();
					//					panelView.repaint();
					}
			});
		}

	private void initComponents()
		{
		buttonAddVector = new JButton("Add vector");
		buttonAddLine = new JButton("Add line");
		buttonRotateVector = new JButton("Rotate by 60° C");
		buttonAddPoint = new JButton("Add points");
		model = new DefaultListModel<Shape3D>();
		listVector = new JList<Shape3D>(model);
		}

	private void initListeners()
		{
		buttonAddVector.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					try
						{
						addVector(JPanelInputs.showVectorInput());
						}
					catch (NotAVectorException e)
						{
						//NOP
						}
					}
			});

		buttonAddLine.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					JPanelInputs.showLineInput();

					//The line above will return a Line3D object
					//TODO Add it to the view
					}
			});

		buttonAddPoint.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{

					addPoint(JPanelInputs.showPointInput());
					try
						{
						addVector(JPanelInputs.showVectorInput());
						}
					catch (NotAVectorException e1)
						{
						//NOP
						}
					}
			});

		buttonAddLine.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					JPanelInputs.showLineInput();

					//The line above will return a Line3D object
					//TODO Add it to the view
					}
			});
		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder("Handling"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JButton buttonAddVector;
	private JButton buttonAddLine;
	private JButton buttonRotateVector;
	private JButton buttonAddPoint;

	private JList<Shape3D> listVector;
	private DefaultListModel<Shape3D> model;

	private List<Shape3D> shapes;
	private TurboCanvas canvas;
	private JPanelView panelView;

	}
