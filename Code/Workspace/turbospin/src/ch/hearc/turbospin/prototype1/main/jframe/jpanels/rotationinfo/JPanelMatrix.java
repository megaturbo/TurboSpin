
package ch.hearc.turbospin.prototype1.main.jframe.jpanels.rotationinfo;

import java.awt.BasicStroke;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;
import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class JPanelMatrix extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelMatrix(Matrix matrix, Matrix matrixRz, Matrix matrixRy, Matrix matrixRx)
		{
		this.matrixRotation = matrix;
		this.matrixRx = matrixRx;
		this.matrixRy = matrixRy;
		this.matrixRz = matrixRz;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void refresh(Matrix matrixRotation, Matrix matrixRz, Matrix matrixRy, Matrix matrixRx)
		{
		this.matrixRotation = matrixRotation;
		this.matrixRx = matrixRx;
		this.matrixRy = matrixRy;
		this.matrixRz = matrixRz;
		this.alpha = Math.acos(matrixRz.getValue(0, 0));
		this.beta = Math.acos(matrixRy.getValue(0, 0));
		this.gamma = Math.acos(matrixRx.getValue(1, 1));
		repaint();
		}

	public void refresh(Matrix rotation)
		{
		this.matrixRotation = rotation;
		this.alpha = MatrixTools.getAlpha(rotation);
		this.beta = MatrixTools.getBeta(rotation);
		this.gamma = MatrixTools.getGamma(rotation);
		this.matrixRx = MatrixTools.createRotationRxMatrix(gamma);
		this.matrixRy = MatrixTools.createRotationRyMatrix(beta);
		this.matrixRz = MatrixTools.createRotationRzMatrix(alpha);

		MatrixTools.getAngles(rotation);
		repaint();
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawString("Rotation matrices generated from this rotation:", 10, 30);

		int offset = (int)(this.getWidth() * 0.08);

		g2d.setStroke(new BasicStroke(3));

		drawRotationMatrix(g2d, matrixRotation, offset, 60);
		g2d.drawString("Complete Rotation", offset + 30, 50);
		g2d.drawString("=", offset + 185, 115);

		g2d.setColor(TurboColors.BLUE.get());
		drawRotationMatrix(g2d, matrixRz, offset + 210, 60);
		g2d.drawString("Rotation around Z axis", offset + 230, 50);
		g2d.drawString("Rotated by: " + String.format("%.3f", alpha / Math.PI)+ Hexacodes.PI_LOWER, offset + 240, 190);

		g2d.setColor(TurboColors.GREEN.get());
		drawRotationMatrix(g2d, matrixRy, offset + 405, 60);
		g2d.drawString("Rotation around Y axis", offset + 425, 50);
		g2d.drawString("Rotated by: " + String.format("%.3f", beta/ Math.PI) + Hexacodes.PI_LOWER, offset + 435, 190);

		g2d.setColor(TurboColors.RED.get());
		drawRotationMatrix(g2d, matrixRx, offset + 600, 60);
		g2d.drawString("Rotation around X axis", offset + 610, 50);
		g2d.drawString("Rotated by: " + String.format("%.3f", gamma / Math.PI)+ Hexacodes.PI_LOWER, offset + 630, 190);

		g2d.setStroke(new BasicStroke(1));

		g2d.setColor(TurboColors.BLACK.get());
		g2d.drawString("X",  offset +387, 115);
		g2d.drawString("X", offset + 582, 115);
		}

	private void drawRotationMatrix(Graphics2D g2d, Matrix matrix, int x, int y)
		{
		//left bracket
		g2d.drawLine(x, y, x, y + 110);
		g2d.drawLine(x, y, x + 10, y);
		g2d.drawLine(x, y + 110, x + 10, y + 110);

		//right bracket
		g2d.drawLine(x + 165, y, x + 165, y + 110);
		g2d.drawLine(x + 165, y, x + 155, y);
		g2d.drawLine(x + 165, y + 110, x + 155, y + 110);

		//values
		for(int i = 1; i <= 3; i++)
			{
			for(int j = 1; j <= 3; j++)
				{
				g2d.drawString(String.format("%.3f", matrix.getValue(i - 1, j - 1)), x - 13 + 40 * j, y - 20 + 40 * i);
				}
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
	private Matrix matrixRotation;
	private Matrix matrixRz;
	private Matrix matrixRy;
	private Matrix matrixRx;
	private double alpha;
	private double beta;
	private double gamma;

	}
