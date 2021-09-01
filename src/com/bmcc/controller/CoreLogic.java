package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class CoreLogic {

    public static void controlFlow(Character userPlayer, Character enemyPlayer) throws Exception {

        while (userPlayer.getHitPoint() > 0 && enemyPlayer.getHitPoint() > 0) {

            String command = GameInput.getCommand();
            GameOutput.clearScreen();
            switch (command) {
                case "ATTACK ENEMY":
                    Attacks.physicalAttack(userPlayer, enemyPlayer, "asset/userFight.txt");
                    break;
                case "USE MAGIC":
                    Attacks.magicalAttack(userPlayer, enemyPlayer, userPlayer.getMagic(), "asset/userFight.txt");
                    break;
                case "END GAME":
                    System.out.println("GoodBye.....");
                    File file = new File("asset/outputFile.txt");
                    file.delete();
                    System.exit(0);
            }
            checkWins(userPlayer, enemyPlayer);

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);

            // enemy player attack back.
            enemyAttack(enemyPlayer, userPlayer);
            checkWins(userPlayer, enemyPlayer);

            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
        }
    }

    private static void checkWins(Character userPlayer, Character enemyPlayer){
        if (enemyPlayer.getHitPoint() <= 0) {
            System.out.println(userPlayer.getName() + " Win!");
            // delete outputFile.txt before game over
            File file = new File("asset/outputFile.txt");
            file.delete();
            System.exit(0);
        } else if (userPlayer.getHitPoint() <= 0) {
            System.out.println(userPlayer.getName() + " Fail!");
            File file = new File("asset/outputFile.txt");
            file.delete();
            System.exit(0);
        }
    }


    private static void enemyAttack(Character enemyPlayer, Character userPlayer) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        Thread.sleep(3000);
        GameOutput.clearScreen();
        Attacks.physicalAttack(enemyPlayer, userPlayer, "asset/enemyFight.txt");
    }
}
