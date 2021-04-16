package org.example.model;

public class WithdrawResponse {
    private int amountNotes;
    private int note;

    public WithdrawResponse(int amountNotes, int note) {
        this.amountNotes = amountNotes;
        this.note = note;
    }

    public int getAmountNotes() {
        return amountNotes;
    }

    public void setAmountNotes(int amountNotes) {
        this.amountNotes = amountNotes;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
