package org.sejonguniv.if_2020.ui.admin.attendance;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminAttendanceBinding;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendanceAdapter;

public class AdminAttendanceFragment extends BaseFragment<FragmentAdminAttendanceBinding, AdminAttendanceViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_admin_attendance, container);
        setViewModel(AdminAttendanceViewModel.class);

        AdminAttendanceAdapter adminAttendanceAdapter = new AdminAttendanceAdapter();
        binding.recyclerview.setAdapter(adminAttendanceAdapter);

        return binding.getRoot();
    }
}