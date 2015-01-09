package ProcessingValidatingRegistrationOfDepositAndTransactions;

/**
 * Created by Marzieh on 12/19/2014.
 * This class is an exception that take place when exception accure beyond upper bound.
 */
public class BeyondUpperBoundException extends Exception {
    private String ExceptionText;
    public BeyondUpperBoundException(String depositType)
    {
        ExceptionText = depositType;
    }
    public String getExceptionText()
    {
        return ExceptionText;
    }
}
