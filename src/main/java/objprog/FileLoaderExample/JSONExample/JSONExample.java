package objprog.FileLoaderExample.JSONExample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

public class JSONExample {
    public static void main(String[] args) {
        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Enable pretty printing for the JSON output
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create a Person object
        Person person = new Person("Alice", 30);
        String fileName = "person.json";

        // Serialize the Person object to a JSON file
        try {
            objectMapper.writeValue(new File(fileName), person);
            System.out.println("JSON has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the JSON back into a Person object
        try {
            Person readPerson = objectMapper.readValue(new File(fileName), Person.class);
            System.out.println("JSON read from file:");
            System.out.println(readPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
