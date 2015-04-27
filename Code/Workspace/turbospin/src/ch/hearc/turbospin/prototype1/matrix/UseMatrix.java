
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
					if(j!=2) {
					System.out.print(test.getValue(i, j) + " ");}
					else {System.out.print(test.getValue(i, j) + "\n");}
					}
				}



		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
