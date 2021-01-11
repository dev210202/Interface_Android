package org.sejonguniv.if_2020.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.Notice;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRepository extends Repository {
    private static AdminRepository instance;

    private AdminRepository() {

    }

    public static AdminRepository getInstance() {
        if (instance == null) {
            synchronized (AdminRepository.class) {
                if (instance == null) {
                    instance = new AdminRepository();
                }
            }
        }
        return instance;
    }

    public Observable<ArrayList<Notice>> getNotice() {
        return service.getNoticeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
/*
        Call<ArrayList<Notice>> request = service.getNotice();
        ArrayList<Notice> data = new ArrayList<Notice>();
        request.enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if (response.body() != null) {
                    if (response.body().isEmpty()) {
                        Notice emptyNotice = new Notice();
                        emptyNotice.setTitle("저장되어있는 공지사항이 없습니다.");
                        data.add(emptyNotice);
                        userList.postValue(data);
                    } else {
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
*/
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

    public void editNotice(int listNumer, Notice notice) {
        // 공지사항의 index에 맞춰서 update/{index}

        Call<Void> request = service.update(listNumer, notice);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() != null) {
                    Log.e("Success save!@#!@#", "!");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail save!@#!@#", t.toString());
            }
        });
    }

    public void deleteNotice(int listNumber) {
        Call<Void> request = service.delete(listNumber);
        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                } else if (response.body() != null) {
                    Log.e("Success delete", "!");
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail delete", t.toString());
            }
        });
    }

    public void getExcelData() {
        // admin 회원데이터 서버에서 가져옴
    }

}
