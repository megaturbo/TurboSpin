
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.Vector3D;

public class Quaternion
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Quaternion(double r, double i, double j, double k)
		{
		this.r = r;
		this.i = i;
		this.j = j;
		this.k = k;
		}

	public Quaternion(Quaternion h)
		{
		this.r = h.r;
		this.i = h.i;
		this.j = h.j;
		this.k = h.k;
		}

	public Quaternion(double r, Vector3D v)
		{
		this.r = r;
		setVector(v);
		}

	public Quaternion()
		{
		r = 0;
		i = 0;
		j = 0;
		k = 0;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(r);
		sb.append(", ");
		sb.append(i);
		sb.append(", ");
		sb.append(j);
		sb.append(", ");
		sb.append(k);
		sb.append("]");
		return sb.toString();
		}

	public void add(Quaternion h)
		{
		r += h.r;
		i += h.i;
		j += h.j;
		k += h.k;
		}

	public void multiplyRight(Quaternion h)
		{
		double rtmp = r * h.r - i * h.i - j * h.j - k * h.k;
		double itmp = r * h.i + i * h.r + j * h.k - k * h.j;
		double jtmp = r * h.j - i * h.k + j * h.r + k * h.i;
		double ktmp = r * h.k + i * h.j - j * h.i + k * h.r;

		this.setReal(rtmp);
		this.setIJK(itmp, jtmp, ktmp);
		}

	public void multiplyLeft(Quaternion h)
		{
		Quaternion tmp = new Quaternion(h);
		tmp.multiplyRight(this);
		this.r = tmp.r;
		this.i = tmp.i;
		this.j = tmp.j;
		this.k = tmp.k;
		}

	public void divide(double n)
		{
		r /= n;
		i /= n;
		j /= n;
		k /= n;
		}

	public Double norm()
		{
		return Math.sqrt(r * r + i * i + j * j + k * k);
		}

	public void conjugate()
		{
		i *= -1.0;
		j *= -1.0;
		k *= -1.0;
		}

	public void inverse()
		{
		conjugate();
		divide(Math.pow(this.norm(), 2));
		}

	public boolean isEqualTo(Quaternion h, double epsilon)
		{

		//DEBUG
		//		System.out.println(Math.abs(r - h.r) + " " + (Math.abs(r - h.r) < epsilon));
		//		System.out.println(Math.abs(i - h.i) + " " + (Math.abs(i - h.i) < epsilon));
		//		System.out.println(Math.abs(j - h.j) + " " + (Math.abs(j - h.j) < epsilon));
		//		System.out.println(Math.abs(k - h.k) + " " + (Math.abs(k - h.k) < epsilon));

		return Math.abs(r - h.r) < epsilon && Math.abs(i - h.i) < epsilon && Math.abs(j - h.j) < epsilon && Math.abs(k - h.k) < epsilon;
		}

	public boolean isEqualTo(Quaternion h)
		{
		return this.isEqualTo(h, 1E-5);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setReal(double r)
		{
		this.r = r;
		}

	public void setI(double i)
		{
		this.i = i;
		}

	public void setJ(double j)
		{
		this.j = j;
		}

	public void setK(double k)
		{
		this.k = k;
		}

	public void setIJK(double i, double j, double k)
		{
		this.i = i;
		this.j = j;
		this.k = k;
		}

	public void setVector(Vector3D im)
		{
		setIJK(im.getA(), im.getB(), im.getC());
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public double getReal()
		{
		return r;
		}

	public double getI()
		{
		return i;
		}

	public double getJ()
		{
		return r;
		}

	public double getK()
		{
		return r;
		}

	public Vector3D getVector()
		{
		return new Vector3D(i, j, k);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Inputs
	private double r;
	private double i;
	private double j;
	private double k;
	}
