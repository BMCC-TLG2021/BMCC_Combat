package com.bmcc.controller;

import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.skill.Magic;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameOutput;
import com.bmcc.model.character.Character;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public class Attacks {

    public static void physicalAttack(Character attacker, Character victim, String filePath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // reduce attacker's weapon integrity
        updateEquipmentIntegrity(attacker, attacker.getWeapon(), "weapon");

        // reduce victim's armor integrity
        updateEquipmentIntegrity(victim, victim.getArmor(), "armor");

        int damagePoint = attacker.getTotalPhysicalAttackPower() - victim.getTotalDefensePower();
        if (damagePoint > 0) {
            victim.damage(damagePoint);
        }
        GameOutput.writeToFile(filePath);
        List<String> actionDamage = GameOutput.showActionDamage(attacker, victim, damagePoint);
        GameOutput.writeStringsToFile(actionDamage);
        GameOutput.attackShowGraphics("asset/outputFile.txt");
        GameAudio.PlayAttackAudio();
    }

    public static void magicalAttack(Character attacker, Character victim, Magic magic, String filePath) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // reduce attacker's weapon integrity
        updateEquipmentIntegrity(attacker, attacker.getWeapon(), "weapon");

        // todo: should I reduce armor integrity for magic attack?

        int damagePoint = attacker.getTotalMagicalPower();

        if (attacker.reduceMagicPoint()) {
            victim.damage(damagePoint);
            GameOutput.writeToFile(filePath);
            List<String> actionDamage = GameOutput.showActionDamage(attacker, victim, damagePoint);
            GameOutput.writeStringsToFile(actionDamage);
            GameOutput.attackShowGraphics("asset/outputFile.txt");
            GameAudio.PlayMagicalAudio();
        } else {
            System.out.println("Player does not have enough Magic Power..");
        }
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
