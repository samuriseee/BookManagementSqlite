package com.example.bookmanagementsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookmanagementsqlite.sqlite.DBHelper;

import java.util.ArrayList;

public class ListviewContent extends AppCompatActivity {
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_content);
        ListView listView = findViewById(R.id.listView2);
        DB = new DBHelper(this);
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = DB.getAllComputer();
        if(cursor.getCount()==0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                list.add("Id:"+cursor.getString(0)+"\nName:"+cursor.getString(1)+"\nPrice:"+cursor.getString(2)+"\nCategory:"+cursor.getString(3));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }

    }
}
