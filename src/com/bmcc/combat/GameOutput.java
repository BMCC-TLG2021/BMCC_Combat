package com.bmcc.combat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameOutput {

    public static void showCharacterStatus(Character character) {
    }

    public static void showActionDamage(Character attacker, Character victim, String attackMethod, int damagePoint) {

    }

    public static void welcomePlayer() {
        try {
            String welcomeBanner = Files.readString(Path.of("asset/BMCC.txt"));
            System.out.println(welcomeBanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showInstructions() {
        try {
            String menu = Files.readString(Path.of("asset/Instructions.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void attackShowGraphics() {
        try {
            String menu = Files.readString(Path.of("asset/fight.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}