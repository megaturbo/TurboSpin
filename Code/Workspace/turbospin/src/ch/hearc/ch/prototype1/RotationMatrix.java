
package ch.hearc.ch.prototype1;

import java.util.Vector;

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

	public RotationMatrix(double inputangle, Vector<Double> unit)
		{
		this.vectorUnit = new Vector<Double>(unit);
		this.angle = inputangle;
		createOriginRotationMatrix();
		}

	public RotationMatrix(double inputangle, Line axis)
		{
		this.axis = new Line(axis);
		this.angle = inputangle;
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

	public Vector<Double> rotate(Vector<Double> point, double angle, Vector<Double> axis)
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

		matrixRotation = new double[4][4];

		//creating the rotation matrix
		matrixRotation[0][0] = Math.cos(angle) + (Math.pow(vectorUnit.get(0), 2) * (1 - Math.cos(angle)));
		matrixRotation[0][1] = vectorUnit.get(0) * vectorUnit.get(1) * (1 - Math.cos(angle)) - vectorUnit.get(2) * Math.sin(angle);
		matrixRotation[0][2] = vectorUnit.get(0) * vectorUnit.get(2) * (1 - Math.cos(angle)) + vectorUnit.get(1) * Math.sin(angle);
		matrixRotation[0][3] = 0;

		matrixRotation[1][0] = vectorUnit.get(0) * vectorUnit.get(1) * (1 - Math.cos(angle)) + vectorUnit.get(2) * Math.sin(angle);
		matrixRotation[1][1] = Math.cos(angle) + (Math.pow(vectorUnit.get(1), 2) * (1 - Math.cos(angle)));
		matrixRotation[1][2] = vectorUnit.get(1) * vectorUnit.get(2) * (1 - Math.cos(angle)) - vectorUnit.get(0) * Math.sin(angle);
		matrixRotation[1][3] = 0;

		matrixRotation[2][0] = vectorUnit.get(2) * vectorUnit.get(0) * (1 - Math.cos(angle)) - vectorUnit.get(1) * Math.sin(angle);
		matrixRotation[2][1] = vectorUnit.get(2) * vectorUnit.get(1) * (1 - Math.cos(angle)) + vectorUnit.get(0) * Math.sin(angle);
		matrixRotation[2][2] = Math.cos(angle) + (Math.pow(vectorUnit.get(2), 2) * (1 - Math.cos(angle)));
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

		matrixRotation = new double[4][4];

		matrixRotation[0][0] = Math.pow(vectorUnit.get(0),2) + (Math.pow(vectorUnit.get(1),2)+Math.pow(vectorUnit.get(2),2))*Math.cos(angle);
		//TODO: look at website and code this shit
		/*matrixRotation[0][1] = vectorUnit.get(0) * vectorUnit.get(1) * (1 - Math.cos(angle)) - vectorUnit.get(2) * Math.sin(angle);
		matrixRotation[0][2] = vectorUnit.get(0) * vectorUnit.get(2) * (1 - Math.cos(angle)) + vectorUnit.get(1) * Math.sin(angle);
		matrixRotation[0][3] = 0;

		matrixRotation[1][0] = vectorUnit.get(0) * vectorUnit.get(1) * (1 - Math.cos(angle)) + vectorUnit.get(2) * Math.sin(angle);
		matrixRotation[1][1] = Math.cos(angle) + (Math.pow(vectorUnit.get(1), 2) * (1 - Math.cos(angle)));
		matrixRotation[1][2] = vectorUnit.get(1) * vectorUnit.get(2) * (1 - Math.cos(angle)) - vectorUnit.get(0) * Math.sin(angle);
		matrixRotation[1][3] = 0;

		matrixRotation[2][0] = vectorUnit.get(2) * vectorUnit.get(0) * (1 - Math.cos(angle)) - vectorUnit.get(1) * Math.sin(angle);
		matrixRotation[2][1] = vectorUnit.get(2) * vectorUnit.get(1) * (1 - Math.cos(angle)) + vectorUnit.get(0) * Math.sin(angle);
		matrixRotation[2][2] = Math.cos(angle) + (Math.pow(vectorUnit.get(2), 2) * (1 - Math.cos(angle)));
		matrixRotation[2][3] = 0;*/

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

	private Line axis;
	private double angle;
	private Vector<Double> vectorUnit;
	}
