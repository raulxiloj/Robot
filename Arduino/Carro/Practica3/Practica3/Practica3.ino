/*
 Name:    Practica3.ino
 Created: 24/02/2020 16:41:42
 Author:  Javier
*/

// the setup function runs once when you press reset or power the board
#include "Carro.h"
#include <EEPROM.h>

Carro carro = Carro();

typedef struct {
    char nombre[6];
    byte moves[8][3] = {};
}Ruta;

Ruta r;
boolean firstChange = true;
boolean barriendo = false;
boolean lastBarrido = false;
boolean isName = false;
byte lastMili = 0;
char lastChar = 'p';
char actualChar;
byte contCharName = 0;
byte generalState = 2;

void setup() {
    randomSeed(analogRead(A5));
    carro.stop();
    Serial.begin(9600);
    Serial2.begin(38400);
}

byte state = 1;
unsigned long lastUpdated = 0;

void loop() {

  if(carro.getCurrMode() == Carro::AUTO_MODE){
    carro.updateAutomatic();
  }
  else{
    switch (generalState) {
    case 1://Automatico
        carro.currMode = Carro::MANUAL_MODE;
        break;
    case 2://Manual
      carro.updateManual();
        manualMode();
        break;
    case 3://Rutas
        rutaMode();
        break;
    default://Quien sabe beibi
        break;
    }
  }
}

void manualMode() {
    carro.updateManual();
    if (Serial2.available() > 0) {
        actualChar = Serial2.peek();

        if (!isName) {
            switch (Serial2.peek()) {
            case 'w':
                Serial.println("Moviendose arriba");
                if (actualChar != lastChar) {
                    //ALMACENAR MOVIMIENTO
                    saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                    lastMili = millis() / 1000;
                    carro.foward();
                    Serial.println("Cambiando hacia arriba");
                }

                lastChar = actualChar;
                break;
            case 'a':
                Serial.println("Moviendose izquierda");
                if (actualChar != lastChar) {
                    //ALMACENAR MOVIMIENTO
                    saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                    lastMili = millis() / 1000;
                    carro.turnLeft();
                    Serial.println("Cambiando hacia izquierda");
                }
                lastChar = actualChar;
                break;
            case 's':
                Serial.println("Moviendose abajo");
                if (actualChar != lastChar) {
                    //ALMACENAR MOVIMIENTO
                    saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                    lastMili = millis() / 1000;
                    carro.reverse();
                    Serial.println("Cambiando hacia abajo");
                }
                lastChar = actualChar;
                break;
            case 'd':
                Serial.println("Moviendose derecha");
                if (actualChar != lastChar) {
                    //ALMACENAR MOVIMIENTO
                    saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                    lastMili = millis() / 1000;
                    carro.turnRight();
                    Serial.println("Cambiando hacia derecha");
                }
                lastChar = actualChar;
                break;
            case 'p':
                Serial.println("Parando");
                if (actualChar != lastChar) {
                    //ALMACENAR MOVIMIENTO
                    saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                    lastMili = millis() / 1000;
                    carro.stop();
                    Serial.println("Cambiando a parar");
                }
                lastChar = actualChar;
                break;
            case 'b':
                Serial.println("Estado barriendo");
                //ALMACENAR MOVIMIENTO
                saveMove(lastChar, (millis() / 1000) - lastMili, barriendo ? 1 : 0);
                lastMili = millis() / 1000;
                //CAMBIAR METODO
                barriendo = !barriendo;
                carro.setBrush(barriendo);
                if (barriendo) {
                    //barrer
                    Serial.println("Activando Escoba");
                }
                else {
                    //detener barrida xd
                    Serial.println("Deteniendo escoba");
                }
                break;
            case 'g':
                isName = true;
                //guardarEEPROM();
                break;
            case 'r':
                Serial.println("Pantalla rutas");
                generalState = 3;
                break;
            default:
                Serial.println("Entro al default");
                break;
            }
        }
        else {
            if (contCharName < 4) {
                r.nombre[contCharName] = actualChar;
                contCharName++;
                Serial.println(contCharName);
            }
            else {
                r.nombre[contCharName] = actualChar;
                guardarEEPROM();
                limpiarNombre();
                contCharName = 0;
                isName = false;
            }

        }
        Serial2.read();
    }
}

