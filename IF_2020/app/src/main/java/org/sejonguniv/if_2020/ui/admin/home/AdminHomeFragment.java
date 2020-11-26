package org.sejonguniv.if_2020.ui.admin.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yalantis.phoenix.PullToRefreshView;

import org.sejonguniv.if_2020.R;

public class AdminHomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);


        return view;
    }
}