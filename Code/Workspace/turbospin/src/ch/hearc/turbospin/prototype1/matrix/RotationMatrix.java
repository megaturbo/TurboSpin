
package ch.hearc.turbospin.prototype1.matrix;

import java.util.Vector;

import ch.hearc.turbospin.prototype1.mathtools.Line3D;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;

public class RotationMatrix
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public RotationMatrix()
		{
		matrixIdentity();
		}

	public RotationMatrix(RotationMatrix src)
		{
			this.angle = src.angle;
			this.vectorUnit = new Vector<Double>(src.vectorUnit);
			createOriginRotationMatrix();
		}

	public RotationMatrix(double inputAngle, Vector<Double> unit)
		{
		this.vectorUnit = new Vector<Double>(unit);
		this.angle = inputAngle;
		createOriginRotationMatrix();
		}

	public RotationMatrix(double inputAngle, Line3D axis)
		{
		this.axis = new Line3D(axis);
		this.angle = inputAngle;
		createSkewRotationMatrix();
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public String toString()
		{
		String tab = new String();

		for(int i = 0; i <= 3; i++)
			{
			tab += "\t";
			for(int j = 0; j <= 3; j++)
				{
				tab += matrixRotation[i][j] + "\t\t";
				}
			tab += "\n";
			}

		return tab;
		}

	public Point3D rotate(Point3D point)
		{

		return null;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public double[][] getRotationMatrix()
		{
		return matrixRotation;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void matrixIdentity()
		{
		matrixRotation = new double[4][4];
		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				matrixRotation[i][j] = (i == j) ? 1 : 0;
				}
			}
		}

	private void createOriginRotationMatrix()
		{
		//normalizing the axis vector
		double norm = vectorAxisNorm();
		vectorUnit.set(0, vectorUnit.get(0) / norm);
		vectorUnit.set(1, vectorUnit.get(1) / norm);
		vectorUnit.set(2, vectorUnit.get(2) / norm);

		//setting vectorUnit's coordinates to (u,v,w)
		double u = vectorUnit.get(0);
		double v = vectorUnit.get(1);
		double w = vectorUnit.get(2);

		matrixRotation = new double[4][4];

		//creating the rotation matrix
		matrixRotation[0][0] = Math.cos(angle) + (Math.pow(u, 2) * (1 - Math.cos(angle)));
		matrixRotation[0][1] = u * v * (1 - Math.cos(angle)) - w * Math.sin(angle);
		matrixRotation[0][2] = u * w * (1 - Math.cos(angle)) + v * Math.sin(angle);
		matrixRotation[0][3] = 0;

		matrixRotation[1][0] = u * v * (1 - Math.cos(angle)) + w * Math.sin(angle);
		matrixRotation[1][1] = Math.cos(angle) + (Math.pow(v, 2) * (1 - Math.cos(angle)));
		matrixRotation[1][2] = v * w * (1 - Math.cos(angle)) - u * Math.sin(angle);
		matrixRotation[1][3] = 0;

		matrixRotation[2][0] = w * u * (1 - Math.cos(angle)) - v * Math.sin(angle);
		matrixRotation[2][1] = w * v * (1 - Math.cos(angle)) + u * Math.sin(angle);
		matrixRotation[2][2] = Math.cos(angle) + (Math.pow(w, 2) * (1 - Math.cos(angle)));
		matrixRotation[2][3] = 0;

		matrixRotation[3][0] = 0;
		matrixRotation[3][1] = 0;
		matrixRotation[3][2] = 0;
		matrixRotation[3][3] = 1;
		}

	private double vectorAxisNorm()
		{
		return Math.sqrt(Math.pow(vectorUnit.get(0), 2) + Math.pow(vectorUnit.get(1), 2) + Math.pow(vectorUnit.get(2), 2));
		}

	private void createSkewRotationMatrix()
		{
		//normalize axis vector
		vectorUnit = new Vector<Double>(axis.getVectorDirection());
		double norm = vectorAxisNorm();

		vectorUnit.set(0, vectorUnit.get(0) / norm);
		vectorUnit.set(1, vectorUnit.get(1) / norm);
		vectorUnit.set(2, vectorUnit.get(2) / norm);

		//setting vectorUnit's coordinates to (u,v,w)
		double u = vectorUnit.get(0);
		double v = vectorUnit.get(1);
		double w = vectorUnit.get(2);

		//setting Point's coordinate to (a,b,c)
		double a = axis.getPointPrimary().getX();
		double b = axis.getPointPrimary().getY();
		double c = axis.getPointPrimary().getZ();

		matrixRotation = new double[4][4];

		matrixRotation[0][0] = Math.pow(u,2) + (Math.pow(v,2)+Math.pow(w,2))*Math.cos(angle);
		//TODO: look at website and code this shit
		matrixRotation[0][1] = u*v*(1 - Math.cos(angle)) - w*Math.sin(angle);
		matrixRotation[0][2] = u*w*(1 - Math.cos(angle)) + v*Math.sin(angle);
		matrixRotation[0][3] = (a*(Math.pow(v, 2) + Math.pow(w, 2) - u*(b*v + c*w))*(1 - Math.cos(angle)) + (b*w - c*v)*Math.sin(angle));

		matrixRotation[1][0] = u*v*(1 - Math.cos(angle)) + w*Math.sin(angle);
		matrixRotation[1][1] = Math.pow(v, 2) + (Math.pow(u, 2) + Math.pow(w, 2))*Math.cos(angle);
		matrixRotation[1][2] = v*w*(1 - Math.cos(angle)) - u*Math.sin(angle);
		matrixRotation[1][3] = (b*(Math.pow(u, 2) + Math.pow(w, 2) - v*(a*u + c*w))*(1 - Math.cos(angle)) + (c*u - a*w)*Math.sin(angle));

		matrixRotation[2][0] = u*w*(1 - Math.cos(angle)) - v*Math.sin(angle);
		matrixRotation[2][1] = v*w*(1 - Math.cos(angle)) - u*Math.sin(angle);
		matrixRotation[2][2] = Math.pow(w, 2) + (Math.pow(u, 2) + Math.pow(v, 2))*Math.cos(angle);
		matrixRotation[2][3] = (c*(Math.pow(u, 2) + Math.pow(v, 2) - w*(a*u + b*v))*(1 - Math.cos(angle)) + (c*u - a*w)*Math.sin(angle));

		matrixRotation[3][0] = 0;
		matrixRotation[3][1] = 0;
		matrixRotation[3][2] = 0;
		matrixRotation[3][3] = 1;
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	// Tool
	private double[][] matrixRotation;

	private Line3D axis;
	private double angle;
	private Vector<Double> vectorUnit;
	}
