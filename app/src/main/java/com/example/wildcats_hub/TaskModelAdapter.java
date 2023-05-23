package com.example.wildcats_hub;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class TaskModelAdapter extends RecyclerView.Adapter<TaskModelAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskModel> taskModels;

    public TaskModelAdapter(Context context, ArrayList<TaskModel> taskModels) {
        this.context = context;
        this.taskModels = taskModels;
    }

    @NonNull
    @Override
    public TaskModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_design, parent, false);
        return new TaskModelAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskModelAdapter.MyViewHolder holder, int position) {
//        assigning values to the views we created
//        Level 1 Priority Level = Mild, Green
//        Level 2 Priority Level = Moderate, Yellow
//        Level 3 Priority Level = Severe, Red
//        Level 1 Priority Level (Mild): Green
//        Light shade: #8BC34A
//        Main shade: #4CAF50
//        Dark shade: #388E3C

//        Level 2 Priority Level (Moderate): Yellow
//        Light shade: #FFF176
//        Main shade: #FFEB3B
//        Dark shade: #FBC02D

//        Level 3 Priority Level (Severe): Red
//        Light shade: #EF9A9A
//        Main shade: #F44336
//        Dark shade: #D32F2F

        holder.taskName.setText(taskModels.get(position).getTaskName());
        if (taskModels.get(position).getPriorityLevel().equals("1")) {
            holder.img.setColorFilter(Color.parseColor("#388E3C"));
        } else if (taskModels.get(position).getPriorityLevel().equals("2")) {
            holder.img.setColorFilter(Color.parseColor("#FBC02D"));
        } else {
            holder.img.setColorFilter(Color.parseColor("#D32F2F"));
        }
    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView taskName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            taskName = itemView.findViewById(R.id.textView2);
        }
    }
}
