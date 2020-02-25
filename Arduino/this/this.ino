#include <EEPROM.h>

typedef struct{
  char nombre[6];
  byte moves[8][3] = {};    
}Ruta;

Ruta r;
//byte ruta[8][3] = {};
boolean firstChange = true;
boolean barriendo = false;
boolean lastBarrido =false;
boolean isName = false;
byte lastMili = 0;
char lastChar = 'p';
char actualChar;
byte contCharName = 0;
void setup() {
  Serial.begin(9600);
  Serial2.begin(38400);
}

void loop() {
  manualMode();
}

void manualMode(){
  if(Serial2.available() > 0){
    //Serial.println(Serial2.peek());
    actualChar = Serial2.peek();

    if(!isName){
    switch(Serial2.peek()){
      case 'w':
      Serial.println("Moviendose arriba");
      if(actualChar != lastChar){
        //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
        //MOVER ARRIBA
        Serial.println("Cambiando hacia arriba");
      }
      
      lastChar = actualChar;
      break;
      case 'a':
      Serial.println("Moviendose izquierda");
      if(actualChar != lastChar){
        //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
        //MOVER IZQUIERDA
        Serial.println("Cambiando hacia izquierda");
      }
      lastChar = actualChar;
      break;
      case 's':
      Serial.println("Moviendose abajo");
      if(actualChar != lastChar){
        //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
        //MOVER ABAJO
        Serial.println("Cambiando hacia abajo");
      }
      lastChar = actualChar;
      break;
      case 'd':
      Serial.println("Moviendose derecha");
      if(actualChar != lastChar){
        //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
        //MOVER DERECHA
        Serial.println("Cambiando hacia derecha");
      }
      lastChar = actualChar;
      break;
      case 'p':
      Serial.println("Parando");
      if(actualChar != lastChar){
        //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
        //PARAR
        Serial.println("Cambiando a parar");
      }
      lastChar = actualChar;
      break;
      case 'b':
      Serial.println("Estado barriendo");
      //ALMACENAR MOVIMIENTO
        saveMove(lastChar, (millis()/1000)-lastMili, barriendo?1:0);
        lastMili = millis()/1000;
      //CAMBIAR METODO
      barriendo = !barriendo;
      if(barriendo){
        //barrer
        Serial.println("Activando Escoba");
      }else{
        //detener barrida xd
        Serial.println("Deteniendo escoba");
      }
      break;
      case 'g':
      isName = true;
      //guardarEEPROM();
      break;
      default:
      Serial.println("Entro al default");
      break;
      
    }
    }else{
      if(contCharName < 4){
        r.nombre[contCharName] = actualChar;
        contCharName++;
        Serial.println(contCharName);  
      }else{
        r.nombre[contCharName] = actualChar;
        guardarEEPROM();
        limpiarRuta();
        contCharName = 0;
        isName = false;
      }
      
    }
    Serial2.read();
  }
}

void saveMove(char direccion, byte tiempo, byte escoba){
  if(firstChange){
    firstChange = false;
  }else{
    //Todo el corrimiento
    for(int i = 6; i >= 0; i--){
      r.moves[i+1][0] = r.moves[i][0];
      r.moves[i+1][1] = r.moves[i][1];
      r.moves[i+1][2] = r.moves[i][2];
    }
      r.moves[0][0] = direccion;
      r.moves[0][1] = tiempo;
      r.moves[0][2] = escoba;
    //debug
    //Serial.println("--------------GUARDANDO--------------");
    //Serial.print("Tiempo: ");
    //Serial.println(tiempo);
    //Serial.print("Direccion: ");
    //Serial.println(direccion);
    //Serial.print("Barriendo: ");
    //Serial.println(escoba);
    //Serial.println("-------------------------------------");
  }
}

//---------------------------------------------------------------------


void guardarEEPROM(){
  Serial.println("-------------------------------NOMBRE RUTA-----------------");
  Serial.println(r.nombre);
  int addr = getAddress();
  EEPROM.put(addr,r);
  limpiarRuta();
}

int getAddress(){
  Ruta r2;
  int i = 0;
  while(i < EEPROM.length()){
    EEPROM.get(i,r2);
    if(strcmp(r2.nombre,"") != 0){
      return i;
    }
    i++;
  }
  return 0;
}

void clearEEPROM(){
  for(char i = 0; i < EEPROM.length(); i++)
    EEPROM.write(i,0);
}

void limpiarRuta(){
  memset(r.nombre,0,sizeof(r.nombre));
}
