package com.example.superfit;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
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

public class AdapterRecipe extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<Recipe> recipes;

    ArrayList<Recipe> listFull;

    public AdapterRecipe(Context context, ArrayList<Recipe> recipes) {
        this.ctx = context;
        this.recipes = recipes;
        this.listFull=recipes;
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

//    public Filter getFilter() {
//        return exampleFilter;
//    }
//
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//           ArrayList<Recipe> filterList = new ArrayList<>();
//           if(ctx==null|| constraint.length()==0){
//               filterList.addAll(listFull);
//           }
//           else {
//               String filterPattern = constraint.toString().toLowerCase().trim();
//               for (Recipe item: listFull){
//                   if(item.getName().toLowerCase().contains(filterPattern)){
//                       filterList.add(item);
//                   }
//               }
//           }
//           FilterResults results = new FilterResults();
//           results.values=filterList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            recipes.clear();
//            recipes.addAll((ArrayList<Recipe>)results.values);
//            notifyDataSetChanged();
//        }
//    };
}
