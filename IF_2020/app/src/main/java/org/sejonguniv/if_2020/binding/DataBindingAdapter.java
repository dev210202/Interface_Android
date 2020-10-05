package org.sejonguniv.if_2020.binding;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;

public class DataBindingAdapter {

    @BindingAdapter("item")
    public static void item(RecyclerView view , ObservableArrayList<String> noticeList){
        NoticeAdapter adapter = (NoticeAdapter) view.getAdapter();

        if(adapter != null){
            adapter.setNotice(noticeList);
        }
    }
}
