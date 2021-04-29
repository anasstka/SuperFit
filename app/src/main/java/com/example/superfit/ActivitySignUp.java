package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.superfit.data.Contract;
import com.example.superfit.data.DbHelper;

import java.sql.SQLData;

public class ActivitySignUp extends AppCompatActivity {

    EditText et_name;
    EditText et_email;
    EditText et_code;
    EditText et_repeatCode;

    String name;
    String email;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_code = findViewById(R.id.et_password);
        et_repeatCode = findViewById(R.id.et_repeat_password);

        LinearLayout btn_signUp = findViewById(R.id.btn_sign_up);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsEmpty() && isAtEmail() && arePasswordsCompare()) {
                    name = et_name.getText().toString().trim();
                    email = et_email.getText().toString().trim();
                    code = Integer.parseInt(et_code.getText().toString().trim());

                    DbHelper dbHelper = new DbHelper(getApplicationContext());

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values= new ContentValues();

                    values.put(Contract.UserEntry.COLUMN_NAME, name);
                    values.put(Contract.UserEntry.COLUMN_EMAIL, email);
                    values.put(Contract.UserEntry.COLUMN_CODE, code);

                    long newRowId = db.insert(Contract.UserEntry.TABLE_NAME, null, values);

//                    if (newRowId == -1) {
//                        // Если ID  -1, значит произошла ошибка
//                        Toast.makeText(getApplicationContext(), "Ошибка при заведении гостя", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Гость заведён под номером: " + newRowId, Toast.LENGTH_SHORT).show();
//                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        LinearLayout btn_signIn = findViewById(R.id.btn_sign_in);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });

    }

    // проверка на пустоту полей
    private boolean areFieldsEmpty() {
        return !et_name.getText().toString().isEmpty()
                && !et_email.getText().toString().isEmpty()
                && !et_code.getText().toString().isEmpty()
                && !et_repeatCode.getText().toString().isEmpty();
    }

    // проверка на наличие символа @
    private boolean isAtEmail() {
        return et_email.getText().toString().contains("@");
    }

    // проверка равенства паролей
    private boolean arePasswordsCompare() {
        return et_code.getText().toString().equals(et_repeatCode.getText().toString());
    }

    // условия для пароля
    private void passwordEntryCondition() {

    }
}