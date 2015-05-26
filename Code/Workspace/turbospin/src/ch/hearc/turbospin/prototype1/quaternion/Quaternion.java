
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

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
		sb.append("q = [r: ");
		sb.append(r);
		sb.append(", i: ");
		sb.append(i);
		sb.append(", j: ");
		sb.append(j);
		sb.append(", k: ");
		sb.append(k);
		sb.append("]");
		return sb.toString();
		}

	public Quaternion add(Quaternion h)
		{
		return new Quaternion(r + h.r, i + h.i, j + h.j, k + h.k);
		}

	public Quaternion multiplyRight(Quaternion h)
		{
		double rtmp = r * h.r - i * h.i - j * h.j - k * h.k;
		double itmp = r * h.i + i * h.r + j * h.k - k * h.j;
		double jtmp = r * h.j - i * h.k + j * h.r + k * h.i;
		double ktmp = r * h.k + i * h.j - j * h.i + k * h.r;
		return new Quaternion(rtmp, itmp, jtmp, ktmp);
		}

	public Quaternion multiplyLeft(Quaternion h)
		{
		Quaternion tmp = new Quaternion(h);
		return tmp.multiplyRight(this);
		}

	public Quaternion divide(double n)
		{
		return new Quaternion(r / n, i / n, j / n, k / n);
		}

	public Double norm()
		{
		return Math.sqrt(r * r + i * i + j * j + k * k);
		}

	public Quaternion conjugate()
		{
		return new Quaternion(r, -i, -j, -k);
		}

	public Quaternion inverse()
		{
		return this.conjugate().divide(Math.pow(this.norm(), 2));
		}

	public boolean isEqualTo(Quaternion h, double epsilon)
		{
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
