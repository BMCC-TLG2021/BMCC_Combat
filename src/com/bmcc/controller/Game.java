package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.item.Item;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.ConsoleColors;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;
import com.bmcc.model.equipment.Weapon;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Player userPlayer;
    private Character userCharacter;
    private Character enemyPlayer;
    private List<Character> characterList;
    private List<Character> enemyList;
//    private List<Weapon> weaponList;
//    private List<Armor> armorList;
//    private List<Item> itemList;
//    private List<Magic> magicList;
    private Vendor vendor;


    public void play() throws Exception {
        GameOutput.clearScreen();
        initCharacter();
        welcomeUser();
        pickCharacter();
        createEnemyList(userCharacter);
        renameCharacter();
//        Player.visitVendor(Player.createInstanceFromCharacter(userPlayer));
        pickMagics(userCharacter);
        createUserPlayer(userCharacter);
        // todo create load game function
        createVendor(userPlayer);
        vendor.tradeEquipment();
        continuePlay(userPlayer);
    }

    private void createUserPlayer(Character userCharacter) {
        userPlayer = Player.createInstanceFromCharacter(userCharacter);
    }


    private void continuePlay(Player player) throws Exception {
        int userLevel = player.getRank();
        int startingIndex = 8 - userLevel;

        for (int i = startingIndex; i < enemyList.size(); i++) {
            enemyPlayer = enemyList.get(i);
            equipEnemy(vendor.getWeaponList(), ((i + 1) * 100));
            equipEnemy(vendor.getArmorList(), ((i + 1) * 100));
            CoreLogic.controlFlow(userPlayer, enemyPlayer);
        }
        System.out.println("You united 7 kingdoms and made your father's dream come true.");
    }


    private void createVendor(Player userPlayer) throws Exception {
        vendor = Vendor.createInstance((Player) userPlayer);
    }

    private void visitVendor(Character userPlayer) throws Exception {
        vendor.tradeEquipment();
    }

    private void createEnemyList(Character userPlayer) {
        enemyList = new ArrayList<>(characterList);
        enemyList.remove(userPlayer);
    }

    private void pickMagics(Character userPlayer) throws Exception {
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");
        userPlayer.setMagic(magic);
    }


    private void initCharacter() throws Exception {
        characterList = Character.getCharacterListFromJsonFile("asset/sampleCharacters.json");
    }

    private void welcomeUser() throws Exception {
        GameOutput.welcomePlayer();
        GameAudio.PlayWelcomeAudio();
        GameOutput.showInstructions();
    }


    private void renameCharacter() throws Exception {
        String setCharacterName = null;
        // Let user pick custom name for their character
        String userName = GameInput.getUserInput("Please enter name for your character:");
        userCharacter.setName(userName);
        GameOutput.clearScreen();

        GameOutput.showGameStory();
        GameAudio.PlayFightAudio();

        String anyKey = GameInput.getUserInput("Press enter to continue.... ");
    }


    private void pickCharacter() throws IOException {
        GameOutput.displayCharacterList(characterList);
        int userInput = 0;
        while (userInput < 1 || userInput > characterList.size()) {
            try {
                String message = String.format("Please select a character ID from above list: ");
                userInput = Integer.parseInt(GameInput.getUserInput(message));
            } catch (Exception ignored) {
            }
        }
        userCharacter = characterList.get(userInput - 1);
        GameOutput.clearScreen();
    }


//    private void pickEquipment(Character player, String type) throws IOException {
//        GameOutput.clearScreen();
//        int listSize = 0;
//        if ("weapon".equalsIgnoreCase(type)) {
//            GameOutput.displayWeaponList(weaponList);
//            listSize = weaponList.size();
//        } else if ("armor".equalsIgnoreCase(type)) {
//            GameOutput.displayArmorList(armorList);
//            listSize = armorList.size();
////        }
//
//        int userInput = 0;
//        while (userInput < 1 || userInput > listSize) {
//            try {
//                String message = String.format("Please choose your %s from the list. (input number only)", type);
//                userInput = Integer.parseInt(GameInput.getUserInput(message));
//            } catch (Exception ignored) {
//            }
//        }
//
//        if ("weapon".equalsIgnoreCase(type)) {
//            player.setWeapon(weaponList.get(userInput - 1));
//            System.out.println("Awesome!! You now have " + userCharacter.getWeapon().getName() + " for Weapon.");
//        } else if ("armor".equalsIgnoreCase(type)) {
//            player.setArmor(armorList.get(userInput - 1));
//            System.out.println("Awesome!! You now have " + userCharacter.getArmor().getName() + " for Armor.");
//            System.out.println();
//        }
//
//    }


//    private void createEnemyCharacter() {
//        while (enemyPlayer == null || enemyPlayer.equals(userCharacter)) {
////            enemyPlayer = randomPicker(characterList);
//            // create enemy for the list and remove from list once created
//            enemyPlayer = enemyList.get(0);
//            enemyList.remove(enemyPlayer);
//        }
//        System.out.println("And you are playing against: " + ConsoleColors.RED_BOLD + enemyPlayer.getName()
//                + ConsoleColors.RESET);
//    }
//
//    private void setEnemyPlayer() {
//        while (enemyPlayer.getWeapon() == null || enemyPlayer.getWeapon().equals(userCharacter.getWeapon())) {
//            enemyPlayer.setWeapon(randomPicker(weaponList));
//        }
//
//        while (enemyPlayer.getArmor() == null || enemyPlayer.getArmor().equals(userCharacter.getArmor())) {
//            enemyPlayer.setArmor((randomPicker(armorList)));
//        }
//
//    }

    private <T> void equipEnemy(List<T> equipmentList, int weaponValue) {

        for (T equipment : equipmentList) {
            if (equipment instanceof Equipment) {
                if (((Equipment) equipment).getMoneyValue() == weaponValue) {
                    if (equipment instanceof Weapon) {
                        enemyPlayer.setWeapon((Weapon) equipment);
//                        GameOutput.displayWeaponList((List<Weapon>)equipmentList);
//                        vendor.removeFromWeaponList((Weapon) equipment);
                        equipmentList.remove((Weapon) equipment);
//                        GameOutput.displayWeaponList((List<Weapon>)equipmentList);
                        break;
                    }
                    if (equipment instanceof Armor) {
                        enemyPlayer.setArmor((Armor) equipment);
//                        vendor.removeFromArmorList((Armor) equipment);
                        equipmentList.remove((Armor) equipment);
                        break;
                    }
                }
            }
        }

//        T pickOne = randomPicker(newEquipmentList);


    }

    private <T> T randomPicker(List<T> listOfThings) {
        Random random = new Random();
        int randInt = random.nextInt(listOfThings.size());
        return listOfThings.get(randInt);
    }
}

