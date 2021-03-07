package org.sejonguniv.if_2020.ui.admin.home;

import android.content.Intent;
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
import org.sejonguniv.if_2020.databinding.FragmentAdminHomeBinding;
import org.sejonguniv.if_2020.model.CalendarData;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.AdminMainActivity;
import org.sejonguniv.if_2020.ui.admin.calendar.AdminCalendarFragment;
import org.sejonguniv.if_2020.ui.admin.excel.AdminExcelFragment;
import org.sejonguniv.if_2020.ui.admin.password.PassWordChangeFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class AdminHomeFragment extends BaseFragment<FragmentAdminHomeBinding, AdminHomeViewModel> {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_home, container);
        setViewModel(AdminHomeViewModel.class);

        viewModel.getRecentNotice();
        viewModel.checkCalnenader();

        binding.setNotice(new Notice("제목", "내용"));
        binding.dateTextview.setText(CalendarDay.today().getMonth() + "월" + CalendarDay.today().getDay() + "일" + getDay());
        binding.calendarArrowButton.setOnClickListener(new onClickListener());
        binding.memberArrowButton.setOnClickListener(new onClickListener());
        binding.ifImage.setOnClickListener(new onClickListener());

        Observer<Notice> noticeObserver = notice -> binding.setNotice(notice);
        Observer<ArrayList<CalendarData>> calendarObsever = calendarDataArrayList -> {
            String today = getToday();
            for (CalendarData calendarData : calendarDataArrayList) {
                if (calendarData.getDate().equals(today)) {
                    binding.calendarTitleTextview.setText(calendarData.getTitle());
                    binding.calendarContentTextview.setText(calendarData.getContent());
                }
            }
        };

        viewModel.calendarDataArrayList.observe(this, calendarObsever);
        viewModel.notice.observe(this, noticeObserver);

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
        if (day == 1) {
            today = " (일)";
        } else if (day == 2) {
            today = " (월)";
        } else if (day == 3) {
            today = " (화)";
        } else if (day == 4) {
            today = " (수)";
        } else if (day == 5) {
            today = " (목)";
        } else if (day == 6) {
            today = " (금)";
        } else if (day == 7) {
            today = " (토)";
        }
        return today;
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.calendar_arrow_button) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminCalendarFragment calendarFragment = new AdminCalendarFragment();
                transaction.replace(R.id.frame_layout, calendarFragment).commitAllowingStateLoss();
            } else if (id == R.id.member_arrow_button) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminExcelFragment excelFragment = new AdminExcelFragment();
                transaction.replace(R.id.frame_layout, excelFragment).commitAllowingStateLoss();
            } else if (id == R.id.check_button) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            } else if (id == R.id.if_image) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                PassWordChangeFragment passWordChangeFragment = new PassWordChangeFragment();
                transaction.replace(R.id.frame_layout, passWordChangeFragment).commitAllowingStateLoss();
            }
        }
    }
}