package org.sejonguniv.if_2020.ui.noticedetail;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeDetailViewModel extends ViewModel {

    Gson gson = new GsonBuilder().setLenient().create();

    ObservableArrayList<Notice> titleList = new ObservableArrayList<>();

    ObservableField<String> title = new ObservableField<>();
    ObservableField<String> content = new ObservableField<>();

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

    public void findNotice(int noticeId, Dialog dialog) {
        Call<Notice> request = service.find(noticeId);
        request.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {
                if (response.body() != null) {
                    Notice data = response.body();
                    title.set(data.getTitle());
                    content.set(data.getContent());
                    dialog.dismiss();
                    Log.e("DADA", title.get() + content.get());


                } else {
                    Log.e("DATA FAIL", "!!");
                }
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {

                Log.e("FAIL", "!!");
            }
        });
    }

}
