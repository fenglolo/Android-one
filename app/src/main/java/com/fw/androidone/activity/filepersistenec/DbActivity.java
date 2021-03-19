package com.fw.androidone.activity.filepersistenec;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.sqlite.DBConstans;
import com.fw.androidone.sqlite.MyDatabaseHelper;

/**
 * description :sql数据库操作：新建、增删改查、
 * author : apple
 * date : 2021/3/15 13:50
 */
public class DbActivity extends BaseActivity {

    private Button btn_db_create;
    private Button btn_db_add;
    private Button btn_db_update;
    private Button btn_db_delete;
    private Button btn_db_query;

    private MyDatabaseHelper helper;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_file_test_db;
    }

    @Override
    protected void initView() {
        btn_db_create = findViewById(R.id.btn_db_create);
        btn_db_add = findViewById(R.id.btn_db_add);
        btn_db_update = findViewById(R.id.btn_db_update);
        btn_db_delete = findViewById(R.id.btn_db_delete);
        btn_db_query = findViewById(R.id.btn_db_query);
    }

    @Override
    protected void initAction() {
        helper = new MyDatabaseHelper(this, DBConstans.DB_NAME, null, DBConstans.DB_VERSION);
    }

    @Override
    protected void initData() {
        btn_db_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.getWritableDatabase();
            }
        });

        //添加数据
        btn_db_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

                //方法一：使用ContentValues
                ContentValues values = new ContentValues();
                values.put("author", "张三");
                values.put("price", "15.8");
                values.put("page", "333");
                values.put("name", "张三de书");
                db.insert(DBConstans.TABLE_NAME, null, values);

                values.clear();
                values.put("author", "李四");
                values.put("price", "55.22");
                values.put("page", "901");
                values.put("name", "在春风里");
                db.insert(DBConstans.TABLE_NAME, null, values);

                //方法二：使用sql语句
                db.execSQL("insert into " + DBConstans.TABLE_NAME + "(author,price,page,name) values (?,?,?,?)", new String[]{"王一", "20.00", "200", "王一的第一本书"});
                db.execSQL("insert into " + DBConstans.TABLE_NAME + "(author,price,page,name) values (?,?,?,?)", new String[]{"王一", "32.10", "300", "王一的第二本书"});
            }
        });

        //更新数据
        btn_db_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                //方法一：使用ContentValues
                ContentValues values = new ContentValues();
                values.put("price", "22.22");//设置price为22.22
                db.update(DBConstans.TABLE_NAME, values, "name = ?", new String[]{"张三de书"});//更新name为"张三de书"的price为22.22

                //方法二：使用sql语句
                db.execSQL("update " + DBConstans.TABLE_NAME + " set price = ? where name = ?", new String[]{"22.22", "张三de书"});
            }
        });

        //删除数据
        btn_db_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                //方法一：使用ContentValues
                db.delete(DBConstans.TABLE_NAME, "page > ?", new String[]{"500"});//删除表中page大于500的数据

                //方法二：使用sql语句
                db.execSQL("delete from " + DBConstans.TABLE_NAME + " where page > ?", new String[]{"500"});
            }
        });

        //查询数据
        btn_db_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

                //方法一：使用ContentValues
                Cursor cursor = db.query(DBConstans.TABLE_NAME, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历cursor
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int page = cursor.getInt(cursor.getColumnIndex("page"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("DbActivity", "name is : " + name);
                        Log.d("DbActivity", "author is : " + author);
                        Log.d("DbActivity", "page is : " + page);
                        Log.d("DbActivity", "price is : " + price);

                        Toast.makeText(DbActivity.this, "name:" + name + "--author:" + author + "--page:" + page + "--price:" + price, Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }

                //方法二：使用sql语句
                Cursor cursor2 = db.rawQuery("select * from " + DBConstans.TABLE_NAME, null);
            }
        });
    }

}
