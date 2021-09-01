package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.item.Item;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.ConsoleColors;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;
import com.bmcc.model.equipment.Weapon;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Game {
    private Character userPlayer;
    private Character enemyPlayer;
    private List<Character> characterList;
    private List<Weapon> weaponList;
    private List<Armor> armorList;
    private List<Item> itemList;
    private List<Magic> magicList;


    public void play() throws Exception {
        GameOutput.clearScreen();
        initGame();
        welcomeUser();
        pickCharacter();
        renameCharacter();
        pickEquipment(userPlayer, "weapon");
        Thread.sleep(3000);
        pickEquipment(userPlayer, "armor");
        Thread.sleep(3000);
        pickMagics(userPlayer);
        createEnemyCharacter();
        setEnemyPlayer();
        CoreLogic.controlFlow(userPlayer, enemyPlayer);
    }

    private void pickMagics(Character userPlayer) throws Exception {
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");
        userPlayer.setMagic(magic);
    }


    private void initGame() throws Exception {
        // todo: init character, weapon, armor, item and magic lists
        characterList = Character.getCharacterListFromJsonFile("asset/sampleCharacters.json");
        weaponList = Weapon.getWeaponListFromJsonFile("asset/sampleWeapons.json");
        armorList = Armor.getArmorListFromJsonFile("asset/sampleArmors.json");
    }

    private void welcomeUser() throws Exception {
        GameOutput.welcomePlayer();
        System.out.println();
        System.out.println();
        System.out.println();
        GameAudio.PlayWelcomeAudio();
        GameOutput.showInstructions();
    }


    private void renameCharacter() throws Exception {
        String setCharacterName = null;
        while (!("no".equalsIgnoreCase(setCharacterName) || "yes".equalsIgnoreCase(setCharacterName))) {
            setCharacterName = GameInput.getUserInput("Do you want to re-name your character? (yes or no) ");
        }

        if ("yes".equalsIgnoreCase(setCharacterName)) {
            // Let user pick custom name for their character
            String userName = GameInput.getUserInput("Please enter name for your character:");
            userPlayer.setName(userName);
        }
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
        userPlayer = characterList.get(userInput - 1);
        GameOutput.clearScreen();
        System.out.println("Great!! You picked: " + ConsoleColors.GREEN_BOLD + userPlayer.getName()
        + ConsoleColors.RESET);
    }


    private void pickEquipment(Character player, String type) throws IOException {
        GameOutput.clearScreen();
        int listSize = 0;
        if ("weapon".equalsIgnoreCase(type)) {
            GameOutput.displayWeaponList(weaponList);
            listSize = weaponList.size();
        } else if ("armor".equalsIgnoreCase(type)) {
            GameOutput.displayArmorList(armorList);
            listSize = armorList.size();
        }

        int userInput = 0;
        while (userInput < 1 || userInput > listSize) {
            try {
                String message = String.format("Please choose your %s from the list. (input number only)", type);
                userInput = Integer.parseInt(GameInput.getUserInput(message));
            } catch (Exception ignored) {
            }
        }

        if ("weapon".equalsIgnoreCase(type)) {
            player.setWeapon(weaponList.get(userInput - 1));
            System.out.println("Awesome!! You now have " + userPlayer.getWeapon().getName() + " for Weapon.");
        } else if ("armor".equalsIgnoreCase(type)) {
            player.setArmor(armorList.get(userInput - 1));
            System.out.println("Awesome!! You now have " + userPlayer.getArmor().getName() + " for Armor.");
            System.out.println();
        }

    }


    private void createEnemyCharacter() {
        while (enemyPlayer == null || enemyPlayer.equals(userPlayer)) {
            enemyPlayer = randomPicker(characterList);
        }
        System.out.println("And you are playing against: " + ConsoleColors.RED_BOLD + enemyPlayer.getName()
        + ConsoleColors.RESET);
    }

    private void setEnemyPlayer() {
        while (enemyPlayer.getWeapon() == null || enemyPlayer.getWeapon().equals(userPlayer.getWeapon())){
            enemyPlayer.setWeapon(randomPicker(weaponList));
        }

        while (enemyPlayer.getArmor() == null || enemyPlayer.getArmor().equals(userPlayer.getArmor())){
            enemyPlayer.setArmor((randomPicker(armorList)));
        }

    }


    private <T> T randomPicker(List<T> listOfThings) {
        Random random = new Random();
        int randInt = random.nextInt(listOfThings.size());
        return listOfThings.get(randInt);
    }
}

