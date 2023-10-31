package com.ixico.passwordmanager;

import com.ixico.passwordmanager.model.MainModel;
import com.ixico.passwordmanager.service.MainService;
import org.apache.commons.lang.StringUtils;

public class MainController {

    private final MainService mainService = new MainService();

    private final MainModel mainModel;

    public MainController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void onPasswordChanged(String password) {
        var hash = mainService.calculateHash(password);
        mainModel.setPasswordHashFragment(hash.substring(71));

        mainModel.setCaseRequirementFulfilled(!password.toLowerCase().equals(password));
        mainModel.setComplexityRequirementFulfilled(!StringUtils.isAlphanumeric(password));
        mainModel.setLengthRequirementFulfilled(password.length() >= 12);
        mainModel.setNotCompromisedRequirementFulfilled(true);
    }
}
