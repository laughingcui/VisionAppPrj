package com.android.cuisy.visionappprj.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.android.cuisy.visionappprj.entity.Constants;


//定义User对象

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "user";

    public static final String sql = "create table if not exists "
                + TABLE_NAME
                + "(id integer primary key autoincrement,"
                + "email varchar(50),"
                + "phone varchar(50),"
                + "password varchar(20),"
                + "init_time datetime,"
                + "update_time datetime,"
                + "remark varchar(10))";
    private Context mContext;

    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        Toast.makeText(mContext, "Create Succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists" + TABLE_NAME);
    }
//保存一条记录
    void addUser() {
    }
    void modify(){

    }
}
