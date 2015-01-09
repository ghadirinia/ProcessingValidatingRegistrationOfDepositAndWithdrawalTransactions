package ProcessingValidatingRegistrationOfDepositAndTransactions;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;
/**
 * Created by Marzieh on 12/18/2014.
 * In Client class we have a main method. In this method we read terminal.xml ;
 * then, set port number and address in terminal.xml
 * afterward, create a socket object and send output stream to server later, receive input stream
 * data of processing transaction's information and write result to xml log file.
 */
public class GreatClient{

    public static void main(String[] args) {

        Transaction transaction;
        Logger logger = Logger.getLogger(GreatClient.class.getName());

        try {
            FileHandler fileHandler = new FileHandler("response.xml",true);

            logger.addHandler(fileHandler);

            SerializeDeserialize serializeDeserialize = new SerializeDeserialize();

            serializeDeserialize.readInputXML("terminal.xml");

            Socket client = new Socket("localhost",8080);

            OutputStream outToServer = client.getOutputStream();

            ObjectOutputStream outSend = new ObjectOutputStream(outToServer);
            //Sending Data to Server Class
            //1
            outSend.writeUTF(""+serializeDeserialize.inputTransactions.getId());

            outSend.writeUTF(""+serializeDeserialize.inputTransactions.transactionsTag.getTransactionList().size());

            for(Transaction inTransaction:serializeDeserialize.inputTransactions.transactionsTag.getTransactionList()){
                //2
                outSend.writeObject(inTransaction);

                InputStream inFromServer = client.getInputStream();

                ObjectInputStream inReceive = new ObjectInputStream(inFromServer);

                //3
                transaction = (Transaction)inReceive.readObject();

                logger.setLevel(Level.INFO);

                fileHandler.setFormatter(new XMLFormatter());

                logger.log(Level.INFO,"Transaction's Id: "
                        +transaction.getId()+" Process: "
                        +transaction.getType()+" Amount: "
                        +transaction.getAmount()+" Account: "
                        +transaction.getAccount()+"");

            }
            fileHandler.close();
            client.close();
        } catch(IOException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}
