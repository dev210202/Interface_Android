package org.sejonguniv.if_2020.ui.adapter;

import android.view.View;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class DataBindingAdapter {
    @BindingAdapter("setText")
    public static void setText(EditText editText, String text) {
        editText.setText(text);
    }

    @BindingAdapter("NoticeClick")
    public static void noticeClick(View view, String notice){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
