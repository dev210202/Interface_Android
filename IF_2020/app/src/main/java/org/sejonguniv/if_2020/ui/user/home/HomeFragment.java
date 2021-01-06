package org.sejonguniv.if_2020.ui.user.home;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentHomeBinding;
import org.sejonguniv.if_2020.model.Attendee;
import org.sejonguniv.if_2020.ui.AdminMainActivity;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.user.excel.ExcelFragment;

import java.util.Calendar;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_home, container);
        setViewModel(HomeViewModel.class);
        viewModel.sendUserToken();
        binding.dateTextview.setText(CalendarDay.today().getMonth() + "월" + CalendarDay.today().getDay() + "일" + getDay());
        binding.checkButton.setOnClickListener(new onClickListener());

        return binding.getRoot();
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

           Intent intent = new Intent(getActivity().getApplicationContext(), AdminMainActivity.class);
           startActivity(intent);

        }
    }
}