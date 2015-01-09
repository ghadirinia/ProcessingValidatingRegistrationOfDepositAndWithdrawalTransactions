package ProcessingValidatingRegistrationOfDepositAndTransactions;

/**
 * Created by Marzieh on 12/19/2014.
 * This exception accure when account number in transaction is not in accounts.
 */
public class ValidationException extends Exception{

    private String ExceptionText;
    public ValidationException(String depositType)
    {
        ExceptionText = depositType;
    }
    public String getExceptionText()
    {
        return ExceptionText;
    }

}
