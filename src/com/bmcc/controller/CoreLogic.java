package com.bmcc.controller;

import com.bmcc.model.character.Character;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;

public class CoreLogic {

    public static void controlFlow(Character userPlayer, Character enemyPlayer) throws Exception {

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
            System.exit(0);
        } else if (userPlayer.getHitPoint() <= 0) {
            System.out.println(userPlayer.getName() + " Fail!");
            System.exit(0);
        }
    }


    private static void enemyAttack(Character enemyPlayer, Character userPlayer) throws InterruptedException {
        Thread.sleep(3000);
        Attacks.physicalAttack(enemyPlayer, userPlayer);
    }
}
