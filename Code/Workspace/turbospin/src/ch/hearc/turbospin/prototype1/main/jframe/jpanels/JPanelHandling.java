
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.media.j3d.Shape3D;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ch.hearc.turbospin.prototype1.exceptions.UserIsAnIdiotException;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.inputs.JPanelInputsFactory;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.views.JPanelView;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.RotationItem;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;
import ch.hearc.turbospin.prototype1.quaternion.QuaternionTools;
import ch.hearc.turbospin.prototype1.tridimensional.TurboCanvas;

public class JPanelHandling extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHandling(TurboCanvas canvas, List<Shape3D> shapes, JPanelView panelView, JPanelRotationInfo panelInfo)
		{
		this.panelInfo = panelInfo;
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

	public void addShape3D(Shape3D shape)
		{
		shapes.add(shape);
		canvas.addShape(shape);
		panelView.repaint();
		shapesModel.addElement(shape);
		}

	public void removeShape3D(Shape3D shape)
		{
		shapes.remove(shapes.indexOf(shape));
		canvas.removeShape(shape);
		panelView.repaint();
		shapesModel.removeElement(shape);
		}

	public void addRotation(RotationItem input)
		{
		rotationModel.addElement(input);
		}

	public void removeRotation(RotationItem rotation)
		{
		rotationModel.removeElement(rotation);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometry()
		{
		// JComponents
		initComponents();

		// Layout
		setLayout(new GridBagLayout());
		
		// Titles labels
		JLabel addObject = new JLabel("Ajouter un objet");
		JLabel addRotation = new JLabel("Créer une rotation");
		Font titleFont = new Font(addObject.getFont().getFontName(), Font.PLAIN, 20);
		addObject.setFont(titleFont);
		addRotation.setFont(titleFont);
		
		Insets defaultInsets = new Insets(0, 0, 0, 0);
		Insets topbotInsets = new Insets(10, 0, 10, 0);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = topbotInsets;
		c.gridwidth = 6;
		add(addObject, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = defaultInsets;
		c.gridwidth = 2;
		add(buttonAddVector, c);
		
		c.gridx = 2;
		c.gridy = 1;
		add(buttonAddPoint, c);
		
		c.gridx = 4;
		c.gridy = 1;
		add(buttonAddVertex, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 6;
		c.insets = topbotInsets;	// Top and Bottom padding
		add(listShapesPane, c);

		c.gridy = 3;
		c.insets = defaultInsets;
		add(buttonRemoveObjectFromList, c);
		
		c.gridy = 4;
		c.insets = topbotInsets;	// Reset padding
		add(new JSeparator(), c);
		
		c.gridy = 5;
		add(addRotation, c);
		
		c.gridy = 6;
		c.gridwidth = 3;
		c.insets = defaultInsets;
		add(buttonAddQuaternion, c);
		
		c.gridx = 3;
		add(buttonAddMatrix, c);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 6;
		add(listRotationsPane, c);
		
		c.gridy = 8;
		add(buttonRotate, c);
		
		c.gridy = 9;
		add(buttonRemoveRotationFromList, c);
		}

	private void control()
		{
		initListeners();
		}

	private void initComponents()
		{
		buttonAddVector = new JButton("Vecteur");
		/*button for future use -> implementing lines
		 * buttonAddLine = new JButton("Add line");*/
		buttonAddVertex = new JButton("Segment");
		buttonAddPoint = new JButton("Point");
		buttonRemoveObjectFromList = new JButton("Supprimer élément sélectionné");
		shapesModel = new DefaultListModel<Shape3D>();
		listShapes = new JList<Shape3D>(shapesModel);
		listShapesPane = new JScrollPane(listShapes);

		buttonAddMatrix = new JButton("Matrice");
		buttonAddQuaternion = new JButton("Quaternion");
		buttonRotate = new JButton("Effectuer la rotation sur l'objet sélectionné");
		buttonRemoveRotationFromList = new JButton("Supprimer élément sélectionné");
		rotationModel = new DefaultListModel<RotationItem>();
		listRotation = new JList<RotationItem>(rotationModel);
		listRotationsPane = new JScrollPane(listRotation);
		}

	private void initListeners()
		{
		buttonAddVector.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent a)
					{
					try
						{
						addShape3D(JPanelInputsFactory.showVector3DInput());
						listShapes.setSelectedValue(shapes.get(shapes.size() - 1), true);
						canvas.setSelected(shapes.get(shapes.size() - 1));
						}
					catch (UserIsAnIdiotException e)
						{
						// NOP
						}
					}
			});

		/*button listener for future use -> implementing lines
		 * buttonAddLine.addActionListener(new ActionListener()
			{

				// The line above will return a Line3D object
				// TODO Add it to the view
				@Override
				public void actionPerformed(ActionEvent a)
					{
					try
						{
						addShape3D(JPanelInputsFactory.showLineInput());
						}
					catch (UserIsAnIdiotException e)
						{
						// NOP
						}
					}
			});*/

		buttonAddVertex.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent a)
					{
					try
						{
						addShape3D(JPanelInputsFactory.showVertexInput());
						canvas.setSelected(shapes.get(shapes.size() - 1));
						}
					catch (UserIsAnIdiotException e)
						{
						// NOP
						}
					}

			});

		buttonAddPoint.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent a)
					{
					addShape3D(new Vector3D(3, 2, 1));
					addRotation(QuaternionTools.createRotationQuaternion(1, new Vector3D(1, 0, 0)));
					}
			});

		buttonRemoveObjectFromList.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (!listShapes.isSelectionEmpty())
						{
						removeShape3D(listShapes.getSelectedValue());
						}
					}
			});

		buttonRemoveRotationFromList.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					if (!listRotation.isSelectionEmpty())
						{
						removeRotation(listRotation.getSelectedValue());
						}
					}
			});

		listRotation.addListSelectionListener(new ListSelectionListener()
			{

				@Override
				public void valueChanged(ListSelectionEvent e)
					{
						{
						if (listRotation.isSelectionEmpty())
							{
							buttonRemoveRotationFromList.setEnabled(false);
							}
						else
							{
							buttonRemoveRotationFromList.setEnabled(true);
							}
						}
						{
						if (listRotation.getSelectedValue() instanceof Matrix)
							{
							panelInfo.displayMatrix();
							panelInfo.refresh((Matrix)listRotation.getSelectedValue());
							}
						else if (listRotation.getSelectedValue() instanceof Quaternion)
							{
							panelInfo.displayQuaternion();
							panelInfo.refresh((Quaternion)listRotation.getSelectedValue());
							canvas.setSelected(listRotation.getSelectedValue());
							}
						}
					}
			});

		listShapes.addListSelectionListener(new ListSelectionListener()
			{

				@Override
				public void valueChanged(ListSelectionEvent e)
					{
					canvas.setSelected(listShapes.getSelectedValue());
					if (listShapes.isSelectionEmpty())
						{
						buttonRemoveObjectFromList.setEnabled(false);
						}
					else
						{
						buttonRemoveObjectFromList.setEnabled(true);
						}
					}
			});

		buttonAddMatrix.addActionListener(new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						addRotation(JPanelInputsFactory.showMatrixInput());
						listRotation.setSelectedValue(rotationModel.firstElement(), true);
						canvas.setSelected(rotationModel.firstElement());
					} catch (UserIsAnIdiotException e1) {
						e1.printStackTrace();
					}
				}
			});
		
		buttonAddQuaternion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addRotation(JPanelInputsFactory.showQuaternionInput());
					listRotation.setSelectedValue(rotationModel.firstElement(), true);
					canvas.setSelected(rotationModel.firstElement());
				} catch (UserIsAnIdiotException e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonRotate.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					Shape3D shape = listShapes.getSelectedValue();
					RotationItem rotationItem = listRotation.getSelectedValue();

					if (rotationItem instanceof Matrix)
						{
						rotateWithMatrix(shape, (Matrix)rotationItem);
						}
					else if (rotationItem instanceof Quaternion)
						{
						rotateWithQuaternion(shape, (Quaternion)rotationItem);
						}
					}
			});

		}

	private void appearance()
		{
		setBorder(BorderFactory.createTitledBorder("Handling"));
		buttonRemoveObjectFromList.setEnabled(false);
		buttonRemoveRotationFromList.setEnabled(false);
		}

	private void rotateWithQuaternion(Shape3D shape, Quaternion rotation)
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
		else if (shape instanceof Vertex3D)
			{
			Vertex3D vertex = (Vertex3D)shape;
			Vertex3D newVertex = QuaternionTools.rotation(vertex, rotation);
			vertex.setA(newVertex.getA());
			vertex.setB(newVertex.getB());
			}

		canvas.refresh();
		panelView.repaint();
		this.repaint();
		}

	private void rotateWithMatrix(Shape3D shape, Matrix rotation)
		{
		if (shape instanceof Vector3D)
			{
			Vector3D vector = (Vector3D)shape;
			vector.set(MatrixTools.rotate(vector, rotation));
			}
		else if (shape instanceof Point3D)
			{
			Point3D point = (Point3D)shape;
			point.set(MatrixTools.rotate(point, rotation));
			}

		canvas.refresh();
		panelView.repaint();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelRotationInfo panelInfo;
	private TurboCanvas canvas;
	private JPanelView panelView;
	private List<Shape3D> shapes;
	// shapes
	private JButton buttonAddVector;
	/*button for future use -> implementing lines
	 * private JButton buttonAddLine;*/
	private JButton buttonAddVertex;
	private JButton buttonAddPoint;
	private JList<Shape3D> listShapes;
	private DefaultListModel<Shape3D> shapesModel;
	private JScrollPane listShapesPane;
	private JButton buttonRemoveObjectFromList;

	// rotations
	private JButton buttonAddMatrix;
	private JButton buttonAddQuaternion;
	private JButton buttonRemoveRotationFromList;
	private JList<RotationItem> listRotation;
	private DefaultListModel<RotationItem> rotationModel;
	private JScrollPane listRotationsPane;

	private JButton buttonRotate;
	}
