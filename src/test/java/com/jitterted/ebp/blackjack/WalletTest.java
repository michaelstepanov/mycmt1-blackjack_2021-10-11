package com.jitterted.ebp.blackjack;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletTest {

    //Z - Zero
    //O - One
    //M - Many
    //B - Boundary
    //I - Interface
    //E - Exceptions
    //S - (Simple)
    @Test
    void testNewWalletIsEmpty() { //Zero Condition
        Wallet wallet = new Wallet();
        boolean result = wallet.isEmpty();
        assertThat(result).isTrue();
    }

    @Test
    void testAddMoneyWalletIsNotEmpty() { //Zero Condition
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        assertThat(wallet.isEmpty()).isFalse();
    }

    @Test
    void testANewWalletHasZeroBalance() { //Zero Condition
        Wallet wallet = new Wallet();
        int result = wallet.balance();
        assertThat(result).isZero();
    }

    @Test
    void testWalletWithAdd15ShouldReturn15Balance() { //Zero Condition
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        int result = wallet.balance();
        assertThat(result).isEqualTo(15);
    }

    @Test
    void testWalletWithAdd15Add15ShouldReturn30Balance() { //Zero Condition
        Wallet wallet = new Wallet();
        wallet.addMoney(15);
        wallet.addMoney(15);
        int result = wallet.balance();
        assertThat(result).isEqualTo(30);
    }


    @Test
    void testWalletNoNegativeAddMoney() { //Zero Condition
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.addMoney(-15))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAdd50Betting20ReducesBalance() {
        Wallet wallet = new Wallet();
        wallet.addMoney(50);
        wallet.bet(20);
        int result = wallet.balance();
        assertThat(result).isEqualTo(30);
    }

    @Test
    void testAdd50Betting60ShouldThrowIllegalArgumentException() {
        Wallet wallet = new Wallet();
        wallet.addMoney(50);
        assertThatThrownBy(() -> wallet.bet(60))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testWalletNoNegativeBet() { //Zero Condition
        Wallet wallet = new Wallet();
        wallet.addMoney(10);
        assertThatThrownBy(() -> wallet.bet(-15))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testMaxInteger() {
    }
}
