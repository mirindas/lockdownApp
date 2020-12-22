package com.example.freeme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText form_name;
    EditText form_surname;
    EditText form_address;
    Button form_Save;
    Button form_Back;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Surname = "surnameKey";
    public static final String Address = "addressKey";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_data);

        form_name = findViewById(R.id.input_name);
        form_surname = findViewById(R.id.input_surname);
        form_address = findViewById(R.id.input_address);
        form_Save = findViewById(R.id.Save);
        form_Back = findViewById(R.id.Back);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            form_name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Surname)) {
            form_surname.setText(sharedpreferences.getString(Surname, ""));
        }
        if (sharedpreferences.contains(Address)) {
            form_address.setText(sharedpreferences.getString(Address, ""));
        }


        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();*/

    }


    public void Save(View view) {
        String n = form_name.getText().toString().toUpperCase().replace(" ","");;
        String s = form_surname.getText().toString().toUpperCase().replace(" ","");;
        String a = form_address.getText().toString().toUpperCase().replace(" ","");;
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Surname, s);
        editor.putString(Address, a);
        editor.apply();
    }

    public void Back(View view){
        finish();
    }

//    public void Get(View view) {
//        form_name = (EditText) findViewById(R.id.input_name);
//        form_surname = (EditText) findViewById(R.id.input_surname);
//        form_address = (EditText) findViewById(R.id.input_address);
//        sharedpreferences = getSharedPreferences(mypreference,
//                Context.MODE_PRIVATE);
//
//        if (sharedpreferences.contains(Name)) {
//            form_name.setText(sharedpreferences.getString(Name, ""));
//        }
//        if (sharedpreferences.contains(Surnmae)) {
//            form_surname.setText(sharedpreferences.getString(Surnmae, ""));
//        }
//        if (sharedpreferences.contains(Address)) {
//            form_address.setText(sharedpreferences.getString(Address, ""));
//        }
//    }

}

