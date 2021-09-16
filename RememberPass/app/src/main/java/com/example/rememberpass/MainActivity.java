package com.example.rememberpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        tvContent = findViewById(R.id.tv_content);
        tvContent.setText("Welcome: " + account);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        // 为了防止在登录后又退出当前页面时，如果点返回键又会重回登录状态的问题
        this.finish();
    }
}