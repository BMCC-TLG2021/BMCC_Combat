package com.bmcc.util;

import com.bmcc.model.character.Character;
import com.bmcc.model.equipment.Weapon;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameOutput {

    public static void showCharacterStatus(Character userPlayer, Character enemyPlayer) {
        String userName = userPlayer.getName();
        String userOccupation = userPlayer.getOccupation();
        String userRace = userPlayer.getRace();
        int userHitPoint = userPlayer.getHitPoint();
        int userMagicPoint = userPlayer.getMagicPoint();
        int userDefensePower = userPlayer.getDefensePower();
        int userAttackPower = userPlayer.getAttackPower();
        String userWeaponName = "";
        int userWeaponIntegrity = 0;
        Weapon userWeapon = userPlayer.getWeapon();
        if (userWeapon != null) {
            userWeaponName = userWeapon.getName();
            userWeaponIntegrity = userWeapon.getIntegrity();
        }


        String enemyName = enemyPlayer.getName();
        String enemyOccupation = enemyPlayer.getOccupation();
        String enemyRace = enemyPlayer.getRace();
        int enemyHitPoint = enemyPlayer.getHitPoint();
        int enemyMagicPoint = enemyPlayer.getMagicPoint();
        int enemyDefensePower = enemyPlayer.getDefensePower();
        int enemyAttackPower = enemyPlayer.getAttackPower();
        String enemyWeaponName = "";
        int enemyWeaponIntegrity = 0;
        Weapon enemyWeapon = enemyPlayer.getWeapon();
        if (enemyWeapon != null) {
            enemyWeaponName = enemyWeapon.getName();
            enemyWeaponIntegrity = enemyWeapon.getIntegrity();
        }

        TableBuilder st = new TableBuilder();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("You", userName, "Enemy", enemyName);//optional - if not used then there will be no header and horizontal lines
        st.addRow("Occupation", userOccupation, "", enemyOccupation);
        st.addRow("Race", userRace, "", enemyRace);
        st.addRow("Hit Point", "" + userHitPoint, "", "" + enemyHitPoint);
        st.addRow("Magic Point", "" + userMagicPoint, "", "" + enemyMagicPoint);
        st.addRow("Defense Power", "" + userDefensePower, "", "" + enemyDefensePower);
        st.addRow("Attack Power", "" + userAttackPower, "", "" + enemyAttackPower);
        st.addRow("Weapon", "" + userWeaponName, "", "" + enemyWeaponName);
        st.addRow("Weapon Integrity", "" + userWeaponIntegrity, "", "" + enemyWeaponIntegrity);
        st.print();
    }

    public static void displayCharacterList(List<Character> characterList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        int i = 1;
        st.setHeaders("ID", "Name", "Occupation", "Race", "Hit Point", "Magic Point", "Defense Power", "Attack Power");
        for (Character aChar : characterList) {
            String[] characterAttributes = new String[8];
            characterAttributes[0] = "" + i;
            i++;
            characterAttributes[1] = aChar.getName();
            characterAttributes[2] = aChar.getOccupation();
            characterAttributes[3] = aChar.getRace();
            characterAttributes[4] = "" + aChar.getHitPoint();
            characterAttributes[5] = "" + aChar.getMagicPoint();
            characterAttributes[6] = "" + aChar.getDefensePower();
            characterAttributes[7] = "" + aChar.getAttackPower();
            st.addRow(characterAttributes);
        }
        st.print();
    }

    public static void displayWeaponList(List<Weapon> weaponList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        int i = 1;
        st.setHeaders("ID", "Name", "Physical damage");
        for (Weapon weapon: weaponList) {
            String[] weaponAttributes =  new String[3];
            weaponAttributes[0] = "" + i;
            i++;
            weaponAttributes[1] = weapon.getName();
            weaponAttributes[2] = "" + weapon.getPhysicalDamage();

            st.addRow(weaponAttributes);
        }
        st.print();
    }

    public static void showActionDamage(Character attacker, Character victim, int damagePoint) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("************************************************************");
        System.out.println("*                                                          *");
        System.out.println("*" + (attacker.getName() + ConsoleColors.RED_BOLD + " ATTACKED " + ConsoleColors.RESET + victim.getName()) + "*");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.println("************************************************************");
        System.out.println();
        System.out.println();

        if (damagePoint < 0) {
            damagePoint = 0;
        }


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("*****************************************************************************************");
        System.out.println("*****************************************************************************************");
        System.out.println("*                                                                                       *");
        System.out.println("*" + (attacker.getName() + ConsoleColors.RED_BOLD + " CREATED " + damagePoint + " Damage to "
                + ConsoleColors.RESET + victim.getName()) + "*");
        System.out.println("*                                                                                       *");
        System.out.println("*****************************************************************************************");
        System.out.println("*****************************************************************************************");
        System.out.println();

    }

    public static void welcomePlayer() {
        try {
            String welcomeBanner = Files.readString(Path.of("asset/BMCC.txt"));
            System.out.println(ConsoleColors.GREEN_BOLD + welcomeBanner + ConsoleColors.RESET);
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

    public static void attackShowGraphics(String path) {
        // clear console magic only works for Mac / linux
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            String menu = Files.readString(Path.of(path));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}