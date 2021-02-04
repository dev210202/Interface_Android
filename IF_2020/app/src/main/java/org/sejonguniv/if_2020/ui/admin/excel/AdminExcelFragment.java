package org.sejonguniv.if_2020.ui.admin.excel;

import android.content.Intent;
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

    int ADD = 10;
    int ADD_DONE = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_admin_excel, container);
        setViewModel(AdminExcelFragmentViewModel.class);

        binding.setPeopleList(viewModel.peopleArrayList);
        setProgressBar();
        dialog.show();
        viewModel.getExcelDataToServer();

        binding.addMemberButton.setOnClickListener(new onClickListener());
        binding.saveLocalButton.setOnClickListener(new onClickListener());
        binding.saveServerButton.setOnClickListener(new onClickListener());

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
                case R.id.add_member_button:{
                    Intent intent = new Intent(getContext(), AdminExcelAddActivity.class);
                    startActivityForResult(intent, ADD);
                    break;
                }

                case R.id.get_local_excel_button: {
                    viewModel.getExcelDataToLocal(getActivity().getApplicationContext());
                    break;
                }

                case R.id.save_local_button :{
                    viewModel.saveDataToLocal();
                    Toast.makeText(getActivity().getApplicationContext(), "다운로드 폴더에 저장되었습니다.", Toast.LENGTH_LONG).show();
                    break;
                }

                case R.id.save_server_button  :{
                    viewModel.saveDataToServer();
                    break;
                }


            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == ADD_DONE){

            viewModel.peopleArrayList.getValue().add((People) data.getSerializableExtra("peopleInfo"));
            viewModel.saveDataToServer();
            // viewModel에서 멤버추가 메소드 실행

        }

    }
}