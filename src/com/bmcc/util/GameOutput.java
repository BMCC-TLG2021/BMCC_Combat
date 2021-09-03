package com.bmcc.util;

import com.bmcc.model.character.Character;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Weapon;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
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
        int userDefensePower = userPlayer.getTotalDefensePower();
        int userAttackPower = userPlayer.getTotalPhysicalAttackPower();
        String userWeaponName = "";
        int userWeaponIntegrity = 0;
        if (userPlayer.getWeapon() != null) {
            userWeaponName = userPlayer.getWeapon().getName();
            userWeaponIntegrity = userPlayer.getWeapon().getIntegrity();
        }
        String userArmorName = "";
        int userArmorIntegrity = 0;
        if (userPlayer.getArmor() != null) {
            userArmorName = userPlayer.getArmor().getName();
            userArmorIntegrity = userPlayer.getArmor().getIntegrity();
        }


        String enemyName = enemyPlayer.getName();
        String enemyOccupation = enemyPlayer.getOccupation();
        String enemyRace = enemyPlayer.getRace();
        int enemyHitPoint = enemyPlayer.getHitPoint();
        int enemyMagicPoint = enemyPlayer.getMagicPoint();
        int enemyDefensePower = enemyPlayer.getTotalDefensePower();
        int enemyAttackPower = enemyPlayer.getTotalPhysicalAttackPower();
        String enemyWeaponName = "";
        int enemyWeaponIntegrity = 0;
        if (enemyPlayer.getWeapon() != null) {
            enemyWeaponName = enemyPlayer.getWeapon().getName();
            enemyWeaponIntegrity = enemyPlayer.getWeapon().getIntegrity();
        }

        String enemyArmorName = "";
        int enemyArmorIntegrity = 0;
        if (enemyPlayer.getArmor() != null) {
            enemyArmorName = enemyPlayer.getArmor().getName();
            enemyArmorIntegrity = enemyPlayer.getArmor().getIntegrity();
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
        st.addRow("Armor", "" + userArmorName, "", "" + enemyArmorName);
        st.addRow("Armor Integrity", "" + userArmorIntegrity, "", "" + enemyArmorIntegrity);
        st.print();
    }

    public static void displayCharacterList(List<Character> characterList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        int i = 1;
        st.setHeaders("ID", "Occupation", "Race", "Hit Point", "Magic Point", "Defense Power", "Attack Power");
        for (Character aChar : characterList) {
            String[] characterAttributes = new String[7];
            characterAttributes[0] = "" + i;
            i++;
//            characterAttributes[1] = aChar.getName();
            characterAttributes[1] = aChar.getOccupation();
            characterAttributes[2] = aChar.getRace();
            characterAttributes[3] = "" + aChar.getHitPoint();
            characterAttributes[4] = "" + aChar.getMagicPoint();
            characterAttributes[5] = "" + aChar.getTotalDefensePower();
            characterAttributes[6] = "" + aChar.getAttackPower();
            st.addRow(characterAttributes);
        }
        st.print();
    }

    public static void displayWeaponList(List<Weapon> weaponList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        int i = 1;
        st.setHeaders("ID", "Name", "Description", "Integrity", "Physical damage", "Magical Power Increased By");
        for (Weapon weapon: weaponList) {
            String[] weaponAttributes =  new String[6];
            weaponAttributes[0] = "" + i;
            i++;
            weaponAttributes[1] = weapon.getName();
            weaponAttributes[2] = weapon.getDesc();
            weaponAttributes[3] = "" + weapon.getIntegrity();
            weaponAttributes[4] = "" + weapon.getPhysicalDamage();
            weaponAttributes[5] = (weapon.getMagicPowerIncrease() * 100) + "%";
            st.addRow(weaponAttributes);
        }
        st.print();
    }

    public static void displayArmorList(List<Armor> armorList) {
        TableBuilder st = new TableBuilder();
        st.setShowVerticalLines(true);
        int i = 1;
        st.setHeaders("ID", "Name", "Description", "Integrity", "Defense Increase");
        for (Armor armor: armorList) {
            String[] armorAttributes =  new String[5];
            armorAttributes[0] = "" + i;
            i++;
            armorAttributes[1] = armor.getName();
            armorAttributes[2] = armor.getDesc();
            armorAttributes[3] = "" + armor.getIntegrity();
            armorAttributes[4] = "" + armor.getDefenceIncrease();
            st.addRow(armorAttributes);
        }
        st.print();
    }

    public static List<String> showActionDamage(Character attacker, Character victim, int damagePoint) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        List<String> actionDamage = new ArrayList<>();
        GameAudio.PlayResultAudio();
        actionDamage.add("************************************************************");
        actionDamage.add("*" + (attacker.getName() + ConsoleColors.RED_BOLD + " ATTACKED " + ConsoleColors.RESET + victim.getName()) + "*");
        actionDamage.add("************************************************************");
        if (damagePoint < 0) {
            damagePoint = 0;
        }
        actionDamage.add("*************************************************************");
        actionDamage.add("*" + (attacker.getName() + ConsoleColors.RED_BOLD + " CREATED " + damagePoint + " Damage to "
                + ConsoleColors.RESET + victim.getName()) + "*");
        actionDamage.add("**************************************************************");
        GameAudio.PlayResultAudio();
        return actionDamage;
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
    public static void showGameStory() {
        try {
            String menu = Files.readString(Path.of("asset/1STORY.txt"));
            System.out.println(ConsoleColors.RED_BOLD + menu + ConsoleColors.RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showWelcomeToWeaponStore() {
        try {
            String menu = Files.readString(Path.of("asset/WEAPONSTORE.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showYouWon() {
        try {
            String menu = Files.readString(Path.of("asset/YOUWON.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showYouLost() {
        try {
            String menu = Files.readString(Path.of("asset/YOULOST.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showGameOver() {
        try {
            String menu = Files.readString(Path.of("asset/GAMEOVER.txt"));
            System.out.println(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void showGameSaved() {
        try {
            String menu = Files.readString(Path.of("asset/GAMESAVED.txt"));
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

    public static void writeToFile(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer = new FileWriter("asset/outputFile.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.flush();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            bufferedReader.close();

            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStringsToFile(List<String> input) {
        try {
            FileWriter writer = new FileWriter("asset/outputFile.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (String s : input) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder process = (os.contains("windows")) ?
                new ProcessBuilder("cmd", "/c", "cls") :
                new ProcessBuilder("clear");
        try {
            process.inheritIO().start().waitFor();
        } catch (InterruptedException ignored) {
        }
    }
}