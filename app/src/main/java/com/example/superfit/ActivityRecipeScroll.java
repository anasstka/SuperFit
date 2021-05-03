package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivityRecipeScroll extends AppCompatActivity {

    ListView lv_ingredients;
    ImageView iv_image;
    TextView tv_name_on_scroll;
    TextView tv_kcal;
    TextView tv_protein;
    TextView tv_fat;
    TextView tv_carbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_scroll);

        lv_ingredients = findViewById(R.id.lv_ingredients);
        iv_image = findViewById(R.id.im_recipe);
        tv_name_on_scroll = findViewById(R.id.tv_name_on_scroll);
        tv_kcal = findViewById(R.id.tv_kcal_on_scroll);
        tv_protein = findViewById(R.id.tv_protein_on_scroll);
        tv_fat = findViewById(R.id.tv_fat_on_scroll);
        tv_carbs = findViewById(R.id.tv_carbs_on_scroll);

        ImageView btn_back_on_scroll=findViewById(R.id.btn_back_on_scroll);
        btn_back_on_scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Recipe recipe = (Recipe) bundle.getSerializable("recipe");

            String image = recipe.getImage();
            Picasso.get().load(recipe.getImage()).into(iv_image);

            String name = recipe.getName();
            tv_name_on_scroll.setText(String.valueOf(name));

            int kcal = recipe.getKcal();
            tv_kcal.setText(String.valueOf(kcal));

            int protein = recipe.getProtein();
            tv_protein.setText(String.valueOf(protein));

            int fat = recipe.getFat();
            tv_fat.setText(String.valueOf(fat));

            int carbs = recipe.getCarbs();
            tv_carbs.setText(String.valueOf(carbs));

            ArrayList<Ingredient> ingredients = recipe.getIngredients();
            AdapterIngredient adapterIngredient = new AdapterIngredient(ActivityRecipeScroll.this, ingredients);
            lv_ingredients.setAdapter(adapterIngredient);
        }



    }
}