package ProcessingValidatingRegistrationOfDepositAndTransactions;

import java.util.List;

/**
 * Created by Marzieh on 12/19/2014.
 * This class have 4 method. In checkId() method give an transaction; then,
 * check transaction id in users that red in server from json file.
 * In checkInitialBalance() method give a transaction object and
 * check weather the initial balance of user account is adequate for this transaction or not?
 * In checkUpperBound() method upper bound for user account balance checked after adding deposit transaction.
 * In executeTransaction() one transaction processing and secceed or failed result determined.
 */
public class ValidationChecker{
    Transaction transaction1 = null;
    List<User> userAccountList;
    public ValidationChecker(List<User> userAcountList){
        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();
        serializeDeserialize.deserializeFromJson();
        this.userAccountList = userAcountList;
    }

    public boolean checkId(Transaction transaction)
    {
        for(int m =0; m<userAccountList.size();m++)
            if(userAccountList.get(m).getId() == transaction.getAccount())
                return true;
        return false;
    }
    public boolean checkInitialBalance(Transaction transaction)
    {
        for(int m =0; m < userAccountList.size();m++)
            if(userAccountList.get(m).getId() == transaction.getAccount() && ( userAccountList.get(m).getInitialBalance().compareTo(transaction.getAmount() ) > 0 || userAccountList.get(m).getInitialBalance().compareTo(transaction.getAmount())==0 ) && transaction.getType().equals("withdraw"))
            {
                return true;
            }
        return false;
    }
    public boolean checkUpperBound(Transaction transaction)
    {
        for(int m =0; m < userAccountList.size();m++) {
            if (userAccountList.get(m).getId() == transaction.getAccount() && userAccountList.get(m).getUpperBound().compareTo(userAccountList.get(m).getInitialBalance().add(transaction.getAmount())) > 0 && transaction.getType().equals("deposit"))
                return true;
        }
        return false;
    }
    public  void executeTransaction(Transaction transaction) throws Exception
    {
        User user;
        transaction1 = transaction;
        if(checkId(transaction)==true ) {
            Loop: for( int i = 0 ;i<userAccountList.size();i++ ){
                user = userAccountList.get(i);
                if (user.getId() == transaction.getAccount() && transaction.getType().equals("withdraw")) {
                    if (!checkInitialBalance(transaction)) {
                        transaction.setType(transaction.getType() + " Failed. There is not adequate balance value for withdrawing");
                        transaction1 = transaction;
                        throw new NonAdequateBalanceException("There is not adequate balance value for withdrawing :" + transaction.getAccount());
                    } else {
                        transaction.setType(transaction.getType() + " Succeed");
                        transaction1 = transaction;
                    }
                    break Loop;
                } else if (user.getId() == transaction.getAccount() && transaction.getType().equals("deposit")) {
                    if (checkUpperBound(transaction) == false) {
                        transaction.setType(transaction.getType() + " Failed. The upper bound exceptin accure.");
                        transaction1 = transaction;
                        throw new BeyondUpperBoundException("Processing :" + transaction.getType() + " Failed. The upper bound exceptin accure.");
                    } else {
                        transaction.setType(transaction.getType() +  " Succeed");
                        transaction1 = transaction;
                    }
                    break Loop;
                } else if((!transaction.getType().equals("withdraw") && !transaction.getType().equals("deposit")))
                {
                    transaction.setType(transaction.getType() + " Failed. Type of process is incorrect");
                    transaction1 = transaction;
                }
            }
        }
        else
        {
            transaction1.setType(transaction.getType()+" Failed.Checking Validation Failed for this account. This account does not exist");
            throw new ValidationException("Checking Validation Failed for account:"+transaction.getId());
        }
    }
}
