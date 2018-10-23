package com.login_page.hp.contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView name;
    FloatingActionButton add;
    String selectquery;
    DBHelper dbHelper;
    Cursor cursor;
    ArrayList<String> names = new ArrayList<String>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.contactName);
        add = findViewById(R.id.addcontact);
        add.setOnClickListener(this);

        //DB VALUE
        dbHelper = new DBHelper(this);
        dbHelper.openConnection();
        //selection query
        selectquery = "select * from contacts";
        cursor = dbHelper.selectData(selectquery);
        if (cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex("name"));
                names.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.closeConnection();


        recyclerView = findViewById(R.id.recycleView);
        adapter = new RecyclerViewAdapter(names,getApplicationContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        if (add == view){
            Intent intent = new Intent(this,AddContact.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}



