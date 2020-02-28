/*
 Name:		Practica3.ino
 Created:	24/02/2020 16:41:42
 Author:	Javier
*/

// the setup function runs once when you press reset or power the board
#include "Carro.h"


Carro carro = Carro();

void setup() {
    randomSeed(analogRead(A5));
    carro.stop();
    Serial.begin(9600);
}

byte state = 1;
unsigned long lastUpdated = 0;
// the loop function runs over and over again until power down or reset
void loop() {
    /*if (state) {
        Engine::foward();
    }
    else {
        Engine::reverse();
    }*/
    //carro.setBrush(true);
    state = !state;

  byte mode =  carro.getCurrMode();
    
	if (mode == Carro::AUTO_MODE) {
		carro.updateAutomatic();
	}
	else {
        byte color = carro.colorSensor.debGetColor();//TODO PONERLO QUE SEA GET COLOR NORMAL Y QUITAR CODIGO PARA IMPRIMIR RESULTADO
        Serial.print("color: ");
        if (color == ColorSensor::BLUE) {
            Serial.println("BLUE");
        }
        else if (color == ColorSensor::RED) {
            Serial.println("RED");
        }
        else if (color == ColorSensor::BLACK) {
            Serial.println("BLACK");
        }
        else if (color == ColorSensor::WHITE) {
            Serial.println("WHITE");
        }
		carro.updateManual();
	}

//Prints para debugging:
  if(millis() - lastUpdated >= 0){
    Serial.print("carro mode: ");
    Serial.println(mode);
    Serial.print("carro state: ");
    Serial.println(carro.currAutoState);
    Serial.print("frontal: ");
    Serial.print(carro.usFront.getDistance());
    Serial.print(". left: ");
    Serial.print(carro.usLeft.getDistance());
    Serial.print(". right: ");
    Serial.println(carro.usRight.getDistance());
    //Color lo da el mismo carro
    lastUpdated = millis();
  }
    



    //byte color = carro.colorSensor.getColor();
   
    /*if (color == carro.colorSensor.RED) {
        Serial.println("RED");
    }
    if (color == carro.colorSensor.BLUE) {
        Serial.println("BLUE");
    }
    else {
        Serial.println("BLACK");
    }*/

    //delay(250);
}
