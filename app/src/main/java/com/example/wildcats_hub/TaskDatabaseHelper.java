package com.example.wildcats_hub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TaskDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASKNAME = "task_name";
    private static final String COLUMN_DESCRIPTION = "task_description";
    private static final String COLUMN_PRIORITYLEVEL = "task_priority_level";
    private static final String COLUMN_TAG = "task_Tag";
    private static final String COLUMN_DUEDATE = "task_due_date";
    private static final String COLUMN_DUETIME = "task_due_time";

    public TaskDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRIORITYLEVEL + " TEXT, " +
                COLUMN_TAG + " TEXT, " +
                COLUMN_DUEDATE + " TEXT, " +
                COLUMN_DUETIME + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTask(String taskName, String taskDescription, String taskPriorityLevel,
                 String taskTag, String taskDueDate, String taskDueTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean tableExists = isTableExists(TABLE_NAME, db);
        if (!tableExists) {
            // Table doesn't exist, create it
            createTable(db);
        }

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASKNAME, taskName);
        cv.put(COLUMN_DESCRIPTION, taskDescription);
        cv.put(COLUMN_PRIORITYLEVEL, taskPriorityLevel);
        cv.put(COLUMN_TAG, taskTag);
        cv.put(COLUMN_DUEDATE, taskDueDate);
        cv.put(COLUMN_DUETIME, taskDueTime);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT) .show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT) .show();
        }
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{id});
        if (rowsAffected > 0) {
            Toast.makeText(context, "Successfully deleted task.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete task.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            // Check if the table exists
            if (isTableExists(TABLE_NAME, db)) {
                String query = "SELECT * FROM " + TABLE_NAME;
                cursor = db.rawQuery(query, null);
            }
        }
        return cursor;
    }

    private boolean isTableExists(String tableName, SQLiteDatabase db) {
        String query = "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?";
        Cursor cursor = db.rawQuery(query, new String[]{tableName});
        boolean tableExists = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return tableExists;
    }

    void updateData(String id, String taskName, String taskDescription, String taskPriorityLevel,
                    String taskTag, String taskDueDate, String taskDueTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_TASKNAME, taskName);
        cv.put(COLUMN_DESCRIPTION, taskDescription);
        cv.put(COLUMN_PRIORITYLEVEL, taskPriorityLevel);
        cv.put(COLUMN_TAG, taskTag);
        cv.put(COLUMN_DUEDATE, taskDueDate);
        cv.put(COLUMN_DUETIME, taskDueTime);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to create the table if it doesn't exist
    private void createTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASKNAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRIORITYLEVEL + " TEXT, " +
                COLUMN_TAG + " TEXT, " +
                COLUMN_DUEDATE + " TEXT, " +
                COLUMN_DUETIME + " TEXT)";

        db.execSQL(createTableQuery);
    }
}
