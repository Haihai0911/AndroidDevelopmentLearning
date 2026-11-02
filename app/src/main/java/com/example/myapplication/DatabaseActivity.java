package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_database;
    private String mDatabaseName; // 数据库名称
    private SQLiteDatabase mDb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database); // 确保你的xml叫这个名字

        tv_database = findViewById(R.id.tv_database);

        // 建议用 Button 接收，虽然 Button 继承自 TextView，但类型更准确
        Button createDB = findViewById(R.id.btnCreateDb);
        Button deleteDB = findViewById(R.id.btnDeleteDb);

        createDB.setOnClickListener(this);
        deleteDB.setOnClickListener(this);

        // 【严重 Bug 修复】: 这里只需要传数据库文件名，不能传绝对路径！
        // 系统会自动帮你建在 /data/data/包名/databases/ 目录下
        mDatabaseName = "test.db";
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCreateDb) {
            try {
                // 如果还没打开，就去打开
                if (mDb == null || !mDb.isOpen()) {
                    mDb = openOrCreateDatabase(mDatabaseName, Context.MODE_PRIVATE, null);
                }

                mDb.execSQL("CREATE TABLE IF NOT EXISTS user (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "age INTEGER)");

                mDb.execSQL("INSERT INTO user (name, age) VALUES ('张三', 25)");

                String desc = String.format("数据库%s创建成功，并已建表", mDb.getPath());
                tv_database.setText(desc);

            } catch (Exception e) {
                tv_database.setText("创建失败: " + e.getMessage());
            }

        }
        else if (v.getId() == R.id.btnDeleteDb) {
            // 【关键步骤】：删除前先断开连接
            if (mDb != null && mDb.isOpen()) {
                mDb.close();
                mDb = null; // 释放引用
            }

            // 然后再删文件
            boolean result = deleteDatabase(mDatabaseName);

            if (result) {
                tv_database.setText(String.format("数据库 %s 删除成功", mDatabaseName));
            } else {
                tv_database.setText(String.format("数据库 %s 删除失败 (可能被占用)", mDatabaseName));
            }
        }
    }
}