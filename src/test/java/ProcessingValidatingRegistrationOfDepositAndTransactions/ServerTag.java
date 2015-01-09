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
public class ServerTag implements Serializable{
    @XmlAttribute
    private String ip;
    @XmlAttribute
    private int port;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
