package org.sejonguniv.if_2020.ui.admin.notice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);

        View view = binding.getRoot();
        Notice notice = new Notice();
        notice.setDate("123123");
        notice.setId(1);
        notice.setTitle("1105 TEST edit");
        notice.setContent("1105 TEST edit 1105 TEST 1105 TEST 1105 TEST 1105 TEST 1105 TEST");
//        viewModel.saveNotice(notice);
        viewModel.editNotice();
       // binding.setNoticeList();
        binding.setViewModel(viewModel);


        NoticeAdapter adapter = new NoticeAdapter();

        binding.noticeRecyclerview.setAdapter(adapter);


        return view;
    }
}