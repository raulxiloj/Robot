// Brush.h

#ifndef _BRUSH_h
#define _BRUSH_h

#if defined(ARDUINO) && ARDUINO >= 100
	#include "arduino.h"
#else
	#include "WProgram.h"
#endif

namespace Brush 
{
	const byte MOTOR_CONTROL_PIN = 6;
	const byte POWER = 0xaf;//Velocidad minima :(

	void init();

	void start();
	void stop();
}

#endif

