
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
		this.columns = M;
		this.rows = M;
		}

	public Matrix(double[][] tab)
		{
		this.matrix = tab;
		this.columns = tab.length;
		this.rows = tab[0].length;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public Matrix add(Matrix toAdd)
		{
		if ((this.columns == toAdd.columns) && (this.rows == toAdd.rows))
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
			{// for now returning the input Matrix
			// TODO: use Exceptions to handle this
			return toAdd;
			}
		}

	public Matrix times(Matrix toMultiply)
		{
		if (this.columns != toMultiply.rows) { return toMultiply; }
		Matrix product = new Matrix(this.columns, toMultiply.rows);

		for(int i = 0; i < this.columns; i++)
			{
			for(int j = 0; j < toMultiply.rows; j++)
				{
				for(int k = 0; k < this.rows; i++)
					{
					product.setValue(i, j, this.getValue(i, k)*toMultiply.getValue(k, j));
					}
				}
			}
		// for now returning the input Matrix
		// TODO: use Exceptions to handle this
		return product;
		}
	public Vector3D times(Vector3D toMultiply)
		{
		if (this.columns != 3&&this.rows!=3) { return toMultiply; }
		Vector3D product = new Vector3D();

		product.setA(toMultiply.getA()*(this.matrix[0][0])+toMultiply.getB()*(this.matrix[1][0])+ toMultiply.getC()*(this.matrix[2][0]));
		product.setB(toMultiply.getA()*(this.matrix[0][1])+toMultiply.getB()*(this.matrix[1][1])+ toMultiply.getC()*(this.matrix[2][1]));
		product.setC(toMultiply.getA()*(this.matrix[0][2])+toMultiply.getB()*(this.matrix[1][2])+ toMultiply.getC()*(this.matrix[2][2]));

		return product;
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
		if (columns != rows)
			{
			return 0;
			}
		else
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

		Matrix subMatrix = new Matrix(columns - 1, rows - 1);

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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private double[][] matrix;
	private int rows;
	private int columns;
	}
