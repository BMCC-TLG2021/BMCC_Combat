package com.bmcc.combat;

import java.sql.SQLOutput;

public class Game {
    private Character userPlayer;
    private Character enemyPlayer;

    public void play() throws Exception {
        welcomeUser();
        setPlayers();
        controlFlow(userPlayer, enemyPlayer);
    }

    private void welcomeUser() {
        GameOutput.welcomePlayer();
        System.out.println();
        System.out.println();
        System.out.println();
        GameOutput.showInstructions();

    }

    private void setPlayers() throws Exception {
        // Create player by using external JSON file
        userPlayer = Character.getInstanceFromJsonFile("asset/samplePlayerCharacter.json");
        enemyPlayer = Character.getInstanceFromJsonFile("asset/sampleEnemyCharacter.json");

        //
        String userName = GameInput.getUserInput("Please enter name for your character:");
        userPlayer.setName(userName);

        // Create weapon by using external JSON file
        PhysicalWeapon pWeapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        MagicalWeapon mWeapon = MagicalWeapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");

        // Set user player's weapon and magic skill
        userPlayer.setWeapon(mWeapon);
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");
        userPlayer.setMagic(magic);

        // Set enemy player's weapon
        enemyPlayer.setWeapon(pWeapon);
    }


    private void controlFlow(Character userPlayer, Character enemyPlayer) throws Exception {
        int userPlayerHP = userPlayer.getHitPoint();
        int enemyPlayerHP = enemyPlayer.getHitPoint();

        while (userPlayerHP > 0 && enemyPlayerHP > 0) {
            String command = GameInput.getCommand();
            switch (command) {
                case "ATTACK ENEMY":
                    attackEnemy(userPlayer, enemyPlayer);
                    userPlayerHP = userPlayer.getHitPoint();
                    enemyPlayerHP = enemyPlayer.getHitPoint();
                    break;
                case "USE MAGIC":
                    useMagic(userPlayer, enemyPlayer);
                    userPlayerHP = userPlayer.getHitPoint();
                    enemyPlayerHP = enemyPlayer.getHitPoint();
                    break;
                case "END GAME":
                    System.out.println("GoodBye.....");
                    System.exit(0);
            }
        }
        if (enemyPlayerHP <= 0) {
            System.out.println(userPlayer.getName() + " Win!");

        } else {
            System.out.println(userPlayer.getName() + " Fail!");
        }
        System.exit(0);
    }

    private void useMagic(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        int damagePoint = userPlayer.getTotalMagicalPower();

        if (userPlayer.reduceMagicPoint()) {
            enemyPlayer.damage(damagePoint);
            GameOutput.attackShowGraphics("asset/fight.txt");
            GameOutput.showActionDamage(userPlayer, enemyPlayer, damagePoint);
            GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
        } else {
            System.out.println("Player does not have enough Magic Power..");
        }

        if (enemyPlayer.getHitPoint() > 0) {
            letEnemyAttack(userPlayer, enemyPlayer);
        }
    }


    private void attackEnemy(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        int damagePoint = userPlayer.getTotalPhysicalAttackPower() - enemyPlayer.getDefensePower();
        if (damagePoint > 0) {
            enemyPlayer.damage(damagePoint);

        }
        GameOutput.attackShowGraphics("asset/fight.txt");
        GameOutput.showActionDamage(userPlayer, enemyPlayer, damagePoint);
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);


        if (enemyPlayer.getHitPoint() > 0) {
            letEnemyAttack(userPlayer, enemyPlayer);
        }
    }


    private void letEnemyAttack(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        Thread.sleep(1500);

        int enemyDamagePoint = enemyPlayer.getTotalPhysicalAttackPower() - userPlayer.getDefensePower();
        if (enemyDamagePoint > 0) {
            userPlayer.damage(enemyDamagePoint);
        }
        GameOutput.attackShowGraphics("asset/fight2.txt");
        GameOutput.showActionDamage(enemyPlayer, userPlayer, enemyDamagePoint);
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
    }


//    private void decideWinner()
}
