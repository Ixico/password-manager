module com.ixico.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;

    opens com.ixico.passwordmanager to javafx.fxml;
    exports com.ixico.passwordmanager;
}