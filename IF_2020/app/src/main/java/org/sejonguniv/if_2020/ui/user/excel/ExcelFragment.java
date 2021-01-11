package org.sejonguniv.if_2020.ui.user.excel;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentExcelBinding;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.User;

import java.util.ArrayList;
import java.util.Observable;

public class ExcelFragment extends BaseFragment<FragmentExcelBinding, ExcelViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_excel, container);
        setViewModel(ExcelViewModel.class);

        binding.setPeopleList(viewModel.peopleArrayList);

        showProgressBar();
        viewModel.getExcelData();

        Observer<ArrayList<People>> peopleArrayListObserver = new Observer<ArrayList<People>>() {
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

        viewModel.peopleArrayList.observe(this, peopleArrayListObserver);
        viewModel.isDataReceive.observe(this, dialogObserver);
        return binding.getRoot();
    }
/*
    private void externalPermissionCheck() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            return;
        }
    }*/
}