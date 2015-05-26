
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.BasicStroke;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.mathtools.Matrix;

public class JPanelMatrix extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMatrix(Matrix matrix)
		{
		this.matrix = matrix;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refresh(Matrix matrix)
		{
		this.matrix = matrix;
		repaint();
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawString("Rotation matrix generated from this rotation:", 10, 30);
		g2d.setStroke(new BasicStroke(3));

			//Rotation Matrix
			{
			//left bracket
			g2d.drawLine(35, 50, 35, 160);
			g2d.drawLine(35, 50, 45, 50);
			g2d.drawLine(35, 160, 45, 160);

			//right bracket
			g2d.drawLine(200, 50, 200, 160);
			g2d.drawLine(200, 50, 190, 50);
			g2d.drawLine(200, 160, 190, 160);

			//values
			for(int i = 1; i <= 3; i++)
				{
				for(int j = 1; j <= 3; j++)
					{

					g2d.drawString(String.format("%.3f", matrix.getValue(i - 1, j - 1)), 22 + 40 * i, 30 + 40 * j);
					}
				}
			}
			/*RzMatrix
				{
				//left bracket
				g2d.drawLine(35, 50, 35, 160);
				g2d.drawLine(35, 50, 45, 50);
				g2d.drawLine(35, 160, 45, 160);

				//right bracket
				g2d.drawLine(200, 50, 200, 160);
				g2d.drawLine(200, 50, 190, 50);
				g2d.drawLine(200, 160, 190, 160);

				//values
				for(int i = 1; i <= 3; i++)
					{
					for(int j = 1; j <= 3; j++)
						{

						g2d.drawString(String.format("%.3f", matrix.getValue(i - 1, j - 1)), 22 + 40 * i, 30 + 40 * j);
						}
					}
				}*/
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

			// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add

		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBorder(BorderFactory.createTitledBorder("Matrices"));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools

	// inputs
	private Matrix matrix;

	}
