package org.sejonguniv.if_2020.ui.admin.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminAttendanceBinding;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.RegistPasskey;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendancePassKeyAdapter;

import java.util.ArrayList;

public class AdminAttendanceFragment extends BaseFragment<FragmentAdminAttendanceBinding, AdminAttendanceViewModel> {

    int REGIST = 10;
    int REGIST_COMPLETE = 30;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_attendance, container);
        setViewModel(AdminAttendanceViewModel.class);
        setProgressBar();

        dialog.show();
        viewModel.getPasskey();

        binding.setPasskey(viewModel.passkeyList.getValue());
        AdminAttendancePassKeyAdapter adminAttendancePassKeyAdapter = new AdminAttendancePassKeyAdapter();
        adminAttendancePassKeyAdapter.setOnItemClickListener((v, position) -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AdminAttendanceUserPassInfoActivity.class);
            intent.putExtra("passkey", viewModel.passkeyList.getValue().get(position));
            startActivity(intent);
        });
        binding.recyclerview.setAdapter(adminAttendancePassKeyAdapter);

        binding.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AdminAttendanceRegistActivity.class);
            startActivityForResult(intent, REGIST);
        });

        binding.swipeRefreshlayout.setOnRefreshListener(() -> {
            dialog.show();
            binding.swipeRefreshlayout.setRefreshing(false);
            viewModel.getPasskey();
        });

        Observer<ArrayList<PassKey>> observer = passKeys -> {
            binding.setPasskey(passKeys);
            dialog.dismiss();
        };

        viewModel.passkeyList.observe(this, observer);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REGIST_COMPLETE) {
            RegistPasskey registPasskey = (RegistPasskey) data.getSerializableExtra("registPasskey");
            viewModel.registPasskey(registPasskey);
        }
    }
}