import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    // Constructor chaining
    public Account() {
        this("N/A", "Unknown", 0.0);
    }

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = balance;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // Deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        balance -= amount;
    }

    public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Balance: " + balance);
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    // Constructor chaining with super
    public SavingsAccount() {
        this("N/A", "Unknown", 0.0, 0.0);
    }

    public SavingsAccount(String accountNumber, String ownerName,
                          double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate / 100.0;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Interest Amount: " + calculateInterest());
    }
}

class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount() {
        this("N/A", "Unknown", 0.0, 0.0);
    }

    public CurrentAccount(String accountNumber, String ownerName,
                          double balance, double overdraftLimit) {
        super(accountNumber, ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalArgumentException(
                    "Withdrawal exceeds overdraft limit.");
        }

        setBalance(getBalance() - amount);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}

public class BankingSystem {
    public static void main(String[] args) {

        List<Account> accounts = new ArrayList<>();

        accounts.add(new SavingsAccount("SA101", "Alice", 10000, 5.0));
        accounts.add(new CurrentAccount("CA201", "Bob", 5000, 2000));

        accounts.get(0).deposit(1000);
        accounts.get(1).withdraw(6000);

        System.out.println("=== Account Details (Polymorphism Demo) ===");
        for (Account acc : accounts) {
            acc.display();
            System.out.println("----------------------");
        }

        assert accounts.size() > 0 : "Account list should not be empty";
    }
}
