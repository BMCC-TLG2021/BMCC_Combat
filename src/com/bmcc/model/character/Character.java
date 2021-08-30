package com.bmcc.model.character;

import com.bmcc.model.equipment.MagicalWeapon;
import com.bmcc.model.equipment.PhysicalWeapon;
import com.bmcc.model.equipment.Weapon;
import com.bmcc.model.skill.Magic;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Character {
    private String name;
    private String occupation;
    private String race;
    private int hitPoint;
    private int magicPoint;
    private int defensePower;
    private int attackPower;
    private Weapon weapon;
    private Magic magic;

    // Constructors
    private Character(String name, String occupation, String race,int hitPoint, int magicPoint, int defensePower, int attackPower) {
        this.name = name;
        this.occupation = occupation;
        this.race = race;
        this.hitPoint = hitPoint;
        this.magicPoint = magicPoint;
        this.defensePower = defensePower;
        this.attackPower = attackPower;
    }

    // Business methods

    public void damage(int points) {
        if (hitPoint < points) {
            hitPoint = 0;
        } else {
            hitPoint -= points;
        }
    }

    public boolean reduceMagicPoint() {
        if (getMagicPoint() >= 20) {
            magicPoint -= 20;
            return true;
        }
        return false;
    }

    public int getTotalPhysicalAttackPower() {
        if (this.getWeapon() instanceof PhysicalWeapon) {
            return this.attackPower + ((PhysicalWeapon) this.getWeapon()).getDamage();
        }
        else {
            return this.attackPower;
        }
    }

    public int getTotalMagicalPower() {
        if (this.getWeapon() instanceof MagicalWeapon) {
            double damageBuffer = ((MagicalWeapon) this.getWeapon()).getMagicPowerIncrease();
            int originalDamage = this.getMagic().getDamage();
            return (int) ((damageBuffer + 1)* originalDamage);
        }
        return this.getMagic().getDamage();
    }

    // Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {return occupation;}

    public String getRace() {return race;}

    public int getHitPoint() {
        return hitPoint;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Magic getMagic() {
        return magic;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hitPoint=" + hitPoint +
                ", magicPoint=" + magicPoint +
                ", defensePower=" + defensePower +
                ", attackPower=" + attackPower +
                ", weapon=" + weapon +
                '}';
    }

    public static Character getInstanceFromJsonFile(String fileName) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(fileName));
        JSONObject jo = (JSONObject) obj;
        String name = (String) jo.get("name");
        String occupation = (String) jo.get("occupation");
        String race = (String) jo.get("race");
        int hitPoint = (int)(long) jo.get("hitPoint");
        int magicPoint = (int)(long) jo.get("magicPoint");
        int defensePower = (int)(long) jo.get("defensePower");
        int attackPower = (int)(long) jo.get("attackPower");

        return new Character(name, occupation, race, hitPoint,magicPoint,defensePower,attackPower);
    }

    public static Character getInstance(String name, String occupation, String race,int hitPoint,
                                 int magicPoint, int defensePower, int attackPower){
        return new Character(name, occupation, race, hitPoint,magicPoint,defensePower,attackPower);
    }


}