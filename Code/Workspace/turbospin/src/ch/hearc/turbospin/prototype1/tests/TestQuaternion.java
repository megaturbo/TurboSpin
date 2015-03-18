
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
	public void testProduct()
		{
		Quaternion q = new Quaternion(1.0, 2.0, 3.0, 4.0);
		Quaternion p = new Quaternion(5.0, 6.0, 7.0, 8.0);

		q.multiplyRight(p);

		Quaternion qTheorique = new Quaternion(-60.0, 12.0, 30.0, 24.0);

		Assert.assertTrue(q.isEqualTo(qTheorique));
		}

	@Test
	public void testRotation()
		{
		Double alpha = Math.PI / 2;
		Vector3D v = new Vector3D(3.0, -7.0, 5.0);

		Quaternion q = Quaternion.createRotationQuaternion(alpha, v);

		Vector3D vp = new Vector3D(2.0, 4.0, 5.0);

		Quaternion p2Empirique = new Quaternion(0.0, vp);

		Quaternion qI = new Quaternion(q);
		qI.conjugate();

		p2Empirique.multiplyLeft(q);
		p2Empirique.multiplyRight(qI);

		Quaternion p2Theorique = new Quaternion(0.0, -5.9286, -0.801833, 3.03459);

		Assert.assertTrue(p2Empirique.isEqualTo(p2Theorique));

		}
	}
