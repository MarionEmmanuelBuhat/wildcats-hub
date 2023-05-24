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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.wildcats_hub.databinding.ActivityMainBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskMaster extends AppCompatActivity {

    private FloatingActionButton addNewTaskButton;
    private NewTaskSheet bottomModalFragment;
    ArrayList<TaskModel> taskModel = new ArrayList<>();
    TaskDatabaseHelper myDB;
    TaskModelAdapter adapter = new TaskModelAdapter(TaskMaster.this, this, taskModel);
    ArrayList<String> tagsArray = new ArrayList<>();
    BottomAppBar bottomAppBar;
    String skipTag;
    PopupMenu popupMenu;
    PopupMenu popupMenu2;
    Menu menu;
    Menu menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_master);

        addNewTaskButton = (FloatingActionButton) findViewById(R.id.addNewTask);
        tagsArray.add("All");

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

        popupMenu = new PopupMenu(getApplicationContext(), this.getWindow().getDecorView().findViewById(android.R.id.content));
        menu = popupMenu.getMenu();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tag = tagsArray.get(i);
            System.out.println(tag + " TAG");
            menu.add(tag);
        }

        popupMenu2 = new PopupMenu(getApplicationContext(), this.getWindow().getDecorView().findViewById(android.R.id.content));
        menu2 = popupMenu2.getMenu();
        menu2.add("Default");
        menu2.add("Priority Level Ascending");
        menu2.add("Priority Level Descending");
        menu2.add("Time Ascending");
        menu2.add("Time Descending");
        menu2.add("Date Ascending");
        menu2.add("Date Descending");


        addEventListener(menu);
        addEventListener2(menu2);


        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.taskmenu:
                        popupMenu.show();
                        return true;
                    case R.id.tasksort:
                        popupMenu2.show();
                        return true;
                }
                return false;
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

    void addEventListener(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // Get the clicked menu item's title or ID
                    String title = item.getTitle().toString();
                    int itemId = item.getItemId();
                    skipTag = title;
                    if (title.equals("All")) {
                        skipTag = null;
                    }
                    reloadModels();
                    // Handle menu item click here
                    // Use the title or ID to determine the action to perform
//                    handleMenuItemClick(title, itemId);

                    return true;
                }
            });
        }
    }

    void addEventListener2(Menu menu2) {
        for (int i = 0; i < menu2.size(); i++) {
            MenuItem menuItem = menu2.getItem(i);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // Get the clicked menu item's title or ID
                    String title = item.getTitle().toString();
                    int itemId = item.getItemId();
                    sortTask(title);
                    adapter.notifyDataSetChanged();
//                    reloadModels();
                    // Handle menu item click here
                    // Use the title or ID to determine the action to perform
//                    handleMenuItemClick(title, itemId);

                    return true;
                }
            });
        }
    }

    void sortTask(String action) {
        switch(action) {
            case "Default":
                reloadModels();
                break;
            case "Priority Level Ascending":
                // Sort by Priority Level Ascending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getPriorityLevel));
                break;
            case "Priority Level Descending":
                // Sort by Priority Level Descending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getPriorityLevel).reversed());
                break;
            case "Time Ascending":
                // Sort by Time Ascending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getDueTime));
                break;
            case "Time Descending":
                // Sort by Time Descending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getDueTime).reversed());
                break;
            case "Date Ascending":
                // Sort by Date Ascending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getDueDate));
                break;
            case "Date Descending":
                // Sort by Date Descending
                Collections.sort(taskModel, Comparator.comparing(TaskModel::getDueDate).reversed());
                break;
        }
    }

    private void setupTaskModel() {
        Cursor cursor = myDB.readAllData();
        if (cursor == null || cursor.getCount() == 0) {
            Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String taskId = cursor.getString(0);
                String taskName = cursor.getString(1);
                String description = cursor.getString(2);
                String priority = cursor.getString(3);
                String tag = cursor.getString(4);
                if (tag != null && !tag.isEmpty() && !tagsArray.contains(tag)) {
                    tagsArray.add(tag);
                }
                String dueDate = cursor.getString(5);
                String dueTime = cursor.getString(6);
                taskModel.add(new TaskModel(taskId, taskName, description,
                        priority, tag, dueDate, dueTime));
            }
        }
    }

    public void reloadModels() {
        taskModel.clear();
        popupMenu = new PopupMenu(getApplicationContext(), this.getWindow().getDecorView().findViewById(android.R.id.content), Gravity.CENTER);
        menu = popupMenu.getMenu();
        for (int i = 0; i < tagsArray.size(); i++) {
            String tag = tagsArray.get(i);
            menu.add(tag);
        }

        addEventListener(menu);

        Cursor cursor = myDB.readAllData();
        if (cursor == null || cursor.getCount() == 0) {
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
                if (skipTag != null && !skipTag.equals(tag)) {
                    continue;
                }
                taskModel.add(new TaskModel(taskId, taskName, description,
                        priority, tag, dueDate, dueTime));
            }
        }
        adapter.notifyDataSetChanged();
    }
}