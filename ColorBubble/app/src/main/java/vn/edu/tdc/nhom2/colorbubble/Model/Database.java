package vn.edu.tdc.nhom2.colorbubble.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "Game";
    private static int DATABASE_VERSION = 1;
    private String TABLE_NAME = "Rank";
    private String KEY_ID = "ID";
    private String KEY_NAME = "Name";
    private String KEY_SCORE = "Score";
    private String KEY_TIME = "Time";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s INTEGER)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_SCORE, KEY_TIME);
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
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}