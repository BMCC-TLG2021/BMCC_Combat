package com.bmcc.combat;

public class Character {
    private String name;
    private int hitPoint;
    private int magicPoint;
    private int defensePower;
    private int attackPower;
    private PhysicalWeapon weapon;

    // Constructors
    public Character(String name, int hitPoint, int manaPoint, int defensePower, int attackPower, PhysicalWeapon weapon) {
        this.name = name;
        this.hitPoint = hitPoint;
        this.magicPoint = manaPoint;
        this.defensePower = defensePower;
        this.attackPower = attackPower;
        this.weapon = weapon;
    }

    // Business methods
    public int physicalAttack() {
        return getAttackPower();
    }

    // TODO 1: implement this method
    public int magicAttack() {
        return 0;
    }

    public void damage(int points) {
        hitPoint -= points;
    }

    private void reduceMagicPoint() {
        if (getMagicPoint() >= 20) {
            magicPoint -= 20;
        } else {
            throw new IllegalArgumentException("Magic point is not enough");
        }
    }

    // Getters
    public String getName() {
        return name;
    }

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

    public PhysicalWeapon getWeapon() {
        return weapon;
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
}
