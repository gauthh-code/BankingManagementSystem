package src;
import java.math.BigDecimal;

public class SavingsAcc extends Account implements InterestBearing{
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.04");
    @Override
    public BigDecimal calculateInterest() {
        return balance.multiply(INTEREST_RATE);
    }

}
