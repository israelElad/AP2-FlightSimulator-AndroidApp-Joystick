package com.example.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onConnect(View view) {
        Intent intent = new Intent(this, JoystickActivity.class);
        EditText editTextIp = findViewById(R.id.ipText);
        intent.putExtra("IP", editTextIp.getText().toString());
        EditText editTextPort = findViewById(R.id.portText);
        intent.putExtra("Port", Integer.parseInt(editTextPort.getText().toString()));
        startActivity(intent);
    }
}
