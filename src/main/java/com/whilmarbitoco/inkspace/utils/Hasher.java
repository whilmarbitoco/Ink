package com.whilmarbitoco.inkspace.utils;


import org.mindrot.jbcrypt.BCrypt;

public class Hasher {

    public static String hash(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    public static boolean compare(String text, String hashed) {
        return BCrypt.checkpw(text, hashed);
    }
}
