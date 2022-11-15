package com.ismaile.paint.Labb3;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveToSvg {
    FileChooser fileChooser = new FileChooser();
    List<String> strings = new ArrayList<>();
    Path filePath;
    Stage stage;

    public void save(PaintModel model) {
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG (*.svg)", "*.svg"));
        fileChooser.setInitialFileName("Namnlös");
        filePath = fileChooser.showSaveDialog(stage).toPath();

        toSvg(model, strings);

        try {
            Files.write(filePath, strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toSvg(PaintModel model, List<String> strings) {
        strings.add("<svg " + "xmlns=\"http://www.w3.org/2000/svg\" " +
                "version=\"1.1\"\n " + "width=\"1024.0\" " + "height=\"720.0\">");

        model.getShapes().forEach(shape -> strings.add(shape.drawSvg()));
        strings.add("</svg>");
    }

}
