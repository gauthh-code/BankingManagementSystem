package src;

import java.math.BigDecimal;
abstract class Account {
   //Account number counter
   private static int num=0;
   //Setting account details
   private String accountNumber;
   private String ifscCode="SBI";
   protected BigDecimal balance=new BigDecimal("0");

   //Constuctor to set account number for each account
   public Account(){
    num++;
    this.accountNumber=ifscCode+String.valueOf((100000+num));
   }
   
   //Deposit money
   public void deposit(BigDecimal depAmount){
    this.balance=this.balance.add(depAmount);
   }
   
   //Withdraw money
   public void withdraw(BigDecimal withAmount)throws InsufficientBalance{
      if(balance.compareTo(withAmount)<0){
         throw new InsufficientBalance("Not enough Balance");
      }
      else{
         this.balance=this.balance.subtract(withAmount);
      }
   }

   //Get balance
   public BigDecimal getBalance(){
      return this.balance;
     }

   //Get accountNumber
   public String getAccountNumber(){
      return this.accountNumber;
   }

}
