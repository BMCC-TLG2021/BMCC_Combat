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
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);

        while (userPlayer.getHitPoint() > 0 && enemyPlayer.getHitPoint() > 0) {

            String command = GameInput.getCommand();
            GameOutput.clearScreen();
            switch (command.toUpperCase()) {
                case "ATTACK ENEMY":
                    Attacks.physicalAttack(userPlayer, enemyPlayer, "asset/userFight.txt");
                    break;
                case "USE MAGIC":
                    Attacks.magicalAttack(userPlayer, enemyPlayer, userPlayer.getMagic(), "asset/userFight.txt");
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
                    GameOutput.showGameSaved();
                    GameAudio.PlayGameSavedAudio();

                    // todo: insert save game method.
            }

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
            checkWins(userPlayer, enemyPlayer);

            // enemy player attack back.
            enemyAttack(enemyPlayer, userPlayer);
            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
            checkWins(userPlayer, enemyPlayer);
        }
    }

    private static void checkWins(Player userPlayer, Character enemyPlayer) throws Exception {
        if (enemyPlayer.getHitPoint() <= 0) {
            System.out.println(userPlayer.getName() + " Win!");
            GameAudio.PlayYouWonAudio();
            GameOutput.showYouWon();
            // delete outputFile.txt before game over
            File file = new File("asset/outputFile.txt");
            file.delete();

            // reward user.
            userPlayer.addGold(100);
            userPlayer.addEquipmentToBackpack((Equipment) enemyPlayer.getWeapon());
            userPlayer.addEquipmentToBackpack((Equipment)enemyPlayer.getArmor());

            // user now can go directly to next battle or see wendor
            String command = GameInput.getSeeVendorCommand();
            switch (command.toUpperCase()){
                case "GO BATTLE":
                    // don't need to do anything
                    break;
                case "SEE VENDOR":
                    Vendor v = Vendor.createInstance(userPlayer);
                    v.tradeEquipment();
                    GameAudio.PlayDoorAudio();
                    GameOutput.showWelcomeToWeaponStore();
            }
        } else if (userPlayer.getHitPoint() <= 0) {

            // todo: update failing store
            System.out.println(userPlayer.getName() + " Fail!");
            GameAudio.PlayYouLostAudio();
            GameOutput.showYouLost();
            Thread.sleep(300);
            GameAudio.PlayYouGameOverAudio();
            GameOutput.showGameOver();
            File file = new File("asset/outputFile.txt");
            file.delete();
            System.exit(0);
        }
    }


    private static void enemyAttack(Character enemyPlayer, Character userPlayer) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        Thread.sleep(4500);
        GameAudio.PlayAttackAudio();
        GameOutput.clearScreen();
        Attacks.physicalAttack(enemyPlayer, userPlayer, "asset/enemyFight.txt");

    }
}
