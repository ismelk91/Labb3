package com.ismaile.paint.Labb3.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(double x, double y, double size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getPointX() - (getSize()), getPointY() - (getSize()), getSize() * 2, getSize() * 2);
    }

    @Override
    public boolean isSelected(double pointX, double pointY) {
        double distX = pointX - getPointX();
        double distY = pointY - getPointY();
        double distance = Math.sqrt((distX * distX) + (distY * distY));

        return distance <= getSize();
    }

    @Override
    public String drawSvg() {
        String color = "#" + getColor().toString().substring(2, 10);

        return "<circle cx=\"" + getPointX() + "\"" +
                " cy=\"" + getPointY() + "\"" +
                " r=\"" + getSize() + "\"" +
                " fill=\"" + color + "\"" + " />";
    }

}



