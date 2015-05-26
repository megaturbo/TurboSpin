
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

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

	public void refresh(Matrix rotation)
		{
		panelMatrix.refresh(rotation);
		}

	public void panelSwitch()
		{
		if(panelQuaternion.isVisible())
			{
			panelMatrix.setVisible(true);
			panelQuaternion.setVisible(false);
			}
		panelMatrix.setVisible(false);
		panelQuaternion.setVisible(true);

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
		panelMatrix = new JPanelMatrix(matrix);

		// Layout : Specification

		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
		setLayout(boxlayout);

		// flowlayout.setHgap(20);
		// flowlayout.setVgap(20);

		// JComponent : add
		add(panelMatrix);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Rotation information"));
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
