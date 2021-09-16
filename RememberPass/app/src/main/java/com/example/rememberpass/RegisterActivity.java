package com.example.rememberpass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE_REGESTER = 0;
    private Button btnRegister;
    private EditText etAccount, etPass, etPassConfirm;
    private CheckBox cb_agree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        etAccount = findViewById(R.id.et_account);
        etPass = findViewById(R.id.et_password);
        etPassConfirm = findViewById(R.id.et_password_confirm);
        cb_agree = findViewById(R.id.cb_agree);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = etAccount.getText().toString();
        String pass = etPass.getText().toString();
        String passConfirm = etPassConfirm.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(RegisterActivity.this, "Username cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(RegisterActivity.this, "Password cannot be null", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.equals(pass, passConfirm))
        {
            Toast.makeText(RegisterActivity.this, "Password is not the same", Toast.LENGTH_LONG).show();
            return;
        }

        if (!cb_agree.isChecked())
        {
            Toast.makeText(RegisterActivity.this, "Please agree the policy", Toast.LENGTH_LONG).show();
            return;
        }

        // Store the Register username and password
        SharedPreferences spf = getSharedPreferences("spfRecord", MODE_PRIVATE);
        SharedPreferences.Editor edit = spf.edit();
        edit.putString("account", name);
        edit.putString("password", pass);

        // 数据回传
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("account", name);
        bundle.putString("password", pass);
        // put the bundle into intent
        intent.putExtras(bundle);
        // put back the data
        setResult(RESULT_CODE_REGESTER, intent);


        Toast.makeText(RegisterActivity.this, "Successfully Registed!", Toast.LENGTH_LONG).show();
        this.finish();
    }
}















