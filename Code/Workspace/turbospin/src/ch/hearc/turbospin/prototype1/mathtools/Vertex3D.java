
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
		this(v.A, v.B);
		}

	public Vertex3D(Vertex3D v, Color3f color, int width)
		{
		this(v.A, v.B, color, width);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

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
		//vector appearance
		Appearance appearance = new Appearance();
		//color
		appearance.setColoringAttributes(new ColoringAttributes(color, 0));
		//width
		LineAttributes la = new LineAttributes();
		la.setLineWidth(width);
		appearance.setLineAttributes(la);

		this.setAppearance(appearance);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Point3D A;
	private Point3D B;
	}
