package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavingAccountTest {


    @Test
    public void testConstructor() {
        SavingAccount account = new SavingAccount(5000, 1000, 10_000, 10);
        Assertions.assertEquals(5000, account.getBalance());
        Assertions.assertEquals(1000, account.getMinBalance());
        Assertions.assertEquals(10_000, account.getMaxBalance());
        Assertions.assertEquals(10, account.getRate());
    }

    @Test
    public void testConstructorInitialBalanceAboveMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(12_000, 1000, 10_000, 10);
        });
    }
    @Test // дописал
    public void testConstructorInitialBalanceBelowMinBalance(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(500, 1000, 10_000, 10);
        });
    }

    @Test
    public void testConstructorNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(5000, 1000, 10_000, -10);
        });
    }

    @Test
    public void testPayAmountBetweenMinAndMaxBalance() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        Assertions.assertFalse(account.pay(4_500));
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void testPay() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.pay(2_000);
        Assertions.assertTrue(result);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void testPayNegativeAmount() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.pay(-1_000);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void testPayBelowMinimumBalance() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.pay(4_500);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void testPayOverMaximumBalance() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.pay(12_000);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void testPayZeroAmount() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.pay(0);
        Assertions.assertFalse(result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void testAddPositiveBalance() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.add(1_500);
        Assertions.assertEquals(true, result);
        Assertions.assertEquals(6_500, account.getBalance());
    }

    @Test
    public void testAddNegativeAmount() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.add(-1_000);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void testAddAboveMaximumBalance() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        boolean result = account.add(7_000);
        Assertions.assertEquals(false, result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        int result = account.yearChange();
        Assertions.assertEquals(500, result);
    }
    @Test
    void testYearChangeZeroRate() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 10_000, 10);
        account.setRate(0);
        assertEquals(0, account.yearChange());
    }


}
