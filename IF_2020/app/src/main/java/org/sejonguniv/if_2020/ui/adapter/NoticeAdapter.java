package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerviewNoticeBinding;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    List<String> noticeList;

    public NoticeAdapter() {
        this.noticeList = new ArrayList<String>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RecyclerviewNoticeBinding binding = RecyclerviewNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
       return new NoticeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = noticeList.get(position);
        holder.bind(title);
    }



    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public void setNotice(List<String> noticeList){
        this.noticeList = noticeList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewNoticeBinding binding;

        public ViewHolder(RecyclerviewNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(String title){
            binding.setTitle(title);
        }
    }

}
