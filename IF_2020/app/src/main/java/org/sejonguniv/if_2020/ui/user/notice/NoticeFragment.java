package org.sejonguniv.if_2020.ui.user.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

import java.util.ArrayList;

public class NoticeFragment extends BaseFragment<FragmentNoticeBinding, NoticeViewModel> {

    NoticeAdapter noticeAdapter = new NoticeAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_notice, container);
        setViewModel(NoticeViewModel.class);
        setProgressBar();

        dialog.show();
        viewModel.getNoticeList();

        binding.setNoticeList(viewModel.noticeList.getValue());
        binding.noticeRecyclerview.setAdapter(noticeAdapter);
        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());

        Observer<ArrayList<Notice>> noticeObserver = notices -> {
            binding.setNoticeList(notices);
            dialog.dismiss();
        };

        viewModel.noticeList.observe(getViewLifecycleOwner(), noticeObserver);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getCompsiteDisposable().clear();
    }

}