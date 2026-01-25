package com.paul.util;

import java.security.SecureRandom;
import java.util.*;

public final class CredentialGenerator {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateUsername(
            String firstName,
            String lastName,
            Collection<String> existingUsernames) {

        String base = firstName + "." + lastName;
        String username = base;
        int counter = 1;

        while (existingUsernames.contains(username)) {
            username = base + counter++;
        }
        return username;
    }

    public static String generatePassword() {
        int length = 10;

        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(randomChar(LOWER));
        passwordChars.add(randomChar(UPPER));
        passwordChars.add(randomChar(DIGITS));
        passwordChars.add(randomChar(SPECIAL));

        String allChars = LOWER + UPPER + DIGITS + SPECIAL;
        while (passwordChars.size() < length) {
            passwordChars.add(randomChar(allChars));
        }

        Collections.shuffle(passwordChars, RANDOM);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    private static char randomChar(String source) {
        return source.charAt(RANDOM.nextInt(source.length()));
    }
}
