package org.sejonguniv.if_2020.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.sejonguniv.if_2020.R;

public abstract class BaseFragment<B extends ViewDataBinding, V extends ViewModel> extends Fragment {
    protected B binding;
    protected V viewModel;
    protected Dialog dialog;

    protected void setBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup container){
        binding =  DataBindingUtil.inflate(inflater, layoutId, container, false);
    }

    protected void setViewModel(Class c) {
            ViewModelProvider.AndroidViewModelFactory viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
            viewModel = (V) new ViewModelProvider(getViewModelStore(), viewModelFactory).get(c);
    }
    protected void setProgressBar() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressbar_main);
    }
}
