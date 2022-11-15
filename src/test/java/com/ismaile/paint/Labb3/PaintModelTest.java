package com.ismaile.paint.Labb3;

import com.ismaile.paint.Labb3.Shapes.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaintModelTest {
    PaintModel model = new PaintModel();

    @Test
    void checkDefaultSizeShape() {
        var actual = model.getSize();
        var expected = 40;

        assertEquals(expected,actual);
    }

    @Test
    void checkDefaultShape() {
        var actual = model.getShapeTypeObjectProperty();
        var expected = ShapeType.CIRCLE;

        assertEquals(expected,actual);
    }

}