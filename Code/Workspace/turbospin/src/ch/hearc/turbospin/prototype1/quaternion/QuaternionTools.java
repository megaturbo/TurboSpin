
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.mathtools.Point3D;
import ch.hearc.turbospin.prototype1.mathtools.Vector3D;
import ch.hearc.turbospin.prototype1.mathtools.Vertex3D;

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
	 * Rotates the given vector by the given quaternion
	 * @param vector
	 * @param rot
	 */
	public static Vector3D rotation(Vector3D vector, Quaternion rot)
		{
		Quaternion p = new Quaternion(0.0, vector);

		Quaternion rotConj = rot.conjugate();

		p = p.multiplyLeft(rot).multiplyRight(rotConj);

		return p.getVector();
		}

	/**
	 * Rotates the given point by the given quaternion
	 * @param point
	 * @param rot
	 */
	public static Point3D rotation(Point3D point, Quaternion rot)
		{
		Vector3D output = rotation(new Vector3D(point.getX(), point.getY(), point.getZ()), rot);
		return new Point3D(output.getA(), output.getB(), output.getC());
		}

	/**
	 * Rotates the given point around the given axis by angle theta
	 * @param point
	 * @param theta
	 * @param axisVector
	 */
	public static Point3D rotation(Point3D point, double theta, Vector3D axisVector)
		{
		Quaternion q = QuaternionTools.createRotationQuaternion(theta, axisVector);
		return rotation(point, q);
		}

	/**
	 * Rotates the given vector around the given axis by angle theta
	 * @param vector
	 * @param theta
	 * @param axisVector
	 */
	public static Vector3D rotation(Vector3D vector, double theta, Vector3D axisVector)
		{
		Quaternion q = QuaternionTools.createRotationQuaternion(theta, axisVector);
		return rotation(vector, q);
		}

	/**
	 * Rotates the given vertex by the given quaternion
	 * @param vertex
	 * @param q
	 */
	public static Vertex3D rotation(Vertex3D vertex, Quaternion q)
		{
		Vertex3D output = new Vertex3D(vertex);
		output.setA(rotation(vertex.getA(), q));
		output.setB(rotation(vertex.getB(), q));
		return output;
		}

	/**
	 * rotates the given vertex around the given axis by angle theta
	 * @param vertex
	 * @param theta
	 * @param axisVector
	 */
	public static Vertex3D rotation(Vertex3D vertex, double theta, Vector3D axisVector)
		{
		Quaternion q = QuaternionTools.createRotationQuaternion(theta, axisVector);
		return rotation(vertex, q);
		}
	}
