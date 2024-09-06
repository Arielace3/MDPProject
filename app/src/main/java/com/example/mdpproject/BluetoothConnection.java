package com.example.mdpproject;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BluetoothConnection extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int REQUEST_BLUETOOTH_CONNECT = 1;

    BluetoothAdapter mBluetoothAdapter;
    TextView bluetoothStatus;
    Switch btnONOFF;

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                Log.d(TAG, "onReceive: Bluetooth state changed. New state: " + state);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        bluetoothStatus.setText("Off");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "onReceive: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "onReceive: STATE ON");
                        bluetoothStatus.setText("On");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "onReceive: STATE TURNING ON");
                        break;
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver1); // Unregister the receiver
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnONOFF = findViewById(R.id.btnONOFF);
//        bluetoothStatus = findViewById(R.id.bluetoothStatus);

        Map map = findViewById(R.id.map);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Register BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver1, filter);

        // Check if BLUETOOTH_CONNECT permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission if not already granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    REQUEST_BLUETOOTH_CONNECT);
        } else {
            // Permission is already granted, proceed with Bluetooth operations
            btnONOFF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: enabling/disabling bluetooth.");
                    enableDisableBT();
                }
            });
        }



        // Set initial Bluetooth status
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()) {
            bluetoothStatus.setText("On");
            //setChecked not working, not sure why, might omit Toggle graphic
//            btnONOFF.setChecked(true);
        } else {
            bluetoothStatus.setText("Off");
//            btnONOFF.setChecked(false);
        }
    }

    private void initBluetooth() {
        // Initialize Bluetooth
    }

    public void enableDisableBT() {
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "enableDisableBT: enabling BT.");
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);
        } else {
            Log.d(TAG, "enableDisableBT: disabling BT.");
            mBluetoothAdapter.disable();
            bluetoothStatus.setText("Off");
            // The receiver will handle updating the status
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_BLUETOOTH_CONNECT) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with Bluetooth operations
                initBluetooth();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Bluetooth permission is required to use this feature.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

