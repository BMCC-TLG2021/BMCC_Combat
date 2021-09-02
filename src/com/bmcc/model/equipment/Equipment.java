package com.bmcc.model.equipment;

public class Equipment {

    private final String name;
    private int integrity;
    private final int maxIntegrity;
    private final String desc;
    private final int moneyValue;


    Equipment(String name, int integrity, int maxIntegrity, String desc, int moneyValue) {
        this.name = name;
        this.integrity = integrity;
        this.maxIntegrity = maxIntegrity;
        this.desc = desc;
        this.moneyValue = moneyValue;
    }

    public String getName(){
        return this.name;
    };

    public int getIntegrity(){
        return this.integrity;
    };

    public int getMaxIntegrity() {
        return maxIntegrity;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public void reduceIntegrity(){
        if (this.getIntegrity() != 0){
            this.integrity -= 1;
        }
    }

    public void restoreIntegrity() {
        this.integrity = maxIntegrity;
    }

}
