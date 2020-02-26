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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    Button botonRutas;


    EditText nombreEnviar;
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

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        //IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
        botonRutas = (Button) findViewById(R.id.button21);

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

        botonRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("r");
                Intent intent = new Intent(MainActivity.this, ActivityRutas.class);
                intent.putExtra("EXTRA_DEVICE_ADDRESS", address);
                intent.putExtra("EXTRA_DEVICE_NOMBRE", verDisp.getText().toString());
                startActivity(intent);
            }
        });

    }
}
