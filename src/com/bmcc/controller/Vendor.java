package com.bmcc.controller;

import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameOutput;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public class Vendor {
    private List<Weapon> weaponList = Weapon.getWeaponListFromJsonFile("asset/sampleWeapons.json");
    private List<Armor> armorList = Armor.getArmorListFromJsonFile("asset/sampleArmors.json");
    private static Vendor instance = null;
    private Player customer;
    // constructor
    private Vendor(Player player) throws Exception {
        customer = player;
    }

    void showWeaponList() {
        GameOutput.displayWeaponList(this.getWeaponList());
    }

    void showArmorList() {
        GameOutput.displayArmorList(this.armorList);
    }

    void sellToPlayer(Equipment equipment) {
        int cost = equipment.getMoneyValue();
        int customerMoneyBalance = customer.getGold();
        if (customerMoneyBalance >= cost) {
            customer.addEquipmentToBackpack(equipment);
            customer.spendGold(cost);
            if (equipment instanceof Weapon) {
                this.getWeaponList().remove((Weapon) equipment);
            } else {
                this.getArmorList().remove((Armor) equipment);
            }
        } else {
            System.out.println("Your remaining balance is not enough to purchase this equipment!");
        }
    }

    void buyFromPlayer(Equipment equipment) {
        boolean removed = customer.removeEquipmentFromBackpack(equipment);
        if (removed) {
//            Equipment tradeEquipment = null;
//            List<Equipment> backpack = customer.getEquipmentFromBackpack();
//            for (Equipment equipment : backpack) {
//                if (equipmentName.equalsIgnoreCase(equipment.getName())) {
//                    tradeEquipment = equipment;
//                }
//            }
            int valueEquipment = equipment.getMoneyValue();
            customer.addGold(valueEquipment);
            // add trade equipment to vendor's lists
            if (equipment instanceof Weapon) {
                getWeaponList().add((Weapon)equipment);
            } else {
                getArmorList().add((Armor)equipment);
            }
            System.out.println("You made " + valueEquipment + " by selling " + equipment.getName());
        } else {
            System.out.println("You can't sell equipment that not in your backpack");
        }
    }

    public static Vendor createInstance(Player player) throws Exception {
        if (instance == null) {
            instance = new Vendor(player);
        }
        return instance;
    }

    // getter
    List<Weapon> getWeaponList() {
        return weaponList;
    }

    List<Armor> getArmorList() {
        return armorList;
    }

    public void tradeEquipment() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        GameAudio.PlayDoorAudio();
        GameOutput.showWelcomeToWeaponStore();
        // sell equipment
        // buy equipment
        // change equipment
    }
}
