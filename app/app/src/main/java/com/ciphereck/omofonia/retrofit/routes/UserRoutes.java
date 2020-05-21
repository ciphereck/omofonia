package com.ciphereck.omofonia.retrofit.routes;

import com.ciphereck.omofonia.model.request.AadhaarRequestModel;
import com.ciphereck.omofonia.model.request.IdToken;
import com.ciphereck.omofonia.model.request.OtpRequestModel;
import com.google.gson.JsonElement;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserRoutes {
    @POST("login")
    Observable<JsonElement> userLogin(@Body IdToken idToken);

    @POST("user/aadhaar")
    Observable<JsonElement> updateAadhaar(@Body AadhaarRequestModel aadhaarRequestModel);

    @POST("user/otp")
    Observable<JsonElement> sendOtp(@Body OtpRequestModel otpRequestModel);

    @PUT("user/mobile")
    Observable<JsonElement> updateMobile(@Body OtpRequestModel otpRequestModel);
}
