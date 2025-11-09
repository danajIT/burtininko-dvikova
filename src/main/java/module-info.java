module com.example.burtininkodvikova {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.desktop;

    opens com.example.burtininkodvikova.fxController to javafx.fxml;
    exports com.example.burtininkodvikova;
}