package com.fw.androidone.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * description :自定义ContentProvider
 * author : apple
 * date : 2021/3/25 15:05
 */
public class MyContentProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    public static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.fw.androidone.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.fw.androidone.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.fw.androidone.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.fw.androidone.provider", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {//匹配是哪种类型的Uri
            case TABLE1_DIR:
                //查询table1表中所有数据
                break;
            case TABLE1_ITEM:
                //查询table1表中单条数据
                break;
            case TABLE2_DIR:
                //查询table2表中所有数据
                break;
            case TABLE2_ITEM:
                //查询table2表中单条数据
                break;
        }
        return null;
    }

    //获取Uri对象对应的MIME类型
    //MIME类型由三部分组成：
    //（1）必须以vnd开头；
    //（2）如果内容URI以路径结尾，则后接android.cursor.dir/；
    // 如果内容URI以id结尾，则后接android.cursor.item/；
    //（3）最后接上vnd.<authorith>.<path>
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.fw.androidone.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.fw.androidone.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.fw.androidone.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.fw.androidone.provider.table2";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
