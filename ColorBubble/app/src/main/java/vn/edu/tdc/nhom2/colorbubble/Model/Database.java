package vn.edu.tdc.nhom2.colorbubble.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "Game";
    private static int DATABASE_VERSION = 1;
    private String TABLE_NAME = "Rank";
    private String KEY_ID = "ID";
    private String KEY_NAME = "Name";
    private String KEY_SCORE = "Score";
    private String KEY_TIME = "Time";
    private String KEY_HINH = "Hinh";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_SCORE, KEY_TIME,KEY_HINH);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void createPost(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName());
        values.put(KEY_SCORE, score.getScore());
        values.put(KEY_TIME, score.getTime());
        values.put(KEY_HINH, score.getHinh());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<Score> getAllScore() {
        ArrayList<Score> Scores = new ArrayList<Score>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setName(cursor.getString(1));
                score.setScore(cursor.getInt(2));
                score.setTime(cursor.getInt(3));
                score.setHinh(cursor.getString(4));
                // Adding contact to list
                Scores.add(score);
            } while (cursor.moveToNext());
        }

        // return contact list
        return Scores;
    }


    public ArrayList<Score> getScore() {
        ArrayList<Score> Scores = new ArrayList<Score>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + KEY_SCORE + " DESC LIMIT 5";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();

                score.setName(cursor.getString(1));
                score.setScore(cursor.getInt(2));
                score.setTime(cursor.getInt(3));
                score.setHinh(cursor.getString(4));
                // Adding contact to list
                Scores.add(score);
            } while (cursor.moveToNext());
        }

        // return contact list
        return Scores;
    }


    public void deleteAllScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName()); // Contact Name
        values.put(KEY_SCORE, score.getScore());
        values.put(KEY_TIME, score.getTime());
        values.put(KEY_HINH, score.getHinh());// Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
}