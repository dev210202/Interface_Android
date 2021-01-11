package org.sejonguniv.if_2020.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.network.APIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Repository {
    protected Gson gson = new GsonBuilder().setLenient().create();

    protected HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    protected OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();
    protected Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    protected APIService service = retrofit.create(APIService.class);

    public Repository(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
