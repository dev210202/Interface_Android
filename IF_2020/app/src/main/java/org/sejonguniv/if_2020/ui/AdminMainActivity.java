package org.sejonguniv.if_2020.ui;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminMainBinding;
import org.sejonguniv.if_2020.ui.admin.attendance.AdminAttendanceFragment;
import org.sejonguniv.if_2020.ui.admin.calendar.AdminCalendarFragment;
import org.sejonguniv.if_2020.ui.admin.home.AdminHomeFragment;
import org.sejonguniv.if_2020.ui.admin.excel.AdminExcelFragment;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.admin.notification.AdminNotificationFragment;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.user.excel.ExcelFragment;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;
import org.sejonguniv.if_2020.ui.user.notice.NoticeFragment;

public class AdminMainActivity extends BaseActivity<ActivityAdminMainBinding> {

    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    Fragment currentFragment = new Fragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        setBinding(R.layout.activity_admin_main);
        if (savedInstanceState != null) {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
            transaction.replace(R.id.frame_layout, currentFragment).commitAllowingStateLoss();
        } else {
            currentFragment = adminHomeFragment;
            transaction.replace(R.id.frame_layout, adminHomeFragment).commitAllowingStateLoss();
        }
        binding.navigationview.setNavigationItemSelectedListener(new navigationItemSelectedListener());
    }

    public void openDrawerLayout(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.END);
    }

    private class navigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (id) {
                case R.id.home: {
                    transaction.replace(R.id.frame_layout, adminHomeFragment).commitAllowingStateLoss();
                    currentFragment = adminHomeFragment;
                    break;
                }
                case R.id.notice: {
                    AdminNoticeFragment adminNoticeFragment = new AdminNoticeFragment();
                    transaction.replace(R.id.frame_layout, adminNoticeFragment).commitAllowingStateLoss();
                    currentFragment = adminNoticeFragment;
                    break;
                }
                case R.id.attendancecheck: {
                    AdminAttendanceFragment adminAttendanceFragment = new AdminAttendanceFragment();
                    transaction.replace(R.id.frame_layout, adminAttendanceFragment).commitAllowingStateLoss();
                    currentFragment = adminAttendanceFragment;
                    break;
                }
                case R.id.excel: {
                    AdminExcelFragment adminExcelFragment = new AdminExcelFragment();
                    transaction.replace(R.id.frame_layout, adminExcelFragment).commitAllowingStateLoss();
                    currentFragment = adminExcelFragment;
                    break;
                }
                case R.id.calendar: {
                    AdminCalendarFragment adminCalendarFragment = new AdminCalendarFragment();
                    transaction.replace(R.id.frame_layout, adminCalendarFragment).commitAllowingStateLoss();
                    currentFragment = adminCalendarFragment;
                    break;
                }
                case R.id.notification: {
                    AdminNotificationFragment adminNotificationFragment = new AdminNotificationFragment();
                    transaction.replace(R.id.frame_layout, adminNotificationFragment).commitAllowingStateLoss();
                    currentFragment = adminNotificationFragment;
                    break;
                }
            }
            binding.drawerlayout.closeDrawer(GravityCompat.END);
            return false;
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        getSupportFragmentManager().putFragment(outState, "currentFragment", currentFragment);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
    }
}