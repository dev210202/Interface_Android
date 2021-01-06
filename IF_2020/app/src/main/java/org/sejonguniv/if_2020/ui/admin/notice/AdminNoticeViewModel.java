package org.sejonguniv.if_2020.ui.admin.notice;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.Data;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.network.APIService;
import org.sejonguniv.if_2020.repository.AdminRepository;

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

public class AdminNoticeViewModel extends BaseViewModel {

    MutableLiveData<ArrayList<Notice>> titleList = new MutableLiveData<>();
    AdminRepository adminRepository = new AdminRepository();

    public void getNoticeList() {
        adminRepository.getNotice(titleList);
    }

    public void saveNotice(Notice notice) {
        adminRepository.saveNotice(notice);
    }

    public void editNotice(int listNumer, Notice notice) {
        adminRepository.editNotice(listNumer, notice);
    }

    public void deleteNotice(int listNumber) {
        adminRepository.deleteNotice(listNumber);
    }

}
