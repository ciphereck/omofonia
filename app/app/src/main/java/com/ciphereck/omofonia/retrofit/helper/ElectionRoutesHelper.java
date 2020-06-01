package com.ciphereck.omofonia.retrofit.helper;

import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.model.request.VoteModel;
import com.ciphereck.omofonia.model.response.ElectionResponseModel;
import com.ciphereck.omofonia.retrofit.RetrofitInstance;
import com.ciphereck.omofonia.retrofit.routes.ElectionRoutes;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ElectionRoutesHelper {
    public static Observable<List<Election>> getAllElectionData() {
        return RetrofitInstance
                .getInstance()
                .create(ElectionRoutes.class)
                .getAllElectionData()
                .subscribeOn(Schedulers.computation())
                .filter(electionResponseModel -> electionResponseModel.isSuccess())
                .map(electionResponseModel -> electionResponseModel.getElectionList())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<JsonElement> vote(VoteModel voteModel) {
        return RetrofitInstance
                .getInstance()
                .create(ElectionRoutes.class)
                .vote(voteModel)
                .subscribeOn(Schedulers.computation())
                .filter(jsonElement -> jsonElement.getAsJsonObject().get("success").getAsBoolean())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ElectionResponseModel> updateElection(List<Election> elections) {
        return RetrofitInstance
                .getInstance()
                .create(ElectionRoutes.class)
                .changeData(elections)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
