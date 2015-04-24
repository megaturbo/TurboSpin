
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class JPanel2D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanel2D(List<Vector3D> vectors, char dim1, char dim2, Map<Character, Vector3D> axes)
		{
		this.vectors = vectors;
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.axes = axes;

		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));

		drawVector(g2d, axes.get('i'), TurboColors.RED.get());
		drawVector(g2d, axes.get('j'), TurboColors.GREEN.get());
		drawVector(g2d, axes.get('k'), TurboColors.BLUE.get());

		for(Vector3D vector:vectors)
			{
			//projection on the plane
			drawVector(g2d, vector, TurboColors.PINK.get());
			}
		}

	private void drawVector(Graphics2D g2d, Vector3D vector, Color color)
		{
		g2d.setColor(color);
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		double yArea = 4.0;
		double xArea = (double)getWidth() / getHeight() * 4.0;
		double deltaX = getVectorValue(vector, dim1);
		double deltaY = getVectorValue(vector, dim2);
		x += deltaX / xArea * getWidth();
		y -= deltaY / yArea * getHeight();
		g2d.drawLine(getWidth() / 2, getHeight() / 2, x, y);
		}

	private double getVectorValue(Vector3D vector, char dim)
		{
		switch(dim)
			{
			case 'i':
				return vector.getA();
			case 'j':
				return vector.getB();
			case 'k':
				return vector.getC();
			default:
				return 0;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		//rien
		}

	private void control()
		{
		// rien
		}

	private void appearance()
		{
		// rien
		setBackground(Color.WHITE);
		//		setBorder(BorderFactory.createTitledBorder(new StringBuilder().append(dim1).append(dim2).toString()));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Map<Character, Vector3D> axes;
	private List<Vector3D> vectors;
	private char dim1;
	private char dim2;
	}
