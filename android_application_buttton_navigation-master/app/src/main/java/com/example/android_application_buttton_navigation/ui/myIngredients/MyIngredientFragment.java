package com.example.android_application_buttton_navigation.ui.myIngredients;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_application_buttton_navigation.Ingredient;
import com.example.android_application_buttton_navigation.MainActivity;
import com.example.android_application_buttton_navigation.R;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;

public class MyIngredientFragment extends Fragment implements View.OnTouchListener {
    private LinearLayout linearLayout;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    float moveX = 0;
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.my_ingredient_fragment, container, false);
        linearLayout = root.findViewById(R.id.linMyIngredients);
        ingredients = MainActivity.getIngredients();
        for(int i = 0; i<ingredients.size(); i++ ){
            if (ingredients.get(i).getIsExist()){
                Button button = new Button(getContext());
                button.setLayoutParams(layoutParams);
                button.setOnTouchListener(this);
                button.setText(ingredients.get(i).getName());
                button.setId(ingredients.get(i).getId());
                linearLayout.addView(button);

            }
        }
        return root;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                moveX = x;
            case MotionEvent.ACTION_MOVE:
                if (x > abs(moveX+300)){
                    layoutParams.leftMargin  = layoutParams.leftMargin +  (int) x;
                    v.setLayoutParams(layoutParams);
                    MainActivity.ingredientIsNotExist(v.getId()-1);
                    linearLayout.removeView(v);


                }
        }
        return false;
    }
}
