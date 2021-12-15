package com.example.javagradle.bl.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncrypter extends BCryptPasswordEncoder {
    public String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
