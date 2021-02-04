package org.sejonguniv.if_2020.ui.admin.calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminCalendarBinding;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.ui.admin.calendar.OtherDecorator;

import java.util.ArrayList;

public class AdminCalendarFragment extends BaseFragment<FragmentAdminCalendarBinding, AdminCalendarViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_admin_calendar, container);
        setViewModel(AdminCalendarViewModel.class);

        viewModel.getCalendarList();

        Observer<ArrayList<CalendarData>> observer = new Observer<ArrayList<CalendarData>>() {
            @Override
            public void onChanged(ArrayList<CalendarData> calendarData) {
                setEventDays();
            }
        };

        viewModel.calendarDataArrayList.observe(this, observer);

        return binding.getRoot();
    }


    private void setEventDays() {
        ArrayList<CalendarDay> calendarDayArrayList = new ArrayList<>();

        for (CalendarData calendarData : viewModel.calendarDataArrayList.getValue()) {
            String date = calendarData.getDate();
            String[] yearSplit = date.split("-");
            int year = Integer.parseInt(yearSplit[0]);
            int month = Integer.parseInt(yearSplit[1]);
            int day = Integer.parseInt(yearSplit[2]);

            Log.e("year", " " + year);
            Log.e("month", " " + month);
            Log.e("day", " " + day);

            calendarDayArrayList.add(CalendarDay.from(year, month, day));
        }

        EventDecorator eventDecorator = new EventDecorator(calendarDayArrayList, getActivity(), binding.titleTextview, binding.contentTextview);
        binding.calendarview.addDecorators(eventDecorator);
        binding.calendarview.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if (calendarDayArrayList.contains(date)) {
                    eventDecorator.setTitleText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getTitle());
                    eventDecorator.setContentText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getContent());
                } else {
                    OtherDecorator otherDecorator = new OtherDecorator(date, getActivity(), binding.titleTextview);
                    binding.calendarview.addDecorator(otherDecorator);
                    eventDecorator.setTitleText("저장된 일정이 없습니다");
                    eventDecorator.setContentText(" ");
                }
            }
        });

    }
}