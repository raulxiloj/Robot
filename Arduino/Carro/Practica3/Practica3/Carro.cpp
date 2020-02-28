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
	currMode = AUTO_MODE;//TODO: en version release esto va a ser MANUAL_MODE por default
	currAutoState = S_FOWARD;
}

//Comportamiento no definido si currState no es un state valido (ver constantes S_ para ver los estados validos)
void Carro::updateAutomatic()
{
//	Serial.print("estado automatico: ");
//	Serial.println(currAutoState);
	updateButton();
	byte color = colorSensor.debGetColor();//TODO PONERLO QUE SEA GET COLOR NORMAL Y QUITAR CODIGO PARA IMPRIMIR RESULTADO
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
	};
	if (!wasPressed && isPressed || color == ColorSensor::BLUE)
	{
		Engine::stop();
		Brush::stop();
		currMode = MANUAL_MODE;
		currAutoState = S_FOWARD;
		return;
	}
	if (color == ColorSensor::BLACK && currAutoState != S_REVERSE) {
		Engine::stop();
		Brush::stop();
		currAutoState = S_REVERSE;
		lastUpdated = millis();
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
		//La primera direccion que va a tratar es aleatoria
		if (random(0, 2)) {//try right primero
			if (usRight.getDistance() > RIGHT_DISTANCE) {
				lastUpdated = millis();
				Engine::turnRight();
				currAutoState = S_RIGHT;
				return;
			}
			else if (usLeft.getDistance() > LEFT_DISTANCE) {
				lastUpdated = millis();
				Engine::turnLeft();
				currAutoState = S_LEFT;
				return;
			}
		}
		else {
			if (usLeft.getDistance() > LEFT_DISTANCE) {
				lastUpdated = millis();
				Engine::turnLeft();
				currAutoState = S_LEFT;
				return;
			}
			else if (usRight.getDistance() > RIGHT_DISTANCE) {
				lastUpdated = millis();
				Engine::turnRight();
				currAutoState = S_RIGHT;
				return;
			}
		}
		lastUpdated = millis();
		Engine::reverse();
		currAutoState = S_REVERSE;
		break;
	case S_LEFT:
		Engine::turnLeft();
		if (millis() - lastUpdated > TURNING_LEFT_TIME) {
			Engine::stop();
			currAutoState = S_FOWARD;
			return;
		}
		break;
	case S_RIGHT:
		Engine::turnRight();
		if (millis() - lastUpdated > TURNING_RIGHT_TIME) {
			Engine::stop();
			currAutoState = S_FOWARD;
			return;
		}
		break;
	case S_REVERSE:
		Engine::reverse();
		if (millis() - lastUpdated > MOVING_REVERSE_TIME) {
			Engine::stop();
			currAutoState = S_FRONTAL_COLLISION;
			return;
		}
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
	Engine::turnRight();
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
