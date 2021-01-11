package org.sejonguniv.if_2020.ui.user.notice;

import android.database.Observable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class NoticeFragment extends BaseFragment<FragmentNoticeBinding, NoticeViewModel> {

    NoticeAdapter noticeAdapter = new NoticeAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_notice, container);
        setViewModel(NoticeViewModel.class);

        binding.setNoticeList(viewModel.titleList);
        binding.noticeRecyclerview.setAdapter(noticeAdapter);
        binding.searchView.setOnQueryTextListener(new onQueryTextListener());
        binding.swipeRefreshlayout.setOnRefreshListener(new onRefreshListener());

        showProgressBar();
        viewModel.getNoticeList();

        Observer<ArrayList<Notice>> noticeObserver = new Observer<ArrayList<Notice>>() {
            @Override
            public void onChanged(ArrayList<Notice> notices) {
                binding.setNoticeList(viewModel.titleList);
                Log.e("CHANGED", "!!");
            }
        };

        Observer<Boolean> dialogObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dialog.dismiss();
            }
        };

        viewModel.titleList.observe(this, noticeObserver);
        viewModel.isDataReceive.observe(this, dialogObserver);

        return binding.getRoot();
    }

    private class onRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            binding.swipeRefreshlayout.setRefreshing(false);
            showProgressBar();
            viewModel.getNoticeList();
        }
    }

    private class onQueryTextListener implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            noticeAdapter.setInput(query);
            Log.e("INPUT QUERY", query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    @Override
    public void onDestroy() {
        // fragment가 파괴되는 시점에 리소스 해제
        super.onDestroy();
        viewModel.getCompsiteDisposable().clear();
    }

}