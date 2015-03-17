
package ch.hearc.turbospin.prototype1.quaternion;

import java.util.Vector;

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
		Vector<Double> v = new Vector<Double>();
		v.add(1.0);
		v.add(0.0);
		v.add(0.0);

		Quaternion q = Quaternion.createRotationQuaternion(alpha, v);

		Vector<Double> vp = new Vector<Double>();
		vp.add(1.0);
		vp.add(1.0);
		vp.add(1.0);

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
