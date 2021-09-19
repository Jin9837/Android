package com.example.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqltest.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mySQLite.db";
    private static final String TABLE_NAME_STUDENT = "student"; // 表名
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_STUDENT +" (id integer primary key autoincrement, name text, number text, gender text, score text)";



    public MySQLiteOpenHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(Student student)
    {
        // 拿到数据库
        SQLiteDatabase db = getWritableDatabase();

        // 准备数据
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("number", student.getNumber());
        values.put("gender", student.getGender());
        values.put("score", student.getScore());

        // insert the data
        return db.insert(TABLE_NAME_STUDENT, null, values);
    }


    public int deleteFromDbByName(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_STUDENT, "name like ?", new String[]{name});
    }


    public int updateData(Student student)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("number", student.getNumber());
        values.put("gender", student.getGender());
        values.put("score", student.getScore());

        return db.update(TABLE_NAME_STUDENT, values, "name like ?", new String[]{student.getName()});
    }


    public List<Student> queryFromDbByName(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Student> studentList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, "name like ?", new String[]{name}, null, null, null);
        if (cursor != null)
        {
            // cursor默认指向数据库第一行数据之前，若这个游标可以移到下一个（true)，则说明有数据
            while (cursor.moveToNext())
            {
                String name1 = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String score = cursor.getString(cursor.getColumnIndex("score"));

                Student student = new Student();
                student.setName(name1);
                student.setNumber(number);
                student.setGender(gender);
                student.setScore(score);

                studentList.add(student);
            }

            cursor.close();
        }
        return studentList;
    }


    public List<Student> queryAllFromDb()
    {
        SQLiteDatabase db = getWritableDatabase();
        List<Student> studentList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_STUDENT, null, null, null, null, null, null);
        if (cursor != null)
        {
            // cursor默认指向数据库第一行数据之前，若这个游标可以移到下一个（true)，则说明有数据
            while (cursor.moveToNext())
            {
                String name1 = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String gender = cursor.getString(cursor.getColumnIndex("gender"));
                String score = cursor.getString(cursor.getColumnIndex("score"));

                Student student = new Student();
                student.setName(name1);
                student.setNumber(number);
                student.setGender(gender);
                student.setScore(score);

                studentList.add(student);
            }

            cursor.close();
        }
        return studentList;
    }

}
