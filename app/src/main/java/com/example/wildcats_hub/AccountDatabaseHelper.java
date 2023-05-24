package com.example.wildcats_hub;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AccountDatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "account";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "acc_username";
    private static final String COLUMN_EMAIL = "acc_email";
    private static final String COLUMN_INS_EMAIL = "acc_ins_email";
    private static final String COLUMN_PASSWORD = "acc_password";

    public AccountDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_INS_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addAccount(String accName, String accEmail, String accInsEmail,
                 String accPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, accName);
        cv.put(COLUMN_EMAIL, accEmail);
        cv.put(COLUMN_INS_EMAIL, accInsEmail);
        cv.put(COLUMN_PASSWORD, accPassword);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT) .show();
        } else {
            Toast.makeText(context, "Successfully Registered", Toast.LENGTH_SHORT) .show();
        }
    }

    public int loginAuthentication(String accName, String accPassword) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to select
        String[] columns = {COLUMN_USERNAME, COLUMN_PASSWORD};

        // Define the selection criteria
        String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {accName, accPassword};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Data exists matching the selection criteria
            Toast.makeText(context, "Successfully Logged In", Toast.LENGTH_SHORT).show();
            cursor.close();
            return 1;
        } else {
            // No data exists matching the selection criteria
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
        }
        return 0;
    }
}
