package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Главный экран
 */
public class MainActivity extends AppCompatActivity {

    // хранит текущего пользователя в системе
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    private double weight = 0.0;
    private double height = 0.0;

    private TextView tv_weight;
    private TextView tv_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_weight = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);

        mSettings = getSharedPreferences(PREFERENCES.APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        // получение роста и веса текущего пользователя
        if(mSettings.contains(PREFERENCES.APP_PREFERENCES_NAME)) {
            weight = Double.parseDouble(mSettings.getString(PREFERENCES.APP_PREFERENCES_WEIGHT, "0"));
            height = Double.parseDouble(mSettings.getString(PREFERENCES.APP_PREFERENCES_HEIGHT, "0"));
        }

        // при нулевых значениях веса и роста задается значение "Undefined"
        if (weight == 0) {
            tv_weight.setText("Undefined");
        } else {
            tv_weight.setText(weight + " kg");
        }
        if (height == 0) {
            tv_height.setText("Undefined");
        } else {
            tv_height.setText(height + " cm");
        }

        // обработка нажатия по кнопке выйти
        LinearLayout btn_sign_out = findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(PREFERENCES.APP_PREFERENCES_NAME).apply();
                editor.remove(PREFERENCES.APP_PREFERENCES_EMAIL).commit();
                editor.remove(PREFERENCES.APP_PREFERENCES_WEIGHT).commit();
                editor.remove(PREFERENCES.APP_PREFERENCES_HEIGHT).commit();
                editor.clear();
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });

        // обработка нажатия по кнопке рецепты
        LinearLayout btn_recipes = findViewById(R.id.recipes);
        btn_recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
                startActivity(intent);
            }
        });

        // обработка нажатия по кнопке See All
        TextView btn_seeAll = findViewById(R.id.btn_see_all);
        btn_seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
                startActivity(intent);
            }
        });

        // обработка нажатия по кнопке детали
        LinearLayout btn_details = findViewById(R.id.btn_details);
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyBodyActivity.class);
                startActivity(intent);
            }
        });
    }
}