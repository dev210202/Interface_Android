package org.sejonguniv.if_2020.binding;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.kelin.scrollablepanel.library.PanelAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

public class DataBindingAdapter {
    @BindingAdapter("setText")
    public static void setText(EditText editText, String text) {
        editText.setText(text);
    }


    @BindingAdapter("item")
    public static void setItem(RecyclerView view, ObservableArrayList<Notice> noticeList) {
        NoticeAdapter adapter = (NoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
        Log.e("DataBindingAdapter", "item set OK");
    }
    @BindingAdapter("adminItem")
    public static void setAdminItem(RecyclerView view, ObservableArrayList<Notice> noticeList) {
        AdminNoticeAdapter adapter = (AdminNoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
        Log.e("DataBindingAdapter", "adminItem set OK");
    }
    @BindingAdapter("noticeSet")
    public static void noticeSet(ExpandableTextView view, Notice notice) {
        view.setText(notice.getContent());
    }

    @BindingAdapter("titleTextInput")
    public static void titleTextInput(TextView view, String input) {


        Log.e("DataBindingAdapter", "titleTextInput input value = " + input);

        int NOT_EXIST_INDEX = -1;

        String text = view.getText().toString();

        SpannableStringBuilder builder = new SpannableStringBuilder(text);

        if (input != "INIT") {

            int startIndex = text.indexOf(input);
            Log.e("startIndex", ""+startIndex);
            int endIndex = startIndex + input.length();


            if (startIndex != NOT_EXIST_INDEX) {
                builder.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new RelativeSizeSpan(1.5f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(builder);
                while (text.indexOf(input, startIndex + 1) != NOT_EXIST_INDEX) {

                    startIndex = text.indexOf(input, startIndex + 1);
                    endIndex = startIndex + input.length();

                    builder.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(new RelativeSizeSpan(1.5f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    view.setText(builder);
                    Log.e("a1 value", "" + startIndex);
                }
            }
            else {
                Log.e("DataBindgAdapter","titleTextInput NOT_EXIST_INDEX");
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(builder);
            }
        }

    }



    @BindingAdapter("contentTextInput")
    public static void contentTextInput(ExpandableTextView view, String input) {


        Log.e("DataBindingAdapter", "contentTextInput input value = " + input);

        int NOT_EXIST_INDEX = -1;

        String text = view.getText().toString();

        SpannableStringBuilder builder = new SpannableStringBuilder(text);

        if (input != "INIT") {

            int startIndex = text.indexOf(input);
            Log.e("startIndex", ""+startIndex);
            int endIndex = startIndex + input.length();


            if (startIndex != NOT_EXIST_INDEX) {
                builder.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.setSpan(new RelativeSizeSpan(1.5f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(builder);
                while (text.indexOf(input, startIndex + 1) != NOT_EXIST_INDEX) {

                    startIndex = text.indexOf(input, startIndex + 1);
                    endIndex = startIndex + input.length();

                    builder.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(new RelativeSizeSpan(1.5f), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    view.setText(builder);
                    Log.e("a2 value", "" + startIndex);
                }
            }else {
                Log.e("DataBindingAdapter","contentTextInput NOT_EXIST_INDEX");
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(builder);
            }
        }

    }


}