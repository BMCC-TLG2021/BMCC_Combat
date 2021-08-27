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
        System.out.println();
        System.out.println();
        GameOutput.attackShowGraphics();
        System.out.println();
        GameOutput.showInstructions();

    }

    private void setPlayers() throws Exception {
        String userName = GameInput.getUserInput("Please enter name for your character:");

        Character userPlayer = new Character(userName, 100, 100, 10, 30);
        Character enemyPlayer = new Character("KING", 100, 100, 12, 25);

        PhysicalWeapon pWeapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        MagicalWeapon mWeapon = MagicalWeapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");

//        userPlayer.setWeapon(pWeapon);
        userPlayer.setWeapon(mWeapon);
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");

        userPlayer.setMagic(magic);

        controlFlow(userPlayer, enemyPlayer);
    }


    private void controlFlow(Character userPlayer, Character enemyPlayer) throws Exception {
        while (true) {
            String command = GameInput.getCommand();
            switch (command) {
                case "ATTACK ENEMY":
                    attackEnemy(userPlayer, enemyPlayer);
                    break;
                case "USE MAGIC":
                    useMagic(userPlayer, enemyPlayer);
                    break;
                case "END GAME":
                    System.out.println("GoodBye.....");
                    System.exit(0);
            }
        }
    }

    private void useMagic(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        int damagePoint = userPlayer.getTotalMagicalPower();

        if (userPlayer.reduceMagicPoint()) {
            enemyPlayer.damage(damagePoint);
            System.out.println("Magic Worked!!");
        } else {
            System.out.println("Player does not have enough Magic Power..");
        }

        Thread.sleep(3000);

        int enemyDamagePoint = enemyPlayer.getTotalPhysicalAttackPower() - userPlayer.getDefensePower();
        userPlayer.damage(enemyDamagePoint);
        System.out.println(userPlayer);
        System.out.println(enemyPlayer);
    }


    private void attackEnemy(Character userPlayer, Character enemyPlayer) throws InterruptedException {
        int damagePoint = userPlayer.getTotalPhysicalAttackPower() - enemyPlayer.getDefensePower();
        enemyPlayer.damage(damagePoint);
        System.out.println(userPlayer);
        System.out.println(enemyPlayer);
        Thread.sleep(3000);

        int enemyDamagePoint = enemyPlayer.getTotalPhysicalAttackPower() - userPlayer.getDefensePower();
        userPlayer.damage(enemyDamagePoint);
        System.out.println(userPlayer);
        System.out.println(enemyPlayer);
    }

//    private void decideWinner()
}
