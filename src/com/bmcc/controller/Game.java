package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;
import com.bmcc.model.equipment.Weapon;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private Player userPlayer = null;
    private Character userCharacter;
    private Character enemyPlayer;
    private List<Character> characterList;
    private List<Character> enemyList;
    private Vendor vendor;


    public void play() throws Exception {
        GameOutput.clearScreen();
        initCharacter();
        welcomeUser();



        if (userPlayer == null) {
            pickCharacter();
            renameCharacter();
            createUserPlayer(userCharacter);
        }

        createEnemyList(userCharacter);

        pickMagics(userPlayer);

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
        System.out.println("You defeated all knights of 7 kingdoms, and You are now the ULTIMATE LORD " +
                "of The GREAT seven Kingdoms ");
    }


    private void createVendor(Player userPlayer) throws Exception {
        vendor = Vendor.createInstance((Player) userPlayer);
    }

    private void visitVendor(Character userPlayer) throws Exception {
        vendor.tradeEquipment();
    }

    private void createEnemyList(Character userCharacter) {
        enemyList = new ArrayList<>(characterList);
        if (userCharacter == null && userPlayer != null) {
            enemyList.removeIf(enemy -> userPlayer.getOccupation().equalsIgnoreCase(enemy.getOccupation())
                    && userPlayer.getRace().equalsIgnoreCase(enemy.getRace())
                    && userPlayer.getHitPoint() == enemy.getHitPoint());
        }

        enemyList.remove(userCharacter);
    }

    private void pickMagics(Player userPlayer) throws Exception {
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
        String resumeGame = GameInput.getUserInput("Do you wish to continue from previous session? [ YES OR NO ]");
        while (!resumeGame.equalsIgnoreCase("yes") && !resumeGame.equalsIgnoreCase("no")) {
            resumeGame = GameInput.getUserInput("Do you wish to continue from previous session? [ YES OR NO ]");
        }
        if (resumeGame.equalsIgnoreCase("yes")) {
            userPlayer = GameHold.retrieveGame();
        }
        GameOutput.clearScreen();
    }

    private void showGameGraphics() throws Exception {

        for (int i = 0; i < 6; i++){
            if (i%2 != 0){
                GameOutput.showEnemyFight2();
            } else {
                GameOutput.showUserFight2();
            }
            GameAudio.PlayMartialAudio();
            Thread.sleep(500);
            GameOutput.clearScreen();
        }
    }


    private void renameCharacter() throws Exception {
        String setCharacterName = null;
        // Let user pick custom name for their character//
              showGameGraphics();
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



    private <T> void equipEnemy(List<T> equipmentList, int weaponValue) {

        for (T equipment : equipmentList) {
            if (equipment instanceof Equipment) {
                if (((Equipment) equipment).getMoneyValue() == weaponValue) {
                    if (equipment instanceof Weapon) {
                        enemyPlayer.setWeapon((Weapon) equipment);
                        equipmentList.remove(equipment);

                        break;
                    }
                    if (equipment instanceof Armor) {
                        enemyPlayer.setArmor((Armor) equipment);

                        equipmentList.remove(equipment);
                        break;
                    }
                }
            }
        }

    }

    private <T> T randomPicker(List<T> listOfThings) {
        Random random = new Random();
        int randInt = random.nextInt(listOfThings.size());
        return listOfThings.get(randInt);
    }
}

