package org.sejonguniv.if_2020.repository;

import android.app.Dialog;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.network.APIService;

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

public class UserRepository extends Repository{

    private static UserRepository instance;
    private UserRepository(){

    }
    public static UserRepository getInstance(){
        if(instance == null){
            synchronized (UserRepository.class){
                if(instance == null){
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public void getNotice(MutableLiveData<ArrayList<Notice>> userList) {

        Call<ArrayList<Notice>> request = service.getNotice();
        ArrayList<Notice> data = new ArrayList<Notice>();
        request.enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if (response.body() != null) {
                    if(response.body().isEmpty()){
                        Notice emptyNotice = new Notice();
                        emptyNotice.setTitle("저장되어있는 공지사항이 없습니다.");
                        data.add(emptyNotice);
                        userList.postValue(data);
                    }
                    else{
                        Log.e("response", response.body().get(0).getTitle());
                        userList.postValue(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                Notice errorNotice = new Notice();
                errorNotice.setTitle("데이터를 불러오지 못했습니다. 당겨서 새로고침을 통해 다시 시도해주세요.");
                data.add(errorNotice);
                userList.postValue(data);
                Log.e("Fail", t.toString());

            }
        });
    }

    public void getExcelData(MutableLiveData<ArrayList<People>> peopleArrayList){

        People base = new People("학번", "이름", "기수", "연락처");
        Call<List<People>> request = service.findAll();
        request.enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {

                // 받아온 값 peopleArrayList에 add
                ArrayList<People> peopleList = new ArrayList<People>();
                peopleList.add(base);
                for (People people : response.body()) {
                    peopleList.add(people);
                    Log.e("getExcelData People", people.getName());
                }
                peopleArrayList.postValue(peopleList);

            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {
                Log.e("getExcelData", "Fail" + t.toString());
            }
        });

    }
}
