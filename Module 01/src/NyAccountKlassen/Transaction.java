package NyAccountKlassen;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private double amount, balance;
	private String description;
	private Date date;
	private char type;	
	
	// 'W' for uttak og 'D' for innskudd
	public Transaction(char type, double amount, double balance, String description) {
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
		this.date = new Date();
	}
	
	public char getType() {
		return type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public double getBalance() {
		return balance;
	}
		
	public String getDescription() {
		return description;
	}
	
	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String stringDate = dateFormat.format(date);
		return String.format("%s %s\t\t%s\t\t%s\t\t%s", stringDate, type, amount, balance, description);
	}
	
}
