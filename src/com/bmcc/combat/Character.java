package com.bmcc.combat;

public class Character {
    private String name;
    private int hitPoint;
    private int magicPoint;
    private int defensePower;
    private int attackPower;
    private Weapon weapon;
    private Magic magic;

    // Constructors
    public Character(String name, int hitPoint, int manaPoint, int defensePower, int attackPower) {
        this.name = name;
        this.hitPoint = hitPoint;
        this.magicPoint = manaPoint;
        this.defensePower = defensePower;
        this.attackPower = attackPower;
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

    boolean reduceMagicPoint() {
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
            return (int) damageBuffer * originalDamage;
        }
        return this.getMagic().getDamage();
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
}
