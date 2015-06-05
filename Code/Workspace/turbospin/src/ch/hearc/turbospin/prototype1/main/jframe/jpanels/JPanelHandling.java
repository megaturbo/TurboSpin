package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.media.j3d.Shape3D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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

public class JPanelHandling extends JPanel {

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelHandling(TurboCanvas canvas, List<Shape3D> shapes,
			JPanelView panelView, JPanelRotationInfo panelInfo) {
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

	public void addShape3D(Shape3D shape) {
		shapes.add(shape);
		canvas.addShape(shape);
		panelView.repaint();
		shapesModel.addElement(shape);
	}

	public void addRotation(RotationItem input) {
		rotationModel.addElement(input);
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void geometry() {
		// JComponents
		initComponents();

		// Layout
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		// JComponent : add
		add(buttonAddVector);
		add(buttonAddPoint);
		add(buttonAddLine);
		add(buttonAddVertex);
		add(listShapesPane);

		add(Box.createGlue());

		add(radioButtonMatrix);
		add(radioButtonQuaternion);
		add(buttonAddRotation);
		add(buttonRotate);
		add(listRotationsPane);
	}

	private void control() {
		initListeners();
	}

	private void initComponents() {
		buttonAddVector = new JButton("Add vector");
		buttonAddLine = new JButton("Add line");
		buttonAddVertex = new JButton("Add vertex");
		buttonAddPoint = new JButton("Add points");
		shapesModel = new DefaultListModel<Shape3D>();
		listShapes = new JList<Shape3D>(shapesModel);
		listShapesPane = new JScrollPane(listShapes);

		buttonAddRotation = new JButton("Add rotation item");
		buttonRotate = new JButton("Rotate selected item");
		rotationModel = new DefaultListModel<RotationItem>();
		listRotation = new JList<RotationItem>(rotationModel);
		listRotationsPane = new JScrollPane(listRotation);

		buttonGroupRadio = new ButtonGroup();
		radioButtonMatrix = new JRadioButton("Rotate using matrices");
		radioButtonMatrix.setSelected(true);
		radioButtonQuaternion = new JRadioButton("Rotate using quaternions");
		buttonGroupRadio.add(radioButtonMatrix);
		buttonGroupRadio.add(radioButtonQuaternion);
	}

	private void initListeners() {
		buttonAddVector.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					addShape3D(JPanelInputsFactory.showVector3DInput());
					canvas.setSelected(shapes.get(shapes.size() - 1));
				} catch (UserIsAnIdiotException e) {
					// NOP
				}
			}
		});

		buttonAddLine.addActionListener(new ActionListener() {

			// The line above will return a Line3D object
			// TODO Add it to the view
			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					addShape3D(JPanelInputsFactory.showLineInput());
				} catch (UserIsAnIdiotException e) {
					// NOP
				}
			}
		});

		buttonAddVertex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					addShape3D(JPanelInputsFactory.showVertexInput());
					canvas.setSelected(shapes.get(shapes.size() - 1));
				} catch (UserIsAnIdiotException e) {
					// NOP
				}
			}

		});

		buttonAddPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					addShape3D(JPanelInputsFactory.showPoint3DInput());
					canvas.setSelected(shapes.get(shapes.size() - 1));
				} catch (UserIsAnIdiotException e) {
					// NOP
				}
			}
		});

		listRotation.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (listRotation.getSelectedValue() instanceof Matrix) {
					panelInfo.displayMatrix();
					panelInfo.refresh((Matrix) listRotation.getSelectedValue());
				} else if (listRotation.getSelectedValue() instanceof Quaternion) {
					panelInfo.displayQuaternion();
					panelInfo.refresh((Quaternion) listRotation
							.getSelectedValue());
					canvas.setSelected(listRotation.getSelectedValue());
				}
			}
		});

		listShapes.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				canvas.setSelected(listShapes.getSelectedValue());
			}
		});

		buttonAddRotation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (radioButtonQuaternion.isSelected()) {
					try {
						addRotation(JPanelInputsFactory.showQuaternionInput());
						canvas.setSelected(rotationModel.firstElement());
					} catch (UserIsAnIdiotException e) {
						// NOP
					}
				} else if (radioButtonMatrix.isSelected()) {
					try {
						addRotation(JPanelInputsFactory.showMatrixInput());
					} catch (UserIsAnIdiotException e) {
						// NOP
					}
				}
			}
		});

		buttonRotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Shape3D shape = listShapes.getSelectedValue();
				RotationItem rotationItem = listRotation.getSelectedValue();

				if (rotationItem instanceof Matrix) {
					rotateWithMatrix(shape, (Matrix) rotationItem);
				} else if (rotationItem instanceof Quaternion) {
					rotateWithQuaternion(shape, (Quaternion) rotationItem);
				}
			}
		});

	}

	private void appearance() {
		setBorder(BorderFactory.createTitledBorder("Handling"));
	}

	private void rotateWithQuaternion(Shape3D shape, Quaternion rotation) {
		if (shape instanceof Vector3D) {
			Vector3D vector = (Vector3D) shape;
			vector.set(QuaternionTools.rotation(vector, rotation));
		} else if (shape instanceof Point3D) {
			Point3D point = (Point3D) shape;
			point.set(QuaternionTools.rotation(point, rotation));
		} else if (shape instanceof Vertex3D) {
			Vertex3D vertex = (Vertex3D) shape;
			Vertex3D newVertex = QuaternionTools.rotation(vertex, rotation);
			vertex.setA(newVertex.getA());
			vertex.setB(newVertex.getB());
		}

		canvas.refresh();
		panelView.repaint();
		this.repaint();
	}

	private void rotateWithMatrix(Shape3D shape, Matrix rotation) {
		if (shape instanceof Vector3D) {
			Vector3D vector = (Vector3D) shape;
			vector.set(MatrixTools.rotate(vector, rotation));
		} else if (shape instanceof Point3D) {
			Point3D point = (Point3D) shape;
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
	private JButton buttonAddLine;
	private JButton buttonAddVertex;
	private JButton buttonAddPoint;
	private JList<Shape3D> listShapes;
	private DefaultListModel<Shape3D> shapesModel;
	private JScrollPane listShapesPane;

	// rotations
	private JButton buttonAddRotation;
	private JList<RotationItem> listRotation;
	private DefaultListModel<RotationItem> rotationModel;
	private JScrollPane listRotationsPane;

	private JButton buttonRotate;
	private ButtonGroup buttonGroupRadio;
	private JRadioButton radioButtonQuaternion;
	private JRadioButton radioButtonMatrix;
}
