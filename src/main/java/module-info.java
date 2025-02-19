module MVCDemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
       // If you need Derby engine
    requires org.apache.derby.commons;   // Add this if your module system requires it

    exports objprog;
}

