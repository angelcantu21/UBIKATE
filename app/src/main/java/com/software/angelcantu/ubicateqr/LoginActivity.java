package com.software.angelcantu.ubicateqr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView user, pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.txtUser);
        pass = findViewById(R.id.txtPass);
        btn = findViewById(R.id.btnLogin);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            Intent log = new Intent(getApplicationContext(), NavigationActivity.class);
            startActivity(log);
            finish();

    }
}
