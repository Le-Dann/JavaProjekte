package nur_ausgabe;
//class used to print pattern of Os and As in rows of 9. 
//Output is set so that the As form an X pattern amongst the Os.
public class MatrixX 
{
	public static void main(String[] args) 
	{
		int min = 1;
		int max = 9;
		int i,a;
		
		for(a=1; a<10;a++)
		{
		for(i=1; i<10;i++)
		{
			if(i== min || i == max)
			{
				System.out.print("A");
			}
			else
			{
				System.out.print("O");
			}
		}
	    min++;
		max--;
		System.out.println();
		}
	}

}
