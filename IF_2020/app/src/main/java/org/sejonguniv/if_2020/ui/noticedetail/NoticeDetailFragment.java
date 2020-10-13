package org.sejonguniv.if_2020.ui.noticedetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentNoticeDetailBinding;

public class NoticeDetailFragment extends BaseFragment<FragmentNoticeDetailBinding, NoticeDetailViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_notice_detail, container);
        setViewModel(NoticeDetailViewModel.class);
        startProgressBar();
        binding.setViewModel(viewModel);
        binding.setTitle(viewModel.title);
        binding.setContent(viewModel.content);
        View view = binding.getRoot();

        Bundle data = getArguments();
        int noticeId = data.getInt("noticeId");

        viewModel.findNotice(noticeId, dialog);

        return view;
    }
}