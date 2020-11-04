package org.sejonguniv.if_2020.ui.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.sejonguniv.if_2020.databinding.RecyclerviewNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    List<Notice> noticeList;
    String input;

    public NoticeAdapter() {
        this.noticeList = new ArrayList<Notice>();
        this.input = new String("INIT");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewNoticeBinding binding = RecyclerviewNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoticeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        String text = input;
        holder.bind(notice, text);
    }


    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void setNotice(List<Notice> noticeList) {
        this.noticeList = noticeList;
        notifyDataSetChanged();
    }

    public void setInput(String input) {
        this.input = input;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewNoticeBinding binding;

        public ViewHolder(RecyclerviewNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Notice notice, String input) {
            binding.setNotice(notice);
            binding.setInput(input);
        }

    }
}
