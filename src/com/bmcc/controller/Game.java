package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.item.Item;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;
import com.bmcc.model.equipment.Weapon;


import java.util.List;
import java.util.Random;

public class Game {
    private Character userPlayer;
    private Character enemyPlayer;
    private List<Character> characterList;
    private List<Weapon> weaponList;
    private List<Armor> armorList;
    private List<Item> itemList;


    public void play() throws Exception {
        initGame();
        welcomeUser();
        pickCharacter();
        createEnemyCharacter();
        pickEquipment(userPlayer, "weapon");
        pickEquipment(userPlayer, "armor");
        setPlayers();
        controlFlow(userPlayer, enemyPlayer);
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


    private void setPlayers() throws Exception {
        String setCharacterName = null;
        while (!("no".equalsIgnoreCase(setCharacterName) || "yes".equalsIgnoreCase(setCharacterName))){
            setCharacterName = GameInput.getUserInput("Do you want to re-name your character? (yes or no) ");
        }

        if ("yes".equalsIgnoreCase(setCharacterName)){
            // Let user pick custom name for their character
            String userName = GameInput.getUserInput("Please enter name for your character:");
            userPlayer.setName(userName);
        }

        // Create weapon by using external JSON file
        Weapon pWeapon = Weapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");

        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");
        userPlayer.setMagic(magic);

        // Set enemy player's weapon
        enemyPlayer.setWeapon(pWeapon);
    }


    private void pickCharacter () {
        String characterInput = "";

        while (!GameInput.isValidCharacter(characterInput, characterList)) {
            GameOutput.displayAllCharacters(characterList);
            characterInput = GameInput.getUserInput("Please select your character ID from above list:");

            for (Character aChar : characterList) {
                if (aChar.getName().equals(characterInput)) {
                    userPlayer = aChar;
                }
            }
        }
        System.out.println("Great!! You picked: " + userPlayer.getName());
    }


    private void pickEquipment(Character player, String type){
        int listSize = 0;
        if ("weapon".equalsIgnoreCase(type)){
            displayList(weaponList);
            listSize = weaponList.size();
        } else if ("armor".equalsIgnoreCase(type)){
            displayList(armorList);
            listSize = armorList.size();
        }

        int userInput = 0;
        while (userInput <1 || userInput > listSize){
            try {
                String message = String.format("Please choose your %s from the list. (input number only)", type);
                userInput = Integer.parseInt(GameInput.getUserInput(message));
            } catch (Exception ignored) {}
        }

        if ("weapon".equalsIgnoreCase(type)){
            player.setWeapon(weaponList.get(userInput-1));
        } else if ("armor".equalsIgnoreCase(type)){
            player.setArmor(armorList.get(userInput-1));
        }
    }


    private void createEnemyCharacter() {
        while (enemyPlayer == null || enemyPlayer.equals(userPlayer)) {
            enemyPlayer = randomPicker(characterList);
        }
        System.out.println("And you are playing against: " + enemyPlayer.getName());
    }


    private void controlFlow(Character userPlayer, Character enemyPlayer) throws Exception {

        while (userPlayer.getHitPoint() > 0 && enemyPlayer.getHitPoint() > 0) {

            String command = GameInput.getCommand();
            switch (command) {
                case "ATTACK ENEMY":
                    Attacks.physicalAttack(userPlayer,enemyPlayer);
                    break;
                case "USE MAGIC":
                    Attacks.magicalAttack(userPlayer, enemyPlayer,userPlayer.getMagic());
                    break;
                case "END GAME":
                    System.out.println("GoodBye.....");
                    System.exit(0);
            }
            checkWins(userPlayer.getHitPoint(), enemyPlayer.getHitPoint());

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);

            // enemy player attack back.
            enemyAttack();
            checkWins(userPlayer.getHitPoint(), enemyPlayer.getHitPoint());

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
        }
    }

    private void checkWins(int userPlayerHP, int enemyPlayerHP){
        if (enemyPlayerHP <= 0) {
            System.out.println(userPlayer.getName() + " Win!");
            System.exit(0);
        } else if (userPlayerHP <= 0) {
            System.out.println(userPlayer.getName() + " Fail!");
            System.exit(0);
        }
    }


    private void enemyAttack() throws InterruptedException {
        Thread.sleep(3000);
        Attacks.physicalAttack(enemyPlayer, userPlayer);
    }

    private <T> void displayList(List<T> itemList){
        int index = 1;
        for (T item : itemList){
            System.out.println("\nIndex: " + index);
            if (item instanceof Equipment){
                System.out.println("Name: "+ ((Equipment)item).getName());
                System.out.println("Desc: "+ ((Equipment)item).getDesc());
                System.out.println("Integrity: "+((Equipment)item).getIntegrity());
            }
            if (item instanceof Weapon){
                System.out.println("Physical Damage: " + ((Weapon) item).getPhysicalDamage());
                System.out.println("magicPowerIncrease: " + ((Weapon) item).getMagicPowerIncrease()*100 + "%");
            }
            index++;
        }
    }

    private <T> T randomPicker(List<T> listOfThings){
        Random random = new Random();
        int randInt = random.nextInt(listOfThings.size());
        return listOfThings.get(randInt);
    }
}

