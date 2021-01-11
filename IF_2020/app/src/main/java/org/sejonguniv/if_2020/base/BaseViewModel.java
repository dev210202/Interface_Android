package org.sejonguniv.if_2020.base;

import androidx.lifecycle.ViewModel;

import org.sejonguniv.if_2020.network.APIService;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    protected OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build();
    protected Retrofit retrofit = new Retrofit.Builder().baseUrl("https://interface-app-dev.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
    protected APIService service = retrofit.create(APIService.class);

    @Override
    protected void onCleared() {
        // viewModel이 제거될때 onCleared 호출
        // compositeDisposable을 재사용하지 않으므로 dispose 수행
        super.onCleared();
        compositeDisposable.dispose();
    }

    public CompositeDisposable getCompsiteDisposable(){
        return compositeDisposable;
    }
}
