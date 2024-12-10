package com.csproject;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {
    public static String sha256(String plainString) {
        return DigestUtils.sha256Hex(plainString.getBytes(StandardCharsets.UTF_8));
    }
}
