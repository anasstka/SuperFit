package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AuthorizationName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_name);

        LinearLayout btn_signInOnAuto = findViewById(R.id.btn_sign_in_on_auto);
        btn_signInOnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_username = findViewById(R.id.et_username);
//                Intent intent = new Intent(getApplicationContext(), AuthorizationCode.class);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("username", et_username.getText().toString());
                startActivity(intent);
            }
        });

        LinearLayout btn_signUp = findViewById(R.id.btn_sign_up);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivitySignUp.class);
                startActivity(intent);
            }
        });
    }
}