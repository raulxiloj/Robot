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
	analogWrite(MOTOR_CONTROL_PIN, POWER);
}

void Brush::stop()
{
	digitalWrite(MOTOR_CONTROL_PIN, LOW);
}


