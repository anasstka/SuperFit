package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Экран входа
 */
public class AuthorizationName extends AppCompatActivity {

    // хранит текущего пользователя в системе
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_name);

        mSettings = getSharedPreferences(PREFERENCES.APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        // проверка, если пользователь уже в системе - пропустить повторную авторизацию
        if(mSettings.contains(PREFERENCES.APP_PREFERENCES_NAME)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        // обработка нажатия по кнопке войти
        LinearLayout btn_signInOnAuto = findViewById(R.id.btn_sign_in_on_auto);
        btn_signInOnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_username = findViewById(R.id.et_username);
                Intent intent = new Intent(getApplicationContext(), AuthorizationCode.class);
                intent.putExtra("username", et_username.getText().toString());
                startActivity(intent);
            }
        });

        // обработка нажатия по кнопке регистрация
        LinearLayout btn_signUp = findViewById(R.id.btn_sign_up);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}