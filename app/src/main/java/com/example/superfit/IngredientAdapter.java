package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * адаптер для заполнения listview элементами класса Ingredient
 */
public class IngredientAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Ingredient> ingredients;
    LayoutInflater inflater;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredients){
        this.context = context;
        this.ingredients = ingredients;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Ingredient getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.lv_ingredients, parent, false);
        }

        Ingredient currIngredient = getItem(position);

        TextView tv_ingredient = (TextView)view.findViewById(R.id.tv_ingredient);
        tv_ingredient.setText(currIngredient.getIngredient());
        return view;
    }
}
