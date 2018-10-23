package com.login_page.hp.contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity implements View.OnClickListener {

    TextView tvName,tvNumber;
    Button delete,update;
    String name,number,nmbr;

    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);

        Intent intent = getIntent();
        name = intent.getStringExtra("Person");
//        Log.e("intent value",""+name);

        tvName = findViewById(R.id.name);
        tvName.setText(name);

        dbHelper = new DBHelper(this);
        dbHelper.openConnection();
        String query = "select * from contacts where name =" + "'" +name+ "'";
        Cursor cursor = dbHelper.selectData(query);

        while (cursor.moveToNext()){
            nmbr = cursor.getString(cursor.getColumnIndex("number"));
        }

//        Log.e("number of contcat",""+nmbr);
        tvNumber = findViewById(R.id.number);
        tvNumber.setText(nmbr);

        delete = findViewById(R.id.delete);
        update =findViewById(R.id.update);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (delete == view){
            String query = "delete from contacts where name ='" +name+ "'";
            dbHelper.updateContact(query);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        else if (update == view){
            Intent intent = new Intent(this,UpdateContacts.class);
            intent.putExtra("name",name);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        dbHelper.closeConnection();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
