package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecycerviewAdminNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;

import java.util.ArrayList;

public class AdminNoticeAdapter extends RecyclerView.Adapter<AdminNoticeAdapter.ViewHolder> {


    ArrayList<Notice> noticeList;
    private OnItemClickListener mListener = null;

    public AdminNoticeAdapter() {
        this.noticeList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycerviewAdminNoticeBinding binding = RecycerviewAdminNoticeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminNoticeAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        holder.bind(notice);

    }

    public void setNotice(ArrayList<Notice> noticeList) {
        this.noticeList = noticeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (noticeList == null) {
            return 0;
        } else {
            return noticeList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecycerviewAdminNoticeBinding binding;

        public ViewHolder(RecycerviewAdminNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.editButton.setOnClickListener(v -> {
                if (mListener != null) {
                    Notice notice = noticeList.get(getAdapterPosition());
                    mListener.onItemClick(v, getAdapterPosition(), notice.getId());
                }

            });
            binding.deleteButton.setOnClickListener(v -> {
                if (mListener != null) {
                    Notice notice = noticeList.get(getAdapterPosition());
                    mListener.onItemClick(v, getAdapterPosition(), notice.getId());
                }
            });
        }

        void bind(Notice notice) {
            binding.setNotice(notice);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position, int noticePosition);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
