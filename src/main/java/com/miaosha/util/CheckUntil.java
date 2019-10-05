package com.miaosha.util;

import java.util.Arrays;

/**
 * @author luther
 */
public class CheckUntil {
    private static final String token = "jieli";

    public static boolean checkSignatures(String signature, String timestamp, String nonce) {
        String[] strings = new String[]{nonce, token, timestamp};
        Arrays.sort(strings);
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            stringBuffer.append(string);
        }
        if (SHA1.encode(stringBuffer.toString()).equals(signature)) {
            return true;
        }
        return false;
    }
}
