package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sqltest.util.ToastUtil;

public class DeleteActivity extends AppCompatActivity {

    private EditText etName;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        etName = findViewById(R.id.et_name);
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
    }

    public void delete(View view) {
        String name = etName.getText().toString().trim();

        // delete the data by name

        int row = mySQLiteOpenHelper.deleteFromDbByName(name);
        if (row > 0)
        {
            ToastUtil.toastLong(this, "Successfully delete the" + row + "th data");
        } else {
            ToastUtil.toastLong(this, "Cannot find the data");
        }
    }
}