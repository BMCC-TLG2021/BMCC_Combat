package com.bmcc.model.equipment;

public abstract class Equipment {
    private String name;
    private int integrity;
    private String desc;


    public String getName(){
        return this.name;
    };
    public int getIntegrity(){
        return this.integrity;
    };
    public String getDesc(){
        return this.desc;
    }

    public void reduceIntegrity(){
        if (this.getIntegrity() != 0){
            this.integrity -= 1;
        }
    }

    public abstract int getDamage();


}
