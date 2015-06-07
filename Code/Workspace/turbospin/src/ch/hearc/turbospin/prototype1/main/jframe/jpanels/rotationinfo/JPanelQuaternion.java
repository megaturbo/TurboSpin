
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.main.jframe.jpanels.JPanelRotationInfo;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class JPanelQuaternion extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelQuaternion(JPanelRotationInfo parent, Quaternion quaternion)
		{
		this.parent = parent;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refresh(Quaternion quaternion)
		{
		this.quaternion = quaternion;
		this.panelSettingsQuaternion.updateQuaternion(quaternion);
		repaint();
		}

	public void refreshCanvas() {
		parent.refreshCanvas();
		repaint();
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
	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//handling fonts
		Font font = this.getFont();
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();

		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		g2d.setFont(font.deriveFont(fontAttributes));

		g2d.drawString("Quaternion generated from this rotation:", 10, 40);

		fontAttributes.put(TextAttribute.UNDERLINE,-1);
		g2d.setFont(font.deriveFont(Font.BOLD));

		g2d.drawString(quaternion.toString(), 15, 70);
		}
	private void geometry()
		{
		panelSettingsQuaternion = new JPanelSettingsQuaternion(this);
		add(panelSettingsQuaternion);
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Quaternion"));

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	// Inputs
	private JPanelRotationInfo parent;
	private Quaternion quaternion;
	private JPanelSettingsQuaternion panelSettingsQuaternion;

	}
