package ProcessingValidatingRegistrationOfDepositAndTransactions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by Marzieh on 12/18/2014.
 * This shows tag's format in a Class that utilized in input transaction file.
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class OutLogTag implements Serializable {
    @XmlAttribute
    private String path ;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
