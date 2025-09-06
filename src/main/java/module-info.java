module com.michaelstucki.java301capstone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.michaelstucki.java301capstone to javafx.fxml;
    exports com.michaelstucki.java301capstone;
}