package org.sejonguniv.if_2020.ui.user.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentCalendarBinding;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.ui.decorator.EventDecorator;
import org.sejonguniv.if_2020.ui.decorator.OtherDecorator;

import java.util.ArrayList;

public class CalendarFragment extends BaseFragment<FragmentCalendarBinding, CalendarViewModel> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_calendar, container);
        setViewModel(CalendarViewModel.class);

        viewModel.getCalendarList();

        Observer<ArrayList<CalendarData>> observer = calendarData -> setEventDays();

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
            calendarDayArrayList.add(CalendarDay.from(year, month, day));
        }

        EventDecorator eventDecorator = new EventDecorator(calendarDayArrayList, getActivity(), binding.titleTextview, binding.contentTextview);
        binding.calendarview.addDecorators(eventDecorator);
        binding.calendarview.setOnDateChangedListener((widget, date, selected) -> {
            if (calendarDayArrayList.contains(date)) {
                eventDecorator.setTitleText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getTitle());
                eventDecorator.setContentText(viewModel.calendarDataArrayList.getValue().get(calendarDayArrayList.lastIndexOf(date)).getContent());
            } else {
                OtherDecorator otherDecorator = new OtherDecorator(date, getActivity(), binding.titleTextview);
                binding.calendarview.addDecorator(otherDecorator);
                eventDecorator.setTitleText("저장된 일정이 없습니다");
                eventDecorator.setContentText(" ");
            }
        });

    }

}