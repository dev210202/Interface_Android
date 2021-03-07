package org.sejonguniv.if_2020.ui.admin.password;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.base.BaseFragment;
import org.sejonguniv.if_2020.databinding.FragmentPassWordChangeBinding;


public class PassWordChangeFragment extends BaseFragment<FragmentPassWordChangeBinding, PassWordChangeViewModel> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setBinding(inflater, R.layout.fragment_pass_word_change, container);
        setViewModel(PassWordChangeViewModel.class);
        setProgressBar();

        binding.completeButton.setOnClickListener(v ->
            viewModel.changePassWord(binding.passwordEdittext.getText().toString(), binding.roleEdittext.getText().toString())
        );

        Observer<String> responseObserver= s -> Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
        viewModel.responseData.observe(this, responseObserver);
        return binding.getRoot();
    }
}