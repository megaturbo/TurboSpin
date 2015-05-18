
package ch.hearc.turbospin.prototype1.mathtools;

<<<<<<< HEAD
import javax.media.j3d.Shape3D;
=======
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PointArray;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import ch.hearc.turbospin.prototype1.tridimensional.TurboColors;
>>>>>>> e239bf2d63814351759ab23c5cb2b4b99bd427ff

public class Point3D extends Shape3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Point3D()
		{
		this(0, 0, 0);
		}

	public Point3D(double x, double y, double z, Color3f color, int width)
		{
		this.x = x;
		this.y = y;
		this.z = z;

		setAppearance(color, width);

		setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);

		setGeometry();
		}

	public Point3D(double x, double y, double z)
		{
		this(x, y, z, TurboColors.BLUE, 5);
		}

	public Point3D(Point3D src)
		{
		this(src.x, src.y, src.z);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String toString()
		{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(x);
		sb.append(", ");
		sb.append(y);
		sb.append(", ");
		sb.append(z);
		sb.append("]");
		return sb.toString();
		}

	public void translate(double deltaX, double deltaY, double deltaZ)
		{
		x += deltaX;
		y += deltaY;
		z += deltaZ;
		}

	public void translate(Vector3D delta)
		{
		x += delta.getA();
		y += delta.getB();
		z += delta.getC();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setX(double x)
		{
		set(x, y, z);
		}

	public void setY(double y)
		{
		set(x, y, z);
		}

	public void setZ(double z)
		{
		set(x, y, z);
		}

	public void set(Point3D src)
		{
		set(src.x, src.y, src.z);
		}

	public void set(double a, double b, double c)
		{
		this.x = a;
		this.y = b;
		this.z = c;
		setGeometry();
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

	private void setGeometry()
		{
		//vector geometry
		Point3d[] arrayPoints = new Point3d[1];
		arrayPoints[0] = new Point3d(x, y, z);
		PointArray pointArray = new PointArray(1, GeometryArray.COORDINATES);
		pointArray.setCoordinates(0, arrayPoints);

		this.setGeometry(pointArray);
		}

	private void setAppearance(Color3f color, int width)
		{
		//vector appearance
		Appearance appearance = new Appearance();
		//color
		appearance.setColoringAttributes(new ColoringAttributes(color, 0));
		//width
		PointAttributes pa = new PointAttributes();
		pa.setPointSize(width);
		appearance.setPointAttributes(pa);

		this.setAppearance(appearance);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private double x;
	private double y;
	private double z;
	}
