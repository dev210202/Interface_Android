package org.sejonguniv.if_2020.ui.admin.attendance;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminAttendanceBinding;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.RegistPasskey;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendancePassKeyAdapter;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeEditActivity;

import java.util.ArrayList;

public class AdminAttendanceFragment extends BaseFragment<FragmentAdminAttendanceBinding, AdminAttendanceViewModel> {

    int REGIST = 10;
    int REGIST_COMPLETE = 30;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_attendance, container);
        setViewModel(AdminAttendanceViewModel.class);

        binding.setPasskey(viewModel.passkeyList);

        setProgressBar();
        dialog.show();
        viewModel.getPasskey();

        AdminAttendancePassKeyAdapter adminAttendancePassKeyAdapter = new AdminAttendancePassKeyAdapter();
        adminAttendancePassKeyAdapter.setOnItemClickListener(new AdminAttendancePassKeyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent intent = new Intent(getActivity().getApplicationContext(), AdminAttendanceUserPassInfoActivity.class);
                intent.putExtra("passkey", viewModel.passkeyList.getValue().get(position));

                startActivity(intent);

            }
        });
        binding.recyclerview.setAdapter(adminAttendancePassKeyAdapter);

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AdminAttendanceRegistActivity.class);
                startActivityForResult(intent, REGIST);
            }
        });

        binding.swipeRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                dialog.show();
                binding.swipeRefreshlayout.setRefreshing(false);
                viewModel.getPasskey();
            }
        });

        Observer<Boolean> dialogObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dialog.dismiss();
            }
        };

        Observer<ArrayList<PassKey>> observer = new Observer<ArrayList<PassKey>>() {
            @Override
            public void onChanged(ArrayList<PassKey> passKeys) {
                binding.setPasskey(viewModel.passkeyList);
            }
        };

        viewModel.passkeyList.observe(this, observer);
        viewModel.isDataReceive.observe(this,dialogObserver);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REGIST_COMPLETE) {
            RegistPasskey registPasskey = (RegistPasskey) data.getSerializableExtra("registPasskey");
            Log.e("PASSKEY", registPasskey.getPasskey());
            viewModel.registPasskey(registPasskey);
        }
    }
}