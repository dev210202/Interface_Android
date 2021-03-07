package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerviewAdminAttendancePasskeyBinding;
import org.sejonguniv.if_2020.model.PassKey;

import java.util.ArrayList;

public class AdminAttendancePassKeyAdapter extends RecyclerView.Adapter<AdminAttendancePassKeyAdapter.ViewHolder>{

    ArrayList<PassKey> passKeyList;

    private AdminAttendancePassKeyAdapter.OnItemClickListener mListener = null;

    public AdminAttendancePassKeyAdapter(){
        this.passKeyList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerviewAdminAttendancePasskeyBinding adminAttendancePasskeyBinding = RecyclerviewAdminAttendancePasskeyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new AdminAttendancePassKeyAdapter.ViewHolder(adminAttendancePasskeyBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PassKey passKey = passKeyList.get(position);
        String startTime;
        String endTime;
        if(!(passKey.getPasskey().equals("저장된 암호가 없습니다.") || passKey.getPasskey().equals("암호를 불러올 수 없습니다."))){
            startTime = setStartTime(passKey);
            endTime = setEndime(passKey);
            passKey.setStartTime(startTime);
            passKey.setEndTime(endTime);
        }else{
            passKey.setStartTime("");
            passKey.setEndTime("");
        }
        holder.bind(passKey);
    }

    @Override
    public int getItemCount(){
        if(passKeyList == null){
            return 0;
        }
        else{
            return passKeyList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerviewAdminAttendancePasskeyBinding binding;

        public ViewHolder(RecyclerviewAdminAttendancePasskeyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(v -> mListener.onItemClick(itemView, getAdapterPosition()));
        }

        void bind(PassKey passKey){
            binding.setPasskey(passKey);
        }
    }

    public void setPassKeyList(ArrayList<PassKey> passKeyList){
        this.passKeyList = passKeyList;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(AdminAttendancePassKeyAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }


    private String setStartTime(PassKey passKey){
        String startTime = passKey.getStartTime();

        String[] split = startTime.split("T");
        String splitDateResult = split[0];

        String[] dateSplit = splitDateResult.split("-");

        String year = dateSplit[0];
        String month = dateSplit[1];
        String day = dateSplit[2];

        return year + "년" + month + "월" + day + "일";
    }
    private String setEndime(PassKey passKey){

        String startTime = passKey.getStartTime();
        String endTime = passKey.getEndTime();

        String[] startSplit = startTime.split("T");
        String[] endSplit = endTime.split("T");


        String startTimeResult = startSplit[1];
        String endTimeResult = endSplit[1];

        return startTimeResult + "-" + endTimeResult;
    }
}