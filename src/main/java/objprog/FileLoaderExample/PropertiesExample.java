package objprog.FileLoaderExample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        String fileName = "config.properties";

        // Attempt to load properties from file if it exists
        try (InputStream input = new FileInputStream(fileName)) {
            props.load(input);
            System.out.println("Loaded Properties:");
            props.forEach((key, value) -> System.out.println(key + " = " + value));
        } catch (IOException ex) {
            System.out.println("Properties file not found. A new file will be created.");
        }

        // Set or update some properties
        props.setProperty("username", "testuser");
        props.setProperty("timeout", "30");

        // Store properties back to file
        try (OutputStream output = new FileOutputStream(fileName)) {
            props.store(output, "Application Configuration");
            System.out.println("Properties saved to " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

