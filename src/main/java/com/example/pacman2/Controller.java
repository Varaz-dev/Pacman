package com.example.pacman2;

import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<KeyEvent>  {

    final private static double FRAMES_PER_SECOND = 5.0;

    @FXML private Label scoreLabel;
    @FXML private Label levelLabel;
    @FXML private Label gameOverLabel;

    @FXML private View pacManView;


    private Model Model;

    private static final String[] levelFiles = {"src/main/resources/level/level1.txt"};
    // Textdatei in der ein Pattern hinterlegt ist, nach diesem werden die Tikles angeordnet
    // auch die Kollosionsabfrage orientiert sich an diesem Pattern

    private Timer timer;
    //private static int ghostEatingModeCounter;
    private boolean paused;


    public Controller() {
        this.paused = false;
    }



    public void initialize() {
        String file = this.getLevelFile(0);
        this.Model = new Model();
        this.update(com.example.pacman2.Model.Direction.NONE);
        //ghostEatingModeCounter = 25;
        this.startTimer();
    }

    public static String getLevelFile(int x)
    {
        return levelFiles[x];
    }
  //Startet den Timer
    private void startTimer() {
        this.timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        update(Model.getCurrentDirection());
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }


       /* Pausiert den timer */
    public void pause() {
        this.timer.cancel();
        this.paused = true;
    }


    public boolean getPaused() {
        return paused;
    }


    public void handle(KeyEvent keyEvent) {
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        if (code == KeyCode.G) {
            pause();
            this.Model.startNewGame();
            this.gameOverLabel.setText(String.format(""));
            paused = false;
            this.startTimer();
        }
    }


    public double getBoardWidth() {
        return View.CELL_WIDTH * this.pacManView.getColumnCount();
    }

    public double getBoardHeight() {
        return View.CELL_WIDTH *  this.pacManView.getRowCount();
    }




    private void update(Model.Direction direction) {
        this.pacManView.update(Model);
        //this.Model.step(direction);    --> Pacmanläuft in richtung xy, später einfügen


    }

}