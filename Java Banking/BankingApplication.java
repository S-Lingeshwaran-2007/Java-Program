// Abstract class defining common operations for all bank accounts
abstract class BankAccount {

    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Deposit method common to all accounts
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        balance += amount;
        System.out.println("Deposited: ₹" + amount);
        System.out.println("New Balance: ₹" + balance);
    }

    // Abstract methods - subclasses must implement these
    public abstract void withdraw(double amount);

    public abstract double calculateInterest();

    // Display account details
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolder);
        System.out.println("Balance        : ₹" + balance);
    }

    public double getBalance() {
        return balance;
    }
}


// Interface for additional loan-related services
interface LoanService {

    void applyForLoan(double amount);

    void calculateLoanInterest(double amount, double rate, int years);
}


// Savings Account
class SavingsAccount extends BankAccount implements LoanService {

    private final double interestRate = 4.0; // 4% annual interest
    private final double minimumBalance = 1000.0;

    public SavingsAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    // Overriding withdraw method
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        // Savings account must maintain minimum balance
        if (balance - amount < minimumBalance) {
            System.out.println(
                "Withdrawal denied! Savings account must maintain a minimum balance of ₹"
                + minimumBalance
            );
            return;
        }

        balance -= amount;

        System.out.println("Withdrawn: ₹" + amount);
        System.out.println("Remaining Balance: ₹" + balance);
    }

    // Overriding interest calculation
    @Override
    public double calculateInterest() {

        double interest = balance * interestRate / 100;

        System.out.println("Savings Account Interest: ₹" + interest);

        return interest;
    }

    // Implementing LoanService
    @Override
    public void applyForLoan(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid loan amount.");
            return;
        }

        // Simple eligibility condition
        if (balance >= 5000) {
            System.out.println(
                "Loan application approved for ₹" + amount
            );
        } else {
            System.out.println(
                "Loan application rejected. Minimum balance of ₹5000 required."
            );
        }
    }

    @Override
    public void calculateLoanInterest(double amount, double rate, int years) {

        double interest = (amount * rate * years) / 100;

        System.out.println("Loan Amount     : ₹" + amount);
        System.out.println("Interest Rate   : " + rate + "%");
        System.out.println("Loan Duration   : " + years + " years");
        System.out.println("Loan Interest   : ₹" + interest);
        System.out.println("Total Repayment : ₹" + (amount + interest));
    }
}


// Current Account
class CurrentAccount extends BankAccount implements LoanService {

    private final double interestRate = 2.0;
    private final double overdraftLimit = 5000.0;

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    // Current account allows overdraft
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        if (amount > balance + overdraftLimit) {
            System.out.println(
                "Withdrawal denied! Overdraft limit of ₹"
                + overdraftLimit + " exceeded."
            );
            return;
        }

        balance -= amount;

        System.out.println("Withdrawn: ₹" + amount);
        System.out.println("Remaining Balance: ₹" + balance);
    }

    // Current account interest calculation
    @Override
    public double calculateInterest() {

        double interest = balance * interestRate / 100;

        System.out.println("Current Account Interest: ₹" + interest);

        return interest;
    }

    // Implementing LoanService
    @Override
    public void applyForLoan(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid loan amount.");
            return;
        }

        if (balance >= 10000) {
            System.out.println(
                "Loan application approved for ₹" + amount
            );
        } else {
            System.out.println(
                "Loan application rejected. Minimum balance of ₹10000 required."
            );
        }
    }

    @Override
    public void calculateLoanInterest(double amount, double rate, int years) {

        double interest = (amount * rate * years) / 100;

        System.out.println("Loan Amount     : ₹" + amount);
        System.out.println("Interest Rate   : " + rate + "%");
        System.out.println("Loan Duration   : " + years + " years");
        System.out.println("Loan Interest   : ₹" + interest);
        System.out.println("Total Repayment : ₹" + (amount + interest));
    }
}


// Main class
public class BankingApplication {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       BANKING APPLICATION");
        System.out.println("=================================");

        // Create Savings Account
        SavingsAccount savings = new SavingsAccount(
            "SA1001",
            "Rahul",
            10000
        );

        savings.displayAccountDetails();

        System.out.println("\n--- Savings Account Operations ---");

        savings.deposit(2000);

        savings.withdraw(3000);

        savings.calculateInterest();

        savings.applyForLoan(50000);

        savings.calculateLoanInterest(
            50000,
            8.0,
            5
        );


        // Create Current Account
        CurrentAccount current = new CurrentAccount(
            "CA2001",
            "Priya",
            15000
        );

        current.displayAccountDetails();

        System.out.println("\n--- Current Account Operations ---");

        current.deposit(5000);

        current.withdraw(18000);

        current.calculateInterest();

        current.applyForLoan(100000);

        current.calculateLoanInterest(
            100000,
            9.0,
            5
        );


        // Demonstrating polymorphism
        System.out.println("\n--- Polymorphism Demonstration ---");

        BankAccount account1 = savings;
        BankAccount account2 = current;

        System.out.println("\nSavings Account Interest:");
        account1.calculateInterest();

        System.out.println("\nCurrent Account Interest:");
        account2.calculateInterest();

        System.out.println("\n=================================");
        System.out.println("       PROGRAM COMPLETED");
        System.out.println("=================================");
    }
}
