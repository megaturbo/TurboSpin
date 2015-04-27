
package ch.hearc.turbospin.prototype1.matrix;

import ch.hearc.turbospin.prototype1.mathtools.Matrix;

public class UseMatrix
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

		Matrix test = new Matrix(3);

		Matrix test2 = new Matrix(3);

		Matrix test3 = new Matrix(3, 1);

		int val = 0;

		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				test.setValue(i, j, val++);
				}
			}
		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				if (j != 2)
					{
					System.out.print(test.getValue(i, j) + " ");
					}
				else
					{
					System.out.print(test.getValue(i, j) + "\n");
					}
				}
			}
		System.out.println("\n " + val + "\n");

		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				test2.setValue(i, j, val--);
				}
			}
		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				if (j != 2)
					{
					System.out.print(test2.getValue(i, j) + " ");
					}
				else
					{
					System.out.print(test2.getValue(i, j) + "\n");
					}
				}
			}
		System.out.println("\n " + val + "\n");

		test3 = test.times(test2);


		for(int i = 0; i < 3; i++)
			{
			for(int j = 0; j < 3; j++)
				{
				if (j != 2)
					{
					System.out.print(test3.getValue(i, j) + " ");
					}
				else
					{
					System.out.print(test3.getValue(i, j) + "\n");
					}
				}
			}

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
