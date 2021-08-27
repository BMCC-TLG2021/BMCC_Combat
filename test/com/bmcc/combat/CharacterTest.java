package com.bmcc.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    Character sampleCharacter;
    PhysicalWeapon samplePhysicalWeapon;
    MagicalWeapon sampleMagicalWeapon;
    Magic sampleMagic;
    @Before
    public void setUp() throws Exception {
        sampleCharacter = Character.getInstance("test name","test job", "test race",
                100,100,10,10);
        samplePhysicalWeapon = PhysicalWeapon.getInstance("test physical weapon", 20);
        sampleMagicalWeapon = MagicalWeapon.getInstance("test magical weapon", 0.5);
        sampleMagic = Magic.getInstance("test magic", 40);
        sampleCharacter.setWeapon(samplePhysicalWeapon);
        sampleCharacter.setMagic(sampleMagic);
    }


    @Test
    public void damage_pos() {
        sampleCharacter.damage(50);
        assertEquals(50, sampleCharacter.getHitPoint());
    }

    @Test
    public void damage_neg() {
        sampleCharacter.damage(150);
        assertEquals(0, sampleCharacter.getHitPoint());
    }

    @Test
    public void reduceMagicPoint() {
        boolean result = sampleCharacter.reduceMagicPoint();
        assertEquals(80,sampleCharacter.getMagicPoint());
        assertTrue(result);
    }

    @Test
    public void getTotalPhysicalAttackPower() {
    }

    @Test
    public void getTotalMagicalPower() {
    }


    @Test
    public void setWeapon() {
    }

    @Test
    public void setMagic() {
    }

    @Test
    public void getInstanceFromJsonFile() throws Exception {
        sampleCharacter = Character.getInstanceFromJsonFile("asset/samplePlayerCharacter.json");
        assertEquals("Kethoth Murnyethara", sampleCharacter.getName());
        assertEquals("Cleric", sampleCharacter.getOccupation());
        assertEquals("Human", sampleCharacter.getRace());
        assertEquals(125,sampleCharacter.getHitPoint());
    }
}