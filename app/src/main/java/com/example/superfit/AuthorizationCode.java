package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.data.Contract;
import com.example.superfit.data.DbHelper;

public class AuthorizationCode extends AppCompatActivity {

    private String code_correct = "1111";
    private String code_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_code);

        TextView tv_email = findViewById(R.id.tv_email);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username").toString().trim();
        tv_email.setText(username);


        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String [] list_column = {
                Contract.UserEntry._ID,
                Contract.UserEntry.COLUMN_NAME,
                Contract.UserEntry.COLUMN_CODE
        };

        Cursor cursor = db.query(
                Contract.UserEntry.TABLE_NAME,
                list_column,
                null,
                null,
                null,
                null,
                null
        );

        int idColumnIndex = cursor.getColumnIndex(Contract.UserEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_NAME);
        int codeColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_CODE);

        while (cursor.moveToNext()) {
            // Используем индекс для получения строки или числа
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            int currentCode = cursor.getInt(codeColumnIndex);

            if (username.equals(currentName)) {
                code_correct = String.valueOf(currentCode);
//                Toast.makeText(getApplicationContext(), "" + code_correct, Toast.LENGTH_SHORT).show();
            }
        }


        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.im_1:
                code_user+="1";
                break;
            case R.id.im_2:
                code_user+="2";
                break;
            case R.id.im_3:
                code_user+="3";
                break;
            case R.id.im_4:
                code_user+="4";
                break;
            case R.id.im_5:
                code_user+="5";
                break;
            case R.id.im_6:
                code_user+="6";
                break;
            case R.id.im_7:
                code_user+="7";
                break;
            case R.id.im_8:
                code_user+="8";
                break;
            case R.id.im_9:
                code_user+="9";
                break;
        }
        System.out.println(code_user);
        if(code_user.length()==4){
            if(code_correct.equals(code_user)){
                Toast.makeText(getApplicationContext(), "Добро пожаловать", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Неверный пароль", Toast.LENGTH_SHORT).show();
                code_user="";
            }
        }
    }
}