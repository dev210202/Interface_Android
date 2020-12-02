package org.sejonguniv.if_2020.base;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.UserData;
import org.sejonguniv.if_2020.network.APIService;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseViewModel extends ViewModel {
    protected Gson gson = new GsonBuilder().setLenient().create();

    protected HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    protected OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();
    protected  Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    protected APIService service = retrofit.create(APIService.class);
}
