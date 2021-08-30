package com.bmcc.model.equipment;

public class Equipment {

    private final String name;
    private int integrity;
    private final String desc;


    Equipment(String name, int integrity, String desc) {
        this.name = name;
        this.integrity = integrity;
        this.desc = desc;
    }

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

}
