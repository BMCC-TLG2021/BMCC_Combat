package com.bmcc.model.equipment;

import com.bmcc.model.equipment.Weapon;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WeaponTest {
    Weapon sampleMagicalWeapon;
    Weapon samplePhysicalWeapon;
    @Before
    public void setUp() throws Exception {
        sampleMagicalWeapon = Weapon.getInstance("testing wand", 3,"A test weapon",
                10, 0.5);
samplePhysicalWeapon = Weapon.getInstance("test soward",10,"A testing soward",
50, 0.0);
    }

    @Test
    public void reduceIntegrity(){
        sampleMagicalWeapon.reduceIntegrity();
        assertEquals(sampleMagicalWeapon.getIntegrity(), 2);
    }

    @Test
    public void getInstanceFromJson() throws Exception {
        sampleMagicalWeapon = Weapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");
        assertEquals("Harry Potter's wand", sampleMagicalWeapon.getName());
        assertEquals(0.5, sampleMagicalWeapon.getMagicPowerIncrease(), 1);
    }

    @Test
    public void getWeaponListFromJsonFile() throws Exception {
        List<Weapon> sampleWeaponList = Weapon.getWeaponListFromJsonFile("asset/sampleWeapons.json");
        Weapon physicalWeapon = sampleWeaponList.get(0);
        Weapon magicalWeapon = sampleWeaponList.get(1);
        assertEquals(physicalWeapon.getName(), "Bad Ass Axe");
        assertEquals(physicalWeapon.getPhysicalDamage(), 25);
        assertEquals(magicalWeapon.getName(), "Harry Potter's wand");
    }


}