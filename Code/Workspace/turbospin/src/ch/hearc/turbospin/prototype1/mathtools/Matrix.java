
package ch.hearc.turbospin.prototype1.mathtools;

public class Matrix
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Matrix(Matrix src)
		{
		this(src.getColumns(), src.getRows());
		}

	public Matrix(int M, int N)
		{
		matrix = new double[M][N];
		this.columns = M;
		this.rows = N;
		}

	public Matrix(int M)
		{
		matrix = new double[M][M];
		}

	public Matrix(double[][] tab)
		{
		this.matrix = tab;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public Matrix add(Matrix toAdd)
		{
		if ((matrix.length == toAdd.getColumns()) && (matrix[0].length == toAdd.getRows()))
			{
			for(int i = 0; i < toAdd.getColumns(); i++)
				{
				for(int j = 0; j < toAdd.getRows(); j++)
					{
					matrix[i][j] += toAdd.getValue(i, j);
					}
				}
			return new Matrix(matrix);
			}
		else
			{//for now returning the input Matrix
				//TODO: use Exceptions to handle this
			return toAdd;
			}
		}

	public Matrix times(Matrix toMultiply)
		{
		//TODO
		return null;
		}

	public Matrix times(double scalar)
		{
		for(int i = 0; i < matrix.length; i++)
			{
			for(int j = 0; j < matrix[0].length; j++)
				{
				matrix[i][j] *= scalar;
				}
			}
		return new Matrix(matrix);
		}

	public double determinant()
		{
			return null;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public int getColumns()
		{
		return matrix.length;
		}

	public int getRows()
		{
		return matrix[0].length;
		}

	public double getValue(int i, int j)
		{
		return matrix[i][j];
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private double[][] matrix;
	private int rows;
	private int columns;
	}
