package org.sejonguniv.if_2020.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentMainBinding;
import org.sejonguniv.if_2020.ui.adapter.RecyclerListAdapter;
import org.sejonguniv.if_2020.ui.list.ListFragmentViewModel;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainFragmentViewModel> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_main,container);
        setViewModel(MainFragmentViewModel.class);

        View view = binding.getRoot();

        return view;
    }
}