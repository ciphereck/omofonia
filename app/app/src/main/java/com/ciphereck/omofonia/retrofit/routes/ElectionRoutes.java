package com.ciphereck.omofonia.retrofit.routes;

import com.ciphereck.omofonia.model.response.ElectionResponseModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ElectionRoutes {
    @GET("election/info")
    Observable<ElectionResponseModel> getAllElectionData();
}
