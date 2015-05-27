
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo.JPanelMatrix;
import ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo.JPanelQuaternion;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class JPanelRotationInfo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelRotationInfo(Quaternion quaternion, Matrix rotationMatrix)
		{
		this.quaternion = quaternion;
		this.matrix = rotationMatrix;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refresh(Quaternion quaternion)
		{
		panelQuaternion.refresh(quaternion);
		}

	public void refresh(Matrix rotation, Matrix matrixRz, Matrix matrixRy, Matrix matrixRx)
		{
		panelMatrix.refresh(rotation, matrixRz, matrixRy, matrixRx);
		}

	public void panelSwitch()
		{
		if (panelQuaternion.isVisible())
			{
			panelMatrix.setVisible(true);
			panelQuaternion.setVisible(false);
			}
		else
			{
			panelMatrix.setVisible(false);
			panelQuaternion.setVisible(true);
			}

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
		// JComponent : Instanciation

		panelQuaternion = new JPanelQuaternion(quaternion);
		panelMatrix = new JPanelMatrix(matrix, new Matrix(3), new Matrix(3), new Matrix(3));

		// Layout : Specification

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(boxlayout);

		// flowlayout.setHgap(20);
		// flowlayout.setVgap(20);

		// JComponent : add
		add(panelMatrix);
		add(panelQuaternion);
		panelQuaternion.setVisible(false);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Rotation information"));
		setPreferredSize(new Dimension(0, 200));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelMatrix panelMatrix;
	private JPanelQuaternion panelQuaternion;

	//inputs
	private Quaternion quaternion;
	private Matrix matrix;

	}
