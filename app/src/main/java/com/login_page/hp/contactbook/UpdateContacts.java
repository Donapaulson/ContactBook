package com.login_page.hp.contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContacts extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    String number,intent_name,query,nmbr;
    EditText name,phonenumber;
    Button update;
    Editable updated_name,updated_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);

        Intent intent = getIntent();
        intent_name = intent.getStringExtra("name");
//        Log.e("intent name",""+intent_name);

        name = findViewById(R.id.update_name);
        name.setText(intent_name);

        dbHelper = new DBHelper(this);
        dbHelper.openConnection();
        query = "select * from contacts where name =" + "'" +intent_name+ "'";
        Cursor cursor = dbHelper.selectData(query);

        while (cursor.moveToNext()){
            nmbr = cursor.getString(cursor.getColumnIndex("number"));
        }

        phonenumber = findViewById(R.id.update_number);
        phonenumber.setText(nmbr);

        update = findViewById(R.id.update);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (update == view){

            updated_name = name.getText();
            updated_number = phonenumber.getText();

            String query_nameChange = "update contacts set name='" +updated_name+
                        "' where name ='" +intent_name+ "'";
            dbHelper.updateContact(query_nameChange);
            String query_numberChange = "update contacts set number='" +updated_number+
                    "' where number ='" +nmbr+ "'";
            dbHelper.updateContact(query_numberChange);
            dbHelper.closeConnection();

            Intent intent1 = new Intent(this,MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent1);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
