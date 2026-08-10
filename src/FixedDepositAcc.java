package src;
import java.math.BigDecimal;

public class FixedDepositAcc extends Account implements InterestBearing{
    public FixedDepositAcc(BigDecimal amount){
        this.balance=amount;
    }

    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.06");
    private boolean maturity=false;
    @Override
    public BigDecimal calculateInterest() {
        return balance.multiply(INTEREST_RATE);
    }
    
    @Override
    public void withdraw(BigDecimal withAmount) throws FixedDepositException{
      if(!maturity){
         throw new FixedDepositException("Account is not matured to withdraw Money!");
      }
      if ((balance.subtract(withAmount)).compareTo(BigDecimal.ZERO) != 0) {
        throw new FixedDepositException("Must withdraw the full amount!");
    }
    this.balance = BigDecimal.ZERO;
   }

   public void markAsMatured(){
    this.maturity=true;
   }

    @Override
    public void deposit(BigDecimal depAmount){
        throw new UnsupportedOperationException("Cannot deposit to an FD Account!");
    }
}