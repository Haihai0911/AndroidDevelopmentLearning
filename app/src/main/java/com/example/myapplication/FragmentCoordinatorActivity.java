package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class FragmentCoordinatorActivity extends AppCompatActivity
        implements ControlPanelFragment.OnItemSelectedListener,
        DisplayPanelFragment.OnFeedbackSendListener {

    private ControlPanelFragment controlPanelFragment;
    private DisplayPanelFragment displayPanelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_coordinator);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            controlPanelFragment = new ControlPanelFragment();
            displayPanelFragment = new DisplayPanelFragment();

            ft.add(R.id.container_control_panel, controlPanelFragment, "CONTROL");
            ft.add(R.id.container_display_panel, displayPanelFragment, "DISPLAY");
            ft.commit();
        } else {
            controlPanelFragment = (ControlPanelFragment) getSupportFragmentManager().findFragmentByTag("CONTROL");
            displayPanelFragment = (DisplayPanelFragment) getSupportFragmentManager().findFragmentByTag("DISPLAY");
        }
    }

    // ===== 左到右：接收左侧选择，通知右侧更新 =====
    @Override
    public void onItemSelected(String title, String content) {
        if (displayPanelFragment != null) {
            displayPanelFragment.showDetail(title, content);
        }
    }

    // ===== 右到左：接收右侧反馈，通知左侧更新 =====
    @Override
    public void onFeedbackSend(String feedback) {
        if (controlPanelFragment != null) {
            controlPanelFragment.updateFeedback(feedback);
        }
    }
}