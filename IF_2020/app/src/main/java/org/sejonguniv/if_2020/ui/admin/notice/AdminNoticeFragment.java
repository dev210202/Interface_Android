package org.sejonguniv.if_2020.ui.admin.notice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class AdminNoticeFragment extends BaseFragment<FragmentAdminNoticeBinding, AdminNoticeViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_notice, container);
        setViewModel(AdminNoticeViewModel.class);

        View view = binding.getRoot();

        binding.setViewModel(viewModel);
        binding.setNoticeList(viewModel.titleList);

        AdminNoticeAdapter adapter = new AdminNoticeAdapter();

        startProgressBar();
        binding.noticeRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdminNoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if(v.getId() == R.id.delete_button){
                   viewModel.deleteNotice(position);
                }
                else {
                    // edit
                }
            }
        });
        viewModel.getNoticeList(dialog);
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.setTitle(binding.titleEdittext.getText().toString());
                notice.setContent(binding.contentEdittext.getText().toString());
                viewModel.editNotice(notice);
                viewModel.getNoticeList(dialog);
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice notice = new Notice();
                notice.setTitle(binding.titleEdittext.getText().toString());
                notice.setContent(binding.contentEdittext.getText().toString());
                viewModel.saveNotice(notice);
            }
        });

        return view;
    }
}