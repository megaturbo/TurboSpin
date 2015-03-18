
package ch.hearc.turbospin.prototype1.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.hearc.turbospin.prototype1.Vector3D;
import ch.hearc.turbospin.prototype1.quaternion.Quaternion;

public class TestQuaternion
	{

	@Before
	public void before()
		{
		// rien
		}

	@After
	public void after()
		{
		// rien
		}

	@Test
	public void test()
		{
		Double alpha = Math.PI / 2;
		Vector3D v = new Vector3D(3.0, -7.0, 5.0);

		Quaternion q = Quaternion.createRotationQuaternion(alpha, v);

		Vector3D vp = new Vector3D(2.0, 4.0, 5.0);

		Quaternion p = new Quaternion(0.0, vp);

		Quaternion p2Empirique = q.multiply(p).multiply(q.conjugate());

		Quaternion p2Theorique = new Quaternion(0.0, -5.9286, -0.801833, 3.03459);

		System.out.println(p2Empirique);
		System.out.println(p2Theorique);
		Assert.assertTrue(p2Empirique.isEqualTo(p2Theorique));

		}
	}
