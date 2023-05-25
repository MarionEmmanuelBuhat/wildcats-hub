package com.example.wildcats_hub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

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

        taskduedate.setInputType(InputType.TYPE_NULL);
        taskduetime.setInputType(InputType.TYPE_NULL);

        taskduedate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayOfMonth) {
                            String month = "";
                            if (String.valueOf(monthofYear).length() == 1) {
                                month = "0" + (monthofYear+1);
                            } else {
                                month = String.valueOf(monthofYear+1);
                            }
                            String selectedDate = year + "-" + month + "-" + dayOfMonth;
                            taskduedate.setText(selectedDate);
                        }
                    },
                    year,month,day
            );
            datePickerDialog.show();
        });
        taskduetime.setOnClickListener(view -> {
            int hour = 0, minute = 0;
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    taskduetime.setText(String.format(Locale.getDefault(),
                            "%02d-%02d-00", selectedHour, selectedMinute));
                }
            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, false);

            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDatabaseHelper myDB = new TaskDatabaseHelper(UpdateActivity.this);
                updateData();
                if (id.isEmpty() || taskname.isEmpty() || description.isEmpty() || priority.isEmpty()
                        || duedate.isEmpty() || duetime.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Textfields must be non-empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
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