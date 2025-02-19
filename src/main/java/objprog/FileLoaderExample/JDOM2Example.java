package objprog.FileLoaderExample;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JDOM2Example {

    public static void main(String[] args) {
        // Step 1: Create an XML document representing a list of books
        Element rootElement = new Element("books");
        Document document = new Document(rootElement);

        // Create first book element
        Element book1 = new Element("book");
        book1.setAttribute("id", "1");
        book1.addContent(new Element("title").setText("Effective Java"));
        book1.addContent(new Element("author").setText("Joshua Bloch"));
        rootElement.addContent(book1);

        // Create second book element
        Element book2 = new Element("book");
        book2.setAttribute("id", "2");
        book2.addContent(new Element("title").setText("Clean Code"));
        book2.addContent(new Element("author").setText("Robert C. Martin"));
        rootElement.addContent(book2);

        // Step 2: Write the XML document to a file
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        String fileName = "books.xml";
        try (FileWriter writer = new FileWriter(fileName)) {
            xmlOutputter.output(document, writer);
            System.out.println("XML file has been saved as " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 3: Read and parse the XML file using SAXBuilder
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document parsedDocument = saxBuilder.build(new File(fileName));
            Element parsedRoot = parsedDocument.getRootElement();
            List<Element> books = parsedRoot.getChildren("book");

            System.out.println("\nParsed XML Content:");
            for (Element book : books) {
                String id = book.getAttributeValue("id");
                String title = book.getChildText("title");
                String author = book.getChildText("author");

                System.out.println("Book ID: " + id);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("----------------------");
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
