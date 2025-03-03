package com.example.manga;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DB_NAME = "UserInformation.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "UserTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "USER_NAME";
    private static final String COLUMN_LASTNAME = "USER_LASTNAME";
    private static final String COLUMN_USERNAME = "USER_USERNAME";
    private static final String COLUMN_PASSWORD = "USER_PASSWROD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        "( "+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT ," +
                        COLUMN_LASTNAME + " TEXT , " +
                        COLUMN_USERNAME + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addInformation(String name,String lastName,String userName,String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_LASTNAME,lastName);
        cv.put(COLUMN_USERNAME,userName);
        cv.put(COLUMN_PASSWORD,password);

        long res = db.insert(TABLE_NAME,null,cv);

        assert context != null;
        View rootView = ((Activity) context).findViewById(android.R.id.content); // Root view

        if (res == -1) {
            Snackbar.make(rootView, "Connection failed", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(rootView, "Your account has been successfully created.", Snackbar.LENGTH_LONG).show();
        }
    }

    boolean isUsernameTaken(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean loginUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean isValid = cursor.getCount() > 0; // If count > 0 = user found
        cursor.close();
        return isValid;
    }

    public boolean loginPassword(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{password});

        boolean isValid = cursor.getCount() > 0; // If count > 0 = pas found
        cursor.close();
        return isValid;
    }
}