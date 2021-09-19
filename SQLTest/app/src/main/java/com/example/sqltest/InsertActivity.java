package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sqltest.bean.Student;
import com.example.sqltest.util.ToastUtil;

public class InsertActivity extends AppCompatActivity {

    private EditText etName, etNumber,etScore;
    private RadioButton rbMale, rbFemale;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        initView();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etScore = findViewById(R.id.et_score);
        rbFemale = findViewById(R.id.rb_female);
        rbMale = findViewById(R.id.rb_male);
    }


    public void insert(View view) {
        String name = etName.getText().toString().trim();//trim 去掉前后的多余的东西（回车键）
        String number = etNumber.getText().toString().trim();
        String score = etScore.getText().toString().trim();
        String gender = "";

        if (rbFemale.isChecked())
        {
            gender = "Female";
        }

        if (rbMale.isChecked())
        {
            gender = "Male";
        }


        Student student = new Student();
        student.setName(name);
        student.setNumber(number);
        student.setGender(gender);
        student.setScore(score);

        // 插入数据库中
        long rowId = mySQLiteOpenHelper.insertData(student);
        if (rowId != -1)
        {
            ToastUtil.toastShort(this, "Insert Successfully");
        } else
        {
            ToastUtil.toastShort(this, "Fail to Insert");
        }
    }
}