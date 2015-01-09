package ProcessingValidatingRegistrationOfDepositAndTransactions;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Created by Marzieh on 12/17/2014.
 * This class maintain xml format file to read data from xml input file.
 */
@XmlRootElement(name = "terminal")
@XmlAccessorType(XmlAccessType.FIELD)
public class InputTransactions implements Serializable {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String type;
    @XmlElement(name = "outLog")
    OutLogTag outLogTag;
    @XmlElement(name = "server")
    ServerTag serverTag;
    @XmlElement(name = "transactions")
    TransactionsTag transactionsTag;
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

    public void setOutLogTag(OutLogTag outLogTag) {
        this.outLogTag = outLogTag;
    }

    public OutLogTag getOutLogTag() {
        return outLogTag;
    }

    public void setServerTag(ServerTag serverTag) {
        this.serverTag = serverTag;
    }

    public ServerTag getServerTag() {
        return serverTag;
    }

    public void setTransactionsTag(TransactionsTag transactionsTag) {
        this.transactionsTag = transactionsTag;
    }

    public TransactionsTag getTransactionsTag() {
        return transactionsTag;
    }
}
