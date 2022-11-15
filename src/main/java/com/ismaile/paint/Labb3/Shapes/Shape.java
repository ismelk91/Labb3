package com.ismaile.paint.Labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public abstract class Shape {
    private final double pointX;
    private final double pointY;
    private double size;
    private Color color;

    public Shape(double x, double y, double size, Color color) {
        this.pointX = x;
        this.pointY = y;
        this.size = size;
        this.color = color;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public double getPointX() {
        return pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public abstract void draw(GraphicsContext context);

    public abstract boolean isSelected(double pointX, double pointY);

    public abstract String drawSvg();

    public static Shape createShape(ShapeType type, double pointX, double pointY, double size, Color color) {
        return switch (type) {
            case CIRCLE -> new Circle(pointX, pointY, size, color);
            case SQUARE -> new Square(pointX, pointY, size, color);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.pointX, pointX) == 0 && Double.compare(shape.pointY, pointY) == 0 && Double.compare(shape.size, size) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointX, pointY, size, color);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                ", size=" + size +
                ", color=" + color +
                '}';
    }

}

