import java.util.Scanner;

public class Cash {

	public static void main(String[] args) {
		int withdraw;

		withdraw = readValue();
		
		int amountNotes = 0;
		int[] notes =  {100, 50, 20, 10};
		
		if(withdraw > 0 && withdraw % 10 == 0) {
			for(int note : notes) {
				amountNotes = withdraw / note;
				withdraw = withdraw % note;
				
				if(amountNotes > 0) {
					System.out.println("Total de " + amountNotes + " cedula de R$" + note);
				}
			}
		} else {
			System.out.println("Valor incorreto! Insira um valor válido.");
		}
	}

	private static int readValue() {
		Scanner toRead = new Scanner(System.in);
		System.out.println("Digite o valor a ser sacado:");
		return toRead.nextInt();
	}
}