package src;
import java.math.BigDecimal;

public class CurrentAccount extends Account {
    private final static BigDecimal OVERDRAFT_LIMIT=new BigDecimal("-3000");

    @Override
    public void withdraw(BigDecimal withAmount)throws InsufficientBalance{
      if((balance.subtract(withAmount)).compareTo(OVERDRAFT_LIMIT)<0){
         throw new InsufficientBalance("Not enough Balance");
      }
      else{
         this.balance=this.balance.subtract(withAmount);
      }
   }
}
