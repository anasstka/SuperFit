package com.example.superfitonwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    //переменные
    TextView btn_start;
    ImageView btn_exit;
    TextView btn_stop;
    Chronometer chronometer;
    TextView btn_excellent;
    TextView btn_prepare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prepare = findViewById(R.id.btn_prepare);
        // обработчик нажатия на кнопку exit
        btn_exit = (ImageView) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Authorization.class);
                startActivity(intent);
            }
        });
        chronometer = findViewById(R.id.chronometer);
        // обработчик нажатия на кнопку start
        btn_start = (TextView) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_prepare.setVisibility(View.VISIBLE);
                btn_start.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_prepare.setVisibility(View.GONE);
                        btn_stop.setVisibility(View.VISIBLE);
                        btn_exit.setVisibility(View.VISIBLE);
                        chronometer.setVisibility(View.VISIBLE);
                        onStartClick();
                    }
                }, 5000);
            }
        });
        // обработчик нажатия на кнопку stop
        btn_stop = findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_excellent.setVisibility(View.VISIBLE);
                btn_stop.setVisibility(View.GONE);
                chronometer.setVisibility(View.GONE);
                onStopClick();
            }
        });

        // обработчик нажатия на кнопку excellent
        btn_excellent = findViewById(R.id.btn_excellent);
        btn_excellent.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                btn_start.setText("Start");
                btn_start.setTextSize(38);
                btn_start.setVisibility(View.VISIBLE);
                btn_exit.setVisibility(View.GONE);
                btn_excellent.setVisibility(View.GONE);
            }
        });

        //метод старта хронометра
    }
    public void onStartClick() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    //метод остановление хронометра
    public void onStopClick() {
        chronometer.stop();
    }
}