package org.sejonguniv.if_2020.ui.user.notice;

import android.app.Dialog;
import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public class NoticeViewModel extends ViewModel {

    Gson gson = new GsonBuilder().setLenient().create();

    ObservableArrayList<Notice> titleList= new ObservableArrayList<>();

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
                        dialog.dismiss();
                    }
                    Notice test = new Notice();
                    test.setTitle("이건 notice 제목이야 근데 엄청 긴 제목이지 왜 제목이 기냐고? 나도 몰라 근데 제목이 길어야해 근데 사실 내용은 메이플 패치노트야");
                    test.setContent("안녕하세요 GM학구입니다.\n" +
                            "\n" +
                            "게임 환경을 악용한 사행성 행위, 일명 도박 행위를 근절하기 위해 지속적으로 노력해왔는데요. 게임 환경만으로는 사행성 행위를 할 수 없도록 보완되었지만 메이플스토리에서 도박 참여자를 모집하고, 게임 외부 사이트로 접근을 유도하는 등의 방법으로 도박 행위가 지속되고 있습니다.\n" +
                            "\n" +
                            "이와 같이 ‘메이플스토리를 단순 매개로 한 도박 패턴’ 또한 근절하기 위해 단속 방법을 계속해서 고도화하고 있으며, 그 결과 사행성 행위 유도, 참여로 이용제한되는 ID와 사행성 행위로 유통된 메소의 회수량도 꾸준히 증가하고 있습니다.\n" +
                            "\n" +
                            "사행성 행위에 대한 호기심으로 한 번쯤은 가벼운 마음으로 참여해본 용사님들도 있을텐데요. 사행성 행위에 참여를 유도하는 경우는 물론, 사행성 행위에 단순히 참여하는 경우도 운영정책에 따라 게임 이용제한 혹은 거래제한 조치될 수 있는 점 안내드립니다. 이 점 주의하셔서 게임을 이용하시면서 사행성 행위 및 사행성 홍보를 목격하시더라도 절대 참여하지 마시고 게임 내 ‘신고하기’ 기능을 통해 적극적으로 신고해 주시길 부탁드리겠습니다.");
                    test.setDate("2020년 10월 19일");
                    titleList.add(test);
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
                    Log.e("Success save", "!");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Fail save", t.toString());
            }
        });
    }

}
