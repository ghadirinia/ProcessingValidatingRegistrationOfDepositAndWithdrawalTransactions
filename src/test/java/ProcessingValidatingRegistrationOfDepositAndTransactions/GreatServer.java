package ProcessingValidatingRegistrationOfDepositAndTransactions;

import java.io.*;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Created by Marzieh on 12/18/2014.
 * In server class we have a main method that produce threads of server class afterward, invoke start()
 * method that call run() method. In run() method we accept request from client and read input stream from client
 * apply doTransaction() method on it and return result as output stream to client.
 */
public class GreatServer extends Thread {
    Logger logger;
    FileHandler fileHandler = null;

    public static void main(String[] args) {
        Thread GreatServer = new GreatServer();
        GreatServer.start();
    }

    public GreatServer() {
        logger = Logger.getLogger(GreatServer.class.getName());

        try {
            fileHandler = new FileHandler("Server.out.xml", true);
            fileHandler.setFormatter(new XMLFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();

        serializeDeserialize.deserializeFromJson();

        List<User> userAccountsList = serializeDeserialize.jsonValidationReadedFromJsonFile.accounts;

        BigDecimal tempBalance;

        BigDecimal upperBoundBalance;

        BigDecimal newBalance = new BigDecimal(0.0);

        upperBoundBalance = new BigDecimal(0.0);


        Transaction transaction;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8080);

            serverSocket.setSoTimeout(200000);

        } catch (IOException e) {

            e.printStackTrace();
        }


        int transactionSize = 0;
        while (true) {
            try {
                Socket server = serverSocket.accept();

                ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
                //1
                String terminalId = objectInputStream.readUTF();
                String size = objectInputStream.readUTF();
                transactionSize = Integer.parseInt(size);
                for (int j = 0; j < transactionSize; j++) {
                    //2
                    transaction = (Transaction) objectInputStream.readObject();
                    //Do Transaction
                    transaction = doTransactionSafe(transaction, userAccountsList);


                    for (int i = 0; i < userAccountsList.size(); i++) {
                        if (userAccountsList.get(i).getId() == transaction.getAccount()) {
                            tempBalance = userAccountsList.get(i).getInitialBalance();
                            if (transaction.getType().equals("withdraw Succeed")) {
                                userAccountsList.get(i).setInitialBalance(tempBalance.subtract(transaction.getAmount()));
                            }
                            if (transaction.getType().equals("deposit Succeed")) {
                                userAccountsList.get(i).setInitialBalance(tempBalance.add(transaction.getAmount()));
                            }
                            newBalance = userAccountsList.get(i).getInitialBalance();
                            upperBoundBalance = userAccountsList.get(i).getUpperBound();
                        }
                    }
                    //End Of Do Transaction
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());


                    objectOutputStream.writeObject(transaction);
                    logger.setLevel(Level.INFO);
                    logger.log(Level.INFO, "Terminal Id: " + terminalId + " Transaction's Id: " +
                            transaction.getId() + " Process: " + transaction.getType() +
                            " Amount: " + transaction.getAmount() +
                            " Account: " + transaction.getAccount() + " Balance Value:" +
                            newBalance + " Balance Upper Bound:" + upperBoundBalance);

                }

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public Transaction doTransactionSafe(Transaction transaction, List<User> userAccountsList) {
        ValidationChecker ValidationChecker = new ValidationChecker(userAccountsList);
        try {
            ValidationChecker.executeTransaction(transaction);
            transaction = ValidationChecker.transaction1;
        } catch (NonAdequateBalanceException e1) {
            e1.printStackTrace();
        } catch (BeyondUpperBoundException e2) {
            e2.printStackTrace();
        } catch (ValidationException e3) {
            e3.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }
}