package com.example.wildcats_hub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.MyViewHolder> {
    private ArrayList<String> items;

    public recycleViewAdapter(ArrayList<String> items) {
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTxt;

        public MyViewHolder(final View view) {
            super(view);
            itemTxt = view.findViewById(R.id.item1);
        }
    }

    @NonNull
    @Override
    public recycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recycleViewAdapter.MyViewHolder holder, int position) {
        String item = items.get(position);
        holder.itemTxt.setText(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
