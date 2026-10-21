package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ControlPanelFragment extends Fragment {

    // 接口1：发给右侧（通过 Activity 转发）
    public interface OnItemSelectedListener {
        void onItemSelected(String title, String content);
    }

    // 接口2：接收右侧的反馈（由 Activity 调用）
    public interface OnFeedbackReceivedListener {
        void onFeedbackReceived(String feedback);
    }

    private OnItemSelectedListener mSendListener;
    private TextView tvFeedback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            mSendListener = (OnItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " 必须实现 OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control_panel, container, false);

        tvFeedback = view.findViewById(R.id.tv_feedback_from_right);
        Button btnItem1 = view.findViewById(R.id.btn_item_1);
        Button btnItem2 = view.findViewById(R.id.btn_item_2);

        // 点击发送数据给右侧
        btnItem1.setOnClickListener(v -> {
            if (mSendListener != null) {
                mSendListener.onItemSelected("文章 A", "这是文章 A 的详细内容，讲述了 Android Fragment 的基本用法。");
            }
        });

        btnItem2.setOnClickListener(v -> {
            if (mSendListener != null) {
                mSendListener.onItemSelected("文章 B", "这是文章 B 的详细内容，讲解了 Fragment 之间的数据交互。");
            }
        });

        return view;
    }

    // 供 Activity 调用的公共方法，用于更新左侧的反馈文字
    public void updateFeedback(String feedback) {
        if (tvFeedback != null) {
            tvFeedback.setText(feedback);
        }
    }
}