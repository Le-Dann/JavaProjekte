package nur_ausgabe;
//Prints the Fibonacci pattern
public class Fibonacci {

	public static void main(String[] args) {
		int num1,num2,num3;
		num1 = 1;
		num2 = 1;
		num3 = num1+num2;
		System.out.print(num1+" "+num2+ " ");
		while(num3 <150)
		{
			System.out.print(num3+" ");
			num1 = num2;
			num2 = num3;
			num3 = num1+num2;
			
		}
	}

}
