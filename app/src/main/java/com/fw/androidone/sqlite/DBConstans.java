package com.fw.androidone.sqlite;

/**
 * description :
 * author : apple
 * date : 2021/3/17 08:45
 */
public class DBConstans {
    //数据库名
    public static String DB_NAME = "BookStore.db";
    //数据库版本
    public static int DB_VERSION = 2;
    //表名
    public static String TABLE_NAME = "Book";
    public static String TABLE_NAME_CATEGORY = "CateGory";

    public static String creatSQl() {
        StringBuffer sql = new StringBuffer();
        sql.append("create table ");
        sql.append(DBConstans.TABLE_NAME);
        sql.append("(");
        sql.append("id integer primary key autoincrement,");
        sql.append("author text,");
        sql.append("price real,");
        sql.append("page integer,");
        sql.append("name text");
        sql.append(")");
        return sql.toString();
    }

    public static String creatSQl2() {
        StringBuffer sql = new StringBuffer();
        sql.append("create table ");
        sql.append(DBConstans.TABLE_NAME_CATEGORY);
        sql.append("(");
        sql.append("id integer primary key autoincrement,");
        sql.append("category_name text,");
        sql.append("category_code integer");
        sql.append(")");
        return sql.toString();
    }
}
