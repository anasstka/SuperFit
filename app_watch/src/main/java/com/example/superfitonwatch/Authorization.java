package com.example.superfitonwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Authorization extends AppCompatActivity {

    TextView btn_sigh_in;
    EditText et_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        et_userName = (EditText) findViewById(R.id.et_userName);
        btn_sigh_in = (TextView) findViewById(R.id.btn_sigh_in);
        btn_sigh_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверка наличия пусоты и пробела в поле Username
                if(!et_userName.getText().toString().equals("")) {
                    boolean isFound = false;
                    if(!et_userName.getText().toString().contains(" ")){
                        Intent intent = new Intent(getApplicationContext(), AuthorizationPassword.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Field have a space!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Empty field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}