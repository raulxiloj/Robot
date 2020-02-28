// 
// 
// 

#include "Engine.h"

using namespace Engine::Pin;
using namespace Engine::State;

//Tienen que ser menos de la mitad de la potencia maxima para que las podamos multiplicar facilmente en turnLeft y turnRight
// [!!!] CUIDADO SI NO SON MENOS DE LA MITAD PUEDE DAR OVERFLOW!!!!!!!!!!!!!!!!!!!!!!!!!!
extern byte Engine::leftPower = 145;//para debugging vamos a usar menos potencia
extern byte Engine::rightPower = 145;

namespace {
	byte currState = Engine::State::WAITING;
}

void Engine::init()
{
	pinMode(MOTOR_IZQ1, OUTPUT);
	digitalWrite(MOTOR_IZQ1, LOW);
	pinMode(MOTOR_IZQ2, OUTPUT);
	digitalWrite(MOTOR_IZQ2, LOW);
	pinMode(MOTOR_DER1, OUTPUT);
	digitalWrite(MOTOR_DER1, LOW);
	pinMode(MOTOR_DER2, OUTPUT);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::stop()
{
	currState = WAITING;

	digitalWrite(MOTOR_IZQ1, LOW);
	digitalWrite(MOTOR_IZQ2, LOW);

	digitalWrite(MOTOR_DER1, LOW);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::turnLeft()
{
	currState = TURING_LEFT;

	digitalWrite(MOTOR_IZQ1, LOW);
	analogWrite(MOTOR_IZQ2, 0xff/*leftPower * 2*/);

	digitalWrite(MOTOR_DER1, LOW);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::turnRight()
{
	currState = TURING_RIGHT;

	digitalWrite(MOTOR_IZQ1, LOW);
	digitalWrite(MOTOR_IZQ2, LOW);

	digitalWrite(MOTOR_DER1, LOW);
	analogWrite(MOTOR_DER2, 0xff/*rightPower * 2*/);
}

void Engine::foward()
{
	currState = MOVING_FOWARD;

	analogWrite(MOTOR_IZQ1, leftPower);
	digitalWrite(MOTOR_IZQ2, LOW);
	
	analogWrite(MOTOR_DER1, rightPower);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::reverse()
{
	currState = MOVING_REVERSE;

	digitalWrite(MOTOR_IZQ1, LOW);
	analogWrite(MOTOR_IZQ2, leftPower);

	digitalWrite(MOTOR_DER1, LOW);
	analogWrite(MOTOR_DER2, rightPower);
}

byte Engine::getState()
{
	return currState;
}
