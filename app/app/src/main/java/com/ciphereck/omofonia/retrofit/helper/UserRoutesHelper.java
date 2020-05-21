package com.ciphereck.omofonia.retrofit.helper;

import com.ciphereck.omofonia.model.User;
import com.ciphereck.omofonia.model.UserInfo;
import com.ciphereck.omofonia.model.request.AadhaarRequestModel;
import com.ciphereck.omofonia.model.request.IdToken;
import com.ciphereck.omofonia.model.request.OtpRequestModel;
import com.ciphereck.omofonia.retrofit.RetrofitInstance;
import com.ciphereck.omofonia.retrofit.routes.UserRoutes;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRoutesHelper {

    public static Observable<User> userLogin(String idToken) {
        return RetrofitInstance
                    .getInstance()
                    .create(UserRoutes.class)
                    .userLogin(new IdToken(idToken))
                    .subscribeOn(Schedulers.computation())
                    .filter((jsonElement -> jsonElement.getAsJsonObject().get("success").getAsBoolean()))
                    .map((jsonElement -> jsonElement.getAsJsonObject().get("data")))
                    .map(jsonElement -> (User) (new Gson()).fromJson(jsonElement, new TypeToken<User>(){}.getType()))
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserInfo> updateAadhaarDetails(AadhaarRequestModel aadhaarRequestModel) {
        return RetrofitInstance
                    .getInstance()
                    .create(UserRoutes.class)
                    .updateAadhaar(aadhaarRequestModel)
                    .subscribeOn(Schedulers.computation())
                    .filter(jsonElement -> jsonElement.getAsJsonObject().get("success").getAsBoolean())
                    .map(jsonElement -> jsonElement.getAsJsonObject().get("data"))
                    .map(jsonElement -> (UserInfo) (new Gson()).fromJson(jsonElement, new TypeToken<UserInfo>(){}.getType()))
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonElement> sendOtp(OtpRequestModel otpRequestModel) {
        return RetrofitInstance
                .getInstance()
                .create(UserRoutes.class)
                .sendOtp(otpRequestModel)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonElement> updateMobileNumber(OtpRequestModel otpRequestModel) {
        return RetrofitInstance
                .getInstance()
                .create(UserRoutes.class)
                .updateMobile(otpRequestModel)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
