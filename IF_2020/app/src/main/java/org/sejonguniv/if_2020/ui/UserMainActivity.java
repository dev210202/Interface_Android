package org.sejonguniv.if_2020.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.databinding.ActivityUserMainBinding;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeFragment;
import org.sejonguniv.if_2020.ui.user.attendance.AttendanceFragment;
import org.sejonguniv.if_2020.ui.user.calendar.CalendarFragment;
import org.sejonguniv.if_2020.ui.admin.list.AdminListFragment;
import org.sejonguniv.if_2020.ui.user.home.HomeFragment;
import org.sejonguniv.if_2020.ui.user.notice.NoticeFragment;

public class UserMainActivity extends BaseActivity<ActivityUserMainBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        setBinding(R.layout.activity_user_main);
        AdminNoticeFragment adminNoticeFragment = new AdminNoticeFragment();
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, homeFragment).commitAllowingStateLoss();

        binding.navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                    case R.id.list: {
                        AdminListFragment adminListFragment = new AdminListFragment();
                        transaction.replace(R.id.frame_layout, adminListFragment).commitAllowingStateLoss();
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
        });
    }

    public void openDrawerLayout(View view) {
        binding.drawerlayout.openDrawer(GravityCompat.START);
    }


}