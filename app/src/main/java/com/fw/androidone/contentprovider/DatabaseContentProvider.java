package com.fw.androidone.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fw.androidone.sqlite.DBConstans;
import com.fw.androidone.sqlite.MyDatabaseHelper;

/**
 * description :自定义查询本地数据库的ContentProvider
 * author : apple
 * date : 2021/3/25 15:05
 */
public class DatabaseContentProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String authority = "com.fw.androidone.provider";

    public static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, "Book", BOOK_DIR);//Book:数据库表名
        uriMatcher.addURI(authority, "Book/#", BOOK_ITEM);
        uriMatcher.addURI(authority, "CateGory", CATEGORY_DIR);
        uriMatcher.addURI(authority, "CateGory/#", CATEGORY_ITEM);
    }

    private MyDatabaseHelper helper;

    @Override
    public boolean onCreate() {
        //初始化数据库
        helper = new MyDatabaseHelper(getContext(), DBConstans.DB_NAME, null, 2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //查询数据
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {//匹配是哪种类型的Uri
            case BOOK_DIR:
                //查询Book表中所有数据
                cursor = db.query(DBConstans.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BOOK_ITEM:
                //查询Book表中单条数据
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query(DBConstans.TABLE_NAME, projection, "id = ?",
                        new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                //查询CateGory表中所有数据
                cursor = db.query(DBConstans.TABLE_NAME_CATEGORY, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                //查询CateGory表中单条数据
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query(DBConstans.TABLE_NAME_CATEGORY, projection, "id = ?",
                        new String[]{categoryId}, null, null, sortOrder);
                break;
        }
        return cursor;
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
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.fw.androidone.provider.Book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.fw.androidone.provider.Book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.fw.androidone.provider.CateGory";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.fw.androidone.provider.CateGory";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //添加数据
        SQLiteDatabase db = helper.getReadableDatabase();
        Uri returnUri = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert(DBConstans.TABLE_NAME, null, values);
                returnUri = Uri.parse("content://" + authority + "/" + DBConstans.TABLE_NAME + "/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert(DBConstans.TABLE_NAME_CATEGORY, null, values);
                returnUri = Uri.parse("content://" + authority + "/" + DBConstans.TABLE_NAME_CATEGORY + "/" + newCategoryId);
                break;
            default:
                break;
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //删除数据
        SQLiteDatabase db = helper.getReadableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = db.delete(DBConstans.TABLE_NAME, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete(DBConstans.TABLE_NAME, "id = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete(DBConstans.TABLE_NAME_CATEGORY, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete(DBConstans.TABLE_NAME_CATEGORY, "id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        //更新数据
        SQLiteDatabase db = helper.getReadableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updateRows = db.update(DBConstans.TABLE_NAME, values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update(DBConstans.TABLE_NAME, values, "id = ?",
                        new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update(DBConstans.TABLE_NAME_CATEGORY, values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update(DBConstans.TABLE_NAME_CATEGORY, values, "id = ?",
                        new String[]{categoryId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}
