package com.jitterted.ebp.blackjack;

public class GameWallet {
    private int balance;
    private int bet;

    public GameWallet() {
    }

    public int playerBalance() {
        return balance;
    }

    public void playerDeposits(int amount) {
        this.balance = amount;
    }

    public void playerBets(int amount) {
        this.bet = amount;
        this.balance -= amount;
    }

    public void playerWins() {
        this.balance += bet * 2;
        resetBet();
    }

    void resetBet() {
        this.bet = 0;
    }

    @SuppressWarnings({"ConstantConditions", "PointlessArithmeticExpression"})
    public void playerLoses() {
        this.balance += bet * 0;
        resetBet();
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    public void playerPushes() {
        this.balance += bet * 1;
        resetBet();
    }
}