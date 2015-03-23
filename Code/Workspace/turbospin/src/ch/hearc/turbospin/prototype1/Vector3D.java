
package ch.hearc.turbospin.prototype1;

public class Vector3D extends EnvironmentObject
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Vector3D(double a, double b, double c)
		{
		this.a = a;
		this.b = b;
		this.c = c;
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

	public void normalize()
		{
		double norm = norm();
		a /= norm;
		b /= norm;
		c /= norm;
		}

	public void add(Vector3D v)
		{
		a += v.a;
		b += v.b;
		c += v.c;
		}

	public void multiply(double x)
		{
		a *= x;
		b *= x;
		c *= x;
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
