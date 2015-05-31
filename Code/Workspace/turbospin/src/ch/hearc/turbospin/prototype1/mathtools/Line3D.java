
package ch.hearc.turbospin.prototype1.mathtools;

import javax.media.j3d.Shape3D;


public class Line3D extends Shape3D
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Line3D()
		{
		this.pointPrimary = new Point3D();
		this.vectorDirection = new Vector3D();
		}

	public Line3D(Line3D src)
		{
		this.pointPrimary = new Point3D(src.pointPrimary);
		this.vectorDirection = new Vector3D(src.vectorDirection);
		}

	// 2 points
	public Line3D(Point3D srcPointA, Point3D srcPointB)
		{
		this.pointPrimary = new Point3D(srcPointA);
		this.vectorDirection = createDirectionVector(srcPointB);
		}

	// Parametric
	public Line3D(Point3D srcPoint, Vector3D srcVectorDirection)
		{
		this.pointPrimary = new Point3D(srcPoint);
		this.vectorDirection = new Vector3D(srcVectorDirection);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setVectorDirection(Vector3D vectorDirection)
		{
		this.vectorDirection = new Vector3D(vectorDirection);
		}

	public void setPointPrimary(Point3D pointPrimary)
		{
		this.pointPrimary = new Point3D(pointPrimary);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public Vector3D getVectorDirection()
		{
		return new Vector3D(this.vectorDirection);
		}

	public Point3D getPointPrimary()
		{
		return new Point3D(this.pointPrimary);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private Vector3D createDirectionVector(Point3D src)
		{
		vectorDirection = new Vector3D();
		vectorDirection.setA(src.getX() - pointPrimary.getX());
		vectorDirection.setB(src.getY() - pointPrimary.getY());
		vectorDirection.setC(src.getZ() - pointPrimary.getZ());
		return vectorDirection;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private Vector3D vectorDirection;

	private Point3D pointPrimary;
	}
