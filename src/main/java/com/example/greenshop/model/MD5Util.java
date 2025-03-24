package com.example.greenshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class MD5Util {

    public static String getMD5(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md5.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}