package com.bmcc.model.equipment;

import com.bmcc.model.equipment.MagicalWeapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MagicalWeaponTest {
    MagicalWeapon sampleMagicalWeapon;
    @Before
    public void setUp() throws Exception {
        sampleMagicalWeapon = MagicalWeapon.getInstance("testing wand", 1);
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
        sampleMagicalWeapon = MagicalWeapon.getInstanceFromJson("asset/sampleMagicalWeapon.json");
        assertEquals("Harry Potter's wand", sampleMagicalWeapon.getName());
        assertEquals(3, sampleMagicalWeapon.getMagicPowerIncrease());
    }

}