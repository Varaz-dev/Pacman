module com.example.pacman2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pacman2 to javafx.fxml;
    exports com.example.pacman2;
}