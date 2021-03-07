package org.sejonguniv.if_2020.ui.user.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentHomeBinding;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.user.excel.ExcelFragment;

import java.util.ArrayList;
import java.util.Calendar;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_home, container);
        setViewModel(HomeViewModel.class);

        viewModel.getRecentNotice();
        viewModel.checkCalnenader();

        binding.setNotice(new Notice("제목", "내용"));
        binding.checkButton.setEnabled(false);
        binding.dateTextview.setText(CalendarDay.today().getMonth() + "월" + CalendarDay.today().getDay() + "일" + getDay());
        binding.checkButton.setOnClickListener(new onClickListener());
        binding.calendarArrowButton.setOnClickListener(new onClickListener());
        binding.memberArrowButton.setOnClickListener(new onClickListener());

        Observer<Notice> noticeObserver = notice -> binding.setNotice(notice);

        Observer<ArrayList<CalendarData>> calendarObsever = calendarDataArrayList -> {
            String today = getToday();
            for (CalendarData calendarData : calendarDataArrayList) {
                if (calendarData.getDate().equals(today)) {
                    binding.calendarTitleTextview.setText(calendarData.getTitle());
                    binding.calendarContentTextview.setText(calendarData.getContent());
                    binding.checkButton.setEnabled(true);
                    binding.checkButton.setBackground(getResources().getDrawable(R.drawable.main_button_background));
                }
            }
        };

        viewModel.notice.observe(this, noticeObserver);
        viewModel.calendarDataArrayList.observe(this, calendarObsever);
        return binding.getRoot();
    }

    private String getToday() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month;
        String day;
        if (calendar.get(Calendar.MONTH) + 1 < 10) {
            month = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }
        if (calendar.get(Calendar.DATE) < 10) {
            day = "0" + calendar.get(Calendar.DATE);
        } else {
            day = String.valueOf(calendar.get(Calendar.DATE));
        }
        return year + "-" + month + "-" + day;
    }

    private String getDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String today = "";
        switch (day) {
            case 1:
                today = " (일)";
                break;
            case 2:
                today = " (월)";
                break;
            case 3:
                today = " (화)";
                break;
            case 4:
                today = " (수)";
                break;
            case 5:
                today = " (목)";
                break;
            case 6:
                today = " (금)";
                break;
            case 7:
                today = " (토)";
                break;
        }
        return today;
    }

    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.calendar_arrow_button) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                CalendarFragment calendarFragment = new CalendarFragment();
                transaction.replace(R.id.frame_layout, calendarFragment).commitAllowingStateLoss();
            } else if (id == R.id.member_arrow_button) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ExcelFragment excelFragment = new ExcelFragment();
                transaction.replace(R.id.frame_layout, excelFragment).commitAllowingStateLoss();
            } else if (id == R.id.check_button) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AttendanceFragment attendanceFragment = new AttendanceFragment();
                transaction.replace(R.id.frame_layout, attendanceFragment).commitAllowingStateLoss();
            }
        }
    }
}