package com.bmcc.combat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



class WelcomeBanner {
    public static void welcomePlayer() {
        try {
            String welcomeBanner = Files.readString(Path.of("./BMCC.txt"));
            System.out.println(welcomeBanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showInstructions() {
        try {
            String menu = Files.readString(Path.of("./Instructions.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
