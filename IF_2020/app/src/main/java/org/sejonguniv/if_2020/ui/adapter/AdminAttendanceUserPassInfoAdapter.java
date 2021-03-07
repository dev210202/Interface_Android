package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerviewAdminAttendanceUserpassinfoBinding;
import org.sejonguniv.if_2020.model.UserPassInfo;

import java.util.ArrayList;

public class AdminAttendanceUserPassInfoAdapter extends RecyclerView.Adapter<AdminAttendanceUserPassInfoAdapter.ViewHolder> {

    ArrayList<UserPassInfo> passInfoList;

    public AdminAttendanceUserPassInfoAdapter() {
        this.passInfoList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAdminAttendanceUserpassinfoBinding binding = RecyclerviewAdminAttendanceUserpassinfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserPassInfo userPassInfo = passInfoList.get(position);
        holder.setBinding(userPassInfo);
    }

    @Override
    public int getItemCount() {

        if (passInfoList == null) {
            return 0;
        } else {
            return passInfoList.size();
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewAdminAttendanceUserpassinfoBinding binding;

        public ViewHolder(RecyclerviewAdminAttendanceUserpassinfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(UserPassInfo userPassInfo) {
            binding.setUserPassInfo(userPassInfo);
        }
    }


    public void setPassInfoList(ArrayList<UserPassInfo> passInfoList){
        this.passInfoList = passInfoList;
        notifyDataSetChanged();

    }
}
