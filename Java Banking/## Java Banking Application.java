## Java Banking Application Using Switch Case

### Java Program

import java.util.Scanner;

// Abstract class
abstract class BankAccount {
    protected String accountNumber;
    protected String holderName;
    protected double balance;

    BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Amount Deposited: ₹" + amount);
    }

    abstract void withdraw(double amount);

    abstract double calculateInterest();

    void display() {
        System.out.println("\nAccount Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Balance        : ₹" + balance);
    }
}

// Interface
interface LoanService {
    void processLoan(double amount);
}

// Savings Account
class SavingsAccount extends BankAccount implements LoanService {

    SavingsAccount(String accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal Successful: ₹" + amount);
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    @Override
    double calculateInterest() {
        return balance * 0.05;
    }

    @Override
    public void processLoan(double amount) {
        System.out.println("Savings Loan Processed: ₹" + amount);
    }
}

// Current Account
class CurrentAccount extends BankAccount implements LoanService {

    CurrentAccount(String accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    void withdraw(double amount) {
        if (amount <= balance + 5000) {
            balance -= amount;
            System.out.println("Withdrawal Successful: ₹" + amount);
        } else {
            System.out.println("Overdraft Limit Exceeded!");
        }
    }

    @Override
    double calculateInterest() {
        return balance * 0.02;
    }

    @Override
    public void processLoan(double amount) {
        System.out.println("Current Account Loan Processed: ₹" + amount);
    }
}

// Main class
public class BankingApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== BANKING APPLICATION =====");

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Enter choice: ");
        int accountChoice = sc.nextInt();

        BankAccount account;

        // Account selection using switch
        switch (accountChoice) {

            case 1:
                account = new SavingsAccount(accNo, name, balance);
                System.out.println("Savings Account Created.");
                break;

            case 2:
                account = new CurrentAccount(accNo, name, balance);
                System.out.println("Current Account Created.");
                break;

            default:
                System.out.println("Invalid Account Type!");
                sc.close();
                return;
        }

        int choice;

        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Display Account");
            System.out.println("5. Process Loan");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter deposit amount: ");
                    double deposit = sc.nextDouble();
                    account.deposit(deposit);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = sc.nextDouble();
                    account.withdraw(withdraw);
                    break;

                case 3:
                    double interest = account.calculateInterest();
                    System.out.println("Interest: ₹" + interest);
                    break;

                case 4:
                    account.display();
                    break;

                case 5:
                    System.out.print("Enter loan amount: ");
                    double loan = sc.nextDouble();

                    if (account instanceof LoanService) {
                        LoanService service = (LoanService) account;
                        service.processLoan(loan);
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the Banking Application.");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}

