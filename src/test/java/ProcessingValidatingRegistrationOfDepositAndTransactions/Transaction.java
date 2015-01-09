package ProcessingValidatingRegistrationOfDepositAndTransactions;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.io.Serializable;
/**
 * Created by Marzieh on 12/17/2014.
 * This class utilize to maintain users transactions in xml file in transactions tag.
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction implements Serializable{
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private BigDecimal amount;
    @XmlAttribute
    private int account;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getAccount() {
        return account;
    }
}
