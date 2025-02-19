package objprog;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MVCDemo extends Application {

    // === MODEL ===
    // This class represents the data and the logic behind our counter.
    public class CounterModel {
        // Using a JavaFX property so the view can easily bind to it.
        private IntegerProperty counter = new SimpleIntegerProperty(0);

        public int getCounter() {
            return counter.get();
        }

        public void setCounter(int value) {
            counter.set(value);
        }

        public IntegerProperty counterProperty() {
            return counter;
        }

        public void incrementCounter() {
            setCounter(getCounter() + 1);
        }
    }

    // === VIEW ===
    // This class represents the user interface.
    public class CounterView extends VBox {
        private Label counterLabel = new Label("0");
        private Button incrementButton = new Button("Increment");

        public CounterView() {
            // Spacing between elements for clarity.
            this.setSpacing(10);
            // Add the label and button to the layout.
            this.getChildren().addAll(counterLabel, incrementButton);
        }

        public Label getCounterLabel() {
            return counterLabel;
        }

        public Button getIncrementButton() {
            return incrementButton;
        }
    }

    // === CONTROLLER ===
    // This class connects the Model and the View.
    public class CounterController {
        private CounterModel model;
        private CounterView view;

        public CounterController(CounterModel model, CounterView view) {
            this.model = model;
            this.view = view;
            attachEvents();
        }

        // Bind view components to model data and set up event handling.
        private void attachEvents() {
            // Bind the label text property to the model's counter property.
            view.getCounterLabel().textProperty().bind(model.counterProperty().asString());

            // Set up the button's action to increment the counter.
            view.getIncrementButton().setOnAction(e -> model.incrementCounter());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Create instances of Model, View, and Controller.
        CounterModel model = new CounterModel();
        CounterView view = new CounterView();
        new CounterController(model, view);

        // Set up the scene and stage.
        Scene scene = new Scene(view, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MVC Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

