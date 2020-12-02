package org.sejonguniv.if_2020.ui.user.list;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentListBinding;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;

public class ListFragment extends BaseFragment<FragmentListBinding, ListViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      
        setBinding(inflater, R.layout.fragment_list, container);
        setViewModel(ListViewModel.class);
        externalPermissionCheck();
        binding.setPeopleList(viewModel.peopleArrayList);
        viewModel.setExcelData();

        return binding.getRoot();
    }

    private void externalPermissionCheck() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            return;
        }
    }
}