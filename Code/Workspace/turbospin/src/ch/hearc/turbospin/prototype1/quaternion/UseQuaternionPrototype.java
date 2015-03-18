
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.Vector3D;

public class UseQuaternionPrototype
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
		Double alpha = Math.PI / 3;
		Vector3D v = new Vector3D(1.0, 0.0, 0.0);

		Quaternion q = Quaternion.createRotationQuaternion(alpha, v);

		Vector3D vp = new Vector3D(1.0, 1.0, 1.0);

		Quaternion p = new Quaternion(0.0, vp);
		System.out.println("Rotation quaternion q: " + q);
		System.out.println("Point before rotation p : " + p.getVector());
		System.out.println();
		Quaternion rotP = q.multiply(p).multiply(q.conjugate());

		System.out.println("Point after rotation p': " + rotP.getVector());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
