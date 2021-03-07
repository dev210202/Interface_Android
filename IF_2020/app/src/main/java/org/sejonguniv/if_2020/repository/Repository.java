package org.sejonguniv.if_2020.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.network.APIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Repository {
    protected Gson gson = new GsonBuilder().setLenient().create();

    protected HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    protected OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build();
    protected Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app.herokuapp.com/api/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build();
    protected APIService service = retrofit.create(APIService.class);

    public Repository(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
