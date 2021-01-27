package org.sejonguniv.if_2020.ui.admin.excel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminExcelBinding;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;

import java.util.ArrayList;


public class AdminExcelFragment extends BaseFragment<FragmentAdminExcelBinding, AdminExcelFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);

        binding.setPeopleList(viewModel.peopleArrayList);
        setProgressBar();
        dialog.show();
        viewModel.getExcelData();


        Observer<ArrayList<People>> arrayListObserver = new Observer<ArrayList<People>>() {
            @Override
            public void onChanged(ArrayList<People> people) {
                binding.setPeopleList(viewModel.peopleArrayList);
            }
        };

        Observer<Boolean> dialogObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                dialog.dismiss();
            }
        };



        viewModel.peopleArrayList.observe(this, arrayListObserver);
        viewModel.isDataReceive.observe(this, dialogObserver);
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

            }
        }
    }

}