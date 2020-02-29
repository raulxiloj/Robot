// 
// 
// 

#include "Brush.h"

void Brush::init() 
{
	pinMode(MOTOR_CONTROL_PIN, OUTPUT);
	digitalWrite(MOTOR_CONTROL_PIN, LOW);
}

void Brush::start()
{
	//Serial.println("brush activo");
	analogWrite(MOTOR_CONTROL_PIN, POWER);
}

void Brush::stop()
{
  //Serial.println("brush apagado");
	digitalWrite(MOTOR_CONTROL_PIN, LOW);
}
