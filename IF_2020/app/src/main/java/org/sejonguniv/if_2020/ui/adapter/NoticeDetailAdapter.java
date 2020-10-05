package org.sejonguniv.if_2020.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeDetailAdapter extends RecyclerView.Adapter<NoticeDetailAdapter.NoticeDeatilViewHolder>{

    @NonNull
    @Override
    public NoticeDeatilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeDeatilViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NoticeDeatilViewHolder extends RecyclerView.ViewHolder {

        public NoticeDeatilViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
