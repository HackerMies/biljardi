module org.example.biljardi {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.biljardi to javafx.fxml;
    exports org.example.biljardi;
}