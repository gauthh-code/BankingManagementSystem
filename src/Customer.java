package src;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    //Customer Id Generator
    private static int customerCounter=0;
    private String customerId;
    private String customerName;
   
    public Customer(String customerName){
        customerCounter++;
        this.customerId = "CID" + String.valueOf(1000 + customerCounter);
        this.customerName=customerName;
    }

    public String getCustomerId() {
        return customerId;
    }
    //Customer name getter and setter

    public String getCustomerName() {
        return customerName;
    }

    //Acoounts
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account acc){
        accounts.add(acc);
    }

    public List<Account> getAccounts() {
        return java.util.Collections.unmodifiableList(this.accounts);
    }
    
    public Account getAccountById(String accountNumber)throws InvalidAccountException{
        for(Account acc:accounts){
            if(acc.getAccountNumber().equals(accountNumber))
                return acc;
        }
        throw new InvalidAccountException("Account with the provided Account Number not found!");
    }

}

