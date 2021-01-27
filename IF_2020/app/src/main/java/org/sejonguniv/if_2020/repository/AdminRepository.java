package org.sejonguniv.if_2020.repository;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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
    }

    public Observable<Response<Void>> saveNotice(Notice notice) {
        return service.saveNotice(notice).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> editNotice(int listNumer, Notice notice) {
        // 공지사항의 index에 맞춰서 update/{index}
        return service.edit(listNumer, notice).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> deleteNotice(int listNumber) {
        return service.delete(listNumber).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<People>> getExcelData() {
        return service.getMemberList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
