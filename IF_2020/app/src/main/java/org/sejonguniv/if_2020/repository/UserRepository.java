package org.sejonguniv.if_2020.repository;

import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.model.Login;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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

    public Observable<ResponseBody> login(String passkey) {
        return service.login(new Login(passkey)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<Notice>> getNotice() {
        return service.getNoticeList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> sendAttendance(Attendee attendee) {
        return service.insertAttendee(attendee).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<People>> getExcelData() {
        return service.getMemberList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<ArrayList<CalendarData>> getCalendarList(){
        return service.getCalendarList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
