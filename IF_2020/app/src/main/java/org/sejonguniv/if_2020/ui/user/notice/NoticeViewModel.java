package org.sejonguniv.if_2020.ui.user.notice;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeViewModel extends BaseViewModel {

    MutableLiveData<ArrayList<Notice>> titleList = new MutableLiveData<>();
    UserRepository userRepository = UserRepository.getInstance();

    public void getNoticeList() {
        userRepository.getNotice(titleList);
        Log.e("getNoticeList", "!!");
    }
}
