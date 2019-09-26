package Bank;

import java.util.concurrent.locks.Lock;

public class Bank {
    private static final int TO_STRING_FREQUENCY = 10;
    private static final int TEST_FREQUENCY = 10000; // Check if the sum of balances does not decrease for every 10000 transfer
    private Lock lock;
    private long transactionCount;
    private int deviationCount;
    private int initialBalance;
    private Account[] accounts;
    private boolean debug;
    private int testCount;

    public Bank(int accountAmount, int initialBalance, boolean debug) {
        this.accounts = new Account[accountAmount];
        this.initialBalance = initialBalance;
        this.debug = debug;
    }


    void transfer (int fromAccount, int toAccount, int amount) {
        // Transfers given amount of money from one account to the other
        if (accounts[fromAccount].getBalance() > amount) {
            accounts[fromAccount].withdraw(amount);
            accounts[toAccount].deposit(amount);
            transactionCount++;
        } //else

        if (transactionCount % TEST_FREQUENCY == 0)
            test();
    }
    private void test() {
        // Tests whether total balance matches proper value. Prints out number of transactions, balance and deviation count to console
        int currentEntireBalanceSum = 0;
        for (Account obj : accounts)
            currentEntireBalanceSum += obj.getBalance();

        if (initialBalance * numberOfAccounts() != currentEntireBalanceSum) {
            System.out.printf("\n--------------------\nNumber of transactions: %s\nBalance: %s\nDeviation count: %s\n" +
                    "Error percentage: %s", getTransactionCount(), currentEntireBalanceSum, getDeviationCount(), getErrorPercentage());
            System.exit(3);
        }
    }
    public String toString() {
        // Returns String representing this bank object.
        // if debug == true then return balance for each account
        String string = String.format("\ntransactionCount: %s\ndeviationCount: %s\ninitialBalance: %s\ntestCount: %s\n", transactionCount, deviationCount, initialBalance, testCount);
        if (debug)
            for (int i = 0; i < accounts.length; i++) {
                if (i == 0)
                    string += "debug was true, showing balances for all Account objects in Bank \n";
                string += String.format("Account[%s]=%s \n", i, accounts[i].getBalance());
            }
        return string;
    }

    private int numberOfAccounts() {
        return accounts.length; //-1?
    }
    private long getTransactionCount() {
        return transactionCount;
    }
    private int getDeviationCount() {
        return deviationCount;
    }
    private double getErrorPercentage() {
        return -1;
    }
}
