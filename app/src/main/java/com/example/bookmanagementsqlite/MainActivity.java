package com.example.bookmanagementsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookmanagementsqlite.sqlite.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtId, edtName, edtPrice, edtCateId, edtCateDescription;
    CardView btnAdd, btnUpdate, btnDelete, btnGetAll;
    DBHelper DB;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = findViewById(R.id.computerId);
        edtName = findViewById(R.id.computerName);
        edtPrice = findViewById(R.id.computerPrice);

        btnAdd = findViewById(R.id.addComputer);
        btnUpdate = findViewById(R.id.UpdateComputers);
        btnDelete = findViewById(R.id.DeleteComputers);
        listView = findViewById(R.id.listView2);

        DB = new DBHelper(this);
        Boolean checkInsertCate = DB.insertCategory("C01","Laptop");
        Boolean checkInsertCate2 = DB.insertCategory("C02","Desktop");
        btnAdd.setOnClickListener(v -> {
            String id = edtId.getText().toString();
            String name = edtName.getText().toString();
            String price = edtPrice.getText().toString();
            Boolean checkinsertdata = DB.insertComputer(id,name,price,"C02");
            if(checkinsertdata==true){
                Toast.makeText(MainActivity.this, "Insert new data successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Insert new data failed", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(v -> {
            String id = edtId.getText().toString();
            String name = edtName.getText().toString();
            String price = edtPrice.getText().toString();
            Boolean checkinsertdata = DB.updateComputer(id,name,price,"C01");
            if(checkinsertdata==true){
                Toast.makeText(MainActivity.this, "Insert new data successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Insert new data failed", Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(v -> {
            String id = edtId.getText().toString();
            Boolean checkinsertdata = DB.DeleteComputer(id);
            if(checkinsertdata==true){
                Toast.makeText(MainActivity.this, "Insert new data successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Insert new data failed", Toast.LENGTH_SHORT).show();
            }
        });
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