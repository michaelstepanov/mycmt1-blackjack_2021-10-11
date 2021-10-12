package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class GameBetPayoffTest {


    @Test
    void testNewGameBalanceIsZero() {
       Game game = new Game();
       int result = game.playerBalance();
       assertThat(result).isZero();
    }

    @Test
    void testPlayerWith100Bets50ThenBalanceIs150() {
       Game game = new Game();
       game.playerDeposits(100);
       game.playerBets(50);

       assertThat(game.playerBalance()).isEqualTo(100 - 50);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenBalanceIs150() {
       Game game = new Game();
       game.playerDeposits(100);
       game.playerBets(50);

       game.playerWins();

       assertThat(game.playerBalance()).isEqualTo(100 - 50 + 100);
    }
}
