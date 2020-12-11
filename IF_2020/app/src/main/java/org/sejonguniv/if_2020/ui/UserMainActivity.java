package org.sejonguniv.if_2020.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.databinding.ActivityUserMainBinding;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;
import org.sejonguniv.if_2020.ui.user.excel.ExcelFragment;
import org.sejonguniv.if_2020.ui.user.notice.NoticeFragment;

public class UserMainActivity extends BaseActivity<ActivityUserMainBinding> {

    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        setBinding(R.layout.activity_user_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
        binding.navigationview.setNavigationItemSelectedListener(new navigationItemSelectedListener());
    }

    public void openDrawerLayout(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.START);
    }

    private class navigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (id) {
                case R.id.home: {
                    transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();
                    break;
                }

                case R.id.notice: {
                    NoticeFragment noticeFragment = new NoticeFragment();
                    transaction.replace(R.id.frame_layout, noticeFragment).commitAllowingStateLoss();
                    break;
                }
                case R.id.attendancecheck: {
                    AttendanceFragment attendanceFragment = new AttendanceFragment();
                    transaction.replace(R.id.frame_layout, attendanceFragment).commitAllowingStateLoss();
                    break;
                }
                case R.id.excel: {
                    ExcelFragment excelFragment = new ExcelFragment();
                    transaction.replace(R.id.frame_layout, excelFragment).commitAllowingStateLoss();
                    break;
                }
                case R.id.calendar: {
                    CalendarFragment calendarFragment = new CalendarFragment();
                    transaction.replace(R.id.frame_layout, calendarFragment).commitAllowingStateLoss();
                    break;
                }

            }
            binding.drawerlayout.closeDrawer(GravityCompat.START);
            return false;
        }
    }


}