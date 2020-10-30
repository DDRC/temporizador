package com.example.organizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView time;
    private EditText AmounTime;
    private Button timerButton;

    private CountDownTimer temporizador;
    private long milsecCounter;
    private boolean activecount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (TextView) findViewById(R.id.cuenta);
        AmounTime = (EditText) findViewById(R.id.tiempo);
timerButton=(Button)findViewById(R.id.button);
    }

    public void contar(View view) {
        /*String value=time.getText().toString();
        milsecCounter = Long.valueOf(value);
        AmounTime.setText(Long.toString(milsecCounter));*/
        if (activecount) {
            stopTimer();
        } else {
            StarTimer();
        }

    }

    private void StarTimer() {
        timerButton.setText("Parar");
        String value = time.getText().toString();
        milsecCounter = Long.valueOf(value) * 60000;
        temporizador = new CountDownTimer(milsecCounter, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                milsecCounter = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
            }
        }.start();
        activecount = true;
    }

    private void stopTimer() {
        temporizador.cancel();
        activecount = false;
        timerButton.setText("Reiniciar");
    }

    public void updateTimer() {
        String value = time.getText().toString();
        milsecCounter = Long.valueOf(value) * 60000;
        int minutes = (int) milsecCounter / 60000;
        int seconds = (int) milsecCounter % 60000 / 1000;
        String timeLeftText;
        timeLeftText = " " + minutes;
        timeLeftText += ": ";
        if (seconds < 10) {
            timeLeftText += "0";
            timeLeftText += seconds;
            AmounTime.setText(timeLeftText);
        }
    }
}