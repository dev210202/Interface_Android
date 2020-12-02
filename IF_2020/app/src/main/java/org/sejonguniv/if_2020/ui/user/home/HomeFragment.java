package org.sejonguniv.if_2020.ui.user.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.iid.FirebaseInstanceId;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONException;
import org.json.JSONObject;
import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentHomeBinding;
import org.sejonguniv.if_2020.model.UserData;
import org.sejonguniv.if_2020.ui.admin.home.AdminHomeFragment;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.user.list.ListFragment;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_home, container);
        setViewModel(HomeViewModel.class);

        binding.dateTextview.setText(CalendarDay.today().getMonth() + "월" + CalendarDay.today().getDay() + "일" + getDay());

        binding.checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                AdminNoticeFragment homeFragment = new AdminNoticeFragment();
                ListFragment listFragment = new ListFragment();

                transaction.replace(R.id.frame_layout, listFragment).commitAllowingStateLoss();
            }
        });

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
}