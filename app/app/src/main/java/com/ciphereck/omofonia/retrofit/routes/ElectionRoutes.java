package com.ciphereck.omofonia.retrofit.routes;

import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.model.request.VoteModel;
import com.ciphereck.omofonia.model.response.ElectionResponseModel;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ElectionRoutes {
    @GET("election/info")
    Observable<ElectionResponseModel> getAllElectionData();

    @POST("user/vote")
    Observable<JsonElement> vote(@Body VoteModel voteModel);

    @POST("election/change")
    Observable<ElectionResponseModel> changeData(@Body List<Election> election);
}
