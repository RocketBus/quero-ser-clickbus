package org.example.service;


import java.util.ArrayList;
import java.util.List;

public class CashService {

    public List<WithdrawResponse> processWithdraw(int withdraw) throws Exception {
        List<Integer> notes = createNotes();

        if (withdraw > 0 && withdraw % 10 == 0) {
            return processWithdrawInternal(withdraw, notes);
        } else {
            throw new Exception("Valor incorreto! Insira um valor válido.");
        }
    }

    private List<Integer> createNotes() {
        List<Integer> notes = new ArrayList<>();
        notes.add(100);
        notes.add(50);
        notes.add(20);
        notes.add(10);
        return notes;
    }

    private List<WithdrawResponse> processWithdrawInternal(int withdraw, List<Integer> notes) {
        int amountNotes;
        List<WithdrawResponse> withdrawResponses = new ArrayList<>();
        for (int note : notes) {
            amountNotes = withdraw / note;
            withdraw = withdraw % note;

            if (amountNotes > 0) {
                WithdrawResponse withdrawResponse = new WithdrawResponse(amountNotes, note);
                withdrawResponses.add(withdrawResponse);
            }
        }
        return withdrawResponses;
    }
}
