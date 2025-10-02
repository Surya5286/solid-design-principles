package design.principles.LSP;

import java.util.List;

public record BanksClient(List<Account> accounts) {
    public void performTransactions() {
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