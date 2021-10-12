package com.jitterted.ebp.blackjack;

public class Wallet {
    private int balance;

    public Wallet() {
    }

    public boolean isEmpty() {
      return this.balance == 0;
    }

    public void addMoney(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
        this.balance += amount;
    }

    public int balance() {
        return balance;
    }

    public void bet(int amount) {
        if (amount < 0) throw new IllegalArgumentException();
        if (amount > balance) throw new IllegalArgumentException();
        this.balance -= amount;
    }
}
