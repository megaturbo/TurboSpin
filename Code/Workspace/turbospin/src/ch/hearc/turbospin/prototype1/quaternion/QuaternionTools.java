
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

final public class QuaternionTools
	{

	//private because static class
	private QuaternionTools()
		{
		}

	/**
	 * Creates a Quaternion containing the rotation of theta around the axis given
	 * @param theta
	 * @param axisVector
	 * @return
	 */
	public static Quaternion createRotationQuaternion(double theta, Vector3D axisVector)
		{
		Vector3D tmp = new Vector3D(axisVector);
		tmp.normalize();
		tmp.multiply(Math.sin(theta / 2.0));
		return new Quaternion(Math.cos(theta / 2.0), tmp);
		}

	/**
	 * Rotates the given point by the given quaternion
	 * @param vp
	 * @param rot
	 */
	public static void rotation(Vector3D vp, Quaternion rot)
		{
		Quaternion p = new Quaternion(0.0, vp);

		Quaternion rotInverse = new Quaternion(rot);
		rotInverse.conjugate();

		p.multiplyLeft(rot);
		p.multiplyRight(rotInverse);

		vp.set(p.getVector());
		}

	/**
	 * Rotates the given point around the given axis by angle theta
	 * @param vp
	 * @param theta
	 * @param axisVector
	 */
	public static void rotation(Vector3D vp, double theta, Vector3D axisVector)
		{
		Quaternion q = QuaternionTools.createRotationQuaternion(theta, axisVector);
		rotation(vp, q);
		}
	}
