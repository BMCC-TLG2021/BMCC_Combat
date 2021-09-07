package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class CoreLogic {

    public static void controlFlow(Player userPlayer, Character enemyPlayer) throws Exception {
        userPlayer.getPlayerReadyToFight();
        GameOutput.clearScreen();
        System.out.println("Your RANK is " + userPlayer.getRank() + ". You will FIGHT against " + enemyPlayer.getName()
        + ".");
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);

        while (userPlayer.getHitPoint() > 0 && enemyPlayer.getHitPoint() > 0) {
            boolean userWin =false;
            String command = GameInput.getCommand();
            GameOutput.clearScreen();
            switch (command.toUpperCase()) {
                case "ATTACK ENEMY":
                    GameOutput.emptyOutputFile();
                    Attacks.physicalAttack(userPlayer, enemyPlayer, "asset/graphics/userFight.txt");
                    break;
                case "USE MAGIC":
                    GameOutput.emptyOutputFile();
                    Attacks.magicalAttack(userPlayer, enemyPlayer, userPlayer.getMagic(), "asset/graphics/userFight.txt");
                    break;
                case "END GAME":
                    System.out.println("GoodBye.....");
                    GameAudio.PlayYouGameOverAudio();
                    GameOutput.showGameOver();
                    File file = new File("asset/outputFile.txt");
                    file.delete();
                    System.exit(0);
                    break;
                case "SAVE GAME":
                    GameHold gh = GameHold.createInstance(userPlayer);
                    gh.saveGame();
                    System.out.println("Great, your game has been saved.");
                    System.exit(0);
                    break;
                case "GIVE ME SUPERPOWER":
                    userPlayer.giveMeSuperPower();
                    System.out.println("BOOOM!! You're the superman NOW!!! ");
                    break;
            }

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
            userWin = checkWins(userPlayer, enemyPlayer);
            if (userWin){return;};
            // enemy player attack back.
            enemyAttack(enemyPlayer, userPlayer);
            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
            userWin = checkWins(userPlayer, enemyPlayer);

            if (userWin){return;};
        }
    }

    private static boolean checkWins(Player userPlayer, Character enemyPlayer) throws Exception {
        if (enemyPlayer.getHitPoint() <= 0) {
            GameOutput.clearScreen();

            GameAudio.PlayYouWonAudio();
            GameOutput.showYouWon();
            // delete outputFile.txt before game over
            File file = new File("asset/outputFile.txt");
            file.delete();

            // reward user.
            userPlayer.addGold(100);
            userPlayer.addEquipmentToBackpack((Equipment) enemyPlayer.getWeapon());
            userPlayer.addEquipmentToBackpack((Equipment)enemyPlayer.getArmor());
            userPlayer.rankUp();

            // user now can go directly to next battle or see wendor
            String command = GameInput.getSeeVendorCommand();
            switch (command.toUpperCase()){
                case "GO BATTLE":
                    // don't need to do anything
                    return true;

                case "SEE VENDOR":
                    Vendor v = Vendor.createInstance(userPlayer);
                    v.tradeEquipment();
                    return true;
                case "SAVE GAME":
                    GameHold gh = GameHold.createInstance(userPlayer);
                    gh.saveGame();
                    System.out.println("Okay, your game has been saved.");
                    System.exit(0);
            }
        } else if (userPlayer.getHitPoint() <= 0) {

            // todo: update failing store
            System.out.println(userPlayer.getName() + " Failed!");
            GameAudio.PlayYouLostAudio();
            GameOutput.showYouLost();
            GameAudio.PlayYouGameOverAudio();
            GameOutput.showGameOver();
            File file = new File("asset/outputFile.txt");
            file.delete();
            System.exit(0);
        }
        return false;
    }


    private static void enemyAttack(Character enemyPlayer, Character userPlayer) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        String anyKey = GameInput.getUserInput("Press enter to continue....");
        GameAudio.PlayAttackAudio();
        GameOutput.clearScreen();
        Attacks.physicalAttack(enemyPlayer, userPlayer, "asset/graphics/enemyFight.txt");

    }
}