void saveMove(char direccion, byte tiempo, byte escoba) {
    if (firstChange) {
        firstChange = false;
    }else {
        //Todo el corrimiento
        for (int i = 6; i >= 0; i--) {
            r.moves[i + 1][0] = r.moves[i][0];
            r.moves[i + 1][1] = r.moves[i][1];
            r.moves[i + 1][2] = r.moves[i][2];
        }
        r.moves[0][0] = direccion;
        r.moves[0][1] = tiempo;
        r.moves[0][2] = escoba;
    }
}


//------------------------EEPROM---------------------

void guardarEEPROM() {
  Ruta aux;
  int i = 0;
  int cont = 0;
  while(i < 90){
    EEPROM.get(i,aux);
    if(strcmp(aux.nombre,"")!=0)
      cont++;
    i += sizeof(Ruta);  
  }
  
  Serial.println("--------------Ruta Guardada------------");
  Serial.println(r.nombre);
  for(int p = 0; p < 7; p++){
      Serial.print("move ");
      Serial.print(p);
      Serial.print(" ");
      Serial.print(r.moves[p][0]);
      Serial.print(" ");
      Serial.print(r.moves[p][1]);
      Serial.print(" ");
      Serial.println(r.moves[p][2]);  
  }
  
  if(cont == 3){//3 max
    Ruta aux2;
    
    for(int x = 30 ; x >= 0; x -= sizeof(Ruta)){
      EEPROM.get(x,aux2);
      EEPROM.put(x+sizeof(Ruta),aux2);
    }
    EEPROM.put(0,r);
  }else{
    int addr = getAddress();
    EEPROM.put(addr, r);
    limpiarNombre();
  }
  
}

int getAddress() {
    Ruta r2;
    int i = 0;
    while (i < EEPROM.length()) {
        EEPROM.get(i, r2);
        if (strcmp(r2.nombre, "") == 0) {
            return i;
        }
        i += sizeof(Ruta);
    }
    return 0;
}

void clearEEPROM() {
  Serial.println("EEPROM borrada");
    for (int i = 0; i < 100; i++)
        EEPROM.write(i, 0);
}

void limpiarNombre() {
    memset(r.nombre, 0, sizeof(r.nombre));
}

void rutaMode() {
    String txt = Serial2.readString();
    if (txt.equals("r")){
       generalState = 2;
       Serial.println("Volviendo a pantalla manual");
    }else if (txt.equals("u")) {
        Serial.println("Sending data....");
        Serial.println(getRutas());
        Serial2.write(getRutas().c_str());
    }else if (txt.charAt(0) == 'v') {
        //buscar ruta c: compu porfa - sin bugs c;
        txt = txt.substring(1);
        Serial.println(txt);
        executeRuta(txt);
    }else if(txt.equals("c"))
      clearEEPROM();
    else if (txt.length() > 0) {
        Serial.println(txt);
        char p[100] = {};
        char p2[100] = {};
        byte cont = 1; //
        byte i = 0; //Indice
        char nombre[5] = {};
        
        strcpy(p, txt.c_str());
        strcpy(p2, txt.substring(6).c_str());
        
        strcpy(nombre, strtok(p, "#"));
        strcpy(r.nombre, nombre);
        
        String token = strtok(p2, ",");
        
        while (token != NULL) {
            switch (cont) {
            case 1:
                r.moves[i][0] = byte(token.charAt(0));
                cont++;
                break;
            case 2:
                r.moves[i][1] = byte(token.toInt());
                cont++;
                break;
            case 3:
                r.moves[i][2] = byte(token.toInt());
                cont = 1;
                i++;
                break;
            }
            token = strtok(NULL, ",");
        }
        guardarEEPROM();
    }
}

String getRutas() {
    String rutas = "";
    Ruta aux;
    int i = 0;
    while (i < 90) {
        EEPROM.get(i, aux);
        if (strcmp(aux.nombre, "") != 0) {
            rutas += aux.nombre;
            rutas += ",";
        }
        i += sizeof(Ruta);
    }
    rutas += "#";
    return rutas;
}

