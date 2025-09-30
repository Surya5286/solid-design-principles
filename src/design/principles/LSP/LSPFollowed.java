package design.principles.LSP;

import java.util.List;

interface DepositOnlyAccount {
    void deposit(double amount);
}

interface WithDrawableAccount extends DepositOnlyAccount {
    void withdraw(double amount);
}

class SavingAccount implements WithDrawableAccount {
    private double balance;

    public SavingAccount(double initialBalance) {
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

class SalaryAccount implements WithDrawableAccount {
    private double balance;

    public SalaryAccount(double initialBalance) {
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

class FDAccount implements DepositOnlyAccount {
    private double balance;

    public FDAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("FD Account Deposited With: " + amount + ", New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }
}

class Client {
    private final List<DepositOnlyAccount> fdAccounts;
    private final List<WithDrawableAccount> drawableAccounts;

    public Client(List<DepositOnlyAccount> fdAccounts, List<WithDrawableAccount> drawableAccounts) {
        this.fdAccounts = fdAccounts;
        this.drawableAccounts = drawableAccounts;
    }

    public void performFDTransactions() {
        fdAccounts.forEach(account -> account.deposit(500));
    }

    public void performDrawableTransactions() {
        drawableAccounts.forEach(account -> {
            account.deposit(500);
            account.withdraw(200);
        });
    }
}

public class LSPFollowed {
    public static void main(String[] args) {

        List<DepositOnlyAccount> fdAccounts = List.of(
                new FDAccount(1000)
        );
        List<WithDrawableAccount> drawableAccounts = List.of(
                new SavingAccount(1000),
                new SalaryAccount(2000)
        );

        // No Violation Of LSP as we don't have to check the type of account to avoid exception
        System.out.println("\n---- WithDrawable Accounts Operations ----");
        Client client = new Client(fdAccounts, drawableAccounts);
        client.performDrawableTransactions();

        System.out.println("\n---- FD Accounts Operations ----");
        client.performFDTransactions();
    }
}
