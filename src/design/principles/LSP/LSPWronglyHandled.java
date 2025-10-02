package design.principles.LSP;

import java.util.List;

public class LSPWronglyHandled {
    public static void main(String[] args) {

        // As all the Classes and Objects have been defined in the LSPViolated.java file,
        // we can create objects of those classes and demonstrate the Liskov Substitution Principle here
        List<Account> accounts = List.of(
                new SavingsAccount(1000),
                new CurrentAccount(2000),
                new FixedDepositAccount()
        );

        BanksClient client = new BanksClient(accounts);
        client.performTransactions();
    }
}

