package org.sejonguniv.if_2020.ui.adapter;

import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {


    @BindingAdapter("setText")
    public static void setText(EditText editText, String text){
        editText.setText(text);
    }
}
