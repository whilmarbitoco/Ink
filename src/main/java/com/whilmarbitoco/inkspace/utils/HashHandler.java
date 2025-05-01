package com.whilmarbitoco.inkspace.utils;


import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public class HashHandler {

    public static String hash(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    public static boolean compare(String text, String hashed) {
        return BCrypt.checkpw(text, hashed);
    }

    public static String generateFilename(String extension) {
        String randomName = UUID.randomUUID().toString();
        return randomName + (extension.startsWith(".") ? extension : "." + extension);
    }
}
