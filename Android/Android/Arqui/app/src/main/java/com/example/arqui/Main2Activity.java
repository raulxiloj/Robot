package com.example.arqui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;

public class Main2Activity extends AppCompatActivity {


    int[][] matriz = new int[8][16];
    Button[][] matrizBotones = new Button[8][16];
    int iCoordenada = 0;
    int jCoordenada = 0;
    EditText nombreFigura;
    String[] nombres = new String[25];
    String nombreArchivo = "g6.txt";

    //1 - fila de botones
    Button boton00;
    Button boton01;
    Button boton02;
    Button boton03;
    Button boton04;
    Button boton05;
    Button boton06;
    Button boton07;
    Button boton08;
    Button boton09;
    Button boton010;
    Button boton011;
    Button boton012;
    Button boton013;
    Button boton014;
    Button boton015;

    //2 - fila de botones
    Button boton10;
    Button boton11;
    Button boton12;
    Button boton13;
    Button boton14;
    Button boton15;
    Button boton16;
    Button boton17;
    Button boton18;
    Button boton19;
    Button boton110;
    Button boton111;
    Button boton112;
    Button boton113;
    Button boton114;
    Button boton115;

    //3 - fila de botones
    Button boton20;
    Button boton21;
    Button boton22;
    Button boton23;
    Button boton24;
    Button boton25;
    Button boton26;
    Button boton27;
    Button boton28;
    Button boton29;
    Button boton210;
    Button boton211;
    Button boton212;
    Button boton213;
    Button boton214;
    Button boton215;

    //4 - fila de botones
    Button boton30;
    Button boton31;
    Button boton32;
    Button boton33;
    Button boton34;
    Button boton35;
    Button boton36;
    Button boton37;
    Button boton38;
    Button boton39;
    Button boton310;
    Button boton311;
    Button boton312;
    Button boton313;
    Button boton314;
    Button boton315;

    //5 - fila de botones
    Button boton40;
    Button boton41;
    Button boton42;
    Button boton43;
    Button boton44;
    Button boton45;
    Button boton46;
    Button boton47;
    Button boton48;
    Button boton49;
    Button boton410;
    Button boton411;
    Button boton412;
    Button boton413;
    Button boton414;
    Button boton415;
    //6 - fila de botones
    Button boton50;
    Button boton51;
    Button boton52;
    Button boton53;
    Button boton54;
    Button boton55;
    Button boton56;
    Button boton57;
    Button boton58;
    Button boton59;
    Button boton510;
    Button boton511;
    Button boton512;
    Button boton513;
    Button boton514;
    Button boton515;
    //7 - fila de botones
    Button boton60;
    Button boton61;
    Button boton62;
    Button boton63;
    Button boton64;
    Button boton65;
    Button boton66;
    Button boton67;
    Button boton68;
    Button boton69;
    Button boton610;
    Button boton611;
    Button boton612;
    Button boton613;
    Button boton614;
    Button boton615;
    //8 - fila de botones
    Button boton70;
    Button boton71;
    Button boton72;
    Button boton73;
    Button boton74;
    Button boton75;
    Button boton76;
    Button boton77;
    Button boton78;
    Button boton79;
    Button boton710;
    Button boton711;
    Button boton712;
    Button boton713;
    Button boton714;
    Button boton715;
    Button botonGrabar;
    String cadena;
    int x = 0;


