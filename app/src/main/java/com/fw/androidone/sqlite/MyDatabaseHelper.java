package com.fw.androidone.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * description :
 * author : apple
 * date : 2021/3/17 08:42
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstans.creatSQl());//创建book表
        db.execSQL(DBConstans.creatSQl2());//创建category表
        Toast.makeText(mContext, "creat db success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除表
        db.execSQL("drop table if exists " + DBConstans.TABLE_NAME);
        db.execSQL("drop table if exists " + DBConstans.TABLE_NAME_CATEGORY);
        onCreate(db);
    }
}
