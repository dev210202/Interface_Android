package org.sejonguniv.if_2020.ui.admin.calendar;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.repository.AdminRepository;

import java.util.ArrayList;

public class AdminCalendarViewModel extends BaseViewModel {

    AdminRepository adminRepository = AdminRepository.getInstance();
    MutableLiveData<ArrayList<CalendarData>>  calendarDataArrayList = new MutableLiveData<>();
    public void getCalendarList(){
        compositeDisposable.add(adminRepository.getCalendarList().subscribe(

                calendarData -> {
                    calendarDataArrayList.postValue(calendarData);
                },
                error ->{

                },
                () -> {

                }

        ));
    }
}