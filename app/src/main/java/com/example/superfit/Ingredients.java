package com.example.superfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Ingredients extends BaseAdapter {
    private Context context;
    private List<String> dataIngredient;

    public Ingredients(Context context, List<String>dataIngredient){
        this.context = context;
        this.dataIngredient = dataIngredient;
    }
    @Override
    public int getCount() {
        return dataIngredient.size();
    }

    @Override
    public Object getItem(int position) {
        return dataIngredient.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.lv_ingredients, null);
        TextView tv_ingredient = (TextView)view.findViewById(R.id.tv_ingredient);
        tv_ingredient.setText(dataIngredient.get(position));
        return view;
    }
}
