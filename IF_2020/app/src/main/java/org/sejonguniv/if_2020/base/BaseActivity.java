package org.sejonguniv.if_2020.base;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    protected T binding;

    protected void setBinding(@LayoutRes int layoutID) {
        binding = DataBindingUtil.setContentView(this, layoutID);
    }
}
