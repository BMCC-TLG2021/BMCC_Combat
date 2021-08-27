package com.bmcc.combat;

import java.sql.SQLOutput;

public class Game {
    private Character character;

    public void play() throws Exception {
        welcomeUser();
        setPlayers();
    }

    private void welcomeUser() {
        GameOutput.welcomePlayer();
        GameOutput.showInstructions();
    }

    private void setPlayers() throws Exception {
        String userName = GameInput.getUserInput("Please enter name for your character:");

        Character userPlayer = new Character(userName, 100, 100, 10, 30);
        Character enemyPlayer = new Character("KING", 100, 100, 12, 50);

        PhysicalWeapon pWeapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        MagicalWeapon mWeapon = MagicalWeapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");

//        userPlayer.setWeapon(pWeapon);
        userPlayer.setWeapon(mWeapon);
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");

        userPlayer.setMagic(magic);

        controlFlow(userPlayer, enemyPlayer);
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
            System.out.println("Magic Worked!!");
        } else {
            System.out.println("Player does not have enough Magic Power..");
        }

        if (enemyPlayer.getHitPoint() > 0) {
            letEnemyAttack(userPlayer, enemyPlayer);
        }
    }


    private void attackEnemy(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        int damagePoint = userPlayer.getTotalPhysicalAttackPower() - enemyPlayer.getDefensePower();
        enemyPlayer.damage(damagePoint);
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);


        if (enemyPlayer.getHitPoint() > 0) {
            letEnemyAttack(userPlayer, enemyPlayer);
        }
    }


    private void letEnemyAttack(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        Thread.sleep(1500);

        int enemyDamagePoint = enemyPlayer.getTotalPhysicalAttackPower() - userPlayer.getDefensePower();
        userPlayer.damage(enemyDamagePoint);
        GameOutput.showCharacterStatus(userPlayer, enemyPlayer);
    }


//    private void decideWinner()
}
