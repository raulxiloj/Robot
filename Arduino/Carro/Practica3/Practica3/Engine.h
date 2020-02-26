// Engine.h

#ifndef _ENGINE_h
#define _ENGINE_h

#if defined(ARDUINO) && ARDUINO >= 100
	#include "arduino.h"
#else
	#include "WProgram.h"
#endif

namespace Engine 
{
	//Constantes
	namespace State 
	{
		const byte WAITING = 0;
		const byte MOVING_FOWARD = 1;
		const byte MOVING_REVERSE = 2;
		const byte TURING_LEFT= 3;
		const byte TURING_RIGHT = 4;
	}
	namespace Pin
	{
		const byte MOTOR_IZQ1 = 4;
		const byte MOTOR_IZQ2 = 5;
		const byte MOTOR_DER1 = 2;
		const byte MOTOR_DER2 = 3;
	}

	extern byte power;

	void init();

	void stop();
	void turnLeft();
	void turnRight();
	void foward();
	void reverse();

	byte getState();
}

#endif

