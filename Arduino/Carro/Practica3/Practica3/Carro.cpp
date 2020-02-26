// 
// 
// 

#include "Carro.h"

void Carro::updateButton()
{
	wasPressed = isPressed;
	isPressed = !digitalRead(MODE_BTN_PIN);
}

Carro::Carro()
{ 
	Brush::init();
	Engine::init();
	pinMode(MODE_BTN_PIN, INPUT_PULLUP);
}

//Comportamiento no definido si currState no es un state valido (ver constantes S_ para ver los estados validos)
void Carro::updateAutomatic()
{
	Serial.print("estado automatico: ");
	Serial.println(currAutoState);
	updateButton();
	byte color = colorSensor.getColor();
	if (!wasPressed && isPressed || color == ColorSensor::BLUE)
	{
		currMode = MANUAL_MODE;
		currAutoState = S_FOWARD;
		return;
	}
	if (color == ColorSensor::BLACK) {
		currAutoState = S_REVERSE;
		return;
	}
	if (color == ColorSensor::RED) {
		Brush::start();
	}
	else {
		Brush::stop();
	}

	switch (currAutoState)
	{
	case S_FOWARD:
		foward();//Redundante porque se va a ejecutar cada vez que corra el estado y no cuando pasa al estado
		if (usFront.getDistance() < FRONTAL_DISTANCE) {
			Engine::stop();
			currAutoState = S_FRONTAL_COLLISION;
		}
		break;
	case S_FRONTAL_COLLISION:
		Engine::stop();
		break;
	case S_LEFT:

		break;
	case S_RIGHT:

		break;
	case S_REVERSE:

		break;
	}
}

void Carro::updateManual()
{
	updateButton();
	if (!wasPressed && isPressed)
	{
		currMode = AUTO_MODE;
		return;
	}

}

void Carro::setBrush(bool val)
{
	if (val) {
		Brush::start();
	}
	else {
		Brush::stop();
	}
}

void Carro::stop()
{
	Engine::stop();
}

void Carro::turnLeft()
{
	Engine::turnLeft();
}

void Carro::turnRight()
{
	Engine::turnRight;
}

void Carro::foward()
{
	Engine::foward();
}

void Carro::reverse()
{
	Engine::reverse();
}

byte Carro::getCurrMode()
{
	return currMode;
}
