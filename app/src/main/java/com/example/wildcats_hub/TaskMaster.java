package com.example.wildcats_hub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.wildcats_hub.databinding.ActivityMainBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TaskMaster extends AppCompatActivity {

    private FloatingActionButton addNewTaskButton;
    private NewTaskSheet bottomModalFragment;
    ArrayList<TaskModel> taskModel = new ArrayList<>();
    TaskDatabaseHelper myDB;
    TaskModelAdapter adapter = new TaskModelAdapter(TaskMaster.this, this, taskModel);
    BottomAppBar bottomAppBar;
    String includeTag;
    boolean skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_master);

        addNewTaskButton = (FloatingActionButton) findViewById(R.id.addNewTask);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        myDB = new TaskDatabaseHelper(TaskMaster.this);
        setupTaskModel();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottomAppBar = findViewById(R.id.bottomAppBar);

        addNewTaskButton.setOnClickListener(view -> {
            bottomModalFragment = new NewTaskSheet();
            bottomModalFragment.show(getSupportFragmentManager(), "NewTaskSheet");
        });

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.taskmenu:
                        System.out.println("1");
                        break;
                    case R.id.tasksort:
                        System.out.println("2");
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    private void setupTaskModel() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String taskId = cursor.getString(0);
                String taskName = cursor.getString(1);
                String description = cursor.getString(2);
                String priority = cursor.getString(3);
                String tag = cursor.getString(4);
                String dueDate = cursor.getString(5);
                String dueTime = cursor.getString(6);
                taskModel.add(new TaskModel(taskId, taskName, description,
                        priority, tag, dueDate, dueTime));
            }
        }
    }

    public void reloadModels() {
        taskModel.clear();
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String taskId = cursor.getString(0);
                String taskName = cursor.getString(1);
                String description = cursor.getString(2);
                String priority = cursor.getString(3);
                String tag = cursor.getString(4);
                String dueDate = cursor.getString(5);
                String dueTime = cursor.getString(6);
                taskModel.add(new TaskModel(taskId, taskName, description,
                        priority, tag, dueDate, dueTime));
            }
        }
        adapter.notifyDataSetChanged();
    }
}