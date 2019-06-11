package com.example.ex4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JoystickActivity extends AppCompatActivity {
    private Client client;
    private float prevX;
    private float prevY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        Intent intent=getIntent();
        String ip= intent.getStringExtra("IP");
        int port=intent.getIntExtra("Port",1234);

        Button joystick = (Button) findViewById(R.id.joystick);
        joystick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float currX,currY;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        prevX = event.getX();
                        prevY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        prevX = event.getX();
                        prevY = event.getY();
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        prevX = 0;
                        prevY = 0;
                        break;
                }
                return true;
            }
        });
        //connect to server
        client = new Client(ip, port);
        client.Connect();
    }
}
