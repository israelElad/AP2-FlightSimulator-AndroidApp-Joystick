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

    /* When the user click the connect button, a request to connect to the server will be sent
     and the user will switch to the joystick's activity */
    public void onConnect(View view) {
        Intent intent = new Intent(this, JoystickActivity.class);
        EditText editTextIp = findViewById(R.id.ipText);
        EditText editTextPort = findViewById(R.id.portText);
        if(editTextIp.getText().toString().equals("")||editTextPort.getText().toString().equals("")){
            return;
        }
        intent.putExtra("IP", editTextIp.getText().toString());
        intent.putExtra("Port", Integer.parseInt(editTextPort.getText().toString()));
        startActivity(intent);
    }
}
