package Bank;

/* exit status(3) = Bank balance has changed */

public class BankTest {
    private static final boolean DEBUG = true;
    private static final int ACCOUNT_AMMOUNT = 10;
    private static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        // Testing goes here
        Bank bank = new Bank(ACCOUNT_AMMOUNT, INITIAL_BALANCE, DEBUG);
    }
}
