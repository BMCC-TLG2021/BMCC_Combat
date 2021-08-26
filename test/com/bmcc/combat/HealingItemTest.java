package com.bmcc.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HealingItemTest {
    HealingItem sampleHealingItem;
    @Before
    public void setUp() throws Exception {
        sampleHealingItem = HealingItem.getInstance("test Healing Item", 50.0, 0.0);
    }

    @Test
    public void getName() {
        assertEquals("test Healing Item", sampleHealingItem.getName());
    }

    @Test
    public void getHealHP() {
        assertEquals(50.0, sampleHealingItem.getHealHP(),1);
    }

    @Test
    public void getHealMP() {
        assertEquals(0.0, sampleHealingItem.getHealMP(),1);
    }

    @Test
    public void getInstanceFromJsonFile() throws Exception {
        sampleHealingItem = HealingItem.getInstanceFromJsonFile("asset/sampleHealingItem.json");
        assertEquals("Apple", sampleHealingItem.getName());
        assertEquals(10.0, sampleHealingItem.getHealHP(),1);
        assertEquals(0.0, sampleHealingItem.getHealMP(),1);
    }
}