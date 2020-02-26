// 
// 
// 

#include "Engine.h"

using namespace Engine::Pin;
using namespace Engine::State;

extern byte Engine::power = 0x8f;//para debugging vamos a usar menos potencia

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
	analogWrite(MOTOR_IZQ2, power);

	digitalWrite(MOTOR_DER1, LOW);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::turnRight()
{
	currState = TURING_RIGHT;

	digitalWrite(MOTOR_IZQ1, LOW);
	digitalWrite(MOTOR_IZQ2, LOW);

	digitalWrite(MOTOR_DER1, LOW);
	analogWrite(MOTOR_DER2, power);
}

void Engine::foward()
{
	currState = MOVING_FOWARD;

	analogWrite(MOTOR_IZQ1, power);
	digitalWrite(MOTOR_IZQ2, LOW);
	
	analogWrite(MOTOR_DER1, power);
	digitalWrite(MOTOR_DER2, LOW);
}

void Engine::reverse()
{
	currState = MOVING_REVERSE;

	digitalWrite(MOTOR_IZQ1, LOW);
	analogWrite(MOTOR_IZQ2, power);

	digitalWrite(MOTOR_DER1, LOW);
	analogWrite(MOTOR_DER2, power);
}

byte Engine::getState()
{
	return currState;
}
