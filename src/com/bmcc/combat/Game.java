package com.bmcc.combat;

import java.util.Arrays;

public class Game {
    private Character character;       // character?? maybe player
    private GameInput input;
//    private GameOutput output;

    public void startGame() {
//        String userName = GameInput.getUserInput("Please enter name for your character:");
        String command = GameInput.getCommand();

        if (command.equals("START GAME")) {
            Character userPlayer = new Character("userName", 100, 100, 10, 30, new Weapon("SWORD"));
            Character enemyPlayer = new Character("KING", 100, 100, 10, 30, new Weapon("BAZOOKA"));

            if (command.equals("ATTACK ENEMY"))
                int attackPoint = userPlayer.physicalAttack();
            int damagePoint = attackPoint - enemyPlayer.getDefensePower();
            enemyPlayer.damage(damagePoint);
        }

    }
}
