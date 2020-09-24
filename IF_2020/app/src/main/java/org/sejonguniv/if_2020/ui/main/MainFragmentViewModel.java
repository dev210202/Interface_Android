package org.sejonguniv.if_2020.ui.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragmentViewModel extends ViewModel {

    Gson gson = new GsonBuilder().setLenient().create();
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build();
    APIService service = retrofit.create(APIService.class);

    public void getNotice() {

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Call<List<Notice>> request = service.getNotice();
        request.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.body() != null) {

                    List<Notice> data = response.body();
                    String a = "";
                    for (Notice u : data) {
                        a = u.toString();
                        Log.e("SUCCESS", u.getTitle() + u.getContent() + u.getAuthor());
                    }

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

    public void saveNotice(Notice notice) {

        Call<String> request = service.saveNotice(notice);
        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    Log.e("Success", "!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Fail", t.toString());
            }
        });
    }
}
