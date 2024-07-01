package com.bet.BettingGame.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageEncoder {
    public static void main(String[] args) {
        try {
            // Replace this with the path to your image file
            Path path = Path.of("C:\\Users\\SAN\\Pictures\\Saved Pictures\\game12.png");
            byte[] imageBytes = Files.readAllBytes(path);
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            System.out.println(base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
