package design.principles.LSP;

import java.util.List;

public record Client(List<DepositOnlyAccount> fdAccounts,
                     List<WithDrawableAccount> drawableAccounts) {
    public void processFDTransactions() {
        fdAccounts.forEach(account -> account.deposit(500));
    }

    public void processWithDrawableTransactions() {
        drawableAccounts.forEach(account -> {
            account.deposit(500);
            account.withdraw(200);
        });
    }
}