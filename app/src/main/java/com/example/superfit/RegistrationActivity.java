package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.superfit.data.Contract;
import com.example.superfit.data.DbHelper;

/**
 * Экран регистрации
 */
public class RegistrationActivity extends AppCompatActivity {

    // хранит текущего пользователя в системе
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    EditText et_name;
    EditText et_email;
    EditText et_code;
    EditText et_repeatCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mSettings = getSharedPreferences(PREFERENCES.APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_code = findViewById(R.id.et_password);
        et_repeatCode = findViewById(R.id.et_repeat_password);

        // обработка нажатия по кнопке зарегистрироваться
        LinearLayout btn_signUp = findViewById(R.id.btn_sign_up);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // валидация полей ввода. При успешной проверке запись данных пользователя в БД
                if (validatePassword() & validateEmail() & validateUserName()) {
                    String name = et_name.getText().toString().trim();
                    String email = et_email.getText().toString().trim();
                    int code = Integer.parseInt(et_code.getText().toString().trim());

                    DbHelper dbHelper = new DbHelper(getApplicationContext());

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(Contract.UserEntry.COLUMN_NAME, name);
                    values.put(Contract.UserEntry.COLUMN_EMAIL, email);
                    values.put(Contract.UserEntry.COLUMN_CODE, code);

                    long newRowId = db.insert(Contract.UserEntry.TABLE_NAME, null, values);

                    if (newRowId == -1) {
                        // Если ID -1, значит произошла ошибка
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    } else {
                        editor.putString(PREFERENCES.APP_PREFERENCES_NAME, String.valueOf(name));
                        editor.putString(PREFERENCES.APP_PREFERENCES_EMAIL, String.valueOf(email));
                        editor.putString(PREFERENCES.APP_PREFERENCES_WEIGHT, String.valueOf(0));
                        editor.putString(PREFERENCES.APP_PREFERENCES_HEIGHT, String.valueOf(0));
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        // обработка нажатия по кнопке войти (перемещение на предыдущий экран)
        LinearLayout btn_signIn = findViewById(R.id.btn_sign_in);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });
    }

    // валидация поля username
    private boolean validateUserName() {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            et_name.setError("Field can't be empty");
            et_name.requestFocus();
            return false;
        } else {
            et_name.setError(null);
            return true;
        }
    }

    // валидация поля email
    private boolean validateEmail() {
        String email = et_email.getText().toString();
        if (email.isEmpty()) {
            et_email.setError("Field can't be empty");
            et_email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please enter a valid email address");
            et_email.requestFocus();
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }

    // валидация поля password
    private boolean validatePassword() {
        String code = et_code.getText().toString();
        String repeatCode = et_repeatCode.getText().toString();

        if (code.length() < 4) {
            et_code.setError("The password must be 4 characters");
            et_code.requestFocus();
            return false;
        } else if (!code.equals(repeatCode)) {
            et_code.setError("Passwords do not match");
            et_code.requestFocus();
            et_repeatCode.setError("Passwords do not match");
            et_code.setText("");
            et_repeatCode.setText("");
            return false;
        } else {
            et_code.setError(null);
            et_repeatCode.setError(null);
            return true;
        }
    }
}