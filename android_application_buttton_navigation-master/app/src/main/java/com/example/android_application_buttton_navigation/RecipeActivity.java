package com.example.android_application_buttton_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    TextView name;
    TextView description;
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        name = (TextView)findViewById(R.id.recipeName);
        description = (TextView)findViewById(R.id.textDescription);
        linearLayout  = (LinearLayout)findViewById(R.id.linLayout);
        ingredients = MainActivity.getIngredients();

        Bundle arguments= getIntent().getExtras();
        Recipe recipe = (Recipe)arguments.getSerializable("Recipe");
        name.setText(recipe.getName());
        description.setText(recipe.getDescription());

        for(int i = 0;i < recipe.getIngredint_id().size();i++){
            for(int j = 0; j < ingredients.size(); j++){
                if (ingredients.get(j).getId() == recipe.ingrediets_id.get(i)){
                    TextView textView = new TextView(this);
                    textView.setText(ingredients.get(j).getName());
                    linearLayout.addView(textView);
                }
            }
        }
    }
}
