package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(PREFERENCES.APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        LinearLayout btn_sign_out = findViewById(R.id.btn_sign_out);
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(PREFERENCES.APP_PREFERENCES_NAME).commit();
                editor.remove(PREFERENCES.APP_PREFERENCES_EMAIL).commit();
                editor.clear();
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });

        LinearLayout btn_recipes = findViewById(R.id.recipes);
        btn_recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
                startActivity(intent);
            }
        });
    }
}