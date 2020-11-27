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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;


import com.kelin.scrollablepanel.library.PanelAdapter;
import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;
import org.sejonguniv.if_2020.ui.admin.notice.AdminNoticeViewModel;

import java.util.ArrayList;
import java.util.List;

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
            Log.e("startIndex", "" + startIndex);
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
            } else {
                Log.e("DataBindgAdapter", "titleTextInput NOT_EXIST_INDEX");
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
            Log.e("startIndex", "" + startIndex);
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
            } else {
                Log.e("DataBindingAdapter", "contentTextInput NOT_EXIST_INDEX");
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF")), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                view.setText(builder);
            }
        }

    }

    @BindingAdapter("listItem")
    public static void listItem(ScrollablePanel view, ObservableArrayList<People> peopleList) {


        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(setTopTilteCell(peopleList));
        adapter.setLeftList(setLeftTitle(peopleList));
        adapter.setCellList(setCell(peopleList));
        view.setPanelAdapter(adapter);
        view.notifyDataSetChanged();


    }

    public static List<TopTitle> setTopTilteCell(ObservableArrayList<People> peopleList) {

        List<TopTitle> topTitles = new ArrayList<TopTitle>();
        if (!peopleList.isEmpty()) {
            for (int i = 0; i < peopleList.get(0).itemSize(); i++) {
                Log.e("TOP ITEMSIZE", ""+ peopleList.get(0).itemSize());
                TopTitle topTitle = new TopTitle();
                topTitle.setTitle(peopleList.get(0).getData(i));
                topTitles.add(topTitle);
            }
        }
        return topTitles;


    }

    public static List<LeftTitle> setLeftTitle(ObservableArrayList<People> peopleArrayList) {

        List<LeftTitle> leftTitles = new ArrayList<LeftTitle>();
        for (int i = 0; i < peopleArrayList.size(); i++) {

            Log.e("LEFT ITEMSIZE", ""+ peopleArrayList.size());
            LeftTitle leftTitle = new LeftTitle();
            if (i == 0) {
                leftTitle.setTitle("");
            } else {
                leftTitle.setTitle("" + i);
            }
            leftTitles.add(leftTitle);
        }
        return leftTitles;
    }

    public static List<List<CellData>> setCell(ObservableArrayList<People> peopleArrayList) {
        List<List<CellData>> cells = new ArrayList<>();
        for (int i = 0; i < peopleArrayList.size(); i++) {
            ArrayList<CellData> cellList = new ArrayList<>();
            Log.e("CELL ITEMSIZE1", ""+ peopleArrayList.size());
            for (int k = 0; k < peopleArrayList.get(i).itemSize(); k++) {
                CellData cellData = new CellData();
                cellData.setTitle(peopleArrayList.get(i).getData(k));

                Log.e("CELL ITEMSIZE2", ""+peopleArrayList.get(i).itemSize());
                cellList.add(cellData);
            }
            cells.add(cellList);
        }
        return cells;
    }

}