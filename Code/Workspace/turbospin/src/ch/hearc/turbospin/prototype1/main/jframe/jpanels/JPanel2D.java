
package ch.hearc.turbospin.prototype1.main.jframe.jpanels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Map;

import javax.media.j3d.Shape3D;
import javax.swing.JPanel;

import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

public class JPanel2D extends JPanel implements MouseMotionListener
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

		addMouseMotionListener(this);

		geometry();
		control();
		appearance();
		centerX = 2.0;
		centerY = 2.0;
		yArea = 4.0;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawVector(g2d, axes.get('x'));
		drawVector(g2d, axes.get('y'));
		drawVector(g2d, axes.get('z'));
		drawVector(g2d, axes.get('i'));
		drawVector(g2d, axes.get('j'));
		drawVector(g2d, axes.get('k'));

		//projection on the plane
		for(Shape3D shape:shapes)
			{
			if (shape instanceof Vector3D)
				{
				drawVector(g2d, (Vector3D)shape);
				}
			if (shape instanceof Point3D)
				{
				drawPoint(g2d, (Point3D)shape);
				}
			}
		}

	private void drawPoint(Graphics2D g2d, Point3D point)
		{
		g2d.setColor(point.getColor().get());
		g2d.setStroke(new BasicStroke(3));
		double deltaX = getPointValue(point, dim1);
		double deltaY = getPointValue(point, dim2);
		double x = (centerX + deltaX) / xArea * getWidth();
		double y = (centerY - deltaY) / yArea * getHeight();
		g2d.drawArc((int)x, (int)y, 3, 3, 0, 360);
		}

	private void drawVector(Graphics2D g2d, Vector3D vector)
		{
		g2d.setColor(vector.getColor().get());
		g2d.setStroke(new BasicStroke(vector.getWidth()));
		double deltaX = getVectorValue(vector, dim1);
		double deltaY = getVectorValue(vector, dim2);
		double x1 = centerX * getWidth() / xArea;
		double y1 = centerY * getHeight() / yArea;
		double x2 = x1 + deltaX / xArea * getWidth();
		double y2 = y1 - deltaY / yArea * getHeight();
		g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}

	private double getPointValue(Point3D point, char dim)
		{
		switch(dim)
			{
			case 'i':
				return point.getX();
			case 'j':
				return point.getY();
			case 'k':
				return point.getZ();
			default:
				return 0;
			}
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

	private void geometry()
		{
		//rien
		}

	private void control()
		{
		this.addComponentListener(new ComponentAdapter()
			{

				@Override
				public void componentResized(ComponentEvent e)
					{
					xArea = (double)getWidth() / getHeight() * yArea;
					repaint();
					}
			});

		this.addMouseListener(new MouseAdapter()
			{

				@Override
				public void mousePressed(MouseEvent e)
					{
					button = e.getButton();
					}

				@Override
				public void mouseReleased(MouseEvent e)
					{
					mouseX = -1;
					mouseY = -1;
					}

			});
		}

	//Mouse control
	@Override
	public void mouseDragged(MouseEvent e)
		{
		if (mouseX > 0 && mouseY > 0)
			{
			int deltaX = e.getX() - mouseX;
			int deltaY = e.getY() - mouseY;
			switch(button)
				{
				case 1:
				case 3:
					centerX += (double)deltaX / getWidth() * xArea;
					centerY += (double)deltaY / getHeight() * yArea;
					break;
				case 2:
					yArea *= Math.pow(Math.E, deltaY / 10.0);
					xArea = (double)getWidth() / getHeight() * yArea;
					centerX *= Math.pow(Math.E, deltaY / 10.0);
					centerY *= Math.pow(Math.E, deltaY / 10.0);
					break;
				default:
				}
			this.repaint();
			}

		mouseX = e.getX();
		mouseY = e.getY();
		}

	@Override
	public void mouseMoved(MouseEvent e)
		{
		//nothing
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

	//Drawing attributes and mouse control
	private double centerX;
	private double centerY;
	private double yArea;
	private double xArea;
	private int mouseX = -1;
	private int mouseY = -1;
	private int button;
	}
