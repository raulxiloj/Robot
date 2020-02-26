// Carro.h

#ifndef _CARRO_h
#define _CARRO_h

#if defined(ARDUINO) && ARDUINO >= 100
	#include "arduino.h"
#else
	#include "WProgram.h"
#endif

#include "UsSensor.h"
#include "ColorSensor.h"
#include "Engine.h"
#include "Brush.h"

class Carro
{
private:
	//Engine engine = Engine();//engine es namespace
	//Brush brush = Brush();//Brush es namespace

	                         //trigger, echo
	UsSensor usLeft = UsSensor(22, 23);
	UsSensor usFront = UsSensor(24, 25);
	UsSensor usRight= UsSensor(26, 27);

	//oe,  s0, s1, out, s2, s3
	ColorSensor colorSensor = ColorSensor(48, 52, 50, 53, 51, 49);

	byte currMode;
	static const byte S_FOWARD = 0;
	static const byte S_FRONTAL_COLLISION = 1;
	static const byte S_LEFT = 2;
	static const byte S_RIGHT = 3;
	static const byte S_REVERSE = 4;
	byte currAutoState;//Para manejar los update automaticos

	static const int FRONTAL_DISTANCE = 540;
	static const int LEFT_DISTANCE = 730;
	static const int RIGHT_DISTANCE = 650;

	bool wasPressed = false;
	bool isPressed = false;
	//Actualiza los valores wasPressed e isPressed
	void updateButton();

public:
	
	static const byte MODE_BTN_PIN = 8;

	static const byte MANUAL_MODE = 0;
	static const byte AUTO_MODE = 1;
	

	Carro();//no olvidar poner init al engine aqui

	void updateAutomatic();
	void updateManual();
	
	//Metodos que solo funcionan en modo manual
	
	void setBrush(bool val);

	void stop();
	void turnLeft();
	void turnRight();
	void foward();
	void reverse();

	byte getCurrMode();

	friend void loop();//SOLO PARA DEBUGGING
};


#endif

