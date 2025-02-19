module MVCDemo {
    requires javafx.controls;
    requires javafx.fxml;
    // If you need Derby engine
    requires org.apache.derby.commons;
    requires org.jdom2;
    requires com.fasterxml.jackson.databind;   // Add this if your module system requires it

    exports objprog;
}

