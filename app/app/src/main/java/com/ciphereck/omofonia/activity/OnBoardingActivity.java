package com.ciphereck.omofonia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ciphereck.omofonia.R;


public class OnBoardingActivity extends AppCompatActivity {
    Button submitButton;
    EditText editText;
    EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initView();
    }

    private void initView() {
        submitButton = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);

        findViewById(R.id.button).setOnClickListener(v -> {
            String aadhaarData = editText.getText().toString();
            String password = editText1.getText().toString();
            System.out.println(aadhaarData);
            System.out.println(password);
        });
    }
}
