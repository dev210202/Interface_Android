package org.sejonguniv.if_2020.ui.admin.notice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

import java.util.ArrayList;


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {
    AdminNoticeAdapter adapter = new AdminNoticeAdapter();
    int EDIT = 10;
    int REGIST = 20;
    int REGIST_DONE = 22;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);

        binding.setNoticeList(viewModel.noticeList);

        setProgressBar();
        dialog.show();

        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());
        binding.registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AdminNoticeRegistActivity.class);
                startActivityForResult(intent, REGIST);
            }
        });

        binding.noticeRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new onAdapterItemClickListener());
        viewModel.getNoticeList();

        Observer<ArrayList<Notice>> noticeObserver = new Observer<ArrayList<Notice>>() {
            @Override
            public void onChanged(ArrayList<Notice> notices) {
                binding.setNoticeList(viewModel.noticeList);
                Log.e("CHANGED", "!!");
            }
        };

        Observer<Boolean> dialogObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dialog.dismiss();
            }
        };

        viewModel.noticeList.observe(getViewLifecycleOwner(), noticeObserver);
        viewModel.isDataReceive.observe(getViewLifecycleOwner(), dialogObserver);



        return binding.getRoot();
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
                Log.e("click position", "" + position);
            } else if (v.getId() == R.id.edit_button) {
                Log.e("click position", "" + position);

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
        // fragment가 파괴되는 시점에 리소스 해제
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
                viewModel.deleteNotice(position);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("request code", "" + requestCode);
        Log.e("result code", "" + resultCode);
        if(resultCode == REGIST_DONE){
            viewModel.saveNotice(new Notice(data.getStringExtra("registTitle"), data.getStringExtra("registContent")));
        }
        else if (resultCode != 0) {
            viewModel.editNotice(resultCode, new Notice(data.getStringExtra("editTitle"), data.getStringExtra("editContent")));
        }
    }
}