package src;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName="SBI";
    private List<Customer> customers=new ArrayList<>();

    public void addCustomer(Customer c){
        customers.add(c);
    }

    public Customer findCustomerById(String ID)throws InvalidCustomerException{
        for(Customer c: customers ){
            if(c.getCustomerId().equals(ID)){
                return c;
            }
        }
        throw new InvalidCustomerException("Customer with specified ID not found!");
    }

    public Account findAccountByNumber(String accountNumber)throws InvalidAccountException{
        for(Customer c:customers){
            for(Account a:c.getAccounts()){
                if(a.getAccountNumber().equals(accountNumber)){
                    return a;
                }
            }
        }
        throw new InvalidAccountException("Account with the provided Account Number not found!");
    }

   public void transferMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount)
   throws InsufficientBalance, InvalidAccountException {
        Account senderAccount = findAccountByNumber(fromAccountNumber);
        Account receiverAccount = findAccountByNumber(toAccountNumber);
        senderAccount.withdraw(amount); 
        receiverAccount.deposit(amount);
    }
}
