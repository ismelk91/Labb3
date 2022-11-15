package com.ismaile.paint.Labb3;

import com.ismaile.paint.Labb3.Shapes.Circle;
import com.ismaile.paint.Labb3.Shapes.Shape;
import com.ismaile.paint.Labb3.Shapes.ShapeType;
import com.ismaile.paint.Labb3.Shapes.Square;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class PaintModel {
    private final DoubleProperty size;
    private final ObjectProperty<Color> color;
    private final ObjectProperty<ShapeType> shapeTypeObjectProperty;

    ObservableList<Shape> shapes;
    ObservableList<ShapeType> shapeTypesList;

    Deque<Command> undoStack = new ArrayDeque<>();
    Deque<Command> redoStack = new ArrayDeque<>();

    public PaintModel() {
        this.size = new SimpleDoubleProperty(40);
        this.color = new SimpleObjectProperty<>(Color.BLUE);
        this.shapeTypeObjectProperty = new SimpleObjectProperty<>(ShapeType.CIRCLE);
        this.shapes = FXCollections.observableArrayList();
        this.shapeTypesList = FXCollections.observableArrayList(ShapeType.values());
    }

    public double getSize() {
        return size.get();
    }

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public ObjectProperty<ShapeType> shapeTypeObjectPropertyProperty() {
        return shapeTypeObjectProperty;
    }

    public ShapeType getShapeTypeObjectProperty() {
        return shapeTypeObjectProperty.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void updateShapes(MouseEvent mouseEvent) {
        for (Shape shape : shapes) {
            if (shape.isSelected(mouseEvent.getX(), mouseEvent.getY())) {
                if (shape instanceof Circle) {
                    shape.setColor(color.getValue());
                    shape.setSize(size.getValue());
                }
                if (shape instanceof Square) {
                    shape.setColor(color.getValue());
                    shape.setSize(size.getValue());
                }
            }
        }
    }

    public void createShape(double x, double y) {
        var newShape = Shape.createShape
                (getShapeTypeObjectProperty(),
                        x, y,
                        getSize(),
                        getColor());

        shapes.add(newShape);
        addToUndoStack(newShape);
        addToRedoStack(newShape);
    }

    private void addToRedoStack(Shape newShape) {
        Command redo = () -> shapes.add(newShape);
        redoStack.push(redo);
    }

    private void addToUndoStack(Shape newShape) {
        Command undo = () -> shapes.remove(newShape);
        undoStack.push(undo);
    }

    public void redo() {
        Command undoToExecute = redoStack.pop();
        undoToExecute.execute();
    }

    public void undo() {
        Command undoToExecute = undoStack.pop();
        undoToExecute.execute();
    }

}

@FunctionalInterface
interface Command {
    void execute();
}



