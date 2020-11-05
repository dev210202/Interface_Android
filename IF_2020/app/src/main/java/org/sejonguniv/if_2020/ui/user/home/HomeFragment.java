package org.sejonguniv.if_2020.ui.user.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater,R.layout.fragment_home, container);
        setViewModel(HomeViewModel.class);

        View view = binding.getRoot();

        return view;


    }
}