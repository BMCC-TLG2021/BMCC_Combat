package com.bmcc.model.equipment;

import com.bmcc.model.equipment.Weapon;
import org.junit.Before;
import org.junit.Test;

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
    public void getName() {
        assertEquals("testing wand", sampleMagicalWeapon.getName());
    }

    @Test
    public void getMagicPowerIncrease() {
        assertEquals(1, sampleMagicalWeapon.getMagicPowerIncrease());
    }

    @Test
    public void getInstanceFromJson() throws Exception {
        sampleMagicalWeapon = Weapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");
        assertEquals("Harry Potter's wand", sampleMagicalWeapon.getName());
        assertEquals(3, sampleMagicalWeapon.getMagicPowerIncrease());
    }

}