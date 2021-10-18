package com.example.pacman2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


import java.io.IOException;

public class Main extends Application {

    @Override

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("pacman.fxml"));  // hole dir Werte etc aus folgender FXML
        Parent root = loader.load();
        primaryStage.setTitle("PacMan");
        Controller controller = loader.getController();

        //root.setOnKeyPressed(controller);
        double sceneWidth = controller.getBoardWidth() + 20.0; //ruft die Methode aus Controller auf um damit das Spielfeld + Rand zu erzeugen
        double sceneHeight = controller.getBoardHeight() + 100.0; // ^^^^
        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight)); // erzeuge ein neues Fenster mit den Werten aus controller bzw aus der FXML
        primaryStage.show(); // zeige das Fenster beim ausführen an
        root.requestFocus(); // setzt fest das die Anwendung "im Vordergrund" ausgeführt wird undalle folgenden eingaben im Fenster verarbeitet werden sollen

    }

    public static void main(String[] args) {
        launch(args);
    }
}