module com.ismaile.paint.newpaintapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ismaile.paint.Labb3 to javafx.fxml;
    exports com.ismaile.paint.Labb3;
}