package com.ciphereck.omofonia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ciphereck.omofonia.R;
import com.ciphereck.omofonia.managers.UserManager;
import com.ciphereck.omofonia.model.request.AadhaarRequestModel;
import com.ciphereck.omofonia.model.request.OtpRequestModel;
import com.ciphereck.omofonia.retrofit.helper.UserRoutesHelper;

import java.util.Random;


public class OnBoardingActivity extends AppCompatActivity {
    Button aadhaarDataSubmitButton;
    EditText aadhaarXml;
    EditText filePassword;
    EditText aadhaarNumber;
    EditText mobileNumber;
    EditText otp;
    Button updateMobileNumberButton;
    Button sendOtpButton;
    String otpNumber = "9999";

    @Override
    protected void onStart() {
        super.onStart();

        if(UserManager.getInstance().getUserInfo().getAadhaarName() != null) {
            aadhadDataSetEnable(false);
        } else {
            mobileFieldSetEnable(false);
        }
        if(UserManager.getInstance().getUserInfo().getMobileNumber() != null) {
            startActivity(new Intent(this, ElectionActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initView();
    }

    private void initView() {
        System.out.println(UserManager.getInstance().getToken());
        aadhaarDataSubmitButton = findViewById(R.id.aadhaarSubmit);
        updateMobileNumberButton = findViewById(R.id.button2);
        sendOtpButton = findViewById(R.id.button3);

        aadhaarNumber = findViewById(R.id.editText4);
        aadhaarXml = findViewById(R.id.editText5);
        filePassword = findViewById(R.id.editText6);
        mobileNumber = findViewById(R.id.editText7);
        otp = findViewById(R.id.editText8);
        otp.setEnabled(false);

        initAadhaarDataButtonListener();

        initSendOtpListener();

        initUpdateMobileNumberListener();

    }

    private void initUpdateMobileNumberListener() {
        updateMobileNumberButton.setOnClickListener(v -> {
            if(otp.getText().toString().equals(otpNumber) == false) {
                showToast("wrong otp");
                return;
            }
            UserRoutesHelper
                    .updateMobileNumber(new OtpRequestModel(mobileNumber.getText().toString(), "random", UserManager.getInstance().getToken()))
                    .subscribe(responseModel -> {
                        if(responseModel.getAsJsonObject().get("success").getAsBoolean()) {
                            UserManager.getInstance().getUserInfo().setMobileNumber(mobileNumber.getText().toString());
                            startActivity(new Intent(this, ElectionActivity.class));
                        }
                        showToast(responseModel.toString());
                    }, err -> System.out.println(err));
        });
    }

    private void initSendOtpListener() {
        Integer numberToGuess = (new Random()).nextInt(2000) - 1000;
        if(numberToGuess < 0) numberToGuess*=1;
        Integer finalNumberToGuess = numberToGuess;
        sendOtpButton.setOnClickListener(v -> {
            UserRoutesHelper
                    .sendOtp(new OtpRequestModel(mobileNumber.getText().toString(), finalNumberToGuess.toString(), UserManager.getInstance().getToken()))
                    .subscribe(responseModel -> {
                        showToast(responseModel.toString());
                        this.onStart();
                        otp.setEnabled(true);
                        if(responseModel.getAsJsonObject().get("success").getAsBoolean())
                            otpNumber = finalNumberToGuess.toString();
                    }, err -> System.out.println(err));
        });
    }

    private void initAadhaarDataButtonListener() {
        aadhaarDataSubmitButton.setOnClickListener(v -> {
            String aadhaarXMLString = aadhaarXml.getText().toString();
            String aadhaarData = "";
            for(int i=0; i<aadhaarXMLString.length(); i++) {
                if(((int)(aadhaarXMLString.charAt(i))) != 10) {
                    aadhaarData+= aadhaarXMLString.charAt(i);
                }
            }
            AadhaarRequestModel aadhaarRequestModel = new AadhaarRequestModel(aadhaarData,
                    filePassword.getText().toString(), aadhaarNumber.getText().toString(),
                    UserManager.getInstance().getToken());

            UserRoutesHelper
                    .updateAadhaarDetails(aadhaarRequestModel)
                    .subscribe(userInfo -> {
                        System.out.println("aaya yhape");
                        showToast("aaya yhape");
                        UserManager.getInstance().setUserInfo(userInfo);
                        aadhadDataSetEnable(false);
                        mobileFieldSetEnable(true);
                    }, err -> System.out.println(err));
        });
    }

    private void aadhadDataSetEnable(boolean enable) {
        aadhaarDataSubmitButton.setEnabled(enable);
        aadhaarXml.setEnabled(enable);
        filePassword.setEnabled(enable);
        aadhaarNumber.setEnabled(enable);
    }

    private void mobileFieldSetEnable(boolean enable) {
        mobileNumber.setEnabled(enable);
        updateMobileNumberButton.setEnabled(enable);
    }

    private void showToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        System.out.println(text);
    }
}
