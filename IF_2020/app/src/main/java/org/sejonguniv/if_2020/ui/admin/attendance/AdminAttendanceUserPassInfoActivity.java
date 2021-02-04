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

        AdminAttendanceUserPassInfoViewModel viewModel = ViewModelProviders.of(this).get(AdminAttendanceUserPassInfoViewModel.class);
        AdminAttendanceUserPassInfoAdapter adminAttendancePassKeyAdapter = new AdminAttendanceUserPassInfoAdapter();
        binding.recyclerview.setAdapter(adminAttendancePassKeyAdapter);
        binding.setUserPassInfo(viewModel.passInfoList);

        Intent intent = getIntent();
        PassKey passKey = (PassKey) intent.getSerializableExtra("passkey");
        binding.setPasskeyInfo(passKey);

        viewModel.getUserPassInfo(passKey.getPasskey());

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteNoticeDialog(viewModel, passKey);
            }
        });
        binding.swipeRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 2/3 10:06 추가
                binding.swipeRefreshlayout.setRefreshing(false);
                binding.setUserPassInfo(viewModel.passInfoList);
                viewModel.getUserPassInfo(passKey.getPasskey());
            }
        });
        Observer<ArrayList<UserPassInfo>> observer = new Observer<ArrayList<UserPassInfo>>() {
            @Override
            public void onChanged(ArrayList<UserPassInfo> userPassInfos) {
                binding.setUserPassInfo(viewModel.passInfoList);
            }
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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDeleteDialog.dismiss();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeDeleteDialog.dismiss();
                viewModel.deletePasskey(passKey.getPasskey());
                finish();
            }
        });
    }

}