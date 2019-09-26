package NyAccountKlassen;

public class TestAccount {

	public static void main(String[] args) {
		
		Account myAccount = new Account("Anders", 1, 1000);
		Account.setAnnualInterestRate(5.5);
		
 		myAccount.deposit(30, "Allowance");
		myAccount.deposit(40, "Lottery prize");
		myAccount.deposit(50, "Grandpa's gift");
		myAccount.withdraw(5, "Ice creams");
		myAccount.withdraw(4, "Scratch card");
		myAccount.withdraw(2, "Bus ticket");
		
		System.out.printf("Name: %s \nAnnual interest rate: %s \nBalance: %s \n\n", myAccount.getName(), Account.getAnnualInterestRate(), myAccount.getBalance());
		System.out.printf("Date   \t\t    Type\tAmount \t \tBalance \tDescription\n");
		
		for (Transaction bufferTransaction : myAccount.getTransactions())
			System.out.printf("%s \n", bufferTransaction);
		
	}

}
