
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.main.jframe.JFrameMain;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo.JPanelMatrix;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo.JPanelQuaternion;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class JPanelRotationInfo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelRotationInfo(JFrameMain parent)
		{
		this.parent = parent;
		//Instantiate visualizable quaternion/matrix
		matrix = new Matrix(3);
		quaternion = new Quaternion();

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refreshCanvas()
		{
		parent.refreshCanvas();
		}

	public void refresh(Quaternion quaternion)
		{
		panelQuaternion.refresh(quaternion);
		}

	public void refresh(Matrix rotation, Matrix matrixRz, Matrix matrixRy, Matrix matrixRx)
		{
		panelMatrix.refresh(rotation, matrixRz, matrixRy, matrixRx);
		}

	public void refresh(Matrix rotation)
		{
		panelMatrix.refresh(rotation);

		}

	public void displayQuaternion()
		{
		panelMatrix.setVisible(false);
		panelQuaternion.setVisible(true);
		}

	public void displayMatrix()
		{
		panelMatrix.setVisible(true);
		panelQuaternion.setVisible(false);
		}

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
		panelQuaternion = new JPanelQuaternion(this, quaternion);
		panelMatrix = new JPanelMatrix(this, matrix, new Matrix(3), new Matrix(3), new Matrix(3));

		// Layout : Specification
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(boxlayout);

		// JComponent : add
		add(panelMatrix);
		add(panelQuaternion);
		panelQuaternion.setVisible(false);
		}

	private void control()
		{
		// rien
		this.addComponentListener(new ComponentAdapter()
			{


				@Override
				public void componentResized(ComponentEvent e)
					{
					// TODO Auto-generated method stub
					panelMatrix.repaint();
					panelQuaternion.repaint();
					}
			});
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Rotation information"));
		setPreferredSize(new Dimension(0, 230));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	public JPanelMatrix panelMatrix;
	public JPanelQuaternion panelQuaternion;

	//inputs
	private JFrameMain parent;
	private Quaternion quaternion;
	private Matrix matrix;

	}
