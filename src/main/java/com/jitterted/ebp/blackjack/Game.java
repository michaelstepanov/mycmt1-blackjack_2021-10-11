package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private int balance;
    private int bet;

    public static void main(String[] args) {
        Game game = new Game();
        displayWelcomeScreen();
        game.initialDeal();
        game.play();
        resetScreen();
    }

    private static void resetScreen() {
        System.out.println(ansi().reset());
    }

    private static void displayWelcomeScreen() {
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" Jitterted's")
                                   .fgBlack().a(" BlackJack"));
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRound();
        dealRound();
    }


    private void dealRound() {
        playerHand.deal(deck.draw());
        dealerHand.deal(deck.draw());
    }

    public void play() {
        boolean playerBusted = playerTurn();
        // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>=stand)
        if (!playerBusted) dealerTurn();
        displayFinalGameState();
        displayOutcome(playerBusted);
    }

    private void displayOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
            //playerLoses();
        } else if (dealerHand.isBust()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
            //playerWins();
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
            //playerWins();
        } else if (playerHand.pushes(dealerHand)) {
            System.out.println("Push: You tie with the Dealer. 💸");
            //playerPushes();
        } else {
            System.out.println("You lost to the Dealer. 💸");
            //playerLoses();
        }
    }

    private void dealerTurn() {
        while (dealerHand.value() <= 16) {
            dealerHand.deal(deck.draw());
        }
    }

    private boolean playerTurn() {
        // get Player's decision: hit until they stand, then they're done (or they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerStands(playerChoice)) {
                break;
            }
            if (playerHits(playerChoice)) {
                playerBusted = playerHits(playerBusted);
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
        return playerBusted;
    }

    private boolean playerHits(boolean playerBusted) {
        playerHand.deal(deck.draw());
        if (playerHand.isBust()) {
            playerBusted = true;
        }
        return playerBusted;
    }

    private boolean playerHits(String playerChoice) {
        return playerChoice.startsWith("h");
    }

    private boolean playerStands(String playerChoice) {
        return playerChoice.startsWith("s");
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        displayDealerFaceUpCard();

        // second card is the hole card, which is hidden
        displayBackOfCard();

        displayPlayerHand();
    }

    private void displayPlayerHand() {
        System.out.println();
        System.out.println("Player has: ");
        playerHand.display();
        System.out.println(" (" + playerHand.value() + ")");
    }

    private void displayDealerFaceUpCard() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.firstCard().display()); // first card is Face Up
    }

    private void displayBackOfCard() {
        System.out.print(
                ansi()
                        .cursorUp(7)
                        .cursorRight(12)
                        .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                        .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                        .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                        .a("└─────────┘"));
    }

    private void displayFinalGameState() {
        displayDealerHand();
        displayPlayerHand();
    }

    private void displayDealerHand() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.display();
        System.out.println(" (" + dealerHand.value() + ")");
    }

    public int playerBalance() { return balance;
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
    }
}
