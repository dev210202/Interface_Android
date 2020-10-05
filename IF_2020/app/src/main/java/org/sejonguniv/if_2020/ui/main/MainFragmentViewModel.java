package org.sejonguniv.if_2020.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragmentViewModel extends ViewModel {

    Gson gson = new GsonBuilder().setLenient().create();

    ObservableArrayList<String> titleList= new ObservableArrayList<>();

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    APIService service = retrofit.create(APIService.class);

    public void getNoticeList() {

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Call<List<Notice>> request = service.getNotice();
        request.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.body() != null) {

                    List<Notice> data = response.body();
                    for (Notice u : data) {
                        titleList.add(u.getTitle());
                        Log.e("SUCCESS get title", u.getTitle());

                    }
                    // 데이터 받아오는 속도가 느려서 rx사용해서 순서대로 진행시켜야할듯.
                } else {
                    Log.e("SS?", "?");
                }

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("Fail", t.toString());
            }
        });

    }

    /*
    public void saveNotice(Notice notice) {

        Call<String> request = service.saveNotice(notice);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    Log.e("Success save", "!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Fail save", t.toString());
            }
        });
    }

    */
}
