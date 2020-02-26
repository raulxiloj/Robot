// ColorSensor.h

#ifndef _COLORSENSOR_h
#define _COLORSENSOR_h

#if defined(ARDUINO) && ARDUINO >= 100
#include "arduino.h"
#else
#include "WProgram.h"
#endif


class ColorSensor {
private:
	byte oe = 48;
	byte s0 = 52;
	byte s1 = 50;

	byte out = 53;
	byte s2 = 51;
	byte s3 = 49;

public:
	static const byte RED = 0;
	static const byte BLUE = 1;
	static const byte BLACK = 2;
	static const byte WHITE = 3;

	ColorSensor(byte oe, byte s0, byte s1, byte out, byte s2, byte s3);

	//void printColor();

	byte getColor();
};

#endif
