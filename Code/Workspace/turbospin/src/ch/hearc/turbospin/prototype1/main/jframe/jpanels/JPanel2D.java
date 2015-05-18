
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.media.j3d.Shape3D;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class JPanel2D extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanel2D(List<Shape3D> shapes, char dim1, char dim2, Map<Character, Vector3D> axes)
		{
		this.shapes = shapes;
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

		drawVector(g2d, axes.get('i'));
		drawVector(g2d, axes.get('j'));
		drawVector(g2d, axes.get('k'));

		for(Shape3D shape:shapes)
			{
			//projection on the plane
			if (shape instanceof Vector3D)
				{
				drawVector(g2d, (Vector3D)shape);
				}
			}
		}

	private void drawVector(Graphics2D g2d, Vector3D vector)
		{
		g2d.setColor(vector.getColor().get());
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
		this.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mouseClicked(MouseEvent e)
					{
					// TODO Auto-generated method stub

					}

				@Override
				public void mousePressed(MouseEvent e)
					{
					// TODO Auto-generated method stub

					}

				@Override
				public void mouseReleased(MouseEvent e)
					{
					// TODO Auto-generated method stub

					}

				@Override
				public void mouseEntered(MouseEvent e)
					{
					// TODO Auto-generated method stub

					}

				@Override
				public void mouseExited(MouseEvent e)
					{
					// TODO Auto-generated method stub

					}

			});
		}

	private void appearance()
		{
		setBackground(Color.WHITE);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private Map<Character, Vector3D> axes;
	private List<Shape3D> shapes;
	private char dim1;
	private char dim2;
	}
