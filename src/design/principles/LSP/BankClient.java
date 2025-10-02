package design.principles.LSP;

import java.util.List;

public record BankClient(List<Account> accounts) {

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
