package com.example.android_application_buttton_navigation;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String name;
    private String description;
    private   int id;
    public ArrayList<Integer> ingrediets_id = new ArrayList<Integer>();
    public Recipe(Recipe r){
        this.name = r.name;
        this.description = r.description;
        this.id = r.id;
        this.ingrediets_id = r.ingrediets_id;
    }
    public Recipe(String name, String description, int id){
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public boolean areEnoughIngredients(ArrayList<Ingredient> ingredients){
        boolean areEnoughIngredients = false;
        int coincidences = 0;

        for (int i = 0; i < ingrediets_id.size() ; i++){
            for (int j = 0; j < ingredients.size(); j++){
                if ((ingrediets_id.get(i) == ingredients.get(j).getId()) && (ingredients.get(j).getIsExist()) ){
                    coincidences++;
                }
            }
        }
        if (coincidences == ingrediets_id.size()){areEnoughIngredients = true;}
        return areEnoughIngredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
    public ArrayList getIngredint_id(){ return ingrediets_id; }

    public void setIngrediets_id(int ingrediet_id) {
        this.ingrediets_id.add(ingrediet_id);
    }
}
