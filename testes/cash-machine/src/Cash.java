import java.util.Scanner;

public class Cash {
	
	public static void main(String[] args) {
		Scanner toRead = new Scanner(System.in);
		int withdraw;
		
		System.out.println("Digite o valor a ser sacado");
		withdraw = toRead.nextInt();
		
		int amountNotes = 0;
		int[] notes =  {100, 50, 20, 10};
		
		if(withdraw > 0 && withdraw % 10 == 0) {
			
		} else {
			
		}
	}
}
	