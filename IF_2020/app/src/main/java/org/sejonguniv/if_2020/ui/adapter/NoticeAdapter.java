package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerviewNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    ArrayList<Notice> noticeList;
    String input;

    public NoticeAdapter() {
        this.noticeList = new ArrayList<>();
        this.input = "INIT";
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewNoticeBinding binding = RecyclerviewNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        String text = input;
        holder.bind(notice, text);
    }


    @Override
    public int getItemCount() {
        if (noticeList == null) {
            return 0;
        } else {
            return noticeList.size();
        }
    }

    public void setNotice(ArrayList<Notice> noticeList) {
        this.noticeList = noticeList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
