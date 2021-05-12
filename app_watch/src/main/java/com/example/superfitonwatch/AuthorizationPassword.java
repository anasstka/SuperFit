package com.example.superfitonwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class AuthorizationPassword extends AppCompatActivity {

    ImageView num_1;
    ImageView num_2;
    ImageView num_3;
    ImageView num_4;
    ImageView num_5;
    ImageView num_6;
    ImageView num_7;
    ImageView num_8;
    ImageView num_9;

    private ImageView[] iv_numbers;
    // массив картинок кнопок
    private int[] imagesNumbers = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8,
            R.drawable.img_9
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

    private String code_correct = "1111";
    private String code_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_password);

        iv_numbers = new ImageView[] {
                findViewById(R.id.num_1),
                findViewById(R.id.num_2),
                findViewById(R.id.num_3),
                findViewById(R.id.num_4),
                findViewById(R.id.num_5),
                findViewById(R.id.num_6),
                findViewById(R.id.num_7),
                findViewById(R.id.num_8),
                findViewById(R.id.num_9)
        };
    }

    // метод для переставления кнопок клавиатуры в случайном порядке
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.num_1:
                code_user += valuesNumbers[0];
                break;
            case R.id.num_2:
                code_user += valuesNumbers[1];
                break;
            case R.id.num_3:
                code_user += valuesNumbers[2];
                break;
            case R.id.num_4:
                code_user += valuesNumbers[3];
                break;
            case R.id.num_5:
                code_user += valuesNumbers[4];
                break;
            case R.id.num_6:
                code_user += valuesNumbers[5];
                break;
            case R.id.num_7:
                code_user += valuesNumbers[6];
                break;
            case R.id.num_8:
                code_user += valuesNumbers[7];
                break;
            case R.id.num_9:
                code_user += valuesNumbers[8];
                break;
        }
        System.out.println(code_user);
        if (code_user.length() == 4) {
            if (code_correct.equals(code_user)) {
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                code_user = "";
            }
        }

        shuffleButtons();
    }
}