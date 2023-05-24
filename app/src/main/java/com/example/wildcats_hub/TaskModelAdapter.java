package com.example.wildcats_hub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class TaskModelAdapter extends RecyclerView.Adapter<TaskModelAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskModel> taskModels;
    String tags;
    Activity activity;
    int pst;

    public TaskModelAdapter(Activity activity, Context context, ArrayList<TaskModel> taskModels) {
        this.activity = activity;
        this.context = context;
        this.taskModels = taskModels;
    }

    public String getTags() {
        return tags;
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
        String tag = taskModels.get(position).getTags();
        tags = tag;

        holder.taskName.setText(taskModels.get(position).getTaskName());
        if (taskModels.get(position).getPriorityLevel().equals("1")) {
            holder.img.setColorFilter(Color.parseColor("#388E3C"));
        } else if (taskModels.get(position).getPriorityLevel().equals("2")) {
            holder.img.setColorFilter(Color.parseColor("#FBC02D"));
        } else {
            holder.img.setColorFilter(Color.parseColor("#D32F2F"));
        }
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(currentDate);
        if (taskModels.get(position).getTags().isEmpty()) {
            holder.tags.setText("");
        } else {
            holder.tags.setText(taskModels.get(position).getTags());
        }
        if (formattedDate.equals(taskModels.get(position).getDueDate())) {
            String time = taskModels.get(position).getDueTime();
            String newTime = "";
            for (int i = 0; i < 5; i++) {
                if (i == 2) {
                    newTime += ":";
                    continue;
                }
                newTime += String.valueOf(time.charAt(i));
            }
            DateFormat inputFormat = new SimpleDateFormat("HH:mm");
            DateFormat outputFormat = new SimpleDateFormat("hh:mm a");
            try {
                Date date = inputFormat.parse(newTime);
                newTime = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.dueTime.setText(newTime);
        } else {
            holder.dueTime.setText("");
            if (!taskModels.get(position).getTags().isEmpty()) {
                holder.dueTime.setText(taskModels.get(position).getTags());
            }
            holder.tags.setText("");
        }
        holder.updateLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(taskModels.get(holder.getAdapterPosition()).getTaskId()));
            intent.putExtra("taskname", String.valueOf(taskModels.get(holder.getAdapterPosition()).getTaskName()));
            intent.putExtra("description", String.valueOf(taskModels.get(holder.getAdapterPosition()).getDescription()));
            intent.putExtra("priority", String.valueOf(taskModels.get(holder.getAdapterPosition()).getPriorityLevel()));
            intent.putExtra("tag", String.valueOf(taskModels.get(holder.getAdapterPosition()).getTags()));
            intent.putExtra("duedate", String.valueOf(taskModels.get(holder.getAdapterPosition()).getDueDate()));
            intent.putExtra("duetime", String.valueOf(taskModels.get(holder.getAdapterPosition()).getDueTime()));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return taskModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView taskName;
        TextView tags;
        TextView dueTime;
        ConstraintLayout updateLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            taskName = itemView.findViewById(R.id.textView2);
            tags = itemView.findViewById(R.id.tag);
            dueTime = itemView.findViewById(R.id.dueTime);
            updateLayout = itemView.findViewById(R.id.updateLayout);
        }
    }
}
