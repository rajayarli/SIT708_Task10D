package com.example.task6d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import com.example.task6d.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";

    // Database Name
    private static final String DATABASE_NAME = "MyDatabase";

    // User table name
    private static final String TABLE_USERS = "users";

    // User Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_IMAGE = "image";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_IMAGE_URI = "image_uri";

    // SQL query to create the user table
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USERNAME + " TEXT,"
            + KEY_FULLNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_PHONE + " TEXT,"
            + KEY_IMAGE + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create user table
        db.execSQL(CREATE_USER_TABLE);
    }
    public int insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_FULLNAME, user.getFullname());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_PHONE, user.getPhone());
        db.insert(TABLE_NAME, null, values);
        db.close();
        return 1;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }
    public List<User> getAllUsers(String username, String fullName, String password, String phone) {
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                user.setId(cursor.getInt(0));
                user.setUsername(username);
                user.setFullname(fullName);
                user.setPassword(password);
                user.setPhone(phone);

                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }
    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { KEY_ID };
        String selection = KEY_USERNAME + "=? and " + KEY_PASSWORD + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }



}

