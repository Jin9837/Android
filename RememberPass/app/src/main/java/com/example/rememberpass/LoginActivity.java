package com.example.rememberpass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REGISTER = 1;
    private Button btnLogin;
    private EditText etAccount, etPassword;
    private CheckBox cbRemember;

    private String userName = "Jin";
    private String pass = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");

        //  用来专门找控件的方法，写在了本文件最下面，使代码更简洁
        initView();

        // 用来专门生成data
        initData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the username and password the user input
                String account = etAccount.getText().toString();
                String password = etPassword.getText().toString();


                if (TextUtils.isEmpty(userName))
                {
                    Toast.makeText(LoginActivity.this, "Please register an account first!", Toast.LENGTH_LONG).show();
                    return;
                }


                // compare the username and password is equal to the data we stored or not
                if (TextUtils.equals(account, userName))
                {
                    if (TextUtils.equals(password, pass))
                    {
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_LONG).show();
                        // if remember password's checkBox is choosed
                        if (cbRemember.isChecked())
                        {
                            SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();
                            edit.putString("account", account);
                            edit.putString("password", password);
                            edit.putBoolean("isRemember", true);
                            edit.apply();
                        } else
                        {
                            SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
                            SharedPreferences.Editor edit = spf.edit();
                            edit.putBoolean("isRemember", false);
                            edit.apply();
                        }


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        // 跳转完之后从任务栈销毁，使当前任务栈中只有main activity
                        LoginActivity.this.finish();

                    } else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Password!", Toast.LENGTH_LONG).show();
                    }
                } else
                {
                    Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
    }


    // initialize the data
    private void initData() {
        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
        boolean isRemember = spf.getBoolean("isRemember", false);
        String account = spf.getString("account", "");
        String password = spf.getString("password", "");

        if (isRemember)
        {
            etAccount.setText(account);
            etPassword.setText(password);
            cbRemember.setChecked(true);
        }
    }

    // when pressed "does not have an account yet, it will call the register function"
    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);

        startActivityForResult(intent, REQUEST_CODE_REGISTER);

    }

    // 注册完之后把用户名和密码默认存下来回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER && resultCode == RegisterActivity.RESULT_CODE_REGESTER && data != null)
        {
            Bundle extras = data.getExtras();
            String account = extras.getString("account", "");
            String password = extras.getString("password", "");

            etAccount.setText(account);
            etPassword.setText(password);

            userName = account;
            pass = password;
        }
    }
}










