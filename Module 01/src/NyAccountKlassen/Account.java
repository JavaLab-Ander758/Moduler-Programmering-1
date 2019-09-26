package NyAccountKlassen;

import java.util.ArrayList;

class Account {
	ArrayList<Transaction> transactions;
	private int id;
	private double balance;
	private static double annualInterestRate;
	private java.util.Date dateCreated;
	private String name;
	
	public Account() {
		dateCreated = new java.util.Date();
	}

	public Account(String name, int newId, double newBalance) {
		id = newId;
		balance = newBalance;
		dateCreated = new java.util.Date();
		this.name = name;
		transactions = new ArrayList<>();
	}

	public int getId() {
    return this.id;
  }

  public double getBalance() {
    return balance;
  }

  public static double getAnnualInterestRate() {
    return annualInterestRate;
  }

  public void setId(int newId) {
    id = newId;
  }

  public void setBalance(double newBalance) {
    balance = newBalance;
  }

  public static void setAnnualInterestRate(double newAnnualInterestRate) {
    annualInterestRate = newAnnualInterestRate;
  }

  public double getMonthlyInterest() {
    return balance * (annualInterestRate / 1200);
  }

  public java.util.Date getDateCreated() {
    return dateCreated;
  }
  
  public String getName() {
	  return name;
  }
  
  public ArrayList<Transaction> getTransactions() {
	  return transactions;
  }

  public void withdraw(double amount, String description) {
    balance -= amount;
    transactions.add(new Transaction('W', amount, balance, description));
  }

  public void deposit(double amount, String description) {
    balance += amount;
    transactions.add(new Transaction('D', amount, balance, description));
  }
}
