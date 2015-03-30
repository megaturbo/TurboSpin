
package ch.hearc.turbospin.prototype1.mathtools;



public class Point3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Point3D()
	{
		this(0,0,0);
	}

	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point3D(Point3D src)
	{
		this(src.x,src.y,src.z);
	}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void translate(double deltaX, double deltaY, double deltaZ)
	{
		x+=deltaX;
		y+=deltaY;
		z+=deltaZ;
	}
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setX(double x)
		{
		this.x = x;
		}

	public void setY(double y)
		{
		this.y = y;
		}

	public void setZ(double z)
		{
		this.z = z;
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public double getX()
		{
		return this.x;
		}

	public double getY()
		{
		return this.y;
		}

	public double getZ()
		{
		return this.z;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private double x;
	private double y;
	private double z;
	}
