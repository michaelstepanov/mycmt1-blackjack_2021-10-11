package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBetPayoffTest {


    @Test
    void testNewGameBalanceIsZero() {
        GameWallet gameWallet = new GameWallet();
        int result = gameWallet.playerBalance();
        assertThat(result).isZero();
    }

    @Test
    void testPlayerWith100Bets50ThenBalanceIs150() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenBalanceIs150() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerWins();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith100Bets50AndLosesThenBalanceIs50() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerLoses();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bets50AndLosesAndLosesAgainThenBalanceIs50() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerLoses();
        gameWallet.playerLoses();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bets50AndLosesThenWinsThenBalanceIs50() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerLoses();
        gameWallet.playerWins();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenLosesThenBalanceIs50() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerWins();
        gameWallet.playerLoses();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith100Bets50AndWinsBets50ThenWinsAgain() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);

        gameWallet.playerWins();
        gameWallet.playerBets(50);
        gameWallet.playerWins();

        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50 + 100 - 50 + 100);
    }

    @Test
    void testPlayerWith100Bets50AndPushes() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);
        gameWallet.playerPushes();
        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50 + 50);
    }

    @Test
    void testPlayerWith100Bets50AndPushesAndThenWins() {
        GameWallet gameWallet = new GameWallet();
        gameWallet.playerDeposits(100);
        gameWallet.playerBets(50);
        gameWallet.playerPushes();
        gameWallet.playerWins();
        assertThat(gameWallet.playerBalance()).isEqualTo(100 - 50 + 50);
    }
}
