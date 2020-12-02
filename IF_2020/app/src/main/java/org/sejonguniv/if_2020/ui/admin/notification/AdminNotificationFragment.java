package org.sejonguniv.if_2020.ui.admin.notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNotificationBinding;

public class AdminNotificationFragment extends BaseFragment<FragmentAdminNotificationBinding, AdminNotificationViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notification, container);
        setViewModel(AdminNotificationViewModel.class);




        return binding.getRoot();
    }
}