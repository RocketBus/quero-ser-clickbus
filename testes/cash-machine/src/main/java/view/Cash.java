package view;

import model.WithdrawResponse;
import service.CashService;

import java.util.List;
import java.util.Scanner;

public class Cash {

    //Front end --> Comunicação com o seu usuário
    // Segunda classe: Processador --> As regras de verdade
    public static void main(String[] args) throws Exception {
        int withdraw;
        CashService cashService = new CashService();

        withdraw = readValue();

        try {
            List<WithdrawResponse> withdrawResponses = cashService.processWithdraw(withdraw);
            for (WithdrawResponse withdrawResponse : withdrawResponses) {
                System.out.println("Total da " + withdrawResponse.getAmountNotes() + " cedula de R$" + withdrawResponse.getNote());
            }
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private static int readValue() {
        Scanner toRead = new Scanner(System.in);
        System.out.println("Digite o valor a ser sacado:");
        return toRead.nextInt();
    }
}