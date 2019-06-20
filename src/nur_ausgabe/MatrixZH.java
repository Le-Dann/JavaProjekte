package nur_ausgabe;
//class used to print pattern of Os and As in rows of 9. 
//Each row gains sequentially one less O and is replaced by one more A.
public class MatrixZH {

	public static void main(String[] args) {
		int i,a;
		for(a=1;a<10;a++)
		{
			for(i=1;i<10;i++)
			{
				if(a>=i)
				{
					System.out.print("A");
				}
				else
				{
					System.out.print("O");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
