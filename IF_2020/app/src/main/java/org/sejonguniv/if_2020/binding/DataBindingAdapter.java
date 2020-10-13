package org.sejonguniv.if_2020.binding;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.R;
import org.sejonguniv.if_2020.model.Notice;
import org.sejonguniv.if_2020.ui.adapter.NoticeAdapter;
import org.sejonguniv.if_2020.ui.noticedetail.NoticeDetailFragment;

public class DataBindingAdapter {
    @BindingAdapter("setText")
    public static void setText(EditText editText, String text) {
        editText.setText(text);
    }


    @BindingAdapter("item")
    public static void setItem(RecyclerView view, ObservableArrayList<Notice> noticeList){
        NoticeAdapter adapter = (NoticeAdapter) view.getAdapter();
        adapter.setNotice(noticeList);
    }

    @BindingAdapter("NoticeClick")
    public static void noticeClick(View view, Notice notice){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                Bundle bundle = new Bundle();
                bundle.putSerializable("noticeId",notice.getId());

                NoticeDetailFragment fragment = new NoticeDetailFragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.frame_layout, fragment);
                transaction.commit();
            }
        });
    }
}