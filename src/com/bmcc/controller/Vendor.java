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
        customer.removeEquipmentFromBackpack(equipment);
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
        GameOutput.clearScreen();
        GameAudio.PlayDoorAudio();
        GameOutput.showWelcomeToWeaponStore();
        boolean stayIn = true;
        while (stayIn) {
            String command = GameInput.getVendorCommand();
            GameOutput.clearScreen();

            switch (command.toUpperCase()) {
                case "SELL":
                    System.out.println("Here are all the equipment in your backpack. \n");
                    System.out.println("------------ WEAPON LIST ------------");
                    GameOutput.displayWeaponList(getWeaponInBackpack());
                    System.out.println("------------ ARMOR LIST ------------");
                    GameOutput.displayArmorList(getArmorInBackpack());
                    // Please pick the equipment you want to sell
                    Equipment equipmentSell = pickEquipment(getWeaponInBackpack(), getArmorInBackpack());
                    if (equipmentSell != null) {
                        buyFromPlayer(equipmentSell);
                    }
                    break;
                case "BUY":
                    System.out.println("\n You have $" + customer.getGold() + " to buy equipment. \n");
                    System.out.println("The following are all the equipment available in store \n");
                    System.out.println("------------ WEAPON LIST ------------");
                    GameOutput.displayWeaponList(this.getWeaponList());
                    System.out.println("------------ ARMOR LIST ------------");
                    GameOutput.displayArmorList(this.getArmorList());
                    // Please pick the equipment you want to buy
                    Equipment equipmentBuy = pickEquipment(this.getWeaponList(), this.getArmorList());
                    if (equipmentBuy != null) {
                        sellToPlayer(equipmentBuy);
                    }
                    break;
                case "EQUIP":
//                    GameOutput.displayBackPack(customer.getEquipmentFromBackpack());
                    GameOutput.displayWeaponList(getWeaponInBackpack());
                    GameOutput.displayArmorList(getArmorInBackpack());
                    // Please pick the equipment you want to use
                    Equipment equipmentEquipped = pickEquipment(getWeaponInBackpack(), getArmorInBackpack());
                    if (equipmentEquipped != null) {
                        changeEquipment(equipmentEquipped);
                    }
                    break;
                case "EXIT STORE":
                  stayIn = false;
            }
        }
    }


    List<Weapon> getWeaponInBackpack() {
        List<Weapon> weaponListBackpack = new ArrayList<>();
        for (Equipment equipment : customer.getEquipmentFromBackpack()) {
            if (equipment instanceof Weapon) {
                weaponListBackpack.add((Weapon) equipment);
            }
        }
        return weaponListBackpack;
    }


    List<Armor> getArmorInBackpack() {
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
        boolean keepPick = true;
        while (keepPick) {
            String commandPick = GameInput.getEquipmentType();
            int listSize = 0;
            switch(commandPick.toUpperCase()) {
                case "WEAPON":
                    listSize = weaponList.size();

                    if (listSize > 0) {
                        int userWeaponChoice = GameInput.getUserEquipmentChoice(listSize);
                        equipment = weaponList.get(userWeaponChoice - 1);
                        System.out.println("You chose " + equipment.getName() + ".");
                        keepPick = false;
                    } else {
                        System.out.println("There is no weapon available to pick");
                    }
                    break;
                case "ARMOR":
                    listSize = armorList.size();
                    if (listSize > 0) {
                        int userArmorChoice = GameInput.getUserEquipmentChoice(listSize);
                        equipment = armorList.get(userArmorChoice - 1);
                        System.out.println("You chose " + equipment.getName() + ".");
                        keepPick = false;
                    } else {
                        System.out.println("There is no armor available to pick");
                    }
                    break;
                case "GO BACK":
                    keepPick = false;
            }
        }
        return equipment;
    }
}
