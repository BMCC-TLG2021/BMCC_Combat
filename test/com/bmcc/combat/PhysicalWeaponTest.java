package com.bmcc.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicalWeaponTest {
    PhysicalWeapon simpleWeapon;
    @Before
    public void setUp() throws Exception {
        simpleWeapon = PhysicalWeapon.getInstance("weapon name", 100.0);
    }

    @Test
    public void getName() {
        assertEquals("weapon name", simpleWeapon.getName());
    }

    @Test
    public void getDamage() {
        assertEquals(100.0, simpleWeapon.getDamage(),1);
    }

    @Test
    public void getInstanceFromJson() throws Exception {
        simpleWeapon = PhysicalWeapon.getInstanceFromJson("asset/simplePhysicalWeapon.json");
        assertEquals("Bad Ass Axe", simpleWeapon.getName());
        assertEquals(22.0, simpleWeapon.getDamage(),1);
    }
}