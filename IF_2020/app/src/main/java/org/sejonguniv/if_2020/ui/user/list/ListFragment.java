package org.sejonguniv.if_2020.ui.user.list;

import android.os.Bundle;

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
        // Inflate the layout for this fragment

        setBinding(inflater, R.layout.fragment_list, container);
        setViewModel(ListViewModel.class);

        binding.setPeopleList(viewModel.peopleArrayList);
        viewModel.setExcelData();




        View view = binding.getRoot();


        return view;
    }
}