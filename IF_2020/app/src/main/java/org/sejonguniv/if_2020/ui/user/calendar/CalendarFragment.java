package org.sejonguniv.if_2020.ui.user.calendar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentCalendarBinding;

import java.util.ArrayList;

public class CalendarFragment extends BaseFragment<FragmentCalendarBinding, CalendarViewModel>  {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_calendar, container);
        setViewModel(CalendarViewModel.class);

        View view = binding.getRoot();

        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
        calendarDayList.add(CalendarDay.today());



        Decorator decorator = new Decorator(Color.RED, calendarDayList, getActivity(), binding.calendarTextview);

        binding.calendarView.addDecorator(decorator);
        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                if(date.equals(CalendarDay.today())){
                    decorator.setText("CLICK");
                }
                else{
                    decorator.setText(" ");
                }
            }
        });

        return view;
    }

}