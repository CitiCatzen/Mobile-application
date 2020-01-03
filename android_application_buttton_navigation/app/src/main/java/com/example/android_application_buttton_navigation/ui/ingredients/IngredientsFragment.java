package com.example.android_application_buttton_navigation.ui.ingredients;


import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.android_application_buttton_navigation.Ingredient;
import com.example.android_application_buttton_navigation.MainActivity;
import com.example.android_application_buttton_navigation.R;

import java.util.ArrayList;



public class IngredientsFragment extends Fragment implements View.OnClickListener, KeyEvent.Callback {

    final String LOG_TAG="myLogs";
    Button btnChecked;
    ListView lvMain;
    EditText ingredientName;
    public static ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    public ArrayList<String> name = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ingredients, container, false);
        lvMain=root.findViewById(R.id.lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        btnChecked=root.findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);
        ingredientName = root.findViewById(R.id.ingredientName);
        ingredients = MainActivity.getIngredients();
        pageRefresh();
        return root;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        pageRefresh();
        return false;
    }

    public void onClick(View v){
        ArrayList<String>itemToRemove = new ArrayList<String>();
        SparseBooleanArray sbArray=lvMain.getCheckedItemPositions();
        for (int i=0;i<sbArray.size();i++){
            int key=sbArray.keyAt(i);
            if (sbArray.get(key)){
                Log.d(LOG_TAG,ingredients.get(key).getName());
                MainActivity.ingredientIsExist(findIngredientsByName(adapter.getItem(key)));
                itemToRemove.add(adapter.getItem(key));

            }
        }
        for (int i = 0; i < itemToRemove.size();i++){
            adapter.remove(itemToRemove.get(i));
        }
        pageRefresh();
    }

    private void pageRefresh(){

        lvMain.setAdapter(null);
        for(int i = 0; i<ingredients.size(); i++){

            if(!ingredients.get(i).getIsExist() && (ingredients.get(i).getName().contains(ingredientName.getText().toString()))) {
                name.add(ingredients.get(i).getName());
            }
        }
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_multiple_choice, name);
        lvMain.setAdapter(adapter);
    }

    private int findIngredientsByName(String name){
        int key = 0;
        for (int i = 0; i < ingredients.size(); i++){
            if (name == ingredients.get(i).getName()){key = i;}
        }
        return  key;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        return false;
    }
}

