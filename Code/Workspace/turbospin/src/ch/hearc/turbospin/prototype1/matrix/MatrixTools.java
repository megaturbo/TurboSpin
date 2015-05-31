
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
		Matrix Rz = new Matrix(3);
		Rz.setValue(0, 0, Math.cos(alpha));
		Rz.setValue(1, 0, Math.sin(alpha));
		Rz.setValue(2, 0, 0);

		Rz.setValue(0, 1, -Math.sin(alpha));
		Rz.setValue(1, 1, Math.cos(alpha));
		Rz.setValue(2, 1, 0);

		Rz.setValue(0, 2, 0);
		Rz.setValue(1, 2, 0);
		Rz.setValue(2, 2, 1);
		return Rz;
		}

	public static Matrix createRotationRyMatrix(double beta)
		{
		Matrix Ry = new Matrix(3);
		Ry.setValue(0, 0, Math.cos(beta));
		Ry.setValue(1, 0, 0);
		Ry.setValue(2, 0, -Math.sin(beta));

		Ry.setValue(0, 1, 0);
		Ry.setValue(1, 1, 1);
		Ry.setValue(2, 1, 0);

		Ry.setValue(0, 2, Math.sin(beta));
		Ry.setValue(1, 2, 0);
		Ry.setValue(2, 2, Math.cos(beta));
		return Ry;
		}

	public static Matrix createRotationRxMatrix(double gamma)
		{
		Matrix Rx = new Matrix(3);
		Rx.setValue(0, 0, 1);
		Rx.setValue(0, 1, 0);
		Rx.setValue(0, 2, 0);

		Rx.setValue(1, 0, 0);
		Rx.setValue(1, 1, Math.cos(gamma));
		Rx.setValue(1, 2, Math.sin(gamma));

		Rx.setValue(2, 0, 0);
		Rx.setValue(2, 1, -Math.sin(gamma));
		Rx.setValue(2, 2, Math.cos(gamma));
		return Rx;
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
