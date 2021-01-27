package org.sejonguniv.if_2020.binding;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.model.User;
import org.sejonguniv.if_2020.ui.adapter.AdminNoticeAdapter;
import org.sejonguniv.if_2020.ui.adapter.ExcelAdapter;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataBindingAdapter {
    @BindingAdapter("setTextToTextView")
    public static void setTextToTextView(TextView textView, String text) {
        textView.setText(text);
    }


    @BindingAdapter("setTextToEditText")
    public static void setTextToEditText(EditText editText, String text) {
        editText.setText(text);
    }

    @BindingAdapter("item")
    public static void setItem(RecyclerView view, MutableLiveData<ArrayList<Notice>> noticeList) {
        NoticeAdapter adapter = (NoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
        Log.e("DataBindingAdapter", "item set OK");
    }

    @BindingAdapter("adminItem")
    public static void setAdminItem(RecyclerView view, MutableLiveData<ArrayList<Notice>> noticeList) {
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
    public static void listItem(ScrollablePanel view, MutableLiveData<ArrayList<People>> peopleList) {

        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(setUserTopTitleCell());
        adapter.setLeftList(setLeftTitleCell(peopleList));
        adapter.setCellList(setUserCell(peopleList));
        view.setPanelAdapter(adapter);
        view.notifyDataSetChanged();
    }

    @BindingAdapter("listAdminItem")
    public static void listAdminItem(ScrollablePanel view, MutableLiveData<ArrayList<People>> peopleList) {
        Log.e("!!!", "!!!");
        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(setTopTitleCell(peopleList));
        adapter.setLeftList(setLeftTitleCell(peopleList));
        adapter.setCellList(setCell(peopleList));
        view.setPanelAdapter(adapter);
        view.notifyDataSetChanged();
    }

    public static List<TopTitle> setUserTopTitleCell() {
        List<TopTitle> topTitles = new ArrayList<>();
        for (int i = 0; i < getTopCellBase().itemSize(); i++) {
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(getTopCellBase().getValue(i));
            topTitles.add(topTitle);
        }
        return topTitles;
    }

    public static List<List<CellData>> setUserCell(MutableLiveData<ArrayList<People>> peopleArrayList) {
        List<List<CellData>> cells = new ArrayList<>();

        if (peopleArrayList.getValue() != null) {
            ArrayList<User> userArrayList = transformPeopleToUser(peopleArrayList);
            for (int i = 0; i < userArrayList.size(); i++) {
                ArrayList<CellData> cellList = new ArrayList<>();
                for (int k = 0; k < getTopCellBase().itemSize(); k++) {
                    CellData cellData = new CellData();
                    cellData.setTitle(userArrayList.get(i).getValue(k));
                    cellList.add(cellData);
                }
                cells.add(cellList);
            }
        }

        return cells;
    }

    public static List<TopTitle> setTopTitleCell(MutableLiveData<ArrayList<People>> peopleArrayList) {
        List<TopTitle> topTitles = new ArrayList<>();
        for (int i = 0; i < getTopPeopleCellBase().itemSize(); i++) {
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(getTopPeopleCellBase().getValue(i));
            topTitles.add(topTitle);
        }
        return topTitles;


    }

    public static <T> List<LeftTitle> setLeftTitleCell(MutableLiveData<ArrayList<T>> peopleArrayList) {
        List<LeftTitle> leftTitles = new ArrayList<LeftTitle>();

        if (peopleArrayList.getValue() != null) {

            for (int i = 0; i < peopleArrayList.getValue().size(); i++) {

                LeftTitle leftTitle = new LeftTitle();
                leftTitle.setTitle("" + (i + 1));
                leftTitles.add(leftTitle);
            }
        }
        return leftTitles;
    }

    public static <T> List<List<CellData>> setCell(MutableLiveData<ArrayList<T>> peopleArrayList) {
        List<List<CellData>> cells = new ArrayList<>();
        if (peopleArrayList.getValue() != null) {

                for (int i = 0; i < peopleArrayList.getValue().size(); i++) {
                    ArrayList<CellData> cellList = new ArrayList<>();
                    for (int k = 0; k < ((People) peopleArrayList.getValue().get(0)).itemSize(); k++) {
                        CellData cellData = new CellData();
                        cellData.setTitle(((People) peopleArrayList.getValue().get(i)).getValue(k));

                        cellList.add(cellData);
                    }
                    cells.add(cellList);
                }


        }
        return cells;
    }

    private static User getTopCellBase() {
        return new User("재학여부", "기수", "이름", "학과");
    }

    private static People getTopPeopleCellBase(){
        return new People("재학여부","기수","이름","학과","학번","전화번호","연락처",new ManageStatus("1학기회비","2학기회비","개총","종총"));
    }

    private static ArrayList<User> transformPeopleToUser(MutableLiveData<ArrayList<People>> peopleArrayList) {
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < peopleArrayList.getValue().size(); i++) {
            User user = new User(
                    peopleArrayList.getValue().get(i).getState(),
                    peopleArrayList.getValue().get(i).getGeneration(),
                    peopleArrayList.getValue().get(i).getName(),
                    peopleArrayList.getValue().get(i).getDepartment()
            );

            userArrayList.add(user);
        }
        return userArrayList;
    }
}