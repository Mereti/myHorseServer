package com.example.myHorseServer.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class AuthmePasswordEncoder implements PasswordEncoder {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toLowerCase();
    private static final int SALT_LENGTH = 16;

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = generateHash();
        try {
            return encode(rawPassword.toString(), salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "ERROR";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String[] data = encodedPassword.split("\\$");
        try {
            return encode(rawPassword.toString(), data[2]).equals(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String encode(String raw, String salt) throws NoSuchAlgorithmException {
        return "$SHA$" + salt + "$" + hash(hash(raw) + salt);
    }

    private String hash(String toEncrypt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                toEncrypt.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String generateHash() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < SALT_LENGTH; i++) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
