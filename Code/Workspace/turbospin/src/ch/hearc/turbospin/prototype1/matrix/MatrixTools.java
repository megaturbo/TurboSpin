
package ch.hearc.turbospin.prototype1.matrix;

import ch.hearc.turbospin.prototype1.mathtools.Matrix;
import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

final public class MatrixTools
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	private MatrixTools()
		{
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public static Matrix createRotationMatrix(double alpha, double beta, double gamma)
		{
		Matrix rotationRzMatrix = createRotationRzMatrix(alpha);
		Matrix rotationRyMatrix = createRotationRyMatrix(beta);
		Matrix rotationRxMatrix = createRotationRxMatrix(gamma);

		return rotationRzMatrix.times(rotationRyMatrix.times(rotationRxMatrix));

		}

	public static Matrix createRotationRzMatrix(double alpha)
		{
		Matrix matrixZ = new Matrix(3);
		matrixZ.setValue(0, 0, Math.cos(alpha));
		matrixZ.setValue(0, 1, Math.sin(alpha));
		matrixZ.setValue(0, 2, 0);

		matrixZ.setValue(1, 0, -Math.sin(alpha));
		matrixZ.setValue(1, 1, Math.cos(alpha));
		matrixZ.setValue(1, 2, 0);

		matrixZ.setValue(2, 0, 0);
		matrixZ.setValue(2, 1, 0);
		matrixZ.setValue(2, 2, 1);
		return matrixZ;
		}

	public static Matrix createRotationRyMatrix(double beta)
		{
		Matrix matrixY = new Matrix(3);
		matrixY.setValue(0, 0, Math.cos(beta));
		matrixY.setValue(0, 1, 0);
		matrixY.setValue(0, 2, -Math.sin(beta));

		matrixY.setValue(1, 0, 0);
		matrixY.setValue(1, 1, 1);
		matrixY.setValue(1, 2, 0);

		matrixY.setValue(2, 0, Math.sin(beta));
		matrixY.setValue(2, 1, 0);
		matrixY.setValue(2, 2, Math.cos(beta));
		return matrixY;
		}

	public static Matrix createRotationRxMatrix(double gamma)
		{
		Matrix matrixX = new Matrix(3);
		matrixX.setValue(0, 0, 1);
		matrixX.setValue(0, 1, 0);
		matrixX.setValue(0, 2, 0);

		matrixX.setValue(1, 0, 0);
		matrixX.setValue(1, 1, Math.cos(gamma));
		matrixX.setValue(1, 2, Math.sin(gamma));

		matrixX.setValue(2, 0, 0);
		matrixX.setValue(2, 1, -Math.sin(gamma));
		matrixX.setValue(2, 2, Math.cos(gamma));
		return matrixX;
		}

	public static Vector3D rotate(Vector3D object, Matrix rotationMatrix)
		{
		return rotationMatrix.times(object);
		}

	public static Point3D rotate(Point3D object, Matrix rotationMatrix)
		{
		return rotationMatrix.times(object);
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	}
