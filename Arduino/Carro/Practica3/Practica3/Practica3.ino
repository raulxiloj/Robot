/*
 Name:		Practica3.ino
 Created:	24/02/2020 16:41:42
 Author:	Javier
*/

// the setup function runs once when you press reset or power the board
#include "Carro.h"


Carro carro = Carro();

void setup() {
    carro.stop();
    Serial.begin(9600);
}

byte state = 1;
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
    
    carro.updateAutomatic();

    Serial.print("frontal: ");
    Serial.print(carro.usFront.getDistance());
    Serial.print(". left: ");
    Serial.print(carro.usLeft.getDistance());
    Serial.print(". right: ");
    Serial.println(carro.usRight.getDistance());



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
