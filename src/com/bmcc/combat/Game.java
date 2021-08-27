package com.bmcc.combat;

public class Game {
    private Character character;       // character?? maybe player


    public void test(){
        //my test
    }

    public void startGame() throws Exception {

        GameOutput.welcomePlayer();
        GameOutput.showInstructions();
//        String userName = GameInput.getUserInput("Please enter name for your character:");

        PhysicalWeapon pWeapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        MagicalWeapon mWeapon = MagicalWeapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");
        Character userPlayer = new Character("userName", 100, 100, 10, 30);
        Character enemyPlayer = new Character("KING", 100, 100, 12, 25);

//        userPlayer.setWeapon(pWeapon);
        userPlayer.setWeapon(mWeapon);
        Magic magic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");

        userPlayer.setMagic(magic);

        String command = GameInput.getCommand();

        if (command.equals("ATTACK ENEMY")) {
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

        if (command.equals("USE MAGIC")) {
            int damagePoint = userPlayer.getTotalMagicalPower();

            if (userPlayer.reduceMagicPoint()) {
                enemyPlayer.damage(damagePoint);
                System.out.println("Magic Worked!!");
            }
            else {
                System.out.println("Player does not have enough Magic Power..");
            }

            Thread.sleep(3000);

            int enemyDamagePoint = enemyPlayer.getTotalPhysicalAttackPower() - userPlayer.getDefensePower();
            userPlayer.damage(enemyDamagePoint);
            System.out.println(userPlayer);
            System.out.println(enemyPlayer);
        }




    }
}
