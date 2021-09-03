package com.bmcc.controller;

import com.bmcc.model.character.Player;
import com.bmcc.model.equipment.Armor;
import com.bmcc.model.equipment.Equipment;
import com.bmcc.model.equipment.Weapon;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameHold {
    private Player currentPlayer;

    private static GameHold instance = null;

    private GameHold(Player player) {
        currentPlayer = player;
    }

    // Create GameHold instance
    public static GameHold createInstance(Player player) {
        if (instance == null) {
            instance = new GameHold(player);
        }
        return instance;
    }

    public void saveGame() throws Exception {
        Vendor vendor = Vendor.createInstance(currentPlayer);
        List<Weapon> weaponListInBackpack = vendor.getWeaponInBackpack();
        List<Armor> armorListInBackpack = vendor.getArmorInBackpack();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("asset/savedGame/player.json"), currentPlayer);
            saveWeapons(weaponListInBackpack);
            saveArmors(armorListInBackpack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveWeapons(List<Weapon> weaponList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("asset/savedGame/weapons.json"), weaponList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveArmors(List<Armor> armorList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("asset/savedGame/armors.json"), armorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player retrieveGame() {
        Player player = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            player = mapper.readValue(new File("asset/savedGame/player.json"), Player.class);
            List<Equipment> backpack = player.getEquipmentFromBackpack();
            List<Weapon> weaponList = new ArrayList<>(Arrays.asList(mapper.readValue(new File("asset/savedGame/weapons.json"), Weapon[].class)));
            List<Armor> armorList = new ArrayList<>(Arrays.asList(mapper.readValue(new File("asset/savedGame/armors.json"), Armor[].class)));
            for (Weapon weapon : weaponList) {
                backpack.add(weapon);
            }
            for (Armor armor : armorList) {
                backpack.add(armor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
}
