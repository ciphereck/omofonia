package com.ciphereck.omofonia.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static volatile Retrofit retrofit;

    private RetrofitInstance() {
        if(retrofit != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Retrofit getInstance() {
        if(retrofit == null) {
            synchronized (Retrofit.class) {
                if(retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://13.58.174.114:3010/")
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return retrofit;
    }
}
