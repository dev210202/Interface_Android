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

    MutableLiveData<ArrayList<Notice>> noticeList = new MutableLiveData<>();
    AdminRepository adminRepository = AdminRepository.getInstance();
    MutableLiveData<Boolean> isDataReceive = new MutableLiveData<>();

    public void getNoticeList() {
        compositeDisposable.add(
                adminRepository.getNotice().subscribe(
                        notices -> {
                            if (notices.isEmpty()) {
                                noticeList.postValue(setEmptyList());
                            } else {
                                noticeList.postValue(notices);
                            }
                        },
                        error -> {
                            noticeList.postValue(setErrorList());
                            isDataReceive.setValue(true);
                        },
                        () -> isDataReceive.setValue(true)
                ));
    }

    public void deleteNotice(int listNumber) {
        compositeDisposable.add(adminRepository.deleteNotice(listNumber).subscribe());
    }

    public void editNotice(int listNumber, Notice notice) {
        compositeDisposable.add(adminRepository.editNotice(listNumber, notice).subscribe());
    }

    public void saveNotice(Notice notice){
        compositeDisposable.add(adminRepository.saveNotice(notice).subscribe());
    }
    private ArrayList<Notice> setErrorList() {
        Notice notice = new Notice("오류","데이터를 불러올 수 없습니다. 다시 시도해주세요");
        ArrayList<Notice> errorList = new ArrayList<>();
        errorList.add(notice);
        return errorList;
    }

    private ArrayList<Notice> setEmptyList() {
        Notice notice = new Notice("공지사항이 없습니다.","");
        ArrayList<Notice> emptyList = new ArrayList<>();
        emptyList.add(notice);
        return emptyList;
    }
}
