
package ch.hearc.turbospin.prototype1.matrix;

import java.util.Vector;



public class UseRotationMatrix
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
		Vector<Double> xAxis = new Vector<Double>();
		xAxis.add((double)2);
		xAxis.add((double)0);
		xAxis.add((double)0);
		Vector<Double> yAxis = new Vector<Double>();
		yAxis.add((double)0);
		yAxis.add((double)2);
		yAxis.add((double)0);
		Vector<Double> zAxis = new Vector<Double>();
		zAxis.add((double)0);
		zAxis.add((double)0);
		zAxis.add((double)2);

		RotationMatrix identity = new RotationMatrix();
		RotationMatrix xAxisRotation = new RotationMatrix(90,xAxis);
		RotationMatrix yAxisRotation = new RotationMatrix(90,yAxis);
		RotationMatrix zAxisRotation = new RotationMatrix(90,zAxis);

		System.out.println(identity.toString()+"\n");
		System.out.println(xAxisRotation.toString()+"\n");
		System.out.println(yAxisRotation.toString()+"\n");
		System.out.println(zAxisRotation.toString()+"\n");

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}

