package com.example.android_application_buttton_navigation;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    static private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    static private ArrayList<Recipe> recipes = new ArrayList<Recipe>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_recipes, R.id.navigation_ingredients, R.id.navigation_my_ingredients)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Работа с БД:
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        Cursor cursor = mDb.rawQuery("SELECT * FROM ingredients", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            ingredients.add(new Ingredient(cursor.getString(1), cursor.getInt(0)));
            cursor.moveToNext();
        }
        cursor.close();
//        массив names содержит все ингредиенты из бд

//        теперь заполним дин массив рецептов
        Cursor cursor1 = mDb.rawQuery("SELECT * FROM recipes", null);
        cursor1.moveToFirst();

        while (!cursor1.isAfterLast()) {
            recipes.add(new Recipe(cursor1.getString(1), cursor1.getString(2),cursor1.getInt(0)));
            cursor1.moveToNext();
        }
        cursor1.close();
        // заполняем дин. массив id ингредиентов в каждом рецепте
        Cursor cursor2 = mDb.rawQuery("SELECT * FROM recipes_ingredients",null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()){
            int recipe_id = cursor2.getInt(1);
            int ingredient_id = cursor2.getInt(0);
            for (int i = 0; i < recipes.size();i++){
                if (recipes.get(i).getId() == recipe_id){
                    recipes.get(i).setIngrediets_id(ingredient_id);
                }
            }
            cursor2.moveToNext();
        }

        cursor2.close();
//        конец работы с бд


    }


    public static ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public static void ingredientIsExist(int key){
        ingredients.get(key).setExistTrue();
    }
    public static void ingredientIsNotExist(int key){
        ingredients.get(key).setExistFalse();
    }

    public static ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
