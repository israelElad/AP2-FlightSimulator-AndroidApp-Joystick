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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the extras from intent
        Intent intent=getIntent();
        String ip= intent.getStringExtra("IP");
        int port=intent.getIntExtra("Port",1234);

        //connect to server
        client = new Client(ip, port);
        client.Connect();

        // create the joystick view
        JoystickView joystickView = new JoystickView(this, client);
        setContentView(joystickView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.CloseConnection();
    }
}
