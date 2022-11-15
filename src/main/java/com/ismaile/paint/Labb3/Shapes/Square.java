package com.ismaile.paint.Labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    public Square(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getPointX() - getSize(), getPointY() - getSize(), getSize() * 2, getSize() * 2);
    }

    @Override
    public boolean isSelected(double pointX, double pointY) {
        return pointX >= getPointX() &&
                pointX <= getPointX() + getSize() &&
                pointY >= getPointY() &&
                pointY <= getPointY() + getSize();
    }

    @Override
    public String drawSvg() {
        String color = "#" + getColor().toString().substring(2, 10);

        return "<rect x =\"" + getPointX() + "\"" +
                " y=\"" + getPointY() + "\"" +
                " width=\"" + (getSize() * 2) + "\"" +
                " height=\"" + (getSize() * 2) + "\"" +
                " fill=\"" + color + "\"" + " />";
    }

}
