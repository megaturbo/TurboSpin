
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

public class Vector3D extends Shape3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Vector3D(double a, double b, double c)
		{
		this(a, b, c, TurboColors.PINK);
		}

	public Vector3D(double a, double b, double c, Color3f color)
		{
		this.a = a;
		this.b = b;
		this.c = c;

		//vector appearance
		Appearance appearance = new Appearance();
		//color
		appearance.setColoringAttributes(new ColoringAttributes(color, 0));
		//width
		LineAttributes la = new LineAttributes();
		la.setLineWidth(3);
		appearance.setLineAttributes(la);

		this.setAppearance(appearance);

		//vector geometry
		Point3d[] pointLineArrayPoints = new Point3d[2];
		pointLineArrayPoints[0] = new Point3d(0, 0, 0);
		pointLineArrayPoints[1] = new Point3d(a, b, c);
		LineArray pointLineArray = new LineArray(2, GeometryArray.COORDINATES);
		pointLineArray.setCoordinates(0, pointLineArrayPoints);

		this.setGeometry(pointLineArray);
		}

	public Vector3D(Vector3D v)
		{
		this(v.a, v.b, v.c);
		}

	public Vector3D()
		{
		this(0, 0, 0);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(a);
		sb.append(", ");
		sb.append(b);
		sb.append(", ");
		sb.append(c);
		sb.append("]");
		return sb.toString();
		}

	public double norm()
		{
		return Math.sqrt(a * a + b * b + c * c);
		}

	public Vector3D normalize()
		{
		double norm = norm();
		//changes this, not implemented
		//		a /= norm;
		//		b /= norm;
		//		c /= norm;
		//no change to this
		return new Vector3D(a / norm, b / norm, c / norm);
		}

	public Vector3D add(Vector3D v)
		{
		//changes this, not implemented
		//		a += v.a;
		//		b += v.b;
		//		c += v.c;
		//no change to this
		return new Vector3D(a + v.a, b + v.b, c + v.c);
		}

	public Vector3D multiply(double x)
		{
		//changes this, not implemented
		//		a *= x;
		//		b *= x;
		//		c *= x;
		//no change to this
		return new Vector3D(a * x, b * x, c * x);
		}

	public double scalarProduct(Vector3D v)
		{
		return a * v.a + b * v.b + c * v.c;
		}

	public Vector3D crossProduct(Vector3D v)
		{
		return new Vector3D(b * v.c - v.b * c, c * v.a - v.c * a, a * v.b - v.a * b);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setA(double a)
		{
		this.a = a;
		}

	public void setB(double b)
		{
		this.b = b;
		}

	public void setC(double c)
		{
		this.c = c;
		}

	public void set(double a, double b, double c)
		{
		this.a = a;
		this.b = b;
		this.c = c;
		}

	public void set(Vector3D v)
		{
		set(v.a, v.b, v.c);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public double getA()
		{
		return this.a;
		}

	public double getB()
		{
		return this.b;
		}

	public double getC()
		{
		return this.c;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private double a;
	private double b;
	private double c;
	}
