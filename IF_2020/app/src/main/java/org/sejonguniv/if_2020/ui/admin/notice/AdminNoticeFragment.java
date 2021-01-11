package org.sejonguniv.if_2020.ui.admin.notice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

import java.util.ArrayList;


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {
    AdminNoticeAdapter adapter = new AdminNoticeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);

        binding.setViewModel(viewModel);
        binding.setNoticeList(viewModel.titleList);

        showProgressBar();

        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());

        binding.noticeRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new onAdapterItemClickListener());
        viewModel.getNoticeList();

        binding.saveButton.setOnClickListener(new onClickListener());
        // update 버튼 미구현 -> 온클릭 리스너 추가해서 구현할 것
        androidx.lifecycle.Observer<ArrayList<Notice>> noticeObserver = new Observer<ArrayList<Notice>>() {
            @Override
            public void onChanged(ArrayList<Notice> notices) {
                binding.setNoticeList(viewModel.titleList);
                Log.e("CHANGED", "!!");
            }
        };

        viewModel.titleList.observe(this, noticeObserver);


        return binding.getRoot();
    }

    private class onRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            binding.swipeRefreshlayout.setRefreshing(false);
            viewModel.getNoticeList();
        }
    }

    private class onAdapterItemClickListener implements AdminNoticeAdapter.OnItemClickListener {
        @Override
        public void onItemClick(View v, int position) {
            if (v.getId() == R.id.delete_button) {
                viewModel.deleteNotice(position);
            } else {
                Notice editNotice = new Notice();
                editNotice.setId(position);
                editNotice.setTitle(binding.titleEdittext.getText().toString());
                editNotice.setContent(binding.contentEdittext.getText().toString());

                viewModel.editNotice(position, editNotice);
            }
        }
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Notice notice = new Notice();
            notice.setTitle(binding.titleEdittext.getText().toString());
            notice.setContent(binding.contentEdittext.getText().toString());
            viewModel.saveNotice(notice);
            viewModel.getNoticeList();
            adapter.notifyDataSetChanged();
        }
    }

}