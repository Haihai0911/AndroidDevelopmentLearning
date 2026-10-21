package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayPanelFragment extends Fragment {

    // 接口：用于向 Activity 发送反馈
    public interface OnFeedbackSendListener {
        void onFeedbackSend(String feedback);
    }

    private OnFeedbackSendListener mFeedbackListener;
    private TextView tvDefaultTip, tvDetailTitle, tvDetailContent;
    private LinearLayout layoutDetail;
    private Button btnSendFeedback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFeedbackSendListener) {
            mFeedbackListener = (OnFeedbackSendListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_panel, container, false);

        tvDefaultTip = view.findViewById(R.id.tv_default_tip);
        layoutDetail = view.findViewById(R.id.layout_detail);
        tvDetailTitle = view.findViewById(R.id.tv_detail_title);
        tvDetailContent = view.findViewById(R.id.tv_detail_content);
        btnSendFeedback = view.findViewById(R.id.btn_send_feedback);

        return view;
    }

    // 供 Activity 调用，展示详情
    public void showDetail(String title, String content) {
        tvDefaultTip.setVisibility(View.GONE);
        layoutDetail.setVisibility(View.VISIBLE);
        tvDetailTitle.setText(title);
        tvDetailContent.setText(content);

        // 设置反馈按钮的点击事件
        btnSendFeedback.setOnClickListener(v -> {
            if (mFeedbackListener != null) {
                mFeedbackListener.onFeedbackSend("《" + title + "》已阅读");
            }
        });
    }
}