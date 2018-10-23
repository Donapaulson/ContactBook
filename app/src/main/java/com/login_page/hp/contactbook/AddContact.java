package com.login_page.hp.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    EditText name,number;
    Button save,cancel;

    DBHelper dbHelper;
    String query;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        name = findViewById(R.id.Name);
        number = findViewById(R.id.contactNumber);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (save == view){
            dbHelper = new DBHelper(this);
            dbHelper.openConnection();
            String contactName = name.getText().toString();
            String contactNumber = number.getText().toString();

            query = "insert into contacts(name,number) values" + "('" + contactName + "','" + contactNumber + "')";
            boolean bool = dbHelper.executeQuery(query);
            if (bool == true){
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Error in value insertion",Toast.LENGTH_SHORT).show();
            }
            dbHelper.closeConnection();
        }
        else if (cancel == view){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
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
