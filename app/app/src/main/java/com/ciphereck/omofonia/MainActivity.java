package com.ciphereck.omofonia;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.activity.OnBoardingActivity;
import com.ciphereck.omofonia.managers.UserManager;
import com.ciphereck.omofonia.retrofit.helper.UserRoutesHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    SignInButton signInButton;
    int RC_SIGN_IN = 1;

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account == null) {
            return;
        }
        initUser(account.getIdToken());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGoogleLogin();
    }

    private void initGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("632226048159-jf79itn3u5q9fe5tehsv7ob9fe70al2d.apps.googleusercontent.com")
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.sign_in_button:
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    break;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                initUser(account.getIdToken());
            } catch (ApiException e) {
                System.out.println("signInResult:failed code=" + e.getStatusCode());
            }
        }
    }

    private void initUser(String idToken) {
        UserRoutesHelper
                .userLogin(idToken)
                .subscribe(user -> {
                    System.out.println("token");
                    System.out.println(user.getToken());
                    System.out.println("token");
                    UserManager.setInstance(user);
                    startActivity(new Intent(this, OnBoardingActivity.class));
                }, err -> System.out.println(err));
    }
}
