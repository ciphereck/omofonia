package com.ciphereck.omofonia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.MainActivity;
import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.managers.UserManager;
import com.ciphereck.omofonia.model.UserInfo;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

public class UserActivity extends AppCompatActivity {
    UserInfo userInfo;
    ImageView imageView;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("632226048159-jf79itn3u5q9fe5tehsv7ob9fe70al2d.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

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

        findViewById(R.id.button).setOnClickListener(v -> {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            // ...
                            Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        }
                    });
        });
    }
}
