package com.ixico.passwordmanager.service;

import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RequiredArgsConstructor
public class ArgonService {

    public String hash(String password) {
        byte[] salt = new byte[0];

        int iterations = 2;
        int memLimit = 200000;
        int hashLength = 32;
        int parallelism = 1;

        var parameters = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(iterations)
                .withMemoryAsKB(memLimit)
                .withParallelism(parallelism)
                .withSalt(salt)
                .build();

        var generator = new Argon2BytesGenerator();
        generator.init(parameters);
        var result1 = new byte[hashLength];
        var result2 = new byte[hashLength];
        generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), result1, 0, result1.length);

        generator.generateBytes(result1, result2, 0, result2.length);
        return new String(Base64.getEncoder().encode(result2));

    }


}
