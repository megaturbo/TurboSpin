
package ch.hearc.turbospin.prototype1.matrix;

import ch.hearc.turbospin.prototype1.main.jframe.utils.Hexacodes;
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
		System.out.println(Math.acos(rotationRzMatrix.getValue(0, 0)));
		Matrix rotationRyMatrix = createRotationRyMatrix(beta);
		System.out.println(Math.acos(rotationRyMatrix.getValue(0, 0)));
		Matrix rotationRxMatrix = createRotationRxMatrix(gamma);
		System.out.println(Math.acos(rotationRxMatrix.getValue(1, 1)));

		Matrix temp = rotationRzMatrix.times(rotationRyMatrix.times(rotationRxMatrix));

		System.out.println(getAlpha(temp));
		System.out.println(getBeta(temp));
		System.out.println(getGamma(temp));

		return temp;
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
		Rx.setValue(1, 0, 0);
		Rx.setValue(2, 0, 0);

		Rx.setValue(0, 1, 0);
		Rx.setValue(1, 1, Math.cos(gamma));
		Rx.setValue(2, 1, Math.sin(gamma));

		Rx.setValue(0, 2, 0);
		Rx.setValue(1, 2, -Math.sin(gamma));
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

	public static double getAlpha(Matrix rotationMatrix)
		{
		//		return Math.atan2(rotationMatrix.getValue(1, 0), rotationMatrix.getValue(0, 0));

		double theta1 = -Math.asin(rotationMatrix.getValue(2, 0));
		double theta2 = Math.PI - theta1;
		return Math.atan2(rotationMatrix.getValue(1, 0) / Math.cos(theta2), rotationMatrix.getValue(0, 0) / Math.cos(theta2));
		}

	public static double getBeta(Matrix rotationMatrix)
		{
		//		return Math.atan2(-rotationMatrix.getValue(2, 0), Math.sqrt(Math.pow(rotationMatrix.getValue(2, 1), 2) + Math.pow(rotationMatrix.getValue(2, 2), 2)));
		//		return -Math.asin(rotationMatrix.getValue(2, 0));

		double theta1 = -Math.asin(rotationMatrix.getValue(2, 0));
		return Math.PI - theta1;
		}

	public static double getGamma(Matrix rotationMatrix)
		{
		//		double beta = getBeta(rotationMatrix);
		//		return Math.atan2(rotationMatrix.getValue(2, 1) / Math.cos(beta), rotationMatrix.getValue(2, 2) / Math.cos(beta));

		double theta1 = -Math.asin(rotationMatrix.getValue(2, 0));
		double theta2 = Math.PI - theta1;
		return Math.atan2(rotationMatrix.getValue(2, 1) / Math.cos(theta2), rotationMatrix.getValue(2, 2) / Math.cos(theta2));
		}

	public static void getAngles(Matrix rotationMatrix)
		{
		if (rotationMatrix.getValue(2, 0) != 1 || rotationMatrix.getValue(2, 0) != -1)
			{
			double beta1 = -Math.asin(rotationMatrix.getValue(2, 0));
			double beta2 = Math.PI - beta1;
			double gamma1 = Math.atan2(rotationMatrix.getValue(2, 1) / Math.cos(beta1), rotationMatrix.getValue(2, 2) / Math.cos(beta1));
			double gamma2 = Math.atan2(rotationMatrix.getValue(2, 1) / Math.cos(beta2), rotationMatrix.getValue(2, 2) / Math.cos(beta2));
			double alpha1 = Math.atan2(rotationMatrix.getValue(1, 0) / Math.cos(beta1), rotationMatrix.getValue(0, 0) / Math.cos(beta1));
			double alpha2 = Math.atan2(rotationMatrix.getValue(1, 0) / Math.cos(beta2), rotationMatrix.getValue(0, 0) / Math.cos(beta2));
			System.out.println(Hexacodes.ALPHA_LOWER + "1: " + alpha1 + " " + Hexacodes.ALPHA_LOWER + "2: " + alpha2 + " ");
			System.out.println(Hexacodes.BETA_LOWER + "1: " + beta1 + " " + Hexacodes.BETA_LOWER + "2: " + beta2 + " ");
			System.out.println(Hexacodes.GAMMA_LOWER + "1: " + gamma1 + " " + Hexacodes.GAMMA_LOWER + "2: " + gamma2);
			}
		else
			{
			double phi = 0.0;
			if (rotationMatrix.getValue(2, 0) == -1.0)
				{
				double beta = Math.PI / 2;
				double psi = phi + Math.atan2(rotationMatrix.getValue(0, 1), rotationMatrix.getValue(0, 2));
				}
			else
				{
				double beta = -Math.PI / 2;
				double psi = -phi + Math.atan2(-rotationMatrix.getValue(0, 1), -rotationMatrix.getValue(0, 2));
				}
			}
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
