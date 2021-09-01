package com.bmcc.model.item;

import com.bmcc.model.item.HealingItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HealingItemTest {
    HealingItem sampleHealingItem;
    @Before
    public void setUp() throws Exception {
        sampleHealingItem = HealingItem.getInstance("test Healing Item", 50, 0);
    }

    @Test
    public void getName() {
        assertEquals("test Healing Item", sampleHealingItem.getName());
    }

    @Test
    public void getHealHP() {
        assertEquals(50, sampleHealingItem.getHealHP());
    }

    @Test
    public void getHealMP() {
        assertEquals(0, sampleHealingItem.getHealMP());
    }

    @Test
    public void getInstanceFromJsonFile() throws Exception {
        sampleHealingItem = HealingItem.getInstanceFromJsonFile("asset/sampleHealingItem.json");
        assertEquals("Apple", sampleHealingItem.getName());
        assertEquals(10, sampleHealingItem.getHealHP());
        assertEquals(0, sampleHealingItem.getHealMP());
    }

    @Test
    public void getHealingItemListFromJsonFile() {
        List<HealingItem> healingItemList = HealingItem.getHealItemListFromJsonFile("asset/sampleHealingItems.json");
        HealingItem healingItem3 = healingItemList.get(2);
        assertEquals("Combo fruit", healingItem3.getName());
        assertEquals(10, healingItem3.getHealHP());
        assertEquals(10, healingItem3.getHealMP());
    }
}