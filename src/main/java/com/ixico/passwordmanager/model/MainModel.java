package com.ixico.passwordmanager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainModel {

    private final StringProperty passwordHashFragment;

    public MainModel() {
        this.passwordHashFragment = new SimpleStringProperty();
    }



    public void setPasswordHashFragment(String value) {
        passwordHashFragment.set(value);
    }
}
