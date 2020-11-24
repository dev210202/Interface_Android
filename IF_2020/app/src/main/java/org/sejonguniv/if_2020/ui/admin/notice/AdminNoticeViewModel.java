package org.sejonguniv.if_2020.ui.admin.notice;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.Data;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminNoticeViewModel extends ViewModel {


    Gson gson = new GsonBuilder().setLenient().create();

    ObservableArrayList<Notice> titleList= new ObservableArrayList<>();

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    APIService service = retrofit.create(APIService.class);

    public void getNoticeList(Dialog dialog) {

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Call<List<Notice>> request = service.getNotice();
        request.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.body() != null) {

                    List<Notice> data = response.body();
                    for (Notice u : data) {
                        titleList.add(u);
                        Log.e("SUCCESS get title", u.getTitle());
                        Log.e("asdasd", ""+ u.getId());

                    }

                } else {
                    Log.e("SS?", "?");

                }
                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("Fail", t.toString());
                dialog.dismiss();
            }
        });

    }


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

    public void editNotice(Notice notice){
        // 공지사항의 index에 맞춰서 update/{index}

        Call<Void> request = service.update(1, notice);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() != null) {
                    Log.e("Success save!@#!@#", "!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail save!@#!@#", t.toString());
            }
        });
    }
    public void deleteNotice(int listNumber){
        Call<Void> request = service.delete(listNumber);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){

                }
                else  if (response.body() != null) {
                    Log.e("Success delete", "!");
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail delete", t.toString());
            }
        });
    }

}
