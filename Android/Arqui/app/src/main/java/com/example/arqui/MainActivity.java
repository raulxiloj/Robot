package com.example.arqui;

import android.app.Activity;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.w3c.dom.Text;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


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



    public  ArrayAdapter<String> adapterRutas;

Button botonVer;
    TextView verDisp;
    Button botonParar;
    Button botonAceptar;
    Button botonEnviar;

    TextView textoEnviado;

    ImageButton direccionIzquierda;
    ImageButton direccionDerecha;
    ImageButton direccionAdelante;
    ImageButton direccionAtras;

    Button botonDesconectar;

    Button botonBarrer;

    EditText numeroTiempo;
    CheckBox barriendo;
    Spinner sp;
    ListView lst;
    Button botonAgregar;
    Button botonCrear;

    Button botonLimpiar;
    EditText nombreEnviar;
    public ArrayList<String> listaRutas = new ArrayList<String>();
    public String varConcat = "";
    Button botonActualizar;
    Spinner spRutas;

    public void act(){
        adapterRutas= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listaRutas);
        spRutas.setAdapter(adapterRutas);
        Button botonLimpiar;

    }
    public void mensajito(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.BOTTOM, 0, 0);
        toast1.show();
    }

    public void iniciarSentido() {
        direccionIzquierda.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
        direccionDerecha.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
        direccionAdelante.getBackground().setColorFilter(Color.parseColor("#FFF7CDE5"), PorterDuff.Mode.DARKEN);
        direccionAtras.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }


    @Override
    public void onResume() {
        super.onResume();
        //Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        address = getIntent().getExtras().getString("EXTRA_DEVICE_ADDRESS");
        //intent.getStringExtra("EXTRA_DEVICE_ADDRESS");//<-<- PARTE A MODIFICAR >->->
        //Setea la direccion MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try {
            btSocket.connect();
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
            }
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        try { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e2) {
        }
    }

    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {

        if (btAdapter == null) {
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
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
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
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verDisp = (TextView) findViewById(R.id.textView12);
        try {
            verDisp.setText(getIntent().getExtras().getString("EXTRA_DEVICE_NOMBRE"));
        } catch (Exception ex) {
            verDisp.setText("No conectado");
        }
        //---------------------------------------------------------------------
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");
                    listaRutas.clear();
                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        mensajito(dataInPrint);
                        String [] vector = dataInPrint.split(",");
                       for(int i = 0; i < vector.length; i++){
                           listaRutas.add(vector[i]);
                       }
                       act();
                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        botonActualizar = (Button) findViewById(R.id.button145);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("r");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyConexionBT.write("u");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyConexionBT.write("r");
            }
        });


        lst = (ListView) findViewById(R.id.lstVerM);
        final ArrayList<String> cadenaRuta = new ArrayList<String>();
        final ArrayList<String> datosLista = new ArrayList<String>();
        final ArrayAdapter<String> adapterLista = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datosLista);
        lst.setAdapter(adapterLista);

        numeroTiempo = (EditText) findViewById(R.id.editText6);

        sp = (Spinner) findViewById(R.id.spinner3);
        final String[] datos = new String[] {"Izquierda", "Derecha", "Adelante", "Atras"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, datos);
        sp.setAdapter(adapter);


        barriendo = (CheckBox) findViewById(R.id.checkBox3);
        botonAgregar = (Button) findViewById(R.id.button141);

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * Como los controles de jugar en el teclado de la compu
                 * Validar tiempo > 0
                 * */
                int b = 0;
                String b2 = "";
                if(barriendo.isChecked()){
                    b = 1;
                    b2 = ", Barriendo";
                }
                if(numeroTiempo.getText().length() > 0){
                    switch (sp.getSelectedItem().toString()){
                        case "Izquierda":
                            cadenaRuta.add("a," + numeroTiempo.getText() + "," + b + ",");
                            break;
                        case "Adelante":
                            cadenaRuta.add("w," + numeroTiempo.getText() + ","+ b + ",");
                            break;
                        case "Atras":
                            cadenaRuta.add("s," + numeroTiempo.getText() + "," + b + ",");
                            break;
                        case "Derecha":
                            cadenaRuta.add("d," + numeroTiempo.getText() + "," + b + ",");
                            break;
                    }
                    datosLista.add((datosLista.size()+1) + "- " + sp.getSelectedItem().toString() + ", " + numeroTiempo.getText() + "s" + b2);
                    lst.setAdapter(adapterLista);
                    numeroTiempo.setText("");
                }else {
                    mensajito("Debe ingresar un tiempo válido para el movimiento");
                }
            }
        });
        botonCrear = (Button) findViewById(R.id.button148);
        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreEnviar.getText().length() > 0){
                    if(nombreEnviar.getText().length() <=5){
                        if(cadenaRuta.size() >= 3){
                            if(cadenaRuta.size() <= 8){
                                /*
                                 * Completando la cadena
                                 * */
                                String textoCompleto = nombreEnviar.getText().toString();
                                if(textoCompleto.length() < 5){
                                    int espacios = 5 - textoCompleto.length();
                                    for(int i = 0; i < espacios; i++){
                                        textoCompleto = textoCompleto + " ";
                                    }
                                }
                                String tmpCadenaEnviar = textoCompleto + "#";
                                /*
                                 * Fin de completando la cadena
                                 * */
                                for(String st : cadenaRuta){
                                    tmpCadenaEnviar = tmpCadenaEnviar + st;
                                }
                                MyConexionBT.write("r");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                MyConexionBT.write(tmpCadenaEnviar);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                MyConexionBT.write("r");

                                datosLista.clear();
                                lst.setAdapter(adapterLista);
                                cadenaRuta.clear();
                                nombreEnviar.setText("");
                                mensajito("Ruta creada");
                            }else{
                                mensajito("Solo se permite ingresar un maximo de 8 movimientos");
                            }
                        }else{
                            mensajito("Se debe ingresar al menos 3 movimientos");
                        }
                    }else{
                        mensajito("El maximo de caracteres para el nombre es 5");
                    }
                }else{
                    mensajito("Debe ingresar un nombre para la ruta");
                }
            }
        });

        botonParar = (Button) findViewById(R.id.button88);
        botonParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("p");
                mensajito("Parar");
            }
        });

        nombreEnviar = (EditText) findViewById(R.id.editText4);
        botonAceptar = (Button) findViewById(R.id.button71);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombreEnviar.getText().length() > 0){
                    if(nombreEnviar.getText().length() <= 5){
                        String textoCompleto = nombreEnviar.getText().toString();
                        if(textoCompleto.length() < 5){
                            int espacios = 5 - textoCompleto.length();
                            for(int i = 0; i < espacios; i++){
                                textoCompleto = textoCompleto + " ";
                            }
                        }
                        //mensajito(textoCompleto);
                        MyConexionBT.write("g");
                        MyConexionBT.write(textoCompleto);
                        //esperar confirmacion
                        nombreEnviar.setText("");
                        mensajito("Ruta guardada");
                    }else{
                        mensajito("El máximo de caracteres para el nombre es 5");
                    }
                }else{
                    mensajito("Debe ingresar un nombre para la ruta");
                }
            }
        });

        botonDesconectar = (Button) findViewById(R.id.button70);
        botonBarrer = (Button) findViewById(R.id.button3);

        botonEnviar = (Button) findViewById(R.id.button);
        textoEnviado = (TextView) findViewById(R.id.editText);

        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textoEnviado.getText().length() <= 20) {
                    if (textoEnviado.getText().length() > 0) {
                        String enviarTxt = textoEnviado.getText().toString();
                        MyConexionBT.write(enviarTxt);
                        mensajito("Texto enviado");
                        textoEnviado.setText("");
                    } else {
                        mensajito("El texto no puede estar vacío");
                    }
                }
            }
        });

        direccionIzquierda = (ImageButton) findViewById(R.id.imageButton4);
        direccionIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccionIzquierda.getBackground().setColorFilter(Color.parseColor("#FFF7CDE5"), PorterDuff.Mode.DARKEN);
                direccionDerecha.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAdelante.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAtras.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                MyConexionBT.write("a");
                mensajito("Activado:\nDirección Izquierda");
            }
        });
        direccionDerecha = (ImageButton) findViewById(R.id.imageButton6);
        direccionDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccionIzquierda.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionDerecha.getBackground().setColorFilter(Color.parseColor("#FFF7CDE5"), PorterDuff.Mode.DARKEN);
                direccionAdelante.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAtras.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                MyConexionBT.write("d");
                mensajito("Activado:\nDirección Derecha");
            }
        });

        direccionAdelante = (ImageButton) findViewById(R.id.imageButton2);
        direccionAdelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccionIzquierda.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionDerecha.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAdelante.getBackground().setColorFilter(Color.parseColor("#FFF7CDE5"), PorterDuff.Mode.DARKEN);
                direccionAtras.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                MyConexionBT.write("w");
                mensajito("Activado:\nDirección Adelante");
            }
        });

        direccionAtras = (ImageButton) findViewById(R.id.imageButton);
        direccionAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direccionIzquierda.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionDerecha.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAdelante.getBackground().setColorFilter(Color.parseColor("#D7DBDD"), PorterDuff.Mode.DARKEN);
                direccionAtras.getBackground().setColorFilter(Color.parseColor("#FFF7CDE5"), PorterDuff.Mode.DARKEN);
                MyConexionBT.write("s");
                mensajito("Activado:\nDirección Atrás");
            }
        });

        iniciarSentido();

        botonDesconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.close();
                    } catch (IOException e) {
                        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                        ;
                    }
                }
                mensajito("Desconectado");
                finish();
                Intent intent = new Intent(MainActivity.this, Portada.class);
                startActivity(intent);
            }
        });

        botonBarrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("b");
                mensajito("Barrer");

            }
        });


        botonLimpiar = (Button) findViewById(R.id.button149);
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("r");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyConexionBT.write("c");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyConexionBT.write("r");
            }
        });


        botonVer = (Button) findViewById(R.id.button150);
        botonVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("r");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mensajito("Ruta seleccionada "+ spRutas.getSelectedItem().toString());
                MyConexionBT.write("v"+ spRutas.getSelectedItem().toString());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MyConexionBT.write("r");
            }
        });

        spRutas = (Spinner) findViewById(R.id.spinner4);

    }
}
