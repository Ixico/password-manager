package com.ixico.passwordmanager.utils;

import atlantafx.base.layout.InputGroup;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class ViewUtils {

    public static HBox twoVbox(List<Node> first, List<Node> second) {
        var hbox = new HBox();
        var vbox1 = new VBox();
        vbox1.getChildren().addAll(first);
        var vbox2 = new VBox();
        vbox2.getChildren().addAll(second);
        vbox1.setAlignment(Pos.BASELINE_LEFT);
        vbox1.setFillWidth(true);
        vbox1.setSpacing(20);
        vbox2.setAlignment(Pos.BASELINE_LEFT);
        vbox2.setFillWidth(true);
        vbox2.setSpacing(20);
        hbox.getChildren().addAll(vbox1, vbox2);
        hbox.setSpacing(50);
        hbox.setFillHeight(true);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        return hbox;
    }

    public static HBox centeringHbox(Node... children) {
        var hbox = new HBox();
        var region = new Region();

        hbox.getChildren().addAll(children);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);
        return hbox;
    }

    public static ColumnConstraints getConstraints() {
        var constraint = new ColumnConstraints();
        constraint.setHalignment(HPos.LEFT);
        return constraint;
    }

}
