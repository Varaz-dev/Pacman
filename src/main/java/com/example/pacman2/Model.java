package com.example.pacman2;

/*
 * Die Model Klasse enthält informationen über verschiedene Game Werte wie der Game Status(pausiert, gestartet, level, score), die aktuellen
 * Positionen der Charactere und der Inhalt der einzelnen Zellen des Spielfeldes
* */

import javafx.geometry.Point2D;

import javafx.fxml.FXML;
import java.io.*;
import java.util.*;

public class Model {

  // Intialisieren verschiedene objekte die wir später in Methoden oder für schleifen benötigen


    public enum CellValue {
        EMPTY, SMALLDOT, BIGDOT, WALL, GHOST1HOME, GHOST2HOME, PACMANHOME

    // Inhalt der Spielfeldzellen, diese sollen nicht veränderbar sein. Deswegen wird als Konstante der Typ Enum gewählt

    };

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    };
    // Enum für die richtungsangaben


    @FXML private int rowCount;
    @FXML private int columnCount;
    //höhe und breite des Spielfelds in Zellen, die Angaben werden aud der FXML entnommen

    private CellValue[][] grid;  // erstellt ein 2D Array welches die Buchstaben aus der Level.txt enthalten wird
    private int level;
    /*
    private int score;

    private int dotCount;
    private static boolean gameOver;
    private static boolean youWon;
    private static boolean ghostEatingMode;
    */

    private Point2D pacmanLocation;
    //die Klasse Point2D enthält jeweils eine x und y Koordinate und zeigt uns an wo unser Spieler sich auf dem Spielfeld befindet

    private Point2D pacmanVelocity;
    // Geschwindigkeit des Spielers
    private Point2D ghost1Location;
    //Position Gegner 1
    private Point2D ghost1Velocity;
    //Geschwindigkeit Gegner 1
    private Point2D ghost2Location;
    private Point2D ghost2Velocity;
    private static Direction lastDirection;
    private static Direction currentDirection;


    public Model() {
        this.startNewGame();
    }

    public void startNewGame() {
        //this.gameOver = false;
        //this.youWon = false;
        //this.ghostEatingMode = false;
        //dotCount = 0;
        rowCount = 0;
        columnCount = 0;
        //this.score = 0;
        this.level = 1;
        this.initializeLevel(Controller.getLevelFile(0));  //ruft die Methode zum initialisieren im Controller auf
    }


    public void initializeLevel(String fileName) {
        File file = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                lineScanner.next();
                columnCount++;
            }
            rowCount++;
        }
        columnCount = columnCount / rowCount;
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        grid = new CellValue[rowCount][columnCount];
        int row = 0;
        int pacmanRow = 0;
        int pacmanColumn = 0;
        int ghost1Row = 0;
        int ghost1Column = 0;
        int ghost2Row = 0;
        int ghost2Column = 0;
        while (scanner2.hasNextLine()) {
            int column = 0;
            String line = scanner2.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String value = lineScanner.next();
                CellValue thisValue;
                if (value.equals("W")) {
                    thisValue = CellValue.WALL;
                } else if (value.equals("S")) {
                    thisValue = CellValue.SMALLDOT;
                    //dotCount++;
                } else if (value.equals("B")) {
                    thisValue = CellValue.BIGDOT;
                   //dotCount++;
                } else if (value.equals("1")) {
                    thisValue = CellValue.GHOST1HOME;
                    ghost1Row = row;
                    ghost1Column = column;
                } else if (value.equals("2")) {
                    thisValue = CellValue.GHOST2HOME;
                    ghost2Row = row;
                    ghost2Column = column;
                } else if (value.equals("P")) {
                    thisValue = CellValue.PACMANHOME;
                    pacmanRow = row;
                    pacmanColumn = column;
                } else //(value.equals("E"))
                {
                    thisValue = CellValue.EMPTY;
                }
                grid[row][column] = thisValue;
                column++;
            }
            row++;
        }
        pacmanLocation = new Point2D(pacmanRow, pacmanColumn);
        pacmanVelocity = new Point2D(0, 0);
        ghost1Location = new Point2D(ghost1Row, ghost1Column);
        ghost1Velocity = new Point2D(-1, 0);
        ghost2Location = new Point2D(ghost2Row, ghost2Column);
        ghost2Velocity = new Point2D(-1, 0);
        currentDirection = Direction.NONE;
        lastDirection = Direction.NONE;
    }



    public void setCurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public static Direction getLastDirection() {
        return lastDirection;
    }

    public static Direction getCurrentDirection() {
        return currentDirection;
    }


    public Point2D getPacmanLocation() {
        return pacmanLocation;
    }

    public void setPacmanLocation(Point2D pacmanLocation) {
        this.pacmanLocation = pacmanLocation;
    }
    public Point2D getGhost1Location() {
        return ghost1Location;
    }

    public void setGhost1Location(Point2D ghost1Location) {
        this.ghost1Location = ghost1Location;
    }

    public Point2D getGhost2Location() {
        return ghost2Location;
    }

    public void setGhost2Location(Point2D ghost2Location) {
        this.ghost2Location = ghost2Location;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }


    public CellValue[][] getGrid() {
        return grid;
    }

    public CellValue getCellValue(int row, int column) {
        assert row >= 0 && row < this.grid.length && column >= 0 && column < this.grid[0].length;
        return this.grid[row][column];
    }


}
