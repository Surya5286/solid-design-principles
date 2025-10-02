package design.principles.LSP;

import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
}

class SavingsAccount implements Account {
    private double balance;

    public SavingsAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

class CurrentAccount implements Account {
    private double balance;

    public CurrentAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }
}

class FixedDepositAccount implements Account {

    public FixedDepositAccount(double amount) {
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            System.out.println("FD Account Deposited With: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Violation of LSP as this method is not supported in FixedDepositAccount
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals are not allowed from Fixed Deposit Account.");
    }
}

class BankClient {
    private final List<Account> accounts;

    public BankClient(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void performTransactions() {
        accounts.forEach(account -> {
            System.out.println("\nUsing account: " + account.getClass().getSimpleName());
            account.deposit(500);
            try {
                account.withdraw(200);
            } catch (UnsupportedOperationException e) {
                System.out.println("Exception : " + e.getMessage());
            }
        });
    }
}

public class LSPViolated {
    public static void main(String[] args) {

        List<Account> accounts = List.of(
                new SavingsAccount(1000),
                new CurrentAccount(2000),
                new FixedDepositAccount(3000)
        );

        // Call to Bank Client to invoke transactions
        BankClient client = new BankClient(accounts);
        client.performTransactions();
    }
}