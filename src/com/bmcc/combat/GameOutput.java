package com.bmcc.combat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameOutput {

    public static void showCharacterStatus(Character userPlayer, Character enemyPlayer) {
        String userName = userPlayer.getName();
        int userHitPoint = userPlayer.getHitPoint();
        int userMagicPoint = userPlayer.getMagicPoint();
        int userDefensePower = userPlayer.getDefensePower();
        int userAttackPower = userPlayer.getAttackPower();

        String enemyName = enemyPlayer.getName();
        int enemyHitPoint = enemyPlayer.getHitPoint();
        int enemyMagicPoint = enemyPlayer.getMagicPoint();
        int enemyDefensePower = enemyPlayer.getDefensePower();
        int enemyAttackPower = enemyPlayer.getAttackPower();

        System.out.printf("%30s %30s", userName, enemyName);
        System.out.println();
        System.out.printf("%30s %30s", userHitPoint, enemyHitPoint);
        System.out.println();
        System.out.printf("%30s %30s", userMagicPoint, enemyMagicPoint);
        System.out.println();
        System.out.printf("%30s %30s", userDefensePower, enemyDefensePower);
        System.out.println();
        System.out.printf("%30s %30s", userAttackPower, enemyAttackPower);
        System.out.println();

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

}