package org.example.model;

public class WithdrawRequest {
    private int amount;

    public WithdrawRequest(int amount) {
        this.amount = amount;
    }
    public WithdrawRequest(){

    }

    public int getAmount() {
        return amount;
    }

    public void setAmountNotes(int amount) {
        this.amount = amount;
    }
}
