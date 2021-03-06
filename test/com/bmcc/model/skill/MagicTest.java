package com.bmcc.model.skill;

import com.bmcc.model.skill.Magic;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MagicTest {
    Magic sampleMagic;
    @Before
    public void setUp() throws Exception {
        sampleMagic = Magic.getInstance("testing magic", 100);
    }

    @Test
    public void getName() {
        assertEquals("testing magic", sampleMagic.getName());
    }

    @Test
    public void getDamage() {
       assertEquals(100,sampleMagic .getDamage());
    }

    @Test
    public void getInstanceFromJsonFile() throws Exception {
        sampleMagic = Magic.getInstanceFromJsonFile("asset/sampleMagic.json");
        assertEquals("Lunar Flare", sampleMagic.getName());
        assertEquals(105,sampleMagic .getDamage());
    }

    @Test
    public void getMagicListFromJsonFile() {
        List<Magic> magicList = Magic.getMagicListFromJsonFile("asset/sampleMagics.json");
        Magic magic5 = magicList.get(4);
        assertEquals("Carrion Swarm", magic5.getName());
        assertEquals(120, magic5.getDamage());
    }
}