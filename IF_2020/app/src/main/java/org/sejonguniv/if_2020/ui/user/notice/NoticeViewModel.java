package org.sejonguniv.if_2020.ui.user.notice;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.base.BaseViewModel;
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

public class NoticeViewModel extends BaseViewModel {

    ObservableArrayList<Notice> titleList = new ObservableArrayList<>();

    public void getNoticeList(Dialog dialog) {

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Call<List<Notice>> request = service.getNotice();
        request.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.body() != null) {
                    List<Notice> data = response.body();
                    if (data.isEmpty()) {
                        Notice emptyNotice = new Notice();
                        emptyNotice.setTitle("저장되어있는 공지사항이 없습니다.");
                        titleList.add(emptyNotice);
                    } else {
                        for (Notice u : data) {
                            titleList.add(u);
                        }
                    }
                } else {
                    Log.e("respose", "error");
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Notice errorNotice = new Notice();
                errorNotice.setTitle("데이터를 불러오지 못했습니다. 당겨서 새로고침을 통해 다시 시도해주세요.");
                titleList.add(errorNotice);
                Log.e("Fail", t.toString());
                dialog.dismiss();
            }
        });

    }
}
