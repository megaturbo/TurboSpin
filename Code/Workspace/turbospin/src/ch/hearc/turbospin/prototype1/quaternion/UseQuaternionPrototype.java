
package ch.hearc.turbospin.prototype1.quaternion;

import ch.hearc.turbospin.prototype1.mathtools.Vector3D;

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
		Vector3D axis = new Vector3D(1.0, 0.0, 0.0);

		Vector3D v = new Vector3D(1.0, 1.0, 1.0);

		Vector3D vp = QuaternionTools.rotation(v, alpha, axis);

		System.out.println("Point after rotation p': " + vp);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
