package com.bmcc.controller;

import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.GameOutput;
import com.bmcc.model.character.Character;
public class Attacks {

    public static void physicalAttack(Character attacker, Character victim){
        // reduce attacker's weapon integrity
        updateEquipmentIntegrity(attacker, attacker.getWeapon(), "weapon");

        // reduce victim's armor integrity
        updateEquipmentIntegrity(victim, victim.getArmor(), "armor");

        int damagePoint = attacker.getTotalPhysicalAttackPower() - victim.getDefensePower();
        if (damagePoint > 0) {
            victim.damage(damagePoint);
        }
        GameOutput.attackShowGraphics("asset/fight.txt");
        GameOutput.showActionDamage(attacker, victim, damagePoint);

        System.out.println("Attacker's weapon integrity is: " + attacker.getWeapon().getIntegrity());
//        System.out.println("victim's armor integrity is: " + attacker.getWeapon().getIntegrity());
    }

    public static void magicalAttack(Character attacker, Character victim, Magic magic){
        // reduce attacker's weapon integrity
        updateEquipmentIntegrity(attacker, attacker.getWeapon(), "armor");

        // todo: should I reduce armor integrity for magic attack?

        int damagePoint = attacker.getTotalMagicalPower();

        if (attacker.reduceMagicPoint()) {
            victim.damage(damagePoint);
            GameOutput.attackShowGraphics("asset/fight.txt");
            GameOutput.showActionDamage(attacker, victim, damagePoint);
        } else {
            System.out.println("Player does not have enough Magic Power..");
        }

        System.out.println("Attacker's weapon integrity is: " + attacker.getWeapon().getIntegrity());
    }

    private static void updateEquipmentIntegrity(Character player, Equipment equipment, String target){
        if (equipment == null) {
            return;
        }
        equipment.reduceIntegrity();
        if (equipment.getIntegrity() == 0){
            if ("weapon".equalsIgnoreCase(target)){
                player.setWeapon(null);
            } else if ("armor".equalsIgnoreCase(target)){
                player.setArmor(null);
            }
        }
    }
}