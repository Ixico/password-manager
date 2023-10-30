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
import org.kordamp.ikonli.material2.Material2OutlinedAL;
import org.kordamp.ikonli.material2.Material2OutlinedMZ;

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

    private final FontIcon lengthRequirementIcon;

    private final FontIcon caseRequirementIcon;

    private final FontIcon complexityRequirementIcon;

    private final FontIcon notCompromisedRequirementIcon;

    public Parent getParent() {
        return root;
    }

    // TODO: dodać silent mode (nie pokazuje hashu i requirementów)
    // TODO: poprawić zmiany ikonek (może ogarnąć inną bibliotekę do nich bo brakuje)
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
        this.lengthRequirementIcon = requirementIcon();
        this.caseRequirementIcon = requirementIcon();
        this.complexityRequirementIcon = requirementIcon();
        this.notCompromisedRequirementIcon = requirementIcon();

        initializeView();
    }

    private void initializeView() {
        customizeRoot();
        root.getChildren().addAll(
                logo,
                passwordCaption,
                passwordInput,
                checksumInputGroup,
                requirementsSeparator,
                requirements()
        );
        observeChecksum();
        observeRequirements();
    }

    private void observeChecksum() {
        model.getPasswordHashFragment().addListener((observableValue, oldValue, newValue) -> {
            checksumValue.setText(newValue);
        });
    }

    private void observeRequirements() {
        model.getCaseRequirementFulfilled().addListener((observableValue, oldValue, newValue) -> {
            caseRequirementIcon.setIconCode(Material2OutlinedAL.CHECK_CIRCLE);
            caseRequirementIcon.getStyleClass().remove(Styles.WARNING);
            caseRequirementIcon.getStyleClass().add(Styles.SUCCESS);
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

    private HBox requirements() {
        var hbox = new HBox();
        var leftVbox = new VBox();
        leftVbox.getChildren().addAll(
                requirement("Minimum 12 characters", lengthRequirementIcon),
                requirement("Numbers and symbols", complexityRequirementIcon)
        );
        var rightVbox = new VBox();
        rightVbox.getChildren().addAll(
                requirement("Uppercase and lowercase letters", caseRequirementIcon),
                requirement("Password not compromised", notCompromisedRequirementIcon)
        );
        leftVbox.setSpacing(20);
        rightVbox.setSpacing(20);
        hbox.getChildren().addAll(leftVbox, rightVbox);
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    private HBox requirement(String text, FontIcon requirementIcon) {
        var hbox = new HBox();
        var label = new Label(text);
        label.getStyleClass().add(Styles.TEXT_CAPTION);
        label.setGraphic(requirementIcon);
        hbox.getChildren().addAll(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);
        return hbox;
    }

    private FontIcon requirementIcon() {
        var icon = new FontIcon(Material2OutlinedAL.ERROR_OUTLINE);
        icon.getStyleClass().addAll(Styles.WARNING);
        return icon;
    }

    private Label requirementLabel(String text) {
        var label = new Label(text);
        label.getStyleClass().add(Styles.TEXT_CAPTION);
        var icon = new FontIcon(Material2AL.CHECK_CIRCLE);
        label.setGraphic(icon);
        icon.getStyleClass().addAll(Styles.SUCCESS);
        return label;
    }


}
