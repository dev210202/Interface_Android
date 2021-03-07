package org.sejonguniv.if_2020.binding;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kelin.scrollablepanel.library.ScrollablePanel;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.sejonguniv.if_2020.model.CellData;
import org.sejonguniv.if_2020.model.LeftTitle;
import org.sejonguniv.if_2020.model.ManageStatus;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.model.PassKey;
import org.sejonguniv.if_2020.model.People;
import org.sejonguniv.if_2020.model.TopTitle;
import org.sejonguniv.if_2020.model.User;
import org.sejonguniv.if_2020.model.UserPassInfo;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendancePassKeyAdapter;
import org.sejonguniv.if_2020.ui.adapter.AdminAttendanceUserPassInfoAdapter;
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

    @BindingAdapter("item")
    public static void setItem(RecyclerView view, ArrayList<Notice> noticeList) {
        NoticeAdapter adapter = (NoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
        Log.e("DataBindingAdapter", "item set OK");
    }

    @BindingAdapter("adminItem")
    public static void setAdminItem(RecyclerView view, ArrayList<Notice> noticeList) {
        AdminNoticeAdapter adapter = (AdminNoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
        Log.e("DataBindingAdapter", "adminItem set OK");
    }

    @BindingAdapter("noticeSet")
    public static void noticeSet(ExpandableTextView view, Notice notice) {
        view.setText(notice.getContent());
    }


    @BindingAdapter("listItem")
    public static void listItem(ScrollablePanel view, ArrayList<People> peopleList) {
        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(setUserTopTitleCell());
        adapter.setLeftList(setLeftTitleCell(peopleList));
        adapter.setCellList(setUserCell(peopleList));
        view.setPanelAdapter(adapter);
        view.notifyDataSetChanged();
    }

    @BindingAdapter("listAdminItem")
    public static void listAdminItem(ScrollablePanel view, ArrayList<People> peopleList) {
        if(peopleList != null){

            for(People people : peopleList){
                Log.e("PEOPLE", people.getName());
            }
        }
        ExcelAdapter adapter = new ExcelAdapter();
        adapter.setTopList(setTopTitleCell());
        adapter.setLeftList(setLeftTitleCell(peopleList));
        adapter.setCellList(setCell(peopleList));
        view.setPanelAdapter(adapter);
        view.notifyDataSetChanged();
    }

    @BindingAdapter("passkeyItem")
    public static void passkeyItem(RecyclerView view, ArrayList<PassKey> passkeyList){
        AdminAttendancePassKeyAdapter adapter = (AdminAttendancePassKeyAdapter) view.getAdapter();
        adapter.setPassKeyList(passkeyList);
    }

    @BindingAdapter("userPassInfo")
    public static void userPassInfo(RecyclerView view, ArrayList<UserPassInfo> userPassList){
        AdminAttendanceUserPassInfoAdapter adapter = (AdminAttendanceUserPassInfoAdapter) view.getAdapter();
        adapter.setPassInfoList(userPassList);
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

    public static List<List<CellData>> setUserCell(ArrayList<People> peopleArrayList) {
        List<List<CellData>> cells = new ArrayList<>();

        if (peopleArrayList != null) {
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

    public static List<TopTitle> setTopTitleCell() {
        List<TopTitle> topTitles = new ArrayList<>();
        for (int i = 0; i < getTopPeopleCellBase().itemSize(); i++) {
            TopTitle topTitle = new TopTitle();
            topTitle.setTitle(getTopPeopleCellBase().getValue(i));
            topTitles.add(topTitle);
        }
        return topTitles;


    }

    public static <T> List<LeftTitle> setLeftTitleCell(ArrayList<T> peopleArrayList) {
        List<LeftTitle> leftTitles = new ArrayList<>();

        if (peopleArrayList != null) {
            for (int i = 0; i < peopleArrayList.size(); i++) {
                LeftTitle leftTitle = new LeftTitle();
                leftTitle.setTitle("" + (i + 1));
                leftTitles.add(leftTitle);
            }
        }
        return leftTitles;
    }

    public static <T> List<List<CellData>> setCell(ArrayList<T> peopleArrayList) {
        List<List<CellData>> cells = new ArrayList<>();
        if (peopleArrayList != null) {

                for (int i = 0; i < peopleArrayList.size(); i++) {
                    ArrayList<CellData> cellList = new ArrayList<>();
                    for (int k = 0; k < ((People) peopleArrayList.get(0)).itemSize(); k++) {
                        CellData cellData = new CellData();
                        cellData.setTitle(((People) peopleArrayList.get(i)).getValue(k));
                        Log.e("CELLDATA", cellData.getTitle());
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

    private static ArrayList<User> transformPeopleToUser(ArrayList<People> peopleArrayList) {
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < peopleArrayList.size(); i++) {
            User user = new User(
                    peopleArrayList.get(i).getState(),
                    peopleArrayList.get(i).getGeneration(),
                    peopleArrayList.get(i).getName(),
                    peopleArrayList.get(i).getDepartment()
            );

            userArrayList.add(user);
        }
        return userArrayList;
    }
}