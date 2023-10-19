module com.ixico.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires spring.security.crypto;
    requires commons.logging;
    requires org.bouncycastle.provider;
    requires lombok;

    opens com.ixico.passwordmanager to javafx.fxml;
    exports com.ixico.passwordmanager;
}