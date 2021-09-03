package com.bmcc.controller;

import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.util.GameAudio;
import com.bmcc.util.GameInput;
import com.bmcc.util.GameOutput;
import jdk.swing.interop.SwingInterOpUtils;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
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

    private void sellToPlayer(Equipment equipment) {
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
            System.out.println(equipment.getName() + " added to your backpack");
        } else {
            System.out.println("Your remaining balance is not enough to purchase this equipment!");
        }
    }

    private void buyFromPlayer(Equipment equipment) {
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

    private void changeEquipment(Equipment equipment) {
        if (equipment instanceof Weapon) {
            customer.setWeapon((Weapon) equipment);
        } else {
            customer.setArmor((Armor) equipment);
        }
        System.out.println(equipment.getName() + " equipped.");
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
        boolean stayIn = true;
        List<Weapon> weaponListBackpack = getWeaponInBackpack();
        List<Armor> armorListBackpack = getArmorInBackpack();
        while (stayIn) {
            String command = GameInput.getVendorCommand();
            GameOutput.clearScreen();

            switch (command.toUpperCase()) {
                case "SELL":
                    System.out.println("Here are all the equipment in your backpack");
                    System.out.println("------------ WEAPON LIST ------------");
                    GameOutput.displayWeaponList(getWeaponInBackpack());
                    System.out.println("------------ ARMOR LIST ------------");
                    GameOutput.displayArmorList(getArmorInBackpack());
                    // Please pick the equipment you want to sell
                    Equipment equipmentSell = pickEquipment(getWeaponInBackpack(), getArmorInBackpack());
                    buyFromPlayer(equipmentSell);
                    break;
                case "BUY":
                    System.out.println("You have $" + customer.getGold() + " to buy equipment.");
                    System.out.println("The following are all the equipment available in store");
                    System.out.println("------------ WEAPON LIST ------------");
                    GameOutput.displayWeaponList(this.getWeaponList());
                    System.out.println("------------ ARMOR LIST ------------");
                    GameOutput.displayArmorList(this.getArmorList());
                    // Please pick the equipment you want to buy
                    Equipment equipmentBuy = pickEquipment(this.getWeaponList(), this.getArmorList());
                    sellToPlayer(equipmentBuy);
                    break;
                case "CHANGE":
                    GameOutput.displayBackPack(customer.getEquipmentFromBackpack());
                    // Please pick the equipment you want to use
                    Equipment equipmentEquipped = pickEquipment(getWeaponInBackpack(), getArmorInBackpack());
                    changeEquipment(equipmentEquipped);
                    break;
                case "EXIT":
                  stayIn = false;
            }
        }
    }

    private List<Weapon> getWeaponInBackpack() {
        List<Weapon> weaponListBackpack = new ArrayList<>();
        for (Equipment equipment : customer.getEquipmentFromBackpack()) {
            if (equipment instanceof Weapon) {
                weaponListBackpack.add((Weapon) equipment);
            }
        }
        return weaponListBackpack;
    }

    private List<Armor> getArmorInBackpack() {
        List<Armor> armorListBackpack = new ArrayList<>();
        for (Equipment equipment : customer.getEquipmentFromBackpack()) {
            if (equipment instanceof Armor) {
                armorListBackpack.add((Armor) equipment);
            }
        }
        return armorListBackpack;
    }

    // Let user pick the equipment
    private Equipment pickEquipment(List<Weapon> weaponList, List<Armor> armorList) {
        Equipment equipment = null;
        String type = GameInput.getEquipmentType();
        int listSize = 0;
        if ("weapon".equalsIgnoreCase(type)) {
            listSize = weaponList.size();
        } else if ("armor".equalsIgnoreCase(type)) {
            listSize = armorList.size();
        }
        int userInput = 0;
        while (userInput < 1 || userInput > listSize) {
            try {
                String message = String.format("Please choose %s from the list. (input number only)", type);
                userInput = Integer.parseInt(GameInput.getUserInput(message));
            } catch (Exception ignored) {

            }
        }

        if ("weapon".equalsIgnoreCase(type)) {
            equipment = weaponList.get(userInput - 1);
            System.out.println("Awesome!! You picked " + equipment.getName() + ".");
        } else if ("armor".equalsIgnoreCase(type)) {
            equipment = armorList.get(userInput - 1);
            System.out.println("Awesome!! You now have " + equipment.getName() + ".");
        }
        return equipment;
    }
}
