public class BankTest {
  private static final int NACCOUNTS = 10;
  private static final int INITIAL_BALANCE = 10000;

  public static void main(String[] args) {
    Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
    int i;
    for (i = 0; i < NACCOUNTS; i++) {
      TransferThread t = new TransferThread(b, i,
          INITIAL_BALANCE);
      t.setPriority(Thread.NORM_PRIORITY + i % 2);
      t.start();
    }
  }
}

class Bank {
  private static final int NTEST = 10000;
  private final int[] accounts;
  private long numberOfTransactions = 0;

  public Bank(int n, int initialBalance) {
    accounts = new int[n];
    int i;
    for (i = 0; i < accounts.length; i++)
      accounts[i] = initialBalance;
    numberOfTransactions = 0;
  }

  public void transfer(int from, int to, int amount) {
    accounts[from] -= amount;
    accounts[to] += amount;
    numberOfTransactions++;
    if (numberOfTransactions % NTEST == 0) test();
  }

  public void test() {
    int sum = 0;

    for (int account : accounts) sum += account;

    System.out.println("Transactions:" + numberOfTransactions + " Sum: " + sum);
  }

  public int size() {
    return accounts.length;
  }
}

class TransferThread extends Thread {
  private Bank bank;
  private int fromAccount;
  private int maxAmount;
  private static final int REPS = 1000;

  public TransferThread(Bank b, int from, int max) {
    bank = b;
    fromAccount = from;
    maxAmount = max;
  }

  public void run() {
    try {
      while (!interrupted()) {
        for (int i = 0; i < REPS; i++) {
          int toAccount = ( int ) (bank.size() * Math.random());
          int amount = ( int ) (maxAmount * Math.random() / REPS);
          bank.transfer(fromAccount, toAccount, amount);
          sleep(1);
        }
      }
    } catch (InterruptedException ignored) {
    }
  }
}

class Account {
  private int balance;
  private int accountNumber;

  public Account(int accountNumber, int balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
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
}
