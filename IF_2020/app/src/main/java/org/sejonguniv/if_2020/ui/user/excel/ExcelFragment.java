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
        setProgressBar();

        dialog.show();
        viewModel.getExcelData();

        binding.setPeopleList(viewModel.peopleArrayList.getValue());

        Observer<ArrayList<People>> peopleArrayListObserver = people -> {
            binding.setPeopleList(people);
            dialog.dismiss();
        };

        viewModel.peopleArrayList.observe(getViewLifecycleOwner(), peopleArrayListObserver);
        return binding.getRoot();
    }
}