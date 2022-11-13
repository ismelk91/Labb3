package com.ismaile.paint.Labb3;

import com.ismaile.paint.Labb3.Shapes.Shape;
import com.ismaile.paint.Labb3.Shapes.ShapeType;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class
PaintController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider slider;
    @FXML
    GraphicsContext context;
    @FXML
    private ChoiceBox<ShapeType> choiceBox;

    Stage stage;

    PaintModel model = new PaintModel();

    public void initialize() {
        slider.valueProperty().bindBidirectional(model.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        choiceBox.setItems(model.shapeTypesList);
        choiceBox.valueProperty().bindBidirectional(model.shapeTypeObjectPropertyProperty());
        model.getShapes().addListener(this::updateCanvas);
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        if( mouseEvent.getButton() == MouseButton.SECONDARY) {
            model.updateShapes(mouseEvent);
            updateCanvas(null);
        }
        else
            model.createShape(mouseEvent.getX(), mouseEvent.getY());
    }

    private void updateCanvas(Observable observable) {
        var context = canvas.getGraphicsContext2D();
        context.clearRect(0,0, canvas.getWidth(),canvas.getHeight());
        for (Shape s : model.getShapes()) {
            s.draw(context);
        }
    }

    public void undoButtonClicked(ActionEvent event) {
        model.undo();
    }

    public void redoButtonClicked(ActionEvent event) {
        model.redo();
    }

    public void saveButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG","*.svg"));
        File selectedFile = fileChooser.showSaveDialog(stage);

        if(selectedFile != null) {
            model.saveToSvg(selectedFile);
        }

    }

    public void closeButtonClicked(ActionEvent event) {
        Platform.exit();
    }
}


