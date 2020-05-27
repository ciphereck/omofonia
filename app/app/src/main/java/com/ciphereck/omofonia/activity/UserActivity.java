package com.ciphereck.omofonia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.managers.UserManager;
import com.ciphereck.omofonia.model.UserInfo;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {
    UserInfo userInfo;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userInfo = UserManager.getInstance().getUserInfo();

        findViewById(R.id.button4).setOnClickListener(v -> {
            startActivity(new Intent(this, ElectionActivity.class));
        });

        imageView = findViewById(R.id.imageView);

        Picasso.get().load(userInfo.getPhoto()).into(imageView);


        ((TextView)findViewById(R.id.textView18)).setText(userInfo.getName());
        ((TextView)findViewById(R.id.textView20)).setText(userInfo.getAadhaarName());
        ((TextView)findViewById(R.id.textView22)).setText(userInfo.getEmail());
        ((TextView)findViewById(R.id.textView24)).setText(userInfo.getDob());
        ((TextView)findViewById(R.id.textView26)).setText(userInfo.getGender());
        ((TextView)findViewById(R.id.textView28)).setText(userInfo.getAadhaarNumber());
        ((TextView)findViewById(R.id.textView30)).setText(userInfo.getAddress());
        ((TextView)findViewById(R.id.textView32)).setText(userInfo.getMobileNumber());
    }
}
