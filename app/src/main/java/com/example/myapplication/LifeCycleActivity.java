package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LifeCycleActivity extends AppCompatActivity {

    // 定义一个统一的 TAG，方便在 Logcat 中过滤
    private static final String TAG = "ActivityLifeCycle";

    // 定义状态保存的 Key
    private static final String KEY_COUNT = "key_count";

    // 定义控件和变量
    private TextView tvCounter;
    private Button btnCount;
    private int count = 0; // 计数变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.d(TAG, "onCreate() 被调用");

        // 1. 初始化控件
        tvCounter = findViewById(R.id.tvCounter);
        btnCount = findViewById(R.id.btnCount);

        // 2. 检查是否有保存的状态（例如屏幕旋转后恢复）
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
            Log.d(TAG, "onCreate: 恢复了保存的计数值 -> " + count);
        }

        // 3. 更新 UI 显示
        updateCounterText();

        // 4. 设置按钮点击事件
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++; // 计数累加
                updateCounterText();
                Log.d(TAG, "按钮被点击，当前计数：" + count);
            }
        });
    }

    // 辅助方法：更新 TextView 显示
    private void updateCounterText() {
        tvCounter.setText("当前计数：" + count);
    }

    // ================== 生命周期方法 ==================

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 被调用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() 被调用");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() 被调用");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() 被调用");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() 被调用");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() 被调用");
    }

    // ================== 状态保存与恢复 ==================

    /**
     * 在 Activity 可能被销毁前（如屏幕旋转、按Home键退居后台内存不足时）调用，
     * 用于保存临时数据。
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 将当前的 count 值存入 Bundle 中
        outState.putInt(KEY_COUNT, count);
        Log.d(TAG, "onSaveInstanceState() 被调用，保存 count = " + count);
    }
}