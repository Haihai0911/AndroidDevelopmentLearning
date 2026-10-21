package com.example.myapplication;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private RadioGroup rgLoginType;
    private TextView tvLabel, tvForgetPwd, tvGetCode;
    private EditText etPhone, etInput;
    private CheckBox cbRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. 初始化控件
        rgLoginType = findViewById(R.id.rg_login_type);
        tvLabel = findViewById(R.id.tv_label);
        tvForgetPwd = findViewById(R.id.tv_forget_pwd);
        tvGetCode = findViewById(R.id.tv_get_code);
        etPhone = findViewById(R.id.et_phone);
        etInput = findViewById(R.id.et_input);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);

        // 2. 监听登录方式切换
        rgLoginType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_password) {
                // --- 切换到密码登录 ---
                tvLabel.setText("登录密码：");
                etInput.setHint("请输入密码");
                etInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); // 恢复密码格式
                etInput.setText(""); // 清空输入

                tvForgetPwd.setVisibility(View.VISIBLE);
                tvGetCode.setVisibility(View.GONE);
                cbRemember.setVisibility(View.VISIBLE);

            } else if (checkedId == R.id.rb_verification) {
                // --- 切换到验证码登录 ---
                tvLabel.setText("验证码：");
                etInput.setHint("请输入验证码");
                etInput.setInputType(InputType.TYPE_CLASS_NUMBER); // 切换为数字键盘
                etInput.setText(""); // 清空输入

                tvForgetPwd.setVisibility(View.GONE);
                tvGetCode.setVisibility(View.VISIBLE);
                cbRemember.setVisibility(View.GONE);
            }
        });

        // 3. 登录按钮点击
        btnLogin.setOnClickListener(v -> {
            String phone = etPhone.getText().toString().trim();
            String input = etInput.getText().toString().trim();

            if (phone.isEmpty()) {
                Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (input.isEmpty()) {
                Toast.makeText(this, "请输入密码或验证码", Toast.LENGTH_SHORT).show();
                return;
            }

            // 判断当前状态
            if (rgLoginType.getCheckedRadioButtonId() == R.id.rb_password) {
                Toast.makeText(this, "执行密码登录", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "执行验证码登录", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. 获取验证码按钮点击
        tvGetCode.setOnClickListener(v -> {
            Toast.makeText(this, "获取验证码...", Toast.LENGTH_SHORT).show();
        });
    }
}