package com.harry;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Battery is: 16%");

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        IntentFilter intentFilter2 = new IntentFilter("com.test.training");
//        IntentFilter intentFilter3 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


        MyReceiver myReceiver = new MyReceiver();
        this.registerReceiver(myReceiver, intentFilter);
        MyReceiver2 myReceiver2 = new MyReceiver2();
        this.registerReceiver(myReceiver2, intentFilter2);

//        MyReceiver3 receiver3 = new MyReceiver3();
//        this.registerReceiver(receiver3, intentFilter3);
    }

    public void br(View view) {
        Intent intent = new Intent();
        intent.setAction("com.test.training");
        intent.putExtra("int", 5);
        sendStickyBroadcast(intent);
    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "LOW level", Toast.LENGTH_SHORT).show();
        }
    }

    class MyReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int t = intent.getIntExtra("int",0);
//            Toast.makeText(MainActivity.this, "CHANGED level: "+t, Toast.LENGTH_SHORT).show();
            Log.d("training", "changed level: " + t);
        }
    }
}
