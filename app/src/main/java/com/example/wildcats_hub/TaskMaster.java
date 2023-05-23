package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.wildcats_hub.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TaskMaster extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private FloatingActionButton addNewTaskButton;
    private NewTaskSheet bottomModalFragment;
    ArrayList<TaskModel> taskModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_master);

        addNewTaskButton = (FloatingActionButton) findViewById(R.id.addNewTask);
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        addNewTaskButton.setOnClickListener(view -> {
            bottomModalFragment = new NewTaskSheet();
            bottomModalFragment.show(getSupportFragmentManager(), "NewTaskSheet");
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        setupTaskModel();
        TaskModelAdapter adapter = new TaskModelAdapter(this, taskModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupTaskModel() {
        String[] taskNames = new String[] {"Gym", "Chores", "Eat", "School", "Exam"};
        String[] priorityLevel = new String[] {"1", "2", "3", "1", "2"};

        for (int i = 0; i < taskNames.length; i++) {
            taskModel.add(new TaskModel(taskNames[i], priorityLevel[i]));
        }
    }
}