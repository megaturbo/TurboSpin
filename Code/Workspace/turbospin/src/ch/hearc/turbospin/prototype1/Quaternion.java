
package ch.hearc.turbospin.prototype1;

import java.util.Vector;

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

	public Quaternion(double r, Vector<Double> v)
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

	public static Quaternion createRotationQuaternion(double theta, Vector<Double> axisVector)
		{
		double axisVectorNorm = Math.sqrt(axisVector.get(0) * axisVector.get(0) + axisVector.get(1) * axisVector.get(1) + axisVector.get(2) * axisVector.get(2));
		Vector<Double> unitVector = new Vector<Double>();
		unitVector.add(axisVector.get(0) * Math.sin(1.0 / 2.0 * theta) / axisVectorNorm);
		unitVector.add(axisVector.get(1) * Math.sin(1.0 / 2.0 * theta) / axisVectorNorm);
		unitVector.add(axisVector.get(2) * Math.sin(1.0 / 2.0 * theta) / axisVectorNorm);
		return new Quaternion(Math.cos(1.0 / 2.0 * theta), unitVector);
		}

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

	public Quaternion add(Quaternion h)
		{
		return new Quaternion(r + h.r, i + h.i, j + h.j, k + h.k);
		}

	public Quaternion multiply(Quaternion h)
		{
		Quaternion output = new Quaternion();
		output.setReal(r * h.r - i * h.i - j * h.j - k * h.k);
		output.setI(r * h.i + i * h.r + j * h.k - k * h.j);
		output.setJ(r * h.j - i * h.k + j * h.r + k * h.i);
		output.setK(r * h.k + i * h.j - j * h.i + k * h.r);
		return output;
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
		return conjugate().divide(Math.pow(this.norm(), 2));
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

	public void setVector(Vector<Double> im) throws IllegalArgumentException
		{
		if (im.size() != 3) { throw new IllegalArgumentException("Method setVector in class Quaternion: expected a vector in R3."); }
		setIJK(im.get(0), im.get(1), im.get(2));
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

	public Vector<Double> getVector()
		{
		Vector<Double> output = new Vector<Double>();
		output.add(i);
		output.add(j);
		output.add(k);
		return output;
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
