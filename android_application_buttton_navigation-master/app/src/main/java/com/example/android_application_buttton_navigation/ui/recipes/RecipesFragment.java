package com.example.android_application_buttton_navigation.ui.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.android_application_buttton_navigation.MainActivity;
import com.example.android_application_buttton_navigation.R;
import com.example.android_application_buttton_navigation.Recipe;
import com.example.android_application_buttton_navigation.RecipeActivity;

import java.util.ArrayList;

public class RecipesFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private Button addButton;
    LinearLayout lin_enough;
    LinearLayout lin_not_enough;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipes, container, false);
        final String LOG_TAG="myLogs";
        lin_enough=(LinearLayout)root.findViewById(R.id.lin_enough);
        lin_not_enough = (LinearLayout)root.findViewById(R.id.lin_not_enough);

        recipes = MainActivity.getRecipes();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        distributionOfRecipes();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d("id = ",Integer.toString(id));
        Intent intent = new Intent(getContext(), RecipeActivity.class);
        Recipe recipe = new Recipe(recipes.get(id-1));
        intent.putExtra("Recipe",recipe);
        startActivity(intent);
    }


    void distributionOfRecipes(){
        lin_enough.removeAllViews();
        lin_not_enough.removeAllViews();
        for (int i = 0;i < recipes.size();i++) {
            addButton = new Button(getContext());
            addButton.setId(recipes.get(i).getId());
            addButton.setOnClickListener(this);
            addButton.setText(recipes.get(i).getName());
            if (recipes.get(i).areEnoughIngredients(MainActivity.getIngredients())) {
                lin_enough.addView(addButton);
            }else
            {lin_not_enough.addView(addButton);}
        }
    }

}