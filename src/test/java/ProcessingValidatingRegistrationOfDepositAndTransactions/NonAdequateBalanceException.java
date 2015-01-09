package ProcessingValidatingRegistrationOfDepositAndTransactions;

/**
 * Created by Marzieh on 12/19/2014.
 * This class extend exception and accure when initial balance is not adequate in processing a transaction.
 */
public class NonAdequateBalanceException extends Exception {
    private String ExceptionText;
    public NonAdequateBalanceException(String depositType)
    {
        ExceptionText = depositType;
    }
    public String getExceptionText()
    {
        return ExceptionText;
    }
}
