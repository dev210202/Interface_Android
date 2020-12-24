package org.sejonguniv.if_2020.ui.admin.notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.iid.FirebaseInstanceId;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNotificationBinding;
import org.sejonguniv.if_2020.service.InterfaceFirebaseInstanceIdService;

public class AdminNotificationFragment extends BaseFragment<FragmentAdminNotificationBinding, AdminNotificationViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notification, container);
        setViewModel(AdminNotificationViewModel.class);

        binding.notificationButton.setOnClickListener(new onClickListener());

        return binding.getRoot();
    }

    private class onClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String title = binding.titleEdittext.getText().toString();
            String content = binding.contentEdittext.getText().toString();

            Log.e("TOKEN:", FirebaseInstanceId.getInstance().getToken());

            viewModel.sendNotification(title, content, FirebaseInstanceId.getInstance().getToken());
        }
    }
}