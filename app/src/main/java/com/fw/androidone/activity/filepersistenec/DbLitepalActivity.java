package com.fw.androidone.activity.filepersistenec;

import android.database.Cursor;
import android.view.View;
import android.widget.Button;

import com.fw.androidone.R;
import com.fw.androidone.base.activity.BaseActivity;
import com.fw.androidone.sqlitelitepal.entity.Book;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**
 * description :litepal数据库操作：新建、增删改查、
 * author : apple
 * date : 2021/3/15 13:50
 */
public class DbLitepalActivity extends BaseActivity {

    private Button btn_db_create;
    private Button btn_db_add;
    private Button btn_db_update;
    private Button btn_db_delete;
    private Button btn_db_query;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_file_test_db_litepal;
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
    }

    @Override
    protected void initData() {
        //创建数据库
        btn_db_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        //添加数据
        btn_db_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor("王天一");
                book.setName("王天一的第一本书");
                book.setPage(333);
                book.setPress("某某出版社");
                book.setPrice(33.32);
                book.save();
            }
        });

        //更新数据
        btn_db_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式一：对已经存储的数据进行更新
                Book book = new Book();
                book.setAuthor("王天二");
                book.setName("王天二的第一本书");
                book.setPage(444);
                book.setPress("某某一出版社");
                book.setPrice(44.98);
                book.save();
                // 重新修改价格，然后点用save方法，
                // 此时，litapal数据库中会发现该数据之前已经存储在数据库中，所以进行更新价格操作。而不会重新插入一条数据。
                book.setPrice(44.2222);
                book.save();

                //方式二：updateAll方法实现
                Book book1 = new Book();
                book1.setPrice(44.6786);
                book1.updateAll("name = ? and author = ?", "王天二的第一本书", "王天二");
            }
        });

        //删除数据
        btn_db_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除book表中price=44.6786的这条数据
                LitePal.deleteAll(Book.class, "price = ?", "44.6786");
            }
        });

        //查询数据
        btn_db_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询所有数据
                List<Book> bookList = LitePal.findAll(Book.class);
                //查询第一条数据
                Book firstBook = LitePal.findFirst(Book.class);
                //查询最后一条数据
                Book lastBook = LitePal.findLast(Book.class);
                //按照条件查询
                //(1）select()方法用于指定查询哪几列的数据，对应sql中的select关键字
                List<Book> books = LitePal.select("name", "author").find(Book.class);
                //(2) where()用于指定查询的约束条件，对应了sql中的where关键字
                List<Book> books1 = LitePal.where("page > ?", "100").find(Book.class);
                //(3) order()方法用于指定结果的排序方式，对应了sql当中的order by 关键字
                List<Book> books2 = LitePal.order("price desc").find(Book.class);
                //(4) limint()方法用于指定查询结果的数量
                List<Book> books3 = LitePal.limit(3).find(Book.class);//查询表中前3条数据
                //(5)offset() 方法用于指定查询结果的偏移量
                List<Book> books4 = LitePal.limit(3).offset(1).find(Book.class);//查询表中第2条、第3条、第4条数据
                //(6) 组合使用查询
                List<Book> books5 = LitePal.select("name", "author")
                        .where("page > ?", "100")
                        .order("price")
                        .limit(3)
                        .offset(2)
                        .find(Book.class);//查询Book表中第3～5条满足page>100页这个条件的name、author这两列数据，并且查询结果按照price升序排序

                //LitePal使用原声SQL查询
                Cursor cursor = LitePal.findBySQL("select * form Book where page > ? and price < ?", "100", "60");
            }
        });
    }

}
