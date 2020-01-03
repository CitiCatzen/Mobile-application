package com.example.android_application_buttton_navigation;

public class Ingredient {
    private String name;
    private int id;
    private boolean isExist = false;
    public Ingredient(String name, int id){
        this.name = name;
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setExistTrue(){isExist = true;}
    public void setExistFalse(){isExist = false;}
    public boolean getIsExist(){return isExist;}

    public int getId() {
        return id;
    }

}