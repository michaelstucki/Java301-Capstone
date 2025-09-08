module com.michaelstucki.java301capstone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.michaelstucki.java301capstone to javafx.fxml;
    exports com.michaelstucki.java301capstone;
    exports com.michaelstucki.java301capstone.controller;
    opens com.michaelstucki.java301capstone.controller to javafx.fxml;
}