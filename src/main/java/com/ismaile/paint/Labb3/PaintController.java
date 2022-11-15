package com.ismaile.paint.Labb3;

import com.ismaile.paint.Labb3.Shapes.Shape;
import com.ismaile.paint.Labb3.Shapes.ShapeType;
import javafx.application.Platform;
import javafx.beans.Observable;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class
PaintController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider slider;
    @FXML
    private ChoiceBox<ShapeType> choiceBox;

    PaintModel model = new PaintModel();

    public void initialize() {
        slider.valueProperty().bindBidirectional(model.sizeProperty());
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        choiceBox.setItems(model.shapeTypesList);
        choiceBox.valueProperty().bindBidirectional(model.shapeTypeObjectPropertyProperty());
        model.getShapes().addListener(this::updateCanvas);
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            model.updateShapes(mouseEvent);
            updateCanvas(null);
        } else
            model.createShape(mouseEvent.getX(), mouseEvent.getY());
    }

    private void updateCanvas(Observable observable) {
        var context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Shape s : model.getShapes()) {
            s.draw(context);
        }
    }

    public void undoButtonClicked() {
        model.undo();
    }

    public void redoButtonClicked() {
        model.redo();
    }

    public void saveButtonClicked() {
        SaveToSvg svgFile = new SaveToSvg();
        svgFile.save(model);
    }

    public void closeButtonClicked() {
        Platform.exit();
    }
}


