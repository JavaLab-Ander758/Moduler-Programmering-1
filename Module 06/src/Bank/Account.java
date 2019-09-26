package Bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Account {
    private int balance;
    private int accountNumber;
    private Lock lock;
    private Condition lockCondition;

    public Account(int balance, int accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }


    void withdraw(int amount) {
        balance -= amount;
    }
    void deposit(int amount) {
        balance += amount;
    }

    int getAccountNumber() {
        return accountNumber;
    }
    public int getBalance() {
        return balance;
    }
    Lock getLock() {
        return lock;
    }
}