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

        // sdk 26이상만 쓸 수 있음
        binding.datepicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                binding.dateTextview.setText(year + "년" + (monthOfYear + 1) + "월" + dayOfMonth + "일");
            }
        });
        binding.timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay > 12) {
                    binding.timeTextview.setText("오후" + (hourOfDay - 12) + "시" + minute + "분");
                } else {
                    binding.timeTextview.setText("오전" + hourOfDay + "시" + minute + "분");
                }
            }
        });

        binding.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GPS, startTime, endTime(startTime + 1시간?), passkey


                Location location = getLocation();

                MeetingTime meetingTime = getMeetingTime();

                RegistPasskey registPasskey = new RegistPasskey(binding.passkeyEdittext.getText().toString(), meetingTime.getStartTime(), meetingTime.getEndTime(), location.getLat(), location.getLon());

                Intent intent = new Intent();
                intent.putExtra("registPasskey", registPasskey);
                setResult(REGIST_COMPLETE, intent);
                finish();
            }
        });
    }

    private MeetingTime getMeetingTime() {

        //2021-12-31T23:34

        int year = binding.datepicker.getYear();
        int month = binding.datepicker.getMonth() + 1;
        int day = binding.datepicker.getDayOfMonth();

        int hour = binding.timepicker.getHour();
        int minute = binding.timepicker.getMinute();

        String startMonth = "";
        String startDay = "";
        String startHour = "";
        String startMinute = "";

        if (month < 10) {
            startMonth = 0 + String.valueOf(month);
        }
        else{
            startMonth = String.valueOf(month);
        }
        if (day < 10) {
            startDay = 0 + String.valueOf(day);
        }
        else{
            startDay = String.valueOf(day);
        }
        if (hour < 10) {
            startHour = 0 + String.valueOf(hour);
        } else{
            startHour = String.valueOf(hour);
        }
        if (minute < 10) {
            startMinute = 0 + String.valueOf(minute);
        } else{
            startMinute = String.valueOf(minute);
        }
        String resultStart = year + "-" + startMonth + "-" + startDay + "T" + startHour + ":" + startMinute;

        String endMonth = "";
        String endDay = "";
        String endHour = "";
        String endMinute = "";

        if (hour >= 23) {
            day++;
            endHour = 0 + String.valueOf(hour - 23);
            Log.e("END HOUR1", endHour);

        }
        else if (hour + 1 < 10) {
            endHour = 0 + String.valueOf(hour + 1);
            Log.e("END HOUR2", endHour);
        }
        else{
            endHour = String.valueOf(hour + 1);
            Log.e("END HOUR3", endHour);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        if (day > calendar.getMaximum(Calendar.DAY_OF_MONTH)){
            month++;

            Log.e("END month", ""+month);
            endDay = 0 + String.valueOf(day - calendar.getMaximum(Calendar.DAY_OF_MONTH));

            Log.e("END endDay", ""+endDay);
        }
        else if(day < 10){
            endDay = 0 + String.valueOf(day);
        }
        else{
            endDay =  String.valueOf(day);
        }
        if(month > 12){
            year++;
            Log.e("END year", ""+year);
            endMonth = 0 + String.valueOf(month - 12);
            Log.e("END endMonth", ""+endMonth);
        }
        else if(month < 10){
            endMonth = 0 + String.valueOf(month);
        }
        else{
            endMonth = String.valueOf(month);
        }

        String resultEnd = year + "-" + endMonth + "-" + endDay + "T" + endHour + ":" + minute;

        MeetingTime meetingTime = new MeetingTime(resultStart, resultEnd);
        return meetingTime;
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

        Log.e("Current Lat, Lon", "lat : " + latitude + " lon : " + longithude);

        Location location = new Location(String.valueOf(latitude), String.valueOf(longithude));
        return location;
    }

    private void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(this.getApplicationContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
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
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}