    public void crearTablero() {
        //primera fila
        boton00 = (Button) findViewById(R.id.button5);
        boton01 = (Button) findViewById(R.id.button6);
        boton02 = (Button) findViewById(R.id.button7);
        boton03 = (Button) findViewById(R.id.button8);
        boton04 = (Button) findViewById(R.id.button9);
        boton05 = (Button) findViewById(R.id.button10);
        boton06 = (Button) findViewById(R.id.button11);
        boton07 = (Button) findViewById(R.id.button12);
        boton08 = (Button) findViewById(R.id.button13);
        boton09 = (Button) findViewById(R.id.button14);
        boton010 = (Button) findViewById(R.id.button15);
        boton011 = (Button) findViewById(R.id.button16);
        boton012 = (Button) findViewById(R.id.button17);
        boton013 = (Button) findViewById(R.id.button18);
        boton014 = (Button) findViewById(R.id.button19);
        boton015 = (Button) findViewById(R.id.button20);
        //segunda fila
        boton10 = (Button) findViewById(R.id.button22);
        boton11 = (Button) findViewById(R.id.button23);
        boton12 = (Button) findViewById(R.id.button24);
        boton13 = (Button) findViewById(R.id.button25);
        boton14 = (Button) findViewById(R.id.button26);
        boton15 = (Button) findViewById(R.id.button27);
        boton16 = (Button) findViewById(R.id.button28);
        boton17 = (Button) findViewById(R.id.button29);
        boton18 = (Button) findViewById(R.id.button30);
        boton19 = (Button) findViewById(R.id.button31);
        boton110 = (Button) findViewById(R.id.button32);
        boton111 = (Button) findViewById(R.id.button33);
        boton112 = (Button) findViewById(R.id.button34);
        boton113 = (Button) findViewById(R.id.button35);
        boton114 = (Button) findViewById(R.id.button36);
        boton115 = (Button) findViewById(R.id.button37);
        //tercera fila
        boton20 = (Button) findViewById(R.id.button38);
        boton21 = (Button) findViewById(R.id.button39);
        boton22 = (Button) findViewById(R.id.button40);
        boton23 = (Button) findViewById(R.id.button41);
        boton24 = (Button) findViewById(R.id.button42);
        boton25 = (Button) findViewById(R.id.button43);
        boton26 = (Button) findViewById(R.id.button44);
        boton27 = (Button) findViewById(R.id.button45);
        boton28 = (Button) findViewById(R.id.button46);
        boton29 = (Button) findViewById(R.id.button47);
        boton210 = (Button) findViewById(R.id.button48);
        boton211 = (Button) findViewById(R.id.button49);
        boton212 = (Button) findViewById(R.id.button50);
        boton213 = (Button) findViewById(R.id.button51);
        boton214 = (Button) findViewById(R.id.button52);
        boton215 = (Button) findViewById(R.id.button53);
        //cuarta fila
        boton30 = (Button) findViewById(R.id.button54);
        boton31 = (Button) findViewById(R.id.button55);
        boton32 = (Button) findViewById(R.id.button56);
        boton33 = (Button) findViewById(R.id.button57);
        boton34 = (Button) findViewById(R.id.button58);
        boton35 = (Button) findViewById(R.id.button59);
        boton36 = (Button) findViewById(R.id.button60);
        boton37 = (Button) findViewById(R.id.button61);
        boton38 = (Button) findViewById(R.id.button62);
        boton39 = (Button) findViewById(R.id.button63);
        boton310 = (Button) findViewById(R.id.button64);
        boton311 = (Button) findViewById(R.id.button65);
        boton312 = (Button) findViewById(R.id.button66);
        boton313 = (Button) findViewById(R.id.button67);
        boton314 = (Button) findViewById(R.id.button68);
        boton315 = (Button) findViewById(R.id.button69);
        //quinta fila
        boton40 = (Button) findViewById(R.id.button72);
        boton41 = (Button) findViewById(R.id.button73);
        boton42 = (Button) findViewById(R.id.button74);
        boton43 = (Button) findViewById(R.id.button75);
        boton44 = (Button) findViewById(R.id.button76);
        boton45 = (Button) findViewById(R.id.button77);
        boton46 = (Button) findViewById(R.id.button78);
        boton47 = (Button) findViewById(R.id.button79);
        boton48 = (Button) findViewById(R.id.button80);
        boton49 = (Button) findViewById(R.id.button81);
        boton410 = (Button) findViewById(R.id.button82);
        boton411 = (Button) findViewById(R.id.button83);
        boton412 = (Button) findViewById(R.id.button84);
        boton413 = (Button) findViewById(R.id.button85);
        boton414 = (Button) findViewById(R.id.button86);
        boton415 = (Button) findViewById(R.id.button87);
        //sexta fila
        boton50 = (Button) findViewById(R.id.button89);
        boton51 = (Button) findViewById(R.id.button90);
        boton52 = (Button) findViewById(R.id.button91);
        boton53 = (Button) findViewById(R.id.button92);
        boton54 = (Button) findViewById(R.id.button93);
        boton55 = (Button) findViewById(R.id.button94);
        boton56 = (Button) findViewById(R.id.button95);
        boton57 = (Button) findViewById(R.id.button96);
        boton58 = (Button) findViewById(R.id.button97);
        boton59 = (Button) findViewById(R.id.button98);
        boton510 = (Button) findViewById(R.id.button99);
        boton511 = (Button) findViewById(R.id.button100);
        boton512 = (Button) findViewById(R.id.button101);
        boton513 = (Button) findViewById(R.id.button102);
        boton514 = (Button) findViewById(R.id.button103);
        boton515 = (Button) findViewById(R.id.button104);
        //septima fila
        boton60 = (Button) findViewById(R.id.button105);
        boton61 = (Button) findViewById(R.id.button106);
        boton62 = (Button) findViewById(R.id.button107);
        boton63 = (Button) findViewById(R.id.button108);
        boton64 = (Button) findViewById(R.id.button109);
        boton65 = (Button) findViewById(R.id.button110);
        boton66 = (Button) findViewById(R.id.button111);
        boton67 = (Button) findViewById(R.id.button112);
        boton68 = (Button) findViewById(R.id.button113);
        boton69 = (Button) findViewById(R.id.button114);
        boton610 = (Button) findViewById(R.id.button115);
        boton611 = (Button) findViewById(R.id.button116);
        boton612 = (Button) findViewById(R.id.button117);
        boton613 = (Button) findViewById(R.id.button118);
        boton614 = (Button) findViewById(R.id.button119);
        boton615 = (Button) findViewById(R.id.button120);
        //octava fila
        boton70 = (Button) findViewById(R.id.button121);
        boton71 = (Button) findViewById(R.id.button122);
        boton72 = (Button) findViewById(R.id.button123);
        boton73 = (Button) findViewById(R.id.button124);
        boton74 = (Button) findViewById(R.id.button125);
        boton75 = (Button) findViewById(R.id.button126);
        boton76 = (Button) findViewById(R.id.button127);
        boton77 = (Button) findViewById(R.id.button128);
        boton78 = (Button) findViewById(R.id.button129);
        boton79 = (Button) findViewById(R.id.button130);
        boton710 = (Button) findViewById(R.id.button131);
        boton711 = (Button) findViewById(R.id.button132);
        boton712 = (Button) findViewById(R.id.button133);
        boton713 = (Button) findViewById(R.id.button134);
        boton714 = (Button) findViewById(R.id.button135);
        boton715 = (Button) findViewById(R.id.button136);


        //primera fila
        matrizBotones[0][0] = boton00;
        matrizBotones[0][1] = boton01;
        matrizBotones[0][2] = boton02;
        matrizBotones[0][3] = boton03;
        matrizBotones[0][4] = boton04;
        matrizBotones[0][5] = boton05;
        matrizBotones[0][6] = boton06;
        matrizBotones[0][7] = boton07;
        matrizBotones[0][8] = boton08;
        matrizBotones[0][9] = boton09;
        matrizBotones[0][10] = boton010;
        matrizBotones[0][11] = boton011;
        matrizBotones[0][12] = boton012;
        matrizBotones[0][13] = boton013;
        matrizBotones[0][14] = boton014;
        matrizBotones[0][15] = boton015;
        //segunda fila
        matrizBotones[1][0] = boton10;
        matrizBotones[1][1] = boton11;
        matrizBotones[1][2] = boton12;
        matrizBotones[1][3] = boton13;
        matrizBotones[1][4] = boton14;
        matrizBotones[1][5] = boton15;
        matrizBotones[1][6] = boton16;
        matrizBotones[1][7] = boton17;
        matrizBotones[1][8] = boton18;
        matrizBotones[1][9] = boton19;
        matrizBotones[1][10] = boton110;
        matrizBotones[1][11] = boton111;
        matrizBotones[1][12] = boton112;
        matrizBotones[1][13] = boton113;
        matrizBotones[1][14] = boton114;
        matrizBotones[1][15] = boton115;
        //tercera fila
        matrizBotones[2][0] = boton20;
        matrizBotones[2][1] = boton21;
        matrizBotones[2][2] = boton22;
        matrizBotones[2][3] = boton23;
        matrizBotones[2][4] = boton24;
        matrizBotones[2][5] = boton25;
        matrizBotones[2][6] = boton26;
        matrizBotones[2][7] = boton27;
        matrizBotones[2][8] = boton28;
        matrizBotones[2][9] = boton29;
        matrizBotones[2][10] = boton210;
        matrizBotones[2][11] = boton211;
        matrizBotones[2][12] = boton212;
        matrizBotones[2][13] = boton213;
        matrizBotones[2][14] = boton214;
        matrizBotones[2][15] = boton215;
        //cuarta fila
        matrizBotones[3][0] = boton30;
        matrizBotones[3][1] = boton31;
        matrizBotones[3][2] = boton32;
        matrizBotones[3][3] = boton33;
        matrizBotones[3][4] = boton34;
        matrizBotones[3][5] = boton35;
        matrizBotones[3][6] = boton36;
        matrizBotones[3][7] = boton37;
        matrizBotones[3][8] = boton38;
        matrizBotones[3][9] = boton39;
        matrizBotones[3][10] = boton310;
        matrizBotones[3][11] = boton311;
        matrizBotones[3][12] = boton312;
        matrizBotones[3][13] = boton313;
        matrizBotones[3][14] = boton314;
        matrizBotones[3][15] = boton315;
        //quinta fila
        matrizBotones[4][0] = boton40;
        matrizBotones[4][1] = boton41;
        matrizBotones[4][2] = boton42;
        matrizBotones[4][3] = boton43;
        matrizBotones[4][4] = boton44;
        matrizBotones[4][5] = boton45;
        matrizBotones[4][6] = boton46;
        matrizBotones[4][7] = boton47;
        matrizBotones[4][8] = boton48;
        matrizBotones[4][9] = boton49;
        matrizBotones[4][10] = boton410;
        matrizBotones[4][11] = boton411;
        matrizBotones[4][12] = boton412;
        matrizBotones[4][13] = boton413;
        matrizBotones[4][14] = boton414;
        matrizBotones[4][15] = boton415;
        //sexta fila
        matrizBotones[5][0] = boton50;
        matrizBotones[5][1] = boton51;
        matrizBotones[5][2] = boton52;
        matrizBotones[5][3] = boton53;
        matrizBotones[5][4] = boton54;
        matrizBotones[5][5] = boton55;
        matrizBotones[5][6] = boton56;
        matrizBotones[5][7] = boton57;
        matrizBotones[5][8] = boton58;
        matrizBotones[5][9] = boton59;
        matrizBotones[5][10] = boton510;
        matrizBotones[5][11] = boton511;
        matrizBotones[5][12] = boton512;
        matrizBotones[5][13] = boton513;
        matrizBotones[5][14] = boton514;
        matrizBotones[5][15] = boton515;
        //septima fila
        matrizBotones[6][0] = boton60;
        matrizBotones[6][1] = boton61;
        matrizBotones[6][2] = boton62;
        matrizBotones[6][3] = boton63;
        matrizBotones[6][4] = boton64;
        matrizBotones[6][5] = boton65;
        matrizBotones[6][6] = boton66;
        matrizBotones[6][7] = boton67;
        matrizBotones[6][8] = boton68;
        matrizBotones[6][9] = boton69;
        matrizBotones[6][10] = boton610;
        matrizBotones[6][11] = boton611;
        matrizBotones[6][12] = boton612;
        matrizBotones[6][13] = boton613;
        matrizBotones[6][14] = boton614;
        matrizBotones[6][15] = boton615;
        //octava fila
        matrizBotones[7][0] = boton70;
        matrizBotones[7][1] = boton71;
        matrizBotones[7][2] = boton72;
        matrizBotones[7][3] = boton73;
        matrizBotones[7][4] = boton74;
        matrizBotones[7][5] = boton75;
        matrizBotones[7][6] = boton76;
        matrizBotones[7][7] = boton77;
        matrizBotones[7][8] = boton78;
        matrizBotones[7][9] = boton79;
        matrizBotones[7][10] = boton710;
        matrizBotones[7][11] = boton711;
        matrizBotones[7][12] = boton712;
        matrizBotones[7][13] = boton713;
        matrizBotones[7][14] = boton714;
        matrizBotones[7][15] = boton715;


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {
                if (matrizBotones[i][j] != null) {
                    Button btnTmp = matrizBotones[i][j];
                    btnTmp.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }
            }
        }

    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }

    public void grabar(String texto) {
        try {
            OutputStreamWriter archivo = null;
            try {
                archivo = new OutputStreamWriter(openFileOutput(
                        nombreArchivo, MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                archivo.write(texto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                archivo.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            archivo.close();
        } catch (IOException e) {
        }
        mensajito("Guardado con éxito");
        finish();
    }

    public void mensajito(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER, 0, 0);
        toast1.show();
    }

    public void crearMetodos0() {
        boton00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][0] == 0) {
                    matriz[0][0] = 1;
                    boton00.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][0] = 0;
                    boton00.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][1] == 0) {
                    matriz[0][1] = 1;
                    boton01.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][1] = 0;
                    boton01.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][2] == 0) {
                    matriz[0][2] = 1;
                    boton02.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][2] = 0;
                    boton02.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][3] == 0) {
                    matriz[0][3] = 1;
                    boton03.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][3] = 0;
                    boton03.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][4] == 0) {
                    matriz[0][4] = 1;
                    boton04.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][4] = 0;
                    boton04.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][5] == 0) {
                    matriz[0][5] = 1;
                    boton05.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][5] = 0;
                    boton05.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][6] == 0) {
                    matriz[0][6] = 1;
                    boton06.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][6] = 0;
                    boton06.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][7] == 0) {
                    matriz[0][7] = 1;
                    boton07.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][7] = 0;
                    boton07.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][8] == 0) {
                    matriz[0][8] = 1;
                    boton08.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][8] = 0;
                    boton08.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][9] == 0) {
                    matriz[0][9] = 1;
                    boton09.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][9] = 0;
                    boton09.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton010.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][10] == 0) {
                    matriz[0][10] = 1;
                    boton010.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][10] = 0;
                    boton010.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton011.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][11] == 0) {
                    matriz[0][11] = 1;
                    boton011.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][11] = 0;
                    boton011.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][12] == 0) {
                    matriz[0][12] = 1;
                    boton012.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][12] = 0;
                    boton012.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton013.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][13] == 0) {
                    matriz[0][13] = 1;
                    boton013.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][13] = 0;
                    boton013.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton014.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][14] == 0) {
                    matriz[0][14] = 1;
                    boton014.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][14] = 0;
                    boton014.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton015.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[0][15] == 0) {
                    matriz[0][15] = 1;
                    boton015.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[0][15] = 0;
                    boton015.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos1() {
        boton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][0] == 0) {
                    matriz[1][0] = 1;
                    boton10.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][0] = 0;
                    boton10.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][1] == 0) {
                    matriz[1][1] = 1;
                    boton11.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][1] = 0;
                    boton11.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][2] == 0) {
                    matriz[1][2] = 1;
                    boton12.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][2] = 0;
                    boton12.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][3] == 0) {
                    matriz[1][3] = 1;
                    boton13.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][3] = 0;
                    boton13.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][4] == 0) {
                    matriz[1][4] = 1;
                    boton14.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][4] = 0;
                    boton14.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][5] == 0) {
                    matriz[1][5] = 1;
                    boton15.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][5] = 0;
                    boton15.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][6] == 0) {
                    matriz[1][6] = 1;
                    boton16.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][6] = 0;
                    boton16.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][7] == 0) {
                    matriz[1][7] = 1;
                    boton17.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][7] = 0;
                    boton17.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][8] == 0) {
                    matriz[1][8] = 1;
                    boton18.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][8] = 0;
                    boton18.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][9] == 0) {
                    matriz[1][9] = 1;
                    boton19.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][9] = 0;
                    boton19.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][10] == 0) {
                    matriz[1][10] = 1;
                    boton110.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][10] = 0;
                    boton110.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][11] == 0) {
                    matriz[1][11] = 1;
                    boton111.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][11] = 0;
                    boton111.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][12] == 0) {
                    matriz[1][12] = 1;
                    boton112.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][12] = 0;
                    boton112.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][13] == 0) {
                    matriz[1][13] = 1;
                    boton113.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][13] = 0;
                    boton113.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton114.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][14] == 0) {
                    matriz[1][14] = 1;
                    boton114.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][14] = 0;
                    boton114.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[1][15] == 0) {
                    matriz[1][15] = 1;
                    boton115.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[1][15] = 0;
                    boton115.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos2() {
        boton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][0] == 0) {
                    matriz[2][0] = 1;
                    boton20.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][0] = 0;
                    boton20.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][1] == 0) {
                    matriz[2][1] = 1;
                    boton21.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][1] = 0;
                    boton21.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][2] == 0) {
                    matriz[2][2] = 1;
                    boton22.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][2] = 0;
                    boton22.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][3] == 0) {
                    matriz[2][3] = 1;
                    boton23.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][3] = 0;
                    boton23.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][4] == 0) {
                    matriz[2][4] = 1;
                    boton24.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][4] = 0;
                    boton24.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][5] == 0) {
                    matriz[2][5] = 1;
                    boton25.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][5] = 0;
                    boton25.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][6] == 0) {
                    matriz[2][6] = 1;
                    boton26.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][6] = 0;
                    boton26.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][7] == 0) {
                    matriz[2][7] = 1;
                    boton27.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][7] = 0;
                    boton27.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][8] == 0) {
                    matriz[2][8] = 1;
                    boton28.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][8] = 0;
                    boton28.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][9] == 0) {
                    matriz[2][9] = 1;
                    boton29.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][9] = 0;
                    boton29.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton210.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][10] == 0) {
                    matriz[2][10] = 1;
                    boton210.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][10] = 0;
                    boton210.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton211.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][11] == 0) {
                    matriz[2][11] = 1;
                    boton211.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][11] = 0;
                    boton211.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton212.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][12] == 0) {
                    matriz[2][12] = 1;
                    boton212.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][12] = 0;
                    boton212.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton213.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][13] == 0) {
                    matriz[2][13] = 1;
                    boton213.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][13] = 0;
                    boton213.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton214.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][14] == 0) {
                    matriz[2][14] = 1;
                    boton214.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][14] = 0;
                    boton214.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton215.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[2][15] == 0) {
                    matriz[2][15] = 1;
                    boton215.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[2][15] = 0;
                    boton215.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos3() {
        boton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][0] == 0) {
                    matriz[3][0] = 1;
                    boton30.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][0] = 0;
                    boton30.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][1] == 0) {
                    matriz[3][1] = 1;
                    boton31.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][1] = 0;
                    boton31.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][2] == 0) {
                    matriz[3][2] = 1;
                    boton32.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][2] = 0;
                    boton32.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][3] == 0) {
                    matriz[3][3] = 1;
                    boton33.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][3] = 0;
                    boton33.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][4] == 0) {
                    matriz[3][4] = 1;
                    boton34.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][4] = 0;
                    boton34.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][5] == 0) {
                    matriz[3][5] = 1;
                    boton35.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][5] = 0;
                    boton35.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][6] == 0) {
                    matriz[3][6] = 1;
                    boton36.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][6] = 0;
                    boton36.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][7] == 0) {
                    matriz[3][7] = 1;
                    boton37.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][7] = 0;
                    boton37.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][8] == 0) {
                    matriz[3][8] = 1;
                    boton38.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][8] = 0;
                    boton38.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][9] == 0) {
                    matriz[3][9] = 1;
                    boton39.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][9] = 0;
                    boton39.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton310.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][10] == 0) {
                    matriz[3][10] = 1;
                    boton310.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][10] = 0;
                    boton310.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton311.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][11] == 0) {
                    matriz[3][11] = 1;
                    boton311.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][11] = 0;
                    boton311.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton312.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][12] == 0) {
                    matriz[3][12] = 1;
                    boton312.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][12] = 0;
                    boton312.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton313.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][13] == 0) {
                    matriz[3][13] = 1;
                    boton313.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][13] = 0;
                    boton313.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton314.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][14] == 0) {
                    matriz[3][14] = 1;
                    boton314.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][14] = 0;
                    boton314.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton315.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[3][15] == 0) {
                    matriz[3][15] = 1;
                    boton315.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[3][15] = 0;
                    boton315.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos4() {
        boton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][0] == 0) {
                    matriz[4][0] = 1;
                    boton40.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][0] = 0;
                    boton40.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][1] == 0) {
                    matriz[4][1] = 1;
                    boton41.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][1] = 0;
                    boton41.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][2] == 0) {
                    matriz[4][2] = 1;
                    boton42.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][2] = 0;
                    boton42.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][3] == 0) {
                    matriz[4][3] = 1;
                    boton43.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][3] = 0;
                    boton43.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][4] == 0) {
                    matriz[4][4] = 1;
                    boton44.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][4] = 0;
                    boton44.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][5] == 0) {
                    matriz[4][5] = 1;
                    boton45.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][5] = 0;
                    boton45.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][6] == 0) {
                    matriz[4][6] = 1;
                    boton46.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][6] = 0;
                    boton46.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][7] == 0) {
                    matriz[4][7] = 1;
                    boton47.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][7] = 0;
                    boton47.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton48.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][8] == 0) {
                    matriz[4][8] = 1;
                    boton48.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][8] = 0;
                    boton48.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton49.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][9] == 0) {
                    matriz[4][9] = 1;
                    boton49.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][9] = 0;
                    boton49.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton410.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][10] == 0) {
                    matriz[4][10] = 1;
                    boton410.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][10] = 0;
                    boton410.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton411.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][11] == 0) {
                    matriz[4][11] = 1;
                    boton411.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][11] = 0;
                    boton411.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton412.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][12] == 0) {
                    matriz[4][12] = 1;
                    boton412.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][12] = 0;
                    boton412.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton413.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][13] == 0) {
                    matriz[4][13] = 1;
                    boton413.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][13] = 0;
                    boton413.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton414.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][14] == 0) {
                    matriz[4][14] = 1;
                    boton414.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][14] = 0;
                    boton414.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton415.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[4][15] == 0) {
                    matriz[4][15] = 1;
                    boton415.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[4][15] = 0;
                    boton415.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos5() {
        boton50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][0] == 0) {
                    matriz[5][0] = 1;
                    boton50.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][0] = 0;
                    boton50.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][1] == 0) {
                    matriz[5][1] = 1;
                    boton51.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][1] = 0;
                    boton51.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][2] == 0) {
                    matriz[5][2] = 1;
                    boton52.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][2] = 0;
                    boton52.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton53.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][3] == 0) {
                    matriz[5][3] = 1;
                    boton53.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][3] = 0;
                    boton53.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][4] == 0) {
                    matriz[5][4] = 1;
                    boton54.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][4] = 0;
                    boton54.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][5] == 0) {
                    matriz[5][5] = 1;
                    boton55.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][5] = 0;
                    boton55.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][6] == 0) {
                    matriz[5][6] = 1;
                    boton56.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][6] = 0;
                    boton56.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][7] == 0) {
                    matriz[5][7] = 1;
                    boton57.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][7] = 0;
                    boton57.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][8] == 0) {
                    matriz[5][8] = 1;
                    boton58.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][8] = 0;
                    boton58.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][9] == 0) {
                    matriz[5][9] = 1;
                    boton59.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][9] = 0;
                    boton59.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton510.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][10] == 0) {
                    matriz[5][10] = 1;
                    boton510.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][10] = 0;
                    boton510.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton511.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][11] == 0) {
                    matriz[5][11] = 1;
                    boton511.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][11] = 0;
                    boton511.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton512.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][12] == 0) {
                    matriz[5][12] = 1;
                    boton512.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][12] = 0;
                    boton512.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton513.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][13] == 0) {
                    matriz[5][13] = 1;
                    boton513.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][13] = 0;
                    boton513.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton514.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][14] == 0) {
                    matriz[5][14] = 1;
                    boton514.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][14] = 0;
                    boton514.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton515.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[5][15] == 0) {
                    matriz[5][15] = 1;
                    boton515.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[5][15] = 0;
                    boton515.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos6() {
        boton60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][0] == 0) {
                    matriz[6][0] = 1;
                    boton60.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][0] = 0;
                    boton60.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][1] == 0) {
                    matriz[6][1] = 1;
                    boton61.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][1] = 0;
                    boton61.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][2] == 0) {
                    matriz[6][2] = 1;
                    boton62.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][2] = 0;
                    boton62.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][3] == 0) {
                    matriz[6][3] = 1;
                    boton63.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][3] = 0;
                    boton63.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][4] == 0) {
                    matriz[6][4] = 1;
                    boton64.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][4] = 0;
                    boton64.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][5] == 0) {
                    matriz[6][5] = 1;
                    boton65.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][5] = 0;
                    boton65.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton66.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][6] == 0) {
                    matriz[6][6] = 1;
                    boton66.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][6] = 0;
                    boton66.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton67.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][7] == 0) {
                    matriz[6][7] = 1;
                    boton67.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][7] = 0;
                    boton67.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton68.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][8] == 0) {
                    matriz[6][8] = 1;
                    boton68.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][8] = 0;
                    boton68.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton69.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][9] == 0) {
                    matriz[6][9] = 1;
                    boton69.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][9] = 0;
                    boton69.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton610.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][10] == 0) {
                    matriz[6][10] = 1;
                    boton610.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][10] = 0;
                    boton610.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton611.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][11] == 0) {
                    matriz[6][11] = 1;
                    boton611.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][11] = 0;
                    boton611.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton612.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][12] == 0) {
                    matriz[6][12] = 1;
                    boton612.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][12] = 0;
                    boton612.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton613.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][13] == 0) {
                    matriz[6][13] = 1;
                    boton613.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][13] = 0;
                    boton613.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton614.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][14] == 0) {
                    matriz[6][14] = 1;
                    boton614.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][14] = 0;
                    boton614.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton615.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[6][15] == 0) {
                    matriz[6][15] = 1;
                    boton615.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[6][15] = 0;
                    boton615.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public void crearMetodos7() {
        boton70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][0] == 0) {
                    matriz[7][0] = 1;
                    boton70.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][0] = 0;
                    boton70.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][1] == 0) {
                    matriz[7][1] = 1;
                    boton71.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][1] = 0;
                    boton71.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton72.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][2] == 0) {
                    matriz[7][2] = 1;
                    boton72.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][2] = 0;
                    boton72.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton73.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][3] == 0) {
                    matriz[7][3] = 1;
                    boton73.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][3] = 0;
                    boton73.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton74.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][4] == 0) {
                    matriz[7][4] = 1;
                    boton74.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][4] = 0;
                    boton74.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][5] == 0) {
                    matriz[7][5] = 1;
                    boton75.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][5] = 0;
                    boton75.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton76.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][6] == 0) {
                    matriz[7][6] = 1;
                    boton76.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][6] = 0;
                    boton76.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton77.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][7] == 0) {
                    matriz[7][7] = 1;
                    boton77.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][7] = 0;
                    boton77.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton78.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][8] == 0) {
                    matriz[7][8] = 1;
                    boton78.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][8] = 0;
                    boton78.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton79.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][9] == 0) {
                    matriz[7][9] = 1;
                    boton79.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][9] = 0;
                    boton79.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton710.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][10] == 0) {
                    matriz[7][10] = 1;
                    boton710.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][10] = 0;
                    boton710.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton711.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][11] == 0) {
                    matriz[7][11] = 1;
                    boton711.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][11] = 0;
                    boton711.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton712.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][12] == 0) {
                    matriz[7][12] = 1;
                    boton712.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][12] = 0;
                    boton712.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton713.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][13] == 0) {
                    matriz[7][13] = 1;
                    boton713.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][13] = 0;
                    boton713.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton714.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][14] == 0) {
                    matriz[7][14] = 1;
                    boton714.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][14] = 0;
                    boton714.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
        boton715.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (matriz[7][15] == 0) {
                    matriz[7][15] = 1;
                    boton715.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);
                } else {
                    matriz[7][15] = 0;
                    boton715.getBackground().setColorFilter(Color.parseColor("#030303"), PorterDuff.Mode.DARKEN);
                }

            }
        });
    }

    public int verificarNombre(String[] arreglo, String nombre, String cadena) {
        int llenando = 0;
        String nombreLlenar = "";
        for (int i = 0; i < cadena.toCharArray().length; i++) {
            if (cadena.toCharArray()[i] == '$') {
                if (llenando == 0) {
                    llenando = 1;
                } else if (llenando == 1) {
                    llenando = 0;
                    for (int b = 0; b < arreglo.length; b++) {
                        if (arreglo[b] == "") {
                            arreglo[b] = nombreLlenar;
                            nombreLlenar = "";
                        }
                    }
                }
            } else {
                if (llenando == 1) {
                    nombreLlenar = nombreLlenar + cadena.toCharArray()[i];
                }
            }
        }
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] != null) {
                if (arreglo[i].equals(nombre)) {
                    return 1;
                }
            }
        }
        return 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cadena = "";

        nombreFigura = (EditText) findViewById(R.id.editText3);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {
                matriz[i][j] = 0;
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 16; j++) {
                matrizBotones[i][j] = null;
            }
        }

        crearTablero();

        crearMetodos0();
        crearMetodos1();
        crearMetodos2();
        crearMetodos3();
        crearMetodos4();
        crearMetodos5();
        crearMetodos6();
        crearMetodos7();


        botonGrabar = (Button) findViewById(R.id.button2);
        botonGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //la cadena
                int bandera = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 16; j++) {
                        if (matriz[i][j] == 1) {
                            bandera = 1;
                            break;
                        }
                    }
                    if (bandera == 1) {
                        break;
                    }
                }

                if (bandera == 1) {
                    if (nombreFigura.getText().length() > 0) {
                        cadena = cadena + "#";
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 16; j++) {
                                cadena = cadena + matriz[i][j];
                            }
                        }
                        cadena = cadena + "#";
                        String[] archivos = fileList();
                        if (existe(archivos, nombreArchivo)) {
                            try {
                                InputStreamReader archivo = new InputStreamReader(
                                        openFileInput(nombreArchivo));
                                BufferedReader br = new BufferedReader(archivo);
                                String linea = br.readLine();
                                String todo = "";
                                while (linea != null) {
                                    todo = todo + linea;
                                    linea = br.readLine();
                                }
                                br.close();
                                archivo.close();

                                for (int i = 0; i < nombres.length; i++) {
                                    nombres[i] = "";
                                }
                                if (verificarNombre(nombres, nombreFigura.getText().toString(), todo) == 1) {
                                    mensajito("El nombre de la figura ya se encuentra registrado");
                                } else {
                                    String concat = todo;
                                    concat = concat + "$" + nombreFigura.getText().toString() + "$" + cadena;
                                    grabar(concat);
                                    cadena = "";
                                }

                            } catch (IOException e) {
                            }
                        } else {
                            String concat = "$" + nombreFigura.getText() + "$" + cadena;
                            grabar(concat);
                            cadena = "";
                        }
                    } else {
                        mensajito("Nombre de la figura vacío");
                    }
                } else {
                    mensajito("La figura está vacía");
                }
            }
        });


    }
}
