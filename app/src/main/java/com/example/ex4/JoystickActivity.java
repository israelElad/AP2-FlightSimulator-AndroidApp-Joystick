package com.example.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JoystickActivity extends AppCompatActivity {
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        Intent intent=getIntent();
        String ip= intent.getStringExtra("IP");
        int port=intent.getIntExtra("Port",1234);

        //connect to server
        client = new Client(ip, port);
        client.Connect();
    }
}
