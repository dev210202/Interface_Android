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
