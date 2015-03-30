
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
		Vector3D tmp = axisVector.normalize().multiply(Math.sin(theta / 2.0));
		return new Quaternion(Math.cos(theta / 2.0), tmp);
		}

	/**
	 * Rotates the given point by the given quaternion
	 * @param vp
	 * @param rot
	 */
	public static Vector3D rotation(Vector3D vp, Quaternion rot)
		{
		Quaternion p = new Quaternion(0.0, vp);

		Quaternion rotConj = rot.conjugate();

		p = p.multiplyLeft(rot).multiplyRight(rotConj);

		return p.getVector();
		}

	/**
	 * Rotates the given point around the given axis by angle theta
	 * @param vp
	 * @param theta
	 * @param axisVector
	 */
	public static Vector3D rotation(Vector3D vp, double theta, Vector3D axisVector)
		{
		Quaternion q = QuaternionTools.createRotationQuaternion(theta, axisVector);
		return rotation(vp, q);
		}
	}
