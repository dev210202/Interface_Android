package org.sejonguniv.if_2020.ui.admin.notice;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;

import java.util.ArrayList;


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {

    AdminNoticeAdapter adapter = new AdminNoticeAdapter();
    int EDIT = 10;
    int REGIST = 20;
    int REGIST_DONE = 22;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);
        setProgressBar();

        dialog.show();
        viewModel.getNoticeList();

        binding.setNoticeList(viewModel.noticeList.getValue());
        binding.noticeRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new onAdapterItemClickListener());
        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());
        binding.registButton.setOnClickListener(new onClickListener());

        Observer<ArrayList<Notice>> noticeObserver = notices -> {
            binding.setNoticeList(notices);
            dialog.dismiss();
        };
        viewModel.noticeList.observe(getViewLifecycleOwner(), noticeObserver);

        return binding.getRoot();
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(), AdminNoticeRegistActivity.class);
            startActivityForResult(intent, REGIST);
        }
    }

    private class onRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            binding.swipeRefreshlayout.setRefreshing(false);
            dialog.show();
            viewModel.getNoticeList();
        }
    }

    private class onAdapterItemClickListener implements AdminNoticeAdapter.OnItemClickListener {
        @Override
        public void onItemClick(View v, int position, int noticePosition) {
            if (v.getId() == R.id.delete_button) {
                showDeleteNoticeDialog(noticePosition);
            } else if (v.getId() == R.id.edit_button) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AdminNoticeEditActivity.class);
                intent.putExtra("title", viewModel.noticeList.getValue().get(position).getTitle());
                intent.putExtra("content", viewModel.noticeList.getValue().get(position).getContent());
                intent.putExtra("position", noticePosition);
                startActivityForResult(intent, EDIT);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getCompsiteDisposable().clear();
    }

    private void showDeleteNoticeDialog(int position) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_notice_delete, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        AlertDialog noticeDeleteDialog = builder.create();
        noticeDeleteDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        noticeDeleteDialog.show();
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button deleteButton = dialogView.findViewById(R.id.delete_button);

        cancelButton.setOnClickListener(v -> noticeDeleteDialog.dismiss());
        deleteButton.setOnClickListener(v -> {
            noticeDeleteDialog.dismiss();
            viewModel.deleteNotice(position);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REGIST_DONE) {
            viewModel.saveNotice(new Notice(data.getStringExtra("registTitle"), data.getStringExtra("registContent")));
        } else if (resultCode != 0) {
            viewModel.editNotice(resultCode, new Notice(data.getStringExtra("editTitle"), data.getStringExtra("editContent")));
        }
    }
}