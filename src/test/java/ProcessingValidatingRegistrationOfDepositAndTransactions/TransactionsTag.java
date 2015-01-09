package ProcessingValidatingRegistrationOfDepositAndTransactions;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
/**
 * Created by Marzieh on 12/18/2014.
 * This class use for maintain data in transactions tag in xml input file in client side.
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionsTag implements Serializable{
    @XmlElement(name = "transaction")
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
