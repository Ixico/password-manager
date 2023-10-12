package com.ixico.passwordmanager.view;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.controls.Spacer;
import atlantafx.base.controls.Tile;
import atlantafx.base.layout.InputGroup;
import atlantafx.base.theme.Styles;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;
import org.kordamp.ikonli.material2.Material2OutlinedAL;
import org.kordamp.ikonli.material2.Material2RoundAL;

public class MainView {

    private final VBox root;

    public MainView() {
        this.root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));
        initialize();
    }

    private void initialize() {
        var image = new Image("logo.png");
        var imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);
//        imageView.setFitHeight(100);
//        imageView.setFitWidth(200);


        var helpButton = new FontIcon(Material2AL.HELP);
        var modalPane = new ModalPane();
        var alert = new Alert(Alert.AlertType.INFORMATION);
        helpButton.setOnMouseClicked(event -> alert.show());
        var startHbox = new HBox();
        startHbox.getChildren().addAll(helpButton);
        startHbox.setAlignment(Pos.CENTER_RIGHT);
        startHbox.setPadding(new Insets(20));

        var passwordTitle = new Text("Enter Master Password:");
        passwordTitle.getStyleClass().add(Styles.TITLE_2);

        var passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setPrefWidth(300);
//        passwordField.getStyleClass().addAll(Styles.ROUNDED);

        var button = new Button("Generate");
//        button.getStyleClass().addAll(Styles.ROUNDED);
        button.setDefaultButton(true);

        var passwordInputGroup = new InputGroup(passwordField, button);
        passwordInputGroup.setAlignment(Pos.CENTER);

        var hashLabel = new Label("Checksum");
        hashLabel.getStyleClass().addAll(Styles.TEXT_CAPTION);

        var label = new Label("8a08d13");
        label.getStyleClass().addAll(Styles.TEXT_MUTED, Styles.TEXT_BOLD);

        var icon = new FontIcon(Material2AL.INFO);
//        icon.getStyleClass().addAll(Styles.WARNING);

        var inputGroup = new InputGroup(hashLabel, label);

        var spacer = new Spacer();
        spacer.setMaxWidth(icon.getIconSize());


        var hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(icon, inputGroup, spacer);
        hbox.setAlignment(Pos.CENTER);


        var label1 = labelWithTick("Minimum 12 characters");
        var label2 = labelWithTick("Uppercase and lowercase letters");
        var label3 = labelWithTick("Numbers and symbols");


        root.getChildren().addAll(imageView , passwordTitle,passwordInputGroup,hbox, new Separator(Orientation.HORIZONTAL), label1, label2, label3);
    }

    public Parent getParent() {
        return root;
    }

    private HBox labelWithTick(String text) {
        var hbox = new HBox();
        var label = new Label(text);
        label.getStyleClass().add(Styles.TEXT_CAPTION);
        var icon = new FontIcon(Material2AL.CHECK_CIRCLE);
        icon.getStyleClass().addAll(Styles.SUCCESS);
        hbox.getChildren().addAll(label, icon);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        return hbox;
    }

}
