package org.sejonguniv.if_2020.ui.user.attendance;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.google.firebase.iid.FirebaseInstanceId;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAttendanceBinding;
import org.sejonguniv.if_2020.gps.GpsTracker;
import org.sejonguniv.if_2020.model.Attendee;

import java.util.Calendar;

public class AttendanceFragment extends BaseFragment<FragmentAttendanceBinding, AttendanceViewModel> {


    private static final int GPS_ENABLE_REQUEST_CODE = 20;
    private static final int PERMISSIONS_REQUEST_CODE = 10;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_attendance, container);
        setViewModel(AttendanceViewModel.class);

        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }

        binding.nameEdittext.addTextChangedListener(textWatcher);
        binding.studentidEdittext.addTextChangedListener(textWatcher);
        binding.groupnumEdittext.addTextChangedListener(textWatcher);
        binding.passwordEdittext.addTextChangedListener(textWatcher);
        binding.checkButton.setOnClickListener(new onClickListener());

        Observer<String> responseObserver = response -> {
            if (response.equals("출석되었습니다.")) {
                Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
            }
        };

        viewModel.responseData.observe(this, responseObserver);

        return binding.getRoot();
    }

    private void checkRunTimePermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(getActivity().getApplicationContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE);
        }
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean isExistEmptyInput() {
        return (binding.nameEdittext.getText().length() == 0 ||
                binding.studentidEdittext.getText().length() == 0 ||
                binding.groupnumEdittext.getText().length() == 0 ||
                binding.passwordEdittext.getText().length() == 0);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isExistEmptyInput()) {
                binding.checkButton.setEnabled(true);
            }
        }
    };

    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (isExistEmptyInput()) {
                Toast.makeText(getActivity().getApplicationContext(), "빈칸이 없게 입력해주세요!", Toast.LENGTH_LONG).show();
            } else {
                viewModel.sendUserAttendance(setAttendee());
            }

        }
    }

    private Attendee setAttendee() {
        GpsTracker gpsTracker = new GpsTracker(getContext());
        double latitude = gpsTracker.getLatitude();
        double longithude = gpsTracker.getLongitude();

        Attendee attendee = new Attendee();
        attendee.setName(binding.nameEdittext.getText().toString());
        attendee.setGeneration(binding.groupnumEdittext.getText().toString());
        attendee.setPasskey(binding.passwordEdittext.getText().toString());
        attendee.setStudentId(binding.studentidEdittext.getText().toString());
        attendee.setToken(FirebaseInstanceId.getInstance().getToken());
        attendee.setLat(String.valueOf(latitude));
        attendee.setLon(String.valueOf(longithude));

        attendee.setDateTime(getDate());
        return attendee;
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        String date = year + "-";
        if (month < 10) {
            date += "0" + month + "-";
        } else {
            date += month + "-";
        }
        if (day < 10) {
            date += "0" + day + "T";
        } else {
            date += day + "T";
        }
        if (hour < 10) {
            date += "0" + hour + ":";
        } else {
            date += hour + ":";
        }
        if(min < 10){
            date += "0" + min;
        }
        else{
            date += min;
        }
        return date;
    }
}