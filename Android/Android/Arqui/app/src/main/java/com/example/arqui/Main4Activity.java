package com.example.arqui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import static android.bluetooth.BluetoothAdapter.getDefaultAdapter;

public class Main4Activity extends AppCompatActivity {

    public void mensajito(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.BOTTOM, 0, 0);
        toast1.show();
    }

    private static final String TAG = "DispositivosBT";

    ListView IdLista;

    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter mPairedDevicesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    @Override
    public void onResume() {
        super.onResume();
        VerificarEstadoBT();
        mPairedDevicesArrayAdapter = new ArrayAdapter(this, R.layout.activity_nombre_dispositivos);
        IdLista = (ListView) findViewById(R.id.IdLista);
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        IdLista.setOnItemClickListener(mDeviceClickListener);
        mBtAdapter = getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            int nombre = info.length() - 17;
            String nom = info.substring(0, nombre);

            mensajito("Conectado");
            Intent i = new Intent(Main4Activity.this, MainActivity.class);
            i.putExtra("EXTRA_DEVICE_ADDRESS", address);
            i.putExtra("EXTRA_DEVICE_NOMBRE", nom);
            startActivity(i);
        }
    };

    private void VerificarEstadoBT() {
        mBtAdapter = getDefaultAdapter();
        if (mBtAdapter == null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()) {
                Log.d(TAG, "...Bluetooth Activado...");
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);

            }
        }
    }
}
