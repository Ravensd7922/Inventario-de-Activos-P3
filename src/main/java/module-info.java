module com.p3.inventarioactivos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.p3.inventarioactivos to javafx.fxml;
    exports com.p3.inventarioactivos;
}