package com.bmcc.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicalWeaponTest {
    PhysicalWeapon samplePhysicalWeapon;
    @Before
    public void setUp() throws Exception {
        samplePhysicalWeapon = PhysicalWeapon.getInstance("weapon name", 100.0);
    }

    @Test
    public void getName() {
        assertEquals("weapon name", samplePhysicalWeapon.getName());
    }

    @Test
    public void getDamage() {
        assertEquals(100.0, samplePhysicalWeapon.getDamage(),1);
    }

    @Test
    public void getInstanceFromJson() throws Exception {
        samplePhysicalWeapon = PhysicalWeapon.getInstanceFromJson("asset/samplePhysicalWeapon.json");
        assertEquals("Bad Ass Axe", samplePhysicalWeapon.getName());
        assertEquals(22.0, samplePhysicalWeapon.getDamage(),1);
    }
}