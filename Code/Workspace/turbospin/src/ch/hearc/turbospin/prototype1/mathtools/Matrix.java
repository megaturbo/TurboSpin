
package ch.hearc.turbospin.prototype1.mathtools;

import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
import ch.hearc.turbospin.prototype1.matrix.MatrixTools;

public class Matrix implements RotationItem
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Matrix(Matrix src)
		{
		this(src.getRows(), src.getColumns());
		for(int i = 0; i < rows; i++)
			{
			for(int j = 0; j < columns; j++)
				{
				matrix[i][j] = src.matrix[i][j];
				}
			}
		}

	public Matrix(int M, int N)
		{
		matrix = new double[M][N];
		this.rows = M;
		this.columns = N;
		init();
		}

	public Matrix(int M)
		{
		matrix = new double[M][M];
		this.rows = M;
		this.columns = M;
		init();
		}

	public Matrix(double[][] tab)
		{
		this.matrix = tab;
		this.rows = tab.length;
		this.columns = tab[0].length;

		for(int i = 0; i < rows; i++)
			{
			for(int j = 0; j < columns; j++)
				{
				matrix[i][j] = tab[i][j];
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public Matrix add(Matrix toAdd)
		{
		try
			{
			for(int i = 0; i < toAdd.getColumns(); i++)
				{
				for(int j = 0; j < toAdd.getRows(); j++)
					{
					matrix[j][i] += toAdd.getValue(j, i);
					}
				}
			return new Matrix(matrix);
			}
		catch (Exception e)
			{
			System.err.println("Not the same sized matrices.");
			e.printStackTrace();
			return null;
			}
		}

	public Matrix times(Matrix toMultiply)
		{

		Matrix product = new Matrix(this.rows, toMultiply.columns);

		try
			{
			for(int i = 0; i < this.rows; i++)
				{
				for(int j = 0; j < toMultiply.columns; j++)
					{
					for(int k = 0; k < this.columns; k++)
						{
						product.matrix[i][j] += matrix[i][k] * toMultiply.matrix[k][j];
						}
					}
				}

			return product;
			}
		catch (Exception e)
			{
			System.err.println("Matrices incompatible for multiplication. Make sure you have a MxN * NxJ product.");
			e.printStackTrace();
			return null;
			}
		}

	public Vector3D times(Vector3D toMultiply)
		{

		Vector3D product = new Vector3D();

		try
			{
			product.setA(toMultiply.getA() * (this.matrix[0][0]) + toMultiply.getB() * (this.matrix[1][0]) + toMultiply.getC() * (this.matrix[2][0]));
			product.setB(toMultiply.getA() * (this.matrix[0][1]) + toMultiply.getB() * (this.matrix[1][1]) + toMultiply.getC() * (this.matrix[2][1]));
			product.setC(toMultiply.getA() * (this.matrix[0][2]) + toMultiply.getB() * (this.matrix[1][2]) + toMultiply.getC() * (this.matrix[2][2]));

			return product;
			}
		catch (Exception e)
			{
			System.err.println("Matrix isn't of size 3x3. Make sure you are using a 3D matrix.");
			e.printStackTrace();
			return null;
			}
		}

	public Point3D times(Point3D toMultiply)
		{
		Point3D product = new Point3D();

		product.set(toMultiply.getX() * (this.matrix[0][0]) + toMultiply.getY() * (this.matrix[1][0]) + toMultiply.getZ() * (this.matrix[2][0]),
					toMultiply.getX() * (this.matrix[0][1]) + toMultiply.getY() * (this.matrix[1][1]) + toMultiply.getZ() * (this.matrix[2][1]),
					toMultiply.getX() * (this.matrix[0][2]) + toMultiply.getY() * (this.matrix[1][2]) + toMultiply.getZ() * (this.matrix[2][2]));
		return product;
		}

	public Matrix times(double scalar)
		{
		Matrix product = new Matrix(rows, columns);
		for(int i = 0; i < rows; i++)
			{
			for(int j = 0; j < columns; j++)
				{
				product.setValue(i, j, matrix[i][j] * scalar);
				}
			}
		return product;
		}

	public double determinant()
		{

		try
			{
			if (columns == 1 || rows == 1)
				{
				return this.getValue(0, 0);
				}
			else
				{
				int determinant = 0;
				for(int i = 0; i < columns; i++)
					{
					determinant += ((i % 2 == 0) ? 1 : -1) * this.getValue(0, i) * this.subMatrix(0, i).determinant();
					}
				return determinant;

				}
			}
		catch (Exception e)
			{
			System.err.println("This matrix isn't square; no determinant possible.");
			e.printStackTrace();
			return 0;
			}
		}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Matrix [");
		sb.append(Hexacodes.ALPHA_LOWER);
		sb.append(" = ");
		sb.append(String.format("%.3f", MatrixTools.getAlpha(this)));
		sb.append(", ");
		sb.append(Hexacodes.BETA_LOWER);
		sb.append(" = ");
		sb.append(String.format("%.3f", MatrixTools.getBeta(this)));
		sb.append(", ");
		sb.append(Hexacodes.GAMMA_LOWER);
		sb.append(" = ");
		sb.append(String.format("%.3f", MatrixTools.getGamma(this)));
		sb.append("]");
		return sb.toString();
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setValue(int i, int j, double value)
		{
		matrix[i][j] = value;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getColumns()
		{
		return columns;
		}

	public int getRows()
		{
		return rows;
		}

	public double getValue(int i, int j)
		{
		return matrix[i][j];
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private Matrix subMatrix(int row, int column)
		{

		Matrix subMatrix = new Matrix(rows - 1, columns - 1);

		int subIndex = 0;

		int smRows = subMatrix.getRows();
		int smCols = subMatrix.getColumns();

		for(int i = 0; i < rows * columns; i++)
			{
			if (!(i / rows == row || i % columns == column))
				{
				subMatrix.setValue(subIndex / smCols, subIndex % smRows, this.getValue(i / rows, i % columns));
				subIndex++;
				}
			}

		return subMatrix;
		}

	private void init()
		{
		for(int i = 0; i < this.rows; i++)
			{
			for(int j = 0; j < this.columns; j++)
				{
				matrix[i][j] = 0;
				}
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private double[][] matrix;
	private int rows;
	private int columns;
	}
