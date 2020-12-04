package org.sejonguniv.if_2020.ui.admin.excel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminExcelBinding;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;


public class AdminExcelFragment extends BaseFragment<FragmentAdminExcelBinding, AdminExcelFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);

        viewModel.getLocalExcelData(getContext());
        viewModel.setExcelData();

        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(viewModel.setTopTilteCell());
        adapter.setLeftList(viewModel.setLeftTitle());
        adapter.setCellList(viewModel.setCell());

        binding.excelpanel.setPanelAdapter(adapter);

        binding.completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveData();
             //   viewModel.uploadData(); -> 서버에 cell data 모두 저장
                Toast.makeText(getActivity().getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  viewModel. -> edittext data 서버에 저장
            }
        });

        return binding.getRoot();
    }


}