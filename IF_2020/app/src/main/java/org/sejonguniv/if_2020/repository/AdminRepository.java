package org.sejonguniv.if_2020.repository;

import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.model.DeleteKey;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.PassWord;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.RegistPasskey;
import org.sejonguniv.if_2020.model.UserPassInfo;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
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
        return service.edit(listNumer, notice).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> deleteNotice(int listNumber) {
        return service.delete(listNumber).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<ArrayList<PassKey>> getPassKey(){
        return service.getPasskeyList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<UserPassInfo>> getPassInfo(String passkey){
        return service.getPassInfo(passkey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> registPasskey(RegistPasskey registPasskey){
        return service.registPasskey(registPasskey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> deletePasskey(DeleteKey passkey){
        return service.deletePasskey(passkey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<ArrayList<People>> getExcelData() {
        return service.getMemberList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Response<Void>> saveExcelData(People people){
        return service.saveExcelData(people).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> deleteExcelData(int number){
        return service.deleteExcelData(number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> editExcelData(int id, People people){
        return service.editExcelData(id, people).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<CalendarData>> getCalendarList(){
       return service.getCalendarList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> addCalendar(CalendarData calendarData){
        return service.addCalendar(calendarData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> deleteCalendar(int position){
        return service.deleteCalendar(position).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<Void>> editCalendar(int position, CalendarData calendarData){
        return service.updateCalendar(position, calendarData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> updatePassword(String password, String role){
        return service.updatePassword(new PassWord(password, role)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
