package ProcessingValidatingRegistrationOfDepositAndTransactions;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;

/**
 * Created by Marzieh on 12/17/2014.
 * In this class we deserialize object from xml and json files; moreover, serialize data to xml and json files.
 * Object formats are with formats of InputTransaction class for xml and JsonValidation Class for json files
 */
public class SerializeDeserialize {

    JSONValidation jsonValidation = null;

    JSONValidation jsonValidationReadedFromJsonFile = null;

    InputTransactions inputTransactions = null;

    public void setAndWritePortNumber(int portNumber){

        jsonValidation.setPort(portNumber);

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.writeValue(new File("user.json"), jsonValidation);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public void jsonValidationWriterToJson(JSONValidation jsonValidation1){

        jsonValidation = jsonValidation1;

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("user.json"), jsonValidation);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void serializeToJson(int portNumber,String outLog) {

        jsonValidation = new JSONValidation(portNumber, outLog);

        User one = new User("Ali Alavai",33227781, new BigDecimal(1000), new BigDecimal(1000000));

        User two = new User("Reza Rezaei",35527439, new BigDecimal(0) , new BigDecimal(0));

        jsonValidation.addUser(one);

        jsonValidation.addUser(two);

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.writeValue(new File("user.json"), jsonValidation);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void deserializeFromJson() {

        ObjectMapper mapper = new ObjectMapper();

        try {

            jsonValidationReadedFromJsonFile = mapper.readValue(new File("user.json"), JSONValidation.class);

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void readInputXML(String pathname) {

        try {

            File file = new File(pathname);

            JAXBContext jaxbContext = JAXBContext.newInstance(InputTransactions.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            inputTransactions = (InputTransactions) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {

            e.printStackTrace();
        }


    }

    public void writeToXML(String str1){

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(InputTransactions.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileOutputStream fileOutputStream = new FileOutputStream(str1);
            jaxbMarshaller.marshal(inputTransactions, fileOutputStream);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
