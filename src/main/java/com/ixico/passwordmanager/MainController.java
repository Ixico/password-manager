package com.ixico.passwordmanager;

import com.ixico.passwordmanager.model.MainModel;
import com.ixico.passwordmanager.service.MainService;

public class MainController {

    private final MainService mainService = new MainService();

    private final MainModel mainModel;

    public MainController(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void onPasswordChanged(String text) {
        var hash = mainService.calculateHash(text);
        mainModel.setPasswordHashFragment(hash.substring(71));
        mainModel.setCaseRequirementFulfilled(!text.toLowerCase().equals(text));
    }
}
