package org.sejonguniv.if_2020.ui.user.calendar;

import androidx.lifecycle.MutableLiveData;

import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.repository.UserRepository;

import java.util.ArrayList;

public class CalendarViewModel extends BaseViewModel {

    UserRepository userRepository = UserRepository.getInstance();
    MutableLiveData<ArrayList<CalendarData>> calendarDataArrayList = new MutableLiveData<>();

    public void getCalendarList(){
        compositeDisposable.add(userRepository.getCalendarList().subscribe(
                calendarData -> calendarDataArrayList.postValue(calendarData)
        ));
    }
}
