package org.sejonguniv.if_2020.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sejonguniv.if_2020.databinding.RecyclerListItemBinding;
import org.sejonguniv.if_2020.model.People;

import java.util.ArrayList;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

    ArrayList<People> peopleArrayList;

    public RecyclerListAdapter(ArrayList<People> peopleArrayList) {
        this.peopleArrayList = peopleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerListItemBinding binding = RecyclerListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        People people = peopleArrayList.get(position);
        holder.bindPeople(people);
    }

    @Override
    public int getItemCount() {
        return peopleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerListItemBinding binding;

        public ViewHolder(RecyclerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindPeople(People people){
            binding.setPeople(people);
        }
    }

}
