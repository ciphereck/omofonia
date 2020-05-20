package com.ciphereck.omofonia.retrofit.routes;

import com.ciphereck.omofonia.model.IdToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRoutes {
    @POST("login")
    Observable<JsonElement> userLogin(@Body IdToken idToken);
}
