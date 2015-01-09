package ProcessingValidatingRegistrationOfDepositAndTransactions;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Marzieh on 12/17/2014.
 * This class utilize to maintain user informations in json's file accounts Array Lists.
 */
public class User {
    private String customer;
    private int id;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;
    private static final String lock = "";
    ArrayList arr = new ArrayList();
    @JsonCreator
    public User(@JsonProperty("customer")String customer,@JsonProperty("id")int id,@JsonProperty("initialBalance")BigDecimal initialBalance,@JsonProperty("upperBound")BigDecimal upperBound) {
       this.customer = customer;
       this.id = id;
       this.initialBalance = initialBalance;
       this.upperBound = upperBound;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        synchronized (lock){
        this.initialBalance = initialBalance;}

    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }
}
