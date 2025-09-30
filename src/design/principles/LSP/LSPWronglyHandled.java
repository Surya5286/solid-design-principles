package design.principles.LSP;

import java.util.List;

public class LSPWronglyHandled {
    public static void main(String[] args) {

        // As all the Classes and Objects have been defined in the LSPViolated.java file,
        // we can create objects of those classes and demonstrate the Liskov Substitution Principle here

        List<Account> accounts = List.of(
                new SavingsAccount(1000),
                new CurrentAccount(2000),
                new FixedDepositAccount(3000)
        );


        // Violation Of OCP as we have to check the type of account to avoid exception
        accounts.forEach(account -> {
            System.out.println("\nUsing account: " + account.getClass().getSimpleName());
            if (account instanceof FixedDepositAccount) {
                account.deposit(500);
                System.out.println("Skipping withdraw operation for FixedDepositAccount.");
            } else {
                account.deposit(500);
                account.withdraw(200);
            }
        });
    }
}

