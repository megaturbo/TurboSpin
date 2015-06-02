
package ch.hearc.turbospin.prototype1.mathtools;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;

public class Vertex3D extends Shape3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Vertex3D(Point3D A, Point3D B)
		{
		this(A, B, TurboColors.PINK, 3);
		}

	public Vertex3D(Vector3D A, Vector3D B)
		{
		this(new Point3D(A.getA(), A.getB(), A.getC()), new Point3D(B.getA(), B.getB(), B.getC()));
		}

	public Vertex3D(Point3D A, Point3D B, Color3f color, int width, int linePattern)
		{
		this.A = new Point3D(A);
		this.B = new Point3D(B);

		setAppearance(color, width, linePattern);

		setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);

		setGeometry();
		}

	public Vertex3D(Point3D A, Point3D B, Color3f color, int width)
		{
		this.A = new Point3D(A);
		this.B = new Point3D(B);

		setAppearance(color, width);

		setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);

		setGeometry();
		}

	public Vertex3D(Vertex3D v)
		{
		this(v.A, v.B, v.getColor(), v.getWidth());
		}

	public Vertex3D(Vertex3D v, Color3f color, int width)
		{
		this(v.A, v.B, color, width);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		return "Vertex: " + A.toString() + ", " + B.toString();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setA(Point3D A)
		{
		this.A = new Point3D(A);
		setGeometry();
		}

	public void setB(Point3D B)
		{
		this.B = new Point3D(B);
		setGeometry();
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Point3D getA()
		{
		return new Point3D(A);
		}

	public Point3D getB()
		{
		return new Point3D(B);
		}

	public Color3f getColor()
		{
		Color3f output = new Color3f();
		this.getAppearance().getColoringAttributes().getColor(output);
		return output;
		}

	public int getWidth()
		{
		return (int)this.getAppearance().getLineAttributes().getLineWidth();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void setGeometry()
		{
		//vector geometry
		Point3d[] pointLineArrayPoints = new Point3d[2];
		pointLineArrayPoints[0] = new Point3d(A.getX(), A.getY(), A.getZ());
		pointLineArrayPoints[1] = new Point3d(B.getX(), B.getY(), B.getZ());
		LineArray pointLineArray = new LineArray(2, GeometryArray.COORDINATES);
		pointLineArray.setCoordinates(0, pointLineArrayPoints);

		this.setGeometry(pointLineArray);
		}

	private void setAppearance(Color3f color, int width)
		{
		setAppearance(color, width, LineAttributes.PATTERN_SOLID);
		}

	private void setAppearance(Color3f color, int width, int linePattern)
		{
		//vector appearance
		Appearance appearance = new Appearance();
		//color
		appearance.setColoringAttributes(new ColoringAttributes(color, 0));
		//width
		LineAttributes la = new LineAttributes();
		la.setLineWidth(width);
		la.setLinePattern(linePattern);
		la.setLineAntialiasingEnable(true);
		appearance.setLineAttributes(la);

		this.setAppearance(appearance);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Point3D A;
	private Point3D B;
	}
