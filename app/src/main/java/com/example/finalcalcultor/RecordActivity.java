package com.example.finalcalcultor;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class RecordActivity extends AppCompatActivity {
    private Button mFirstButton;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        mFirstButton = (Button) findViewById(R.id.login);
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this, LoginActivity.class);
                startActivity(intent);
                // Start CheatActivity
            }
        });
        mRegisterButton =(Button) findViewById(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
