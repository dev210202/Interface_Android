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
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;


public class AdminExcelFragment extends BaseFragment<FragmentAdminExcelBinding, AdminExcelFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);

        viewModel.getLocalExcelData(getContext());
        viewModel.getExcelData();

        binding.setPeopleList(viewModel.peopleArrayList);

        binding.completeButton.setOnClickListener(new onClickListener());
        binding.saveButton.setOnClickListener(new onClickListener());

        return binding.getRoot();
    }

    private class onClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.complete_button :{
                    viewModel.saveData();
                    Toast.makeText(getActivity().getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_LONG).show();
                    break;
                }
                case R.id.save_button:{
                    break;
                }
            }
        }
    }

}