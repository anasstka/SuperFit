package com.example.superfit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.os.FileUtils.copy;

public class AdapterRecipe extends BaseAdapter{

    Context ctx;
    LayoutInflater inflater;
    ArrayList<Recipe> recipes;

    public AdapterRecipe(Context context, ArrayList<Recipe> recipes) {
        this.ctx = context;
        this.recipes = recipes;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Recipe getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.lv_recipes_items, parent, false);
        }

        Recipe currentRecipe = getItem(position);

        ((TextView) view.findViewById(R.id.tv_name_dish)).setText(currentRecipe.getName());
        ImageView iv_recipe = (ImageView) view.findViewById(R.id.iv_recipe);
        Picasso.get().load(currentRecipe.getImage()).into(iv_recipe);
        ((TextView) view.findViewById(R.id.tv_kcal)).setText(String.valueOf(currentRecipe.getKcal()));
        ((TextView) view.findViewById(R.id.tv_protein)).setText(String.valueOf(currentRecipe.getProtein()));
        ((TextView) view.findViewById(R.id.tv_fat)).setText(String.valueOf(currentRecipe.getFat()));
        ((TextView) view.findViewById(R.id.tv_carbs)).setText(String.valueOf(currentRecipe.getCarbs()));

        return view;
    }
}