void executeRuta(String rutaName) {
    Ruta aux;
    int i = 0;

    while (i < 90) {
        EEPROM.get(i, aux);
        if (strcmp(aux.nombre, rutaName.c_str()) == 0) {
            Serial.println(aux.moves[0][0]);
            Serial.println(aux.moves[1][1]);
            Serial.println(aux.moves[3][0]);
            Serial.println(aux.moves[4][0]);
            Serial.println(aux.moves[5][0]);
            Serial.println(rutaName);
            executeInstructions(aux);
            break;
        }
        i += 30;
    }
    
}

void executeInstructions(Ruta ruta) {
  
  for (int i = 0; i < 7; i++) {
    Serial.print("movimiento " );
    Serial.println(ruta.moves[i][0]);
    switch (ruta.moves[i][0]) {
      case 0:
        Serial.println(ruta.moves[i][0]);
        Serial.println("Movimiento no registrado");
      break;
      case 97:
        Serial.println("izquierda");
        carro.turnLeft();
        carro.setBrush(ruta.moves[i][2]);
        delay(ruta.moves[i][1]*1000);
        carro.stop();
      break;
      case 115:
        Serial.println("atras");
        carro.reverse();
        carro.setBrush(ruta.moves[i][2]);
        delay(ruta.moves[i][1]*1000);
        carro.stop();
      break;
      case 100:
        Serial.println("derecha");
        carro.turnRight();
        carro.setBrush(ruta.moves[i][2]);
        delay(ruta.moves[i][1]*1000);
        carro.stop();
      break;
      case 119:
        Serial.println("adelante");
        carro.foward();
        carro.setBrush(ruta.moves[i][2]);
        delay(ruta.moves[i][1]*1000);
        carro.stop();
      break;
      default:
        Serial.println("Default del executeInstructions");
      break;
    }
  }
}




























///*
// Name:		Practica3.ino
// Created:	24/02/2020 16:41:42
// Author:	Javier
//*/
//
//// the setup function runs once when you press reset or power the board
//#include "Carro.h"
//
//Carro carro = Carro();
//
//void setup() {
//    randomSeed(analogRead(A5));
//    carro.stop();
//    Serial.begin(9600);
//}
//
//byte state = 1;
//unsigned long lastUpdated = 0;
//// the loop function runs over and over again until power down or reset
//void loop() {
//    /*if (state) {
//        Engine::foward();
//    }
//    else {
//        Engine::reverse();
//    }*/
//    //carro.setBrush(true);
//    state = !state;
//
//  byte mode =  carro.getCurrMode();
//    
//	if (mode == Carro::AUTO_MODE) {
//		carro.updateAutomatic();
//	}
//	else {
//        byte color = carro.colorSensor.debGetColor();//TODO PONERLO QUE SEA GET COLOR NORMAL Y QUITAR CODIGO PARA IMPRIMIR RESULTADO
//        Serial.print("color: ");
//        if (color == ColorSensor::BLUE) {
//            Serial.println("BLUE");
//        }
//        else if (color == ColorSensor::RED) {
//            Serial.println("RED");
//        }
//        else if (color == ColorSensor::BLACK) {
//            Serial.println("BLACK");
//        }
//        else if (color == ColorSensor::WHITE) {
//            Serial.println("WHITE");
//        }
//		carro.updateManual();
//	}
//
////Prints para debugging:
//  if(millis() - lastUpdated >= 0){
//    Serial.print("carro mode: ");
//    Serial.println(mode);
//    Serial.print("carro state: ");
//    Serial.println(carro.currAutoState);
//    Serial.print("frontal: ");
//    Serial.print(carro.usFront.getDistance());
//    Serial.print(". left: ");
//    Serial.print(carro.usLeft.getDistance());
//    Serial.print(". right: ");
//    Serial.println(carro.usRight.getDistance());
//    //Color lo da el mismo carro
//    lastUpdated = millis();
//  }
//    
//
//
//
//    //byte color = carro.colorSensor.getColor();
//   
//    /*if (color == carro.colorSensor.RED) {
//        Serial.println("RED");
//    }
//    if (color == carro.colorSensor.BLUE) {
//        Serial.println("BLUE");
//    }
//    else {
//        Serial.println("BLACK");
//    }*/
//
//    //delay(250);
//}
