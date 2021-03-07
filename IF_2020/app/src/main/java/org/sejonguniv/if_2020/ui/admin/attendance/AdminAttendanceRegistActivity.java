package org.sejonguniv.if_2020.ui.admin.attendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminAttendanceRegistBinding;
import org.sejonguniv.if_2020.gps.GpsTracker;
import org.sejonguniv.if_2020.model.Location;
import org.sejonguniv.if_2020.model.MeetingTime;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.RegistPasskey;

import java.util.Calendar;

public class AdminAttendanceRegistActivity extends BaseActivity<ActivityAdminAttendanceRegistBinding> {

    private static final int GPS_ENABLE_REQUEST_CODE = 20;
    private static final int PERMISSIONS_REQUEST_CODE = 10;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    int REGIST_COMPLETE = 30;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_attendance_regist);
        setBinding(R.layout.activity_admin_attendance_regist);


        binding.datepicker.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> binding.dateTextview.setText(year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일"));
        binding.timepicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            if (hourOfDay > 12) {
                binding.timeTextview.setText("오후" + (hourOfDay - 12) + "시" + minute + "분");
            } else {
                binding.timeTextview.setText("오전" + hourOfDay + "시" + minute + "분");
            }
        });

        binding.completeButton.setOnClickListener(v -> {
            if (checkInput()) {
                Location location = getLocation();
                MeetingTime meetingTime = getMeetingTime();
                RegistPasskey registPasskey = new RegistPasskey(binding.passkeyEdittext.getText().toString(), meetingTime.getStartTime(), meetingTime.getEndTime(), location.getLat(), location.getLon());

                Intent intent = new Intent();
                intent.putExtra("registPasskey", registPasskey);
                setResult(REGIST_COMPLETE, intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "설정값을 모두 설정한 뒤 완료버튼을 눌러주세요.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private MeetingTime getMeetingTime() {

        int year = binding.datepicker.getYear();
        int month = binding.datepicker.getMonth() + 1;
        int day = binding.datepicker.getDayOfMonth();

        int hour = binding.timepicker.getHour();
        int minute = binding.timepicker.getMinute();

        String startMonth;
        String startDay;
        String startHour;
        String startMinute;

        if (month < 10) {
            startMonth = 0 + String.valueOf(month);
        } else {
            startMonth = String.valueOf(month);
        }
        if (day < 10) {
            startDay = 0 + String.valueOf(day);
        } else {
            startDay = String.valueOf(day);
        }
        if (hour < 10) {
            startHour = 0 + String.valueOf(hour);
        } else {
            startHour = String.valueOf(hour);
        }
        if (minute < 10) {
            startMinute = 0 + String.valueOf(minute);
        } else {
            startMinute = String.valueOf(minute);
        }
        String resultStart = year + "-" + startMonth + "-" + startDay + "T" + startHour + ":" + startMinute;

        String endMonth;
        String endDay;
        String endHour;

        if (hour >= 23) {
            day++;
            endHour = 0 + String.valueOf(hour - 23);
        } else if (hour + 1 < 10) {
            endHour = 0 + String.valueOf(hour + 1);
        } else {
            endHour = String.valueOf(hour + 1);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        if (day > calendar.getMaximum(Calendar.DAY_OF_MONTH)) {
            month++;
            endDay = 0 + String.valueOf(day - calendar.getMaximum(Calendar.DAY_OF_MONTH));
        } else if (day < 10) {
            endDay = 0 + String.valueOf(day);
        } else {
            endDay = String.valueOf(day);
        }
        if (month > 12) {
            year++;
            endMonth = 0 + String.valueOf(month - 12);
        } else if (month < 10) {
            endMonth = 0 + String.valueOf(month);
        } else {
            endMonth = String.valueOf(month);
        }

        String resultEnd = year + "-" + endMonth + "-" + endDay + "T" + endHour + ":" + minute;

        return new MeetingTime(resultStart, resultEnd);
    }

    private Location getLocation() {
        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }

        GpsTracker gpsTracker = new GpsTracker(this);
        double latitude = gpsTracker.getLatitude();
        double longithude = gpsTracker.getLongitude();

        return new Location(String.valueOf(latitude), String.valueOf(longithude));
    }

    private void checkRunTimePermission() {

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (!(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(this.getApplicationContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE);
        }
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", (dialog, id) -> {
            Intent callGPSSettingIntent
                    = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
        });
        builder.setNegativeButton("취소", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    private boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean checkInput() {
        return !(binding.dateTextview.getText().toString().equals("") && binding.timeTextview.getText().toString().equals("") && binding.passkeyEdittext.getText().equals(""));
    }
}