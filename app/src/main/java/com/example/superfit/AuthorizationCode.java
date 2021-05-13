package com.example.superfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superfit.data.Contract;
import com.example.superfit.data.DbHelper;

import java.util.Random;

/**
 * Экран ввода кода
 */
public class AuthorizationCode extends AppCompatActivity {

    private ImageView[] iv_numbers;
    // массив картинок кнопок
    private int[] imagesNumbers = {
            R.drawable.number_1,
            R.drawable.number_2,
            R.drawable.number_3,
            R.drawable.number_4,
            R.drawable.number_5,
            R.drawable.number_6,
            R.drawable.number_7,
            R.drawable.number_8,
            R.drawable.number_9
    };
    // массив значений кнопок
    private String[] valuesNumbers = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
    };

    // хранит текущего пользователя в системе
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    private String code_correct = "1111";
    private String code_user = "";

    // данные текущего пользователя
    private String db_username = null;
    private String db_userEmail = null;
    private double dv_userWeight = 0.0;
    private double db_userHeight = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_code);

        iv_numbers = new ImageView[] {
                findViewById(R.id.im_1),
                findViewById(R.id.im_2),
                findViewById(R.id.im_3),
                findViewById(R.id.im_4),
                findViewById(R.id.im_5),
                findViewById(R.id.im_6),
                findViewById(R.id.im_7),
                findViewById(R.id.im_8),
                findViewById(R.id.im_9)
        };

        mSettings = getSharedPreferences(PREFERENCES.APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = mSettings.edit();

        // получение имени пользователя, введенное на прошлом экране
        TextView tv_email = findViewById(R.id.tv_email);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username").toString().trim();
        tv_email.setText(username);

        // работа с БД
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // список просматриваемыъ столбцов
        String [] list_column = {
                Contract.UserEntry._ID,
                Contract.UserEntry.COLUMN_NAME,
                Contract.UserEntry.COLUMN_EMAIL,
                Contract.UserEntry.COLUMN_CODE,
                Contract.UserEntry.COLUMN_WEIGHT,
                Contract.UserEntry.COLUMN_HEIGHT
        };

        // открытие БД
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
        int emailColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_EMAIL);
        int codeColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_CODE);
        int weightColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_WEIGHT);
        int heightColumnIndex = cursor.getColumnIndex(Contract.UserEntry.COLUMN_HEIGHT);

        while (cursor.moveToNext()) {
            // Используем индекс для получения строки или числа
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            String currentEmail = cursor.getString(emailColumnIndex);
            double currentWeight = cursor.getDouble(weightColumnIndex);
            double currentHeight = cursor.getDouble(heightColumnIndex);
            int currentCode = cursor.getInt(codeColumnIndex);

            // если имя пользователя есть в системе, то продолжаем работу
            if (username.equals(currentName)) {
                db_username = currentName;
                db_userEmail = currentEmail;
                dv_userWeight = currentWeight;
                db_userHeight = currentHeight;

                code_correct = String.valueOf(currentCode);

                break;
            }
        }

        // если имени пользователя нет в БД, то возвращение на окно входа
        if (db_username == null) {
            Toast.makeText(getApplicationContext(), "This user does not exist", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent= new Intent(getApplicationContext(), AuthorizationName.class);
                    startActivity(intent);
                }
            }, 700);
        }

        // обработка нажатия по кнопке назад
        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AuthorizationName.class);
                startActivity(intent);
            }
        });

        shuffleButtons();
    }

    // метод для перестановки кнопок клавиатуры в случайном порядке
    private void shuffleButtons() {
        Random rnd = new Random();
        for (int i = iv_numbers.length - 1; i >= 1; i--) {
            int j = rnd.nextInt(i + 1);

            int tmp = imagesNumbers[j];
            imagesNumbers[j] = imagesNumbers[i];
            imagesNumbers[i] = tmp;

            String tmp2 = valuesNumbers[j];
            valuesNumbers[j] = valuesNumbers[i];
            valuesNumbers[i] = tmp2;
        }

        for (int i = 0; i < iv_numbers.length; i++) {
            iv_numbers[i].setImageResource(imagesNumbers[i]);
        }
    }

    // метод нажатия на кнопки кода. Обработка ввода пароля
    public void onClick(View v){
        switch (v.getId()){
            case R.id.im_1:
                code_user+=valuesNumbers[0];
                break;
            case R.id.im_2:
                code_user+=valuesNumbers[1];
                break;
            case R.id.im_3:
                code_user+=valuesNumbers[2];
                break;
            case R.id.im_4:
                code_user+=valuesNumbers[3];
                break;
            case R.id.im_5:
                code_user+=valuesNumbers[4];
                break;
            case R.id.im_6:
                code_user+=valuesNumbers[5];
                break;
            case R.id.im_7:
                code_user+=valuesNumbers[6];
                break;
            case R.id.im_8:
                code_user+=valuesNumbers[7];
                break;
            case R.id.im_9:
                code_user+=valuesNumbers[8];
                break;
        }
        System.out.println(code_user);

        // если длина кода равна 4, проверяется его совпадение с кодом пользователя. При истинности - перемещение на главный экран,
        // иначе - сообщение об ошибке
        if(code_user.length()==4){
            if(code_correct.equals(code_user)){
                editor.putString(PREFERENCES.APP_PREFERENCES_NAME, String.valueOf(db_username));
                editor.putString(PREFERENCES.APP_PREFERENCES_EMAIL, String.valueOf(db_userEmail));
                editor.putString(PREFERENCES.APP_PREFERENCES_WEIGHT, String.valueOf(dv_userWeight));
                editor.putString(PREFERENCES.APP_PREFERENCES_HEIGHT, String.valueOf(db_userHeight));
                editor.apply();

                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                code_user="";
            }
        }

        shuffleButtons();
    }
}