import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cash {

    public static void main(String[] args) throws Exception {
        int withdraw;

        withdraw = readValue();

        List<Integer> notes = createNotes();

        if (withdraw > 0 && withdraw % 10 == 0) {
            processWithdraw(withdraw, notes);
        } else {
            throw new Exception("Valor incorreto! Insira um valor válido.");
        }
    }

    private static int readValue() {
        Scanner toRead = new Scanner(System.in);
        System.out.println("Digite o valor a ser sacado:");
        return toRead.nextInt();
    }

    private static void processWithdraw(int withdraw, List<Integer> notes) {
        int amountNotes;
        for (int note : notes) {
            amountNotes = withdraw / note;
            withdraw = withdraw % note;

            if (amountNotes > 0) {
                System.out.println("Total de " + amountNotes + " cedula de R$" + note);
            }
        }
    }

    private static List<Integer> createNotes() {
        List<Integer> notes = new ArrayList<>();
        notes.add(100);
        notes.add(50);
        notes.add(20);
        notes.add(10);
        return notes;
    }
}