package com.example.pacman2;


import javafx.scene.Group;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import com.example.pacman2.Model.CellValue;  //<----------------------------!!!!!!!!


public class View extends Group {

    public static double CELL_WIDTH = 20.0;


    @FXML private int rowCount;  // Anzahl der Reihen, holt er sich aus der FXML datei, deswegen auch das @
    @FXML private int columnCount; //Anzahl der Spalten, holt er sich aus der FXML datei, deswegen auch das @
    private ImageView[][] cellViews; // erstellt ein Array


    // Alle genutzen Tiles werden zunächst als leere Image Objekte erzeugt und im Anschluss mit den Sprites befüllt

    private Image pacmanRightImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image ghost1Image;
    private Image ghost2Image;
    private Image blueGhostImage;
    private Image wallImage;
    private Image bigDotImage;
    private Image smallDotImage;


    public void PacManView() {
        //hier kommen die Sprites rein, diese liegen in einem Ordner Namens Res
        this.pacmanRightImage = new Image(getClass().getResourceAsStream("src/main/resources/res/pacmanRight.gif"));
        this.pacmanUpImage = new Image(getClass().getResourceAsStream("src/main/resources/res/pacmanUp.gif"));
        this.pacmanDownImage = new Image(getClass().getResourceAsStream("src/main/resources/res/pacmanDown.gif"));
        this.pacmanLeftImage = new Image(getClass().getResourceAsStream("src/main/resources/res/pacmanLeft.gif"));
        this.ghost1Image = new Image(getClass().getResourceAsStream("src/main/resources/res/redghost.gif"));
        this.ghost2Image = new Image(getClass().getResourceAsStream("src/main/resources/res/ghost2.gif"));
        this.blueGhostImage = new Image(getClass().getResourceAsStream("src/main/resources/res/blueghost.gif"));
        this.wallImage = new Image(getClass().getResourceAsStream("src/main/resources/res/wall.png"));
        this.bigDotImage = new Image(getClass().getResourceAsStream("src/main/resources/res/whitedot.png"));
        this.smallDotImage = new Image(getClass().getResourceAsStream("src/main/resources/res/smalldot.png"));
    }


    public void update(Model model) {
        assert model.getRowCount() == this.rowCount && model.getColumnCount() == this.columnCount;
        //for each ImageView, set the image to correspond with the CellValue of that cell
        for (int row = 0; row < this.rowCount; row++){
            for (int column = 0; column < this.columnCount; column++){
                CellValue value = model.getCellValue(row, column);
                if (value == CellValue.WALL) {
                    this.cellViews[row][column].setImage(this.wallImage);
                }
                else if (value == CellValue.BIGDOT) {
                    this.cellViews[row][column].setImage(this.bigDotImage);
                }
                else if (value == CellValue.SMALLDOT) {
                    this.cellViews[row][column].setImage(this.smallDotImage);
                }
                else {
                    this.cellViews[row][column].setImage(null);
                }
                //check which direction PacMan is going in and display the corresponding image
                if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && (Model.getLastDirection() == Model.Direction.RIGHT || Model.getLastDirection() == Model.Direction.NONE)) {
                    this.cellViews[row][column].setImage(this.pacmanRightImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && Model.getLastDirection() == Model.Direction.LEFT) {
                    this.cellViews[row][column].setImage(this.pacmanLeftImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && Model.getLastDirection() == Model.Direction.UP) {
                    this.cellViews[row][column].setImage(this.pacmanUpImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && Model.getLastDirection() == Model.Direction.DOWN) {
                    this.cellViews[row][column].setImage(this.pacmanDownImage);
                }
                //dispaly regular ghost images otherwise
                else {
                    if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost1Image);
                    }
                    if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost2Image);
                    }
                }
            }
        }
    }





    public int getRowCount() {  //Funktion um die Anzahl der Reihen zurück zu geben
        return this.rowCount;
    }


    public void setRowCount(int rowCount) {  // setzt den Intwert aus der FXML fest und startet die Funktion Initialisiere Grid
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() { //Funktion um die Anzahl der Spalten zurück zu geben
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) { // setzt den Intwert aus der FXML fest und startet die Funktion Initialisiere Grid
        this.columnCount = columnCount;
        this.initializeGrid();
    }


    private void initializeGrid() {     // For Schleife welche ein Spielfeld im Schachbrettmuster erstellt.
                                        // Werte für höhe und breite
                                        //holt er sich aus den obigen Methoden bzw aus der FXML
                                        // das Spielfeld ist zunächst leer (leere Container) und wird später mit Sprites gefüllt
        if (this.rowCount > 0 && this.columnCount > 0) {
            this.cellViews = new ImageView[this.rowCount][this.columnCount];
            for (int row = 0; row < this.rowCount; row++) {
                for (int column = 0; column < this.columnCount; column++) {
                    ImageView imageView = new ImageView();
                    imageView.setX((double)column * CELL_WIDTH);
                    imageView.setY((double)row * CELL_WIDTH);
                    imageView.setFitWidth(CELL_WIDTH);
                    imageView.setFitHeight(CELL_WIDTH);
                    this.cellViews[row][column] = imageView;
                    this.getChildren().add(imageView);
                }
            }
        }
    }






}
