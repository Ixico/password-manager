package com.ixico.passwordmanager.service;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class MainService {

    private final Argon2PasswordEncoder encoder;

    public MainService() {
        this.encoder = new Argon2PasswordEncoder(0, 32, 1, 4096, 10);
    }

    public String calculateHash(String password) {
        return encoder.encode(password);
    }




}
