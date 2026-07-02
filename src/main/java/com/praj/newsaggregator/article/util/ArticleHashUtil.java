package com.praj.newsaggregator.article.util;

import java.security.MessageDigest;

public class ArticleHashUtil {

    public static String generate(String title, String url) {
        try {
            String input = title + "|" + url;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}