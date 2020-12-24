package org.sejonguniv.if_2020.ui.admin.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentAdminHomeBinding;

public class AdminHomeFragment extends BaseFragment<FragmentAdminHomeBinding, AdminHomeViewModel> {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setBinding(inflater, R.layout.fragment_admin_home, container);
        setViewModel(AdminHomeViewModel.class);

        return binding.getRoot();
    }
}