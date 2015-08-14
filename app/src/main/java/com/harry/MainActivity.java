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

//        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //        MyReceiver myReceiver = new MyReceiver();
//        this.registerReceiver(myReceiver, intentFilter);
//        this.registerReceiver(new MyReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

//        IntentFilter intentFilter2 = new IntentFilter("com.test.training");
//        MyReceiver2 myReceiver2 = new MyReceiver2();
        this.registerReceiver(new MyReceiver2(), new IntentFilter("com.test.training"));
        this.registerReceiver(new MyReceiver2(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        // BR can also be defined in this way instead of creating different class
        BroadcastReceiver broadcastReceiver4 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, "Receiver 4 CHANGED level: ", Toast.LENGTH_SHORT).show();

            }
        };

        this.registerReceiver(broadcastReceiver4, new IntentFilter("com.test.training"));

//        IntentFilter intentFilter3 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        MyReceiver3 receiver3 = new MyReceiver3();
//        this.registerReceiver(new MyReceiver3(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public void br(View view) {
        Intent intent = new Intent();
        intent.setAction("com.test.training");
        intent.putExtra("int", 7);
        sendBroadcast(intent);
//        sendStickyBroadcast(intent);
    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "Receiver 1 LOW level", Toast.LENGTH_SHORT).show();
        }
    }

    class MyReceiver2 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int t = intent.getIntExtra("int",0);
            Toast.makeText(MainActivity.this, "Receiver 2 CHANGED level: "+t, Toast.LENGTH_SHORT).show();
//            Log.d("training", "changed level: " + t);
        }
    }
}
// We can register multiple IntentFilters for 1 BR and also multiple BR's for 1 IntentFilter