
package ch.hearc.ch.prototype1;

import java.util.Vector;

public class RotationMatrix
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public RotationMatrix()
		{
		matrixRotation = new double[3][3];
		matrixIdentity();
		}

	public RotationMatrix(double angle, Vector<Double> unit)
		{
		this.vectorUnit = unit;
		setRotationMatrix(angle, unit);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public String toString()
		{
		String tab = new String();

		for(int j = 0; j < 3; j++)
			{
			tab += "\t"+ matrixRotation[j][j];
			}

		return tab;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void matrixIdentity()
		{
		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				matrixRotation[i][j] = (i == j) ? 1 : 0;
				}
			}
		}

	private void setRotationMatrix(double angle, Vector<Double> unit)
		{
		matrixRotation = new double[3][3];

		matrixRotation[0][0] = Math.cos(angle) + (Math.pow(unit.get(0), 2) * (1 - Math.cos(angle)));
		matrixRotation[0][1] = unit.get(0) * unit.get(1) * (1 - Math.cos(angle)) - unit.get(2) * Math.sin(angle);
		matrixRotation[0][2] = unit.get(0) * unit.get(2) * (1 - Math.cos(angle)) + unit.get(1) * Math.sin(angle);

		matrixRotation[1][0] = unit.get(0) * unit.get(1) * (1 - Math.cos(angle)) + unit.get(2) * Math.sin(angle);
		matrixRotation[1][1] = Math.cos(angle) + (Math.pow(unit.get(1), 2) * (1 - Math.cos(angle)));
		matrixRotation[1][2] = unit.get(1) * unit.get(2) * (1 - Math.cos(angle)) - unit.get(0) * Math.sin(angle);

		matrixRotation[2][0] = unit.get(2) * unit.get(0) * (1 - Math.cos(angle)) - unit.get(1) * Math.sin(angle);
		matrixRotation[2][1] = unit.get(2) * unit.get(1) * (1 - Math.cos(angle)) + unit.get(0) * Math.sin(angle);
		matrixRotation[2][2] = Math.cos(angle) + (Math.pow(unit.get(2), 2) * (1 - Math.cos(angle)));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Tool
	private double[][] matrixRotation;

	private Vector<Double> vectorUnit;
	}
