package org.sejonguniv.if_2020.ui.admin.attendance;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseActivity;
import org.sejonguniv.if_2020.databinding.ActivityAdminAttendanceUserPassInfoBinding;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.UserPassInfo;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendancePassKeyAdapter;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendanceUserPassInfoAdapter;

import java.util.ArrayList;

public class AdminAttendanceUserPassInfoActivity extends BaseActivity<ActivityAdminAttendanceUserPassInfoBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_attendance_user_pass_info);
        setBinding(R.layout.activity_admin_attendance_user_pass_info);
        setProgressBar();

        AdminAttendanceUserPassInfoViewModel viewModel = ViewModelProviders.of(this).get(AdminAttendanceUserPassInfoViewModel.class);
        PassKey passKey = (PassKey) getIntent().getSerializableExtra("passkey");

        dialog.show();
        viewModel.getUserPassInfo(passKey.getPasskey());

        AdminAttendanceUserPassInfoAdapter adminAttendancePassKeyAdapter = new AdminAttendanceUserPassInfoAdapter();
        binding.recyclerview.setAdapter(adminAttendancePassKeyAdapter);
        binding.setUserPassInfo(viewModel.passInfoList.getValue());
        binding.setPasskeyInfo(passKey);
        binding.deleteButton.setOnClickListener(v -> showDeleteNoticeDialog(viewModel, passKey));
        binding.swipeRefreshlayout.setOnRefreshListener(() -> {
            dialog.show();
            binding.swipeRefreshlayout.setRefreshing(false);
            binding.setUserPassInfo(viewModel.passInfoList.getValue());
            viewModel.getUserPassInfo(passKey.getPasskey());
        });

        Observer<ArrayList<UserPassInfo>> observer = userPassInfos -> {
            binding.setUserPassInfo(userPassInfos);
            dialog.dismiss();
        };
        viewModel.passInfoList.observe(this, observer);
    }

    private void showDeleteNoticeDialog(AdminAttendanceUserPassInfoViewModel viewModel, PassKey passKey) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_attendance_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog noticeDeleteDialog = builder.create();
        noticeDeleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        noticeDeleteDialog.show();
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button deleteButton = dialogView.findViewById(R.id.delete_button);

        cancelButton.setOnClickListener(v -> noticeDeleteDialog.dismiss());
        deleteButton.setOnClickListener(v -> {
            noticeDeleteDialog.dismiss();
            viewModel.deletePasskey(passKey.getPasskey());
            finish();
        });
    }

}