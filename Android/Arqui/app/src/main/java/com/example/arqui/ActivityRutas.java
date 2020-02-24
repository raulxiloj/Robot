package com.example.arqui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityRutas extends AppCompatActivity {

    //bt

    public Handler bluetoothIn;
    public final int handlerState = 0;
    public BluetoothAdapter btAdapter = null;
    public BluetoothSocket btSocket = null;
    public StringBuilder DataStringIN = new StringBuilder();
    public ConnectedThread MyConexionBT;
    public static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    public static String address = null;

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }



    @Override
    public void onResume()
    {
        super.onResume();
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = intent.getStringExtra("EXTRA_DEVICE_ADDRESS");//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try
        {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {}
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    //Crea la clase que permite crear el evento de conexion
    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }



    Spinner sp;
    ListView lst;
    Button botonAgregar;
    Button botonCrear;
    Button botonCancelar;
    Button botonActualizar;
    Button botonVer;
    Button botonLimpiar;
    EditText textoNombreRuta;
    EditText numeroTiempo;

    public void mensajito(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.BOTTOM, 0, 0);
        toast1.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        //---------------------------------------------------------------------
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        mensajito("Dato: " + dataInPrint);
                        //textoNombreRuta.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        /*
        * Spinner de movimientos
        * a = atras
        * d = derecha
        * i = izquierda
        * t = atras
        * */

        textoNombreRuta = (EditText) findViewById(R.id.editText2);
        numeroTiempo = (EditText) findViewById(R.id.editText5);

        sp = (Spinner) findViewById(R.id.spinner2);
        final String[] datos = new String[] {"Izquierda", "Derecha", "Adelante", "Atras"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        sp.setAdapter(adapter);

        /*
        * Lista de movimientos
        * */
        lst = (ListView) findViewById(R.id.listview);
        final ArrayList<String> cadenaRuta = new ArrayList<String>();
        final ArrayList<String> datosLista = new ArrayList<String>();
        final ArrayAdapter<String> adapterLista = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datosLista);
        lst.setAdapter(adapterLista);

        botonAgregar = (Button) findViewById(R.id.button137);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * Como los controles de jugar en el teclado de la compu
                * Validar tiempo > 0
                * */
                if(!numeroTiempo.getText().equals("")){
                    switch (sp.getSelectedItem().toString()){
                        case "Izquierda":
                            cadenaRuta.add("a" + numeroTiempo.getText() + ",");
                            break;
                        case "Adelante":
                            cadenaRuta.add("w" + numeroTiempo.getText() + ",");
                            break;
                        case "Atras":
                            cadenaRuta.add("s" + numeroTiempo.getText() + ",");
                            break;
                        case "Derecha":
                            cadenaRuta.add("d" + numeroTiempo.getText() + ",");
                            break;
                    }
                    datosLista.add((datosLista.size()+1) + "- " + sp.getSelectedItem().toString() + ", " + numeroTiempo.getText() + "s");
                    lst.setAdapter(adapterLista);
                    numeroTiempo.setText("");
                }else {
                    mensajito("Debe ingresar un tiempo para el movimiento");
                }
            }
        });

        botonCrear = (Button) findViewById(R.id.button138);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!textoNombreRuta.getText().equals("")){
                   String tmpCadenaEnviar = textoNombreRuta.getText().toString() + "#";

                   for(String st : cadenaRuta){
                       tmpCadenaEnviar = tmpCadenaEnviar + st;
                   }
                   tmpCadenaEnviar = tmpCadenaEnviar + "#";

                   MyConexionBT.write(tmpCadenaEnviar);
                   datosLista.clear();
                   lst.setAdapter(adapterLista);
                   textoNombreRuta.setText("");
                   mensajito("Ruta creada");

               }else{
                   mensajito("Debe ingresar un nombre para la ruta");
               }
            }
        });

        botonCancelar = (Button) findViewById(R.id.button139);
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosLista.clear();
                cadenaRuta.clear();
                lst.setAdapter(adapterLista);
                mensajito("Creación de ruta cancelada");
            }
        });

        botonActualizar = (Button) findViewById(R.id.button147);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("y");
            }
        });

        botonVer = (Button) findViewById(R.id.button143);
        botonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("v");
            }
        });

        botonLimpiar = (Button) findViewById(R.id.button144);
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("l");
            }
        });
    }
}
