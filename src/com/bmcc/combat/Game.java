package com.bmcc.combat;

import java.util.Arrays;

public class Game {
    private Character character;       // character?? maybe player
    private GameInput input;
//    private GameOutput output;

    public void startGame() throws Exception {
//        String userName = GameInput.getUserInput("Please enter name for your character:");

        PhysicalWeapon weapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        Character userPlayer = new Character("userName", 100, 100, 10, 30, weapon);
        Character enemyPlayer = new Character("KING", 100, 100, 12, 25, weapon);

        String command = GameInput.getCommand();

        if (command.equals("ATTACK ENEMY")) {
            int attackPoint = userPlayer.physicalAttack();
            int damagePoint = attackPoint + (int)weapon.getDamage() - enemyPlayer.getDefensePower();
            enemyPlayer.damage(damagePoint);
            System.out.println(userPlayer);
            System.out.println(enemyPlayer);
            Thread.sleep(300);

            int enemyAttackPoint = enemyPlayer.physicalAttack();
            int enemyDamagePoint = enemyAttackPoint + (int)enemyPlayer.getWeapon().getDamage() - userPlayer.getDefensePower();
            userPlayer.damage(enemyDamagePoint);
            System.out.println(userPlayer);
            System.out.println(enemyPlayer);
        }


    }
}
