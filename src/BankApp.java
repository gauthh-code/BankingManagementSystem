package src;

import java.math.BigDecimal;

public class BankApp {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // ---------- SETUP ----------
        Customer alice = new Customer("Alice");
        Customer bob = new Customer("Bob");
        bank.addCustomer(alice);
        bank.addCustomer(bob);

        SavingsAcc aliceAccount = new SavingsAcc();
        CurrentAccount bobAccount = new CurrentAccount();
        alice.addAccount(aliceAccount);
        bob.addAccount(bobAccount);

        aliceAccount.deposit(new BigDecimal("1000"));

        System.out.println("--- Scenario 1: Happy path ---");
        System.out.println("Alice balance : " +aliceAccount.getBalance());

        try {
            bank.transferMoney(aliceAccount.getAccountNumber(), bobAccount.getAccountNumber(), new BigDecimal("300"));
        } catch (Exception e) {
            System.out.println("Unexpected failure: " + e.getMessage());
        }
      
        System.out.println("Alice balance : " + aliceAccount.getBalance());
        System.out.println("Bob balance : " + bobAccount.getBalance());


        System.out.println("\n--- Scenario 2: Failed transfer ---");
        System.out.println("Bob balance : " + bobAccount.getBalance());
     
        try {
            bank.transferMoney(aliceAccount.getAccountNumber(), bobAccount.getAccountNumber(), new BigDecimal("999999"));
        } catch (Exception e) {
            System.out.println("Caught expected failure: " + e.getMessage());
        }
       
        System.out.println("Bob balace : " + bobAccount.getBalance());

        System.out.println("\n--- Scenario 3: Interest calculation ---");
    FixedDepositAcc fd = new FixedDepositAcc(new BigDecimal("10000"));

    Account[] testAccounts = { aliceAccount, bobAccount, fd };
    for (Account acc : testAccounts) {
        if (acc instanceof InterestBearing ib) {
        System.out.println(ib.calculateInterest());
         }else{
            System.out.println("No interest for this account!");
         }
    }

    System.out.println("\n--- Scenario 4: FD rules ---");
    try {
        fd.withdraw(new BigDecimal("1000") );
    } catch (InsufficientBalance e) {
        System.out.println(e.getMessage());
    }

    fd.markAsMatured();
     try {
        fd.withdraw(new BigDecimal("10000") );
    } catch (InsufficientBalance e) {
        System.out.println(e.getMessage());
    }
    System.out.println("FD balance after full withdrawal: " + fd.getBalance());
    
    try {
    Customer c = bank.findCustomerById("NONEXISTENT");
    } catch (InvalidCustomerException e) {
     System.out.println(e.getMessage());
    }
}
}