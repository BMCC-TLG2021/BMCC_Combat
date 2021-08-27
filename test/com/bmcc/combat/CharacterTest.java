package com.bmcc.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    Character sampleCharacter;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void physicalAttack() {
    }

    @Test
    public void magicAttack() {
    }

    @Test
    public void damage() {
    }

    @Test
    public void reduceMagicPoint() {
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