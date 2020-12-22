package com.example.freeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.freeme.DataActivity.mypreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedpreferences;
    TextView form_label;
    public String phoneNumber = "13033";
    public String content = " ";
    private static final int PERMISSION_SEND_SMS = 2;

    public String user_name = "";
    public String user_surname = "";
    public String user_address = "";

    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button b5;
    public Button b6;

    public Button[] arrayBtn = new Button[6];
    public String Temp = content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // A button to change activity window
        Button Move = findViewById(R.id.Move);
        Move.setOnClickListener(v -> NextActivity());


        form_label = findViewById(R.id.title);
        content = (user_name+ " "+user_surname+" "+user_address);

        //Button b1 = findViewById(R.id.button1);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);

        //Set click listener for every button
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);

        // populate the array with App buttons
        arrayBtn[0] = b1;
        arrayBtn[1] = b2;
        arrayBtn[2] = b3;
        arrayBtn[3] = b4;
        arrayBtn[4] = b5;
        arrayBtn[5] = b6;

        //Display the data in a label to check them
        //form_label.setText(user_name+ " "+user_surname+" "+user_address);
        form_label.setText(content);


    }

    public void NextActivity() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }


    private void sendSMS(String strPhone, String strInfo) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS );
        } else {
            PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
                    SENT), 0);

            PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                    new Intent(DELIVERED), 0);

            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(strPhone, null, strInfo, sentPI, deliveredPI);
        }
    }

    @Override
    public void onClick(View v) {
        String Temp = content;
        // Perform action on click
        if(content.trim().length() > 0) {
            switch (v.getId()) {
                case R.id.button1:
                    //If the first button is clicked then send option 1 plus the content
                    content = "1 " + content;
                    buttonActivation();
                    break;
                case R.id.button2:
                    content = "2 " + content;
                    buttonActivation();
                    break;
                case R.id.button3:
                    content = "3 " + content;
                    buttonActivation();
                    break;
                case R.id.button4:
                    content = "4 " + content;
                    buttonActivation();
                    break;
                case R.id.button5:
                    content = "5 " + content;
                    buttonActivation();
                    break;
                case R.id.button6:
                    content = "6 " + content;
                    buttonActivation();
                    break;
            }
        }else{
            Toast.makeText(this, "Παρακαλώ εισάγεται τα στοιχεία σας", Toast.LENGTH_SHORT).show();
        }
    }

    public void ButtonTimer(View buttons[]){
        //public void ButtonTimer(View buttons[]){
        buttons[0].setEnabled(false);
        buttons[1].setEnabled(false);
        buttons[2].setEnabled(false);
        buttons[3].setEnabled(false);
        buttons[4].setEnabled(false);
        buttons[5].setEnabled(false);

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> b1.setEnabled(true));
                runOnUiThread(() -> b2.setEnabled(true));
                runOnUiThread(() -> b3.setEnabled(true));
                runOnUiThread(() -> b4.setEnabled(true));
                runOnUiThread(() -> b5.setEnabled(true));
                runOnUiThread(() -> b6.setEnabled(true));
            }
        }, 5000);
    }

    @Override
    public void onResume(){
        super.onResume();
        getPref();
        content = (user_name+ " "+user_surname+" "+user_address);
        form_label.setText(content);
    }

    public void getPref (){
        //GET shared preferences from DataActivity
        sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);
        if (sharedpreferences.contains("nameKey")) {
            user_name = sharedpreferences.getString("nameKey", "");
            user_name = user_name.replace(" ","");
        }
        if (sharedpreferences.contains("nameKey")) {
            user_surname = sharedpreferences.getString("surnameKey", "");
            user_surname = user_surname.replace(" ","");
        }
        if (sharedpreferences.contains("nameKey")) {
            user_address = sharedpreferences.getString("addressKey", "");
            user_address = user_address.replace(" ","");
        }
    }

    public void buttonActivation() {
        Log.d("content change", content);
        sendSMS(phoneNumber, content);
        content = Temp;
        ButtonTimer(arrayBtn);
    }

}