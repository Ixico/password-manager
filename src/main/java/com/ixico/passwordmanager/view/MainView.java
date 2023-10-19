package com.ixico.passwordmanager.view;

import atlantafx.base.layout.InputGroup;
import atlantafx.base.theme.Styles;
import com.ixico.passwordmanager.MainController;
import com.ixico.passwordmanager.model.MainModel;
import com.ixico.passwordmanager.service.MainService;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;

public class MainView {

    private final MainController controller;

    private final MainModel model;

    private final VBox root;

    private final ImageView logo;

    private final Text passwordCaption;

    private final InputGroup passwordInput;

    private final ProgressBar hashingProgress;

    private final InputGroup checksumInputGroup;

//    private final Label checksumLabel;

    private Label checksumValue;

    private final Separator requirementsSeparator;

    private final HBox lengthRequirement;

    private final HBox caseRequirement;

    private final HBox complexityRequirement;

    public Parent getParent() {
        return root;
    }

    public MainView(MainController mainController, MainModel mainModel) {
        this.controller = mainController;
        this.model = mainModel;
        this.root = new VBox();
        this.logo = logo();
        this.passwordCaption = passwordCaption();
        this.passwordInput = passwordInput();
        this.hashingProgress = hashingProgress();
        this.checksumInputGroup = checksumInputGroup();
        this.requirementsSeparator = requirementsSeparator();
        this.lengthRequirement = requirement("Minimum 12 characters");
        this.caseRequirement = requirement("Uppercase and lowercase letters");
        this.complexityRequirement = requirement("Numbers and symbols");
        initializeView();
    }

    private void initializeView() {
        customizeRoot();
        root.getChildren().addAll(
                logo,
                passwordCaption,
                passwordInput,
                checksumInputGroup,
                hashingProgress,
                requirementsSeparator,
                lengthRequirement,
                caseRequirement,
                complexityRequirement
        );
        observeChecksum();
    }

    private void observeChecksum() {
        model.getPasswordHashFragment().addListener((observableValue, oldValue, newValue) -> {
            checksumValue.setText(newValue);
        });
    }

    private void customizeRoot() {
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));
    }

    private ImageView logo() {
        var image = new Image("logo.png");
        var imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);
        return imageView;
    }

    private Text passwordCaption() {
        var passwordTitle = new Text("Enter Master Password:");
        passwordTitle.getStyleClass().add(Styles.TITLE_2);
        return passwordTitle;
    }

    private InputGroup passwordInput() {
        var passwordField = new PasswordField();
        passwordField.setPromptText("Master password...");
        passwordField.setAlignment(Pos.CENTER);
        passwordField.setPrefWidth(300);
        var service = new MainService();
        passwordField.setOnKeyTyped(e -> {
            controller.onPasswordChanged(passwordField.getText());
        });

        var button = new Button("Generate");
        button.setDefaultButton(true);

        var passwordInputGroup = new InputGroup(passwordField, button);
        passwordInputGroup.setAlignment(Pos.CENTER);
        return passwordInputGroup;
    }

    private ProgressBar hashingProgress() {
        var bar = new ProgressBar(0.3);
        bar.setMinHeight(10);
        bar.setMinWidth(200);
        return bar;
    }

    private InputGroup checksumInputGroup() {
        var hashLabel = new Label("Checksum");
        hashLabel.getStyleClass().addAll(Styles.TEXT_CAPTION);

        checksumValue = new Label("8a08d13");
        checksumValue.getStyleClass().addAll(Styles.TEXT_MUTED, Styles.TEXT_BOLD);

        var icon = new FontIcon(Material2AL.INFO);

        var tooltip = new Tooltip("Validate your password produces\ncorrect checksum");

        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_RIGHT);
        hashLabel.setTooltip(tooltip);
        hashLabel.setGraphic(icon);
        var inputGroup = new InputGroup(hashLabel, checksumValue);
        inputGroup.setAlignment(Pos.CENTER);
        return inputGroup;
    }

    private Separator requirementsSeparator() {
        return new Separator(Orientation.HORIZONTAL);
    }

    private HBox requirement(String text) {
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
