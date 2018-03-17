package vn.edu.tdc.nhom2.nhatky.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TIEN on 3/17/2018.
 */

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "Diary";
    private static int DATABASE_VERSION = 1;
    private String TABLE_NAME = "Post";
    private String KEY_ID = "ID";
    private String KEY_TITLE = "Title";
    private String KEY_CONTENT = "Content";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_TITLE, KEY_CONTENT);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void createPost(Post post){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, post.getTitle());
        values.put(KEY_CONTENT, post.getContent());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
