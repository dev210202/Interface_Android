package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerviewAdminAttendancePasskeyBinding;
import org.sejonguniv.if_2020.databinding.RecyclerviewAdminAttendanceUserpassinfoBinding;
import org.sejonguniv.if_2020.model.UserPassInfo;

import java.util.ArrayList;

public class AdminAttendanceUserPassInfoAdapter extends RecyclerView.Adapter<AdminAttendanceUserPassInfoAdapter.ViewHolder> {

    MutableLiveData<ArrayList<UserPassInfo>> passInfoList;

    public AdminAttendanceUserPassInfoAdapter() {
        this.passInfoList = new MutableLiveData<ArrayList<UserPassInfo>>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewAdminAttendanceUserpassinfoBinding binding = RecyclerviewAdminAttendanceUserpassinfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AdminAttendanceUserPassInfoAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserPassInfo userPassInfo = passInfoList.getValue().get(position);
        holder.setBinding(userPassInfo);
    }

    @Override
    public int getItemCount() {

        if (passInfoList.getValue() == null) {
            return 0;
        } else {
            return passInfoList.getValue().size();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewAdminAttendanceUserpassinfoBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public ViewHolder(RecyclerviewAdminAttendanceUserpassinfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void setBinding(UserPassInfo userPassInfo) {
            binding.setUserPassInfo(userPassInfo);
        }
    }


    public void setPassInfoList(MutableLiveData<ArrayList<UserPassInfo>> passInfoList){
        this.passInfoList = passInfoList;
        notifyDataSetChanged();

    }
}
