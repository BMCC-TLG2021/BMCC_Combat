package com.bmcc.model.character;

import com.bmcc.model.equipment.Weapon;
import com.bmcc.model.skill.Magic;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CharacterTest {
    Character sampleCharacter;
    Weapon samplePhysicalWeapon;
    Weapon sampleMagicalWeapon;
    Magic sampleMagic;
    @Before
    public void setUp() throws Exception {
        sampleCharacter = Character.getInstance("test name","test job", "test race",
                100,100,10,10);
        samplePhysicalWeapon = Weapon.getInstance("test physical weapon", 20, 20, "A test p weapon",
                100, 20, 0.0);
        sampleMagicalWeapon = Weapon.getInstance("test magical weapon", 5, 5, "A test m weapon",
                200, 5, 0.5);
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
    public void reduceMagicPoint_withEnoughMP() {
        boolean result = sampleCharacter.reduceMagicPoint();
        assertEquals(80,sampleCharacter.getMagicPoint());
        assertTrue(result);
    }
    @Test
    public void reduceMagicPoint_withNotEnoughMP() {
        sampleCharacter.reduceMagicPoint();
        sampleCharacter.reduceMagicPoint();
        sampleCharacter.reduceMagicPoint();
        sampleCharacter.reduceMagicPoint();
        sampleCharacter.reduceMagicPoint();
        boolean result = sampleCharacter.reduceMagicPoint();
        assertEquals(0,sampleCharacter.getMagicPoint());
        assertFalse(result);
    }


    @Test
    public void getTotalPhysicalAttackPower() {
        int actual = sampleCharacter.getTotalPhysicalAttackPower();
        assertEquals(30, actual);
    }

    @Test
    public void getTotalMagicalPower() {
        sampleCharacter.setWeapon(sampleMagicalWeapon);
        int actual = sampleCharacter.retrieveTotalMagicalPower();
        assertEquals(60, sampleCharacter.retrieveTotalMagicalPower());
    }


    @Test
    public void getInstanceFromJsonFile() throws Exception {
        sampleCharacter = Character.getInstanceFromJsonFile("asset/samplePlayerCharacter.json");
        assertEquals("Kethoth Murnyethara", sampleCharacter.getName());
        assertEquals("Cleric", sampleCharacter.getOccupation());
        assertEquals("Human", sampleCharacter.getRace());
        assertEquals(125,sampleCharacter.getHitPoint());
    }

    @Test
    public void getCharacterListFromJsonFile() {
        List<Character> characterList = Character.getCharacterListFromJsonFile("asset/sampleCharacters.json");
        Character character2 = characterList.get(1);
        assertEquals("Gardain Dankil", character2.getName());
        assertEquals("Fighter", character2.getOccupation());
        assertEquals("Dwarf", character2.getRace());
        assertEquals(255, character2.getHitPoint());
        assertEquals(null, character2.getWeapon());
        assertEquals(null, character2.getMagic());
    }
}