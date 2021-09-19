package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqltest.bean.Student;
import com.example.sqltest.util.ToastUtil;

import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private EditText etName;
    private TextView tvResult;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        etName = findViewById(R.id.et_name);
        tvResult = findViewById(R.id.tv_result);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    public void query(View view) {
        String name = etName.getText().toString().trim();

        if (TextUtils.isEmpty(name))
        {
            // query for all data
            List<Student> students = mySQLiteOpenHelper.queryAllFromDb();
            showData(students);
            return;
        }

        // Query the data by name

        List<Student> students = mySQLiteOpenHelper.queryFromDbByName(name);
        showData(students);
    }



    private void showData(List<Student> students)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Student s : students)
        {
            stringBuilder.append("name: ");
            stringBuilder.append(s.getName());
            stringBuilder.append(", number: ");
            stringBuilder.append(s.getNumber());
            stringBuilder.append(", gender: ");
            stringBuilder.append(s.getGender());
            stringBuilder.append(", score: ");
            stringBuilder.append(s.getScore() + "\n");
        }




        tvResult.setText(stringBuilder.toString());
    }
}