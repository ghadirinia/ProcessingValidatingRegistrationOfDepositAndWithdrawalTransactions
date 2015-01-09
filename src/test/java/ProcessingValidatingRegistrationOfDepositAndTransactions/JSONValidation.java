package ProcessingValidatingRegistrationOfDepositAndTransactions;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marzieh on 12/17/2014.
 * This class maintain information format in json input file at server side.
 */

public class JSONValidation {
    private int port;
    public List<User> accounts = new ArrayList<User>();
    private String outLog;
    @JsonCreator
    public JSONValidation(@JsonProperty("port") int port, @JsonProperty("outLog") String outLog)
    {
        this.port = port;
        this.outLog = outLog;
    }

    public List<User> addUser(User user) {
        accounts.add(user);
        return accounts;
    }

    public String getOutLog() {
        return outLog;
    }

    public void setOutLog(String outLog) {
        this.outLog = outLog;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
