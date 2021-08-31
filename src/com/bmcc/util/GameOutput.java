package com.bmcc.util;

import com.bmcc.model.character.Character;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        String enemyName = enemyPlayer.getName();
        String enemyOccupation = enemyPlayer.getOccupation();
        String enemyRace = enemyPlayer.getRace();
        int enemyHitPoint = enemyPlayer.getHitPoint();
        int enemyMagicPoint = enemyPlayer.getMagicPoint();
        int enemyDefensePower = enemyPlayer.getDefensePower();
        int enemyAttackPower = enemyPlayer.getAttackPower();


//        System.out.printf("%30s %30s %30s", "Name", userName, enemyName);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Occupation", userOccupation, enemyOccupation);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Race", userRace, enemyRace);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Hit Point", userHitPoint, enemyHitPoint);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Magic Point", userMagicPoint, enemyMagicPoint);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Defense Power", userDefensePower, enemyDefensePower);
//        System.out.println();
//        System.out.printf("%30s %30s %30s", "Attack Power", userAttackPower, enemyAttackPower);
//        System.out.println();
//        System.out.println();


        TableBuilder st = new TableBuilder();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders(ConsoleColors.GREEN_BOLD + "You", userName, ConsoleColors.RESET
                + ConsoleColors.RED + "Enemy", enemyName + ConsoleColors.RESET);//optional - if not used then there will be no header and horizontal lines
        st.addRow("Occupation", userOccupation, "", enemyOccupation);
        st.addRow("Race", userRace, "", enemyRace);
        st.addRow("Hit Point", "" + userHitPoint, "", "" + enemyHitPoint);
        st.addRow("Magic Point", "" + userMagicPoint, "", "" + enemyMagicPoint);
        st.addRow("Defense Power", "" + userDefensePower, "", "" + enemyDefensePower);
        st.addRow("Attack Power", "" + userAttackPower, "", "" + enemyAttackPower);
        st.print();
    }

    public static void displayCharacterList(List<Character> characterList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        for(Character achar : characterList) {
            st.addRow("name: " + achar.getName());
            st.addRow("Occupation: " + achar.getOccupation());
            st.print();
        }
    }


    public static void displayAllCharacters(List<Character> characterList) {
        System.out.println();
        System.out.println(ConsoleColors.GREEN_BOLD + "List of Characters: \n"
                + "--------------------" + ConsoleColors.RESET);
        for (Character charItem : characterList) {
            System.out.println(
                    "[ name=" + charItem.getName() +
                            ", Race=" + charItem.getRace() +
                            ", Occupation=" + charItem.getOccupation() +
                            " ]");
        }
        System.out.println();
    }

    public static  void showActionDamage(Character attacker, Character victim, int damagePoint) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("************************************************************");
        System.out.println("*                                                          *");
        System.out.println("*"+(attacker.getName() + ConsoleColors.RED_BOLD + " ATTACKED " + ConsoleColors.RESET + victim.getName())+"*");
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
                System.out.println("*"+(attacker.getName() + ConsoleColors.RED_BOLD + " CREATED " + damagePoint + " Damage to "
                + ConsoleColors.RESET + victim.getName())+"*");
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