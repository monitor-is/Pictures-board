package com.mlh.tvapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private ApiService jsonService;

    public ApiService getJsonService() {
        if (jsonService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://cleanclean.tom.ru/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            jsonService = retrofit.create(ApiService.class);
        }
        return jsonService;
    }
}
