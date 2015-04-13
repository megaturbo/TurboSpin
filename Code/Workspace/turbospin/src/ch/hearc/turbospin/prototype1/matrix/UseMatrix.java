
package ch.hearc.turbospin.prototype1.matrix;

import ch.hearc.turbospin.prototype1.mathtools.Matrix;

public class UseMatrix
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{
		Matrix matrixR3 = new Matrix(3);

		matrixR3.setValue(0,0,3);
		matrixR3.setValue(0,1,28);
		matrixR3.setValue(0,2,2);
		matrixR3.setValue(1,0,9);
		matrixR3.setValue(1,1,3);
		matrixR3.setValue(1,2,-12e-5);
		matrixR3.setValue(2,0,1);
		matrixR3.setValue(2,1,483.2);
		matrixR3.setValue(2,2,3);

		double determinant = matrixR3.determinant();
		System.out.println(determinant);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
