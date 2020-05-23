package com.ciphereck.omofonia.retrofit.helper;

import com.ciphereck.omofonia.model.election.Election;
import com.ciphereck.omofonia.retrofit.RetrofitInstance;
import com.ciphereck.omofonia.retrofit.routes.ElectionRoutes;

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
}
