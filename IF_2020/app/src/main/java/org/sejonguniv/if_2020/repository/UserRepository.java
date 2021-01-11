package org.sejonguniv.if_2020.repository;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository extends Repository {

    private static UserRepository instance;

    private UserRepository() {

    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public Observable<ArrayList<Notice>> getNotice() {
        return service.getNoticeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<People>> getExcelData() {
        return service.getMemberList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
