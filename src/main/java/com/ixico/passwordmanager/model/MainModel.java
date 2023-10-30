package com.ixico.passwordmanager.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

@Getter
public class MainModel {

    private final StringProperty passwordHashFragment;

    private final BooleanProperty lengthRequirementFulfilled;

    private final BooleanProperty caseRequirementFulfilled;

    private final BooleanProperty complexityRequirementFulfilled;

    private final BooleanProperty notCompromisedRequirementFulfilled;


    public MainModel() {
        this.passwordHashFragment = new SimpleStringProperty();
        this.lengthRequirementFulfilled = new SimpleBooleanProperty();
        this.caseRequirementFulfilled = new SimpleBooleanProperty();
        this.complexityRequirementFulfilled = new SimpleBooleanProperty();
        this.notCompromisedRequirementFulfilled = new SimpleBooleanProperty();
    }

    public void setPasswordHashFragment(String value) {
        passwordHashFragment.set(value);
    }

    public void setLengthRequirementFulfilled(boolean fulfilled) {
        lengthRequirementFulfilled.set(fulfilled);
    }

    public void setCaseRequirementFulfilled(boolean fulfilled) {
        caseRequirementFulfilled.set(fulfilled);
    }

    public void setComplexityRequirementFulfilled(boolean fulfilled) {
        complexityRequirementFulfilled.set(true);
    }

    public void setNotCompromisedRequirementFulfilled(boolean fulfilled) {
        notCompromisedRequirementFulfilled.set(fulfilled);
    }

}
