package org.sejonguniv.if_2020.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecycerviewAdminNoticeBinding;
import org.sejonguniv.if_2020.databinding.RecyclerviewNoticeBinding;
import org.sejonguniv.if_2020.model.Notice;

import java.util.ArrayList;
import java.util.List;

public class AdminNoticeAdapter extends RecyclerView.Adapter<AdminNoticeAdapter.ViewHolder> {


    List<Notice> noticeList;
    private OnItemClickListener mListener = null;

    public AdminNoticeAdapter() {
        this.noticeList = new ArrayList<Notice>();
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

    public void setNotice(List<Notice> noticeList) {
        this.noticeList = noticeList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecycerviewAdminNoticeBinding binding;

        public ViewHolder(RecycerviewAdminNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {

                        Notice notice = noticeList.get(getAdapterPosition());
                        mListener.onItemClick(v, notice.getId());
                        Log.e("edit", "onclick");
                    }

                }
            });
            binding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mListener != null) {
                        Notice notice = noticeList.get(getAdapterPosition());
                        mListener.onItemClick(v, notice.getId());
                    }
                }
            });
        }

        void bind(Notice notice) {
            binding.setNotice(notice);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
