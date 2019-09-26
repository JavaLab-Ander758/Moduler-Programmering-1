package Bank;

import java.util.Random;


public class AccountThreads implements Runnable {
    private Bank bank;
    private boolean debug;
    private int accountIndex;
    private int maxTransferAmount;
    private Random random;

    public AccountThreads (Bank bank, int accountIndex, int maxTransferAmount, boolean debug) {
        this.bank = bank;
        this.accountIndex = accountIndex;
        this.maxTransferAmount = maxTransferAmount;
        this.debug = debug;
    }


    @Override
    public void run() {
        int randomAccountindex = -1;
        while (randomAccountindex != accountIndex)
            randomAccountindex = random.nextInt(10-1); // 10-1?

        bank.transfer(accountIndex, randomAccountindex, random.nextInt(maxTransferAmount + 1) + 1);
    }
}
