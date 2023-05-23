package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    private TextInputEditText taskNameEditText;
    private TextInputEditText taskDescriptionEditText;
    private AutoCompleteTextView priorityMenuAutoCompleteTextView;
    private TextInputEditText taskTagsEditText;
    private TextInputEditText taskduedate;
    private TextInputEditText taskduetime;
    private Button updateButton;
    private Button deleteButton;
    String id, taskname, description, priority, tag, duedate, duetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        taskNameEditText = findViewById(R.id.taskname2);
        taskDescriptionEditText = findViewById(R.id.taskdescription2);
        priorityMenuAutoCompleteTextView = findViewById(R.id.drop_priority_menu2);
        taskTagsEditText = findViewById(R.id.tasktags2);
        taskduedate = findViewById(R.id.taskduedate2);
        taskduetime = findViewById(R.id.taskduetime2);
        updateButton = findViewById(R.id.updateButton2);
        getIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDatabaseHelper myDB = new TaskDatabaseHelper(UpdateActivity.this);
                updateData();
                myDB.updateData(id, taskname, description, priority, tag, duedate, duetime);
            }
        });
        deleteButton = findViewById(R.id.deleteButton2);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDatabaseHelper myDB = new TaskDatabaseHelper(UpdateActivity.this);
                myDB.deleteData(id);
            }
        });
    }

    void updateData() {
//        id, taskname, description, priority, tag, duedate, duetime;
        taskname = taskNameEditText.getText().toString();
        description = taskDescriptionEditText.getText().toString();
        priority = priorityMenuAutoCompleteTextView.getText().toString();
        tag = taskTagsEditText.getText().toString();
        duedate = taskduedate.getText().toString();
        duetime = taskduetime.getText().toString();
    }

    void getIntentData() {
        id = getIntent().getStringExtra("id");
        taskname = getIntent().getStringExtra("taskname");
        description = getIntent().getStringExtra("description");
        priority = getIntent().getStringExtra("priority");
        tag = getIntent().getStringExtra("tag");
        duedate = getIntent().getStringExtra("duedate");
        duetime = getIntent().getStringExtra("duetime");

        taskNameEditText.setText(taskname);
        taskDescriptionEditText.setText(description);
        priorityMenuAutoCompleteTextView = findViewById(R.id.drop_priority_menu2);
        if (tag != null) {
            taskTagsEditText.setText(tag);
        } else {
            taskTagsEditText.setText("");
        }
        taskduedate.setText(duedate);
        taskduetime.setText(duetime);
    }
//    intent.putExtra("id", String.valueOf(taskModels.get(position).getTaskId()));
//    intent.putExtra("taskname", String.valueOf(taskModels.get(position).getTaskName()));
//    intent.putExtra("description", String.valueOf(taskModels.get(position).getDescription()));
//    intent.putExtra("priority", String.valueOf(taskModels.get(position).getPriorityLevel()));
//    intent.putExtra("tag", String.valueOf(taskModels.get(position).getTags()));
//    intent.putExtra("duedate", String.valueOf(taskModels.get(position).getDueDate()));
//    intent.putExtra("duetime", String.valueOf(taskModels.get(position).getDueTime()));
}