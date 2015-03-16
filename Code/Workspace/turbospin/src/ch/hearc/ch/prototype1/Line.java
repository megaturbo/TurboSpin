
package ch.hearc.ch.prototype1;

import java.util.Vector;

public class Line
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Line()
		{
		this.pointPrimary = new Point3D();
		this.vectorDirection = new Vector<Double>();
		}

	public Line(Line src)
		{
		this.pointPrimary = new Point3D(src.pointPrimary);
		this.vectorDirection = new Vector<Double>(src.vectorDirection);
		}

	public Line(Point3D srcPointA, Point3D srcPointB)
		{
		this.pointPrimary = new Point3D(srcPointA);
		this.pointSecondary = new Point3D(srcPointB);
		this.vectorDirection = createDirectionVector();
		}

	public Line(Point3D srcPoint, Vector<Double> srcVectorDirection)
		{
		this.pointPrimary = new Point3D(srcPoint);
		this.vectorDirection = new Vector<Double>(srcVectorDirection);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setVectorDirection(Vector<Double> vectorDirection)
		{
		this.vectorDirection = vectorDirection;
		}

	public void setPointPrimary(Point3D pointPrimary)
		{
		this.pointPrimary = pointPrimary;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public Vector<Double> getVectorDirection()
		{
		return this.vectorDirection;
		}

	public Point3D getPointPrimary()
		{
		return this.pointPrimary;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private Vector<Double> createDirectionVector()
		{
		vectorDirection = new Vector<Double>();
		vectorDirection.add(pointSecondary.getX() - pointPrimary.getX());
		vectorDirection.add(pointSecondary.getY() - pointPrimary.getY());
		vectorDirection.add(pointSecondary.getZ() - pointPrimary.getZ());
		return vectorDirection;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//tools
	private Vector<Double> vectorDirection;

	private Point3D pointPrimary;
	private Point3D pointSecondary;
	}
