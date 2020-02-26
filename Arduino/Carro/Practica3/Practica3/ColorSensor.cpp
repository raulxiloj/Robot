// 
// 
// 

#include "ColorSensor.h"

//SOLO PARA DEBUGGING
//void ColorSensor::printColor()
//{//TODO arreglar la cantidad de tiempo que espera ca color
//	digitalWrite(OE, LOW);
//	digitalWrite(S0, LOW);
//	digitalWrite(S1, HIGH);
//	delayMicroseconds(100);
//
//	unsigned long pulseWidth = 0;
//
//	unsigned long red = 0;
//	unsigned long green = 0;
//	unsigned long blue = 0;
//	//Red
//	digitalWrite(S2, LOW);
//	digitalWrite(S3, LOW);
//	delayMicroseconds(100);
//	red = pulseIn(OUT, HIGH, 20000);
//
//	//Green
//	digitalWrite(S2, HIGH);
//	digitalWrite(S3, HIGH);
//	delayMicroseconds(100);
//	green = pulseIn(OUT, HIGH, 10000);
//	
//
//	//Blue
//	digitalWrite(S2, LOW);
//	digitalWrite(S3, HIGH);
//	delayMicroseconds(100);
//	blue = pulseIn(OUT, HIGH, 10000);
//
//	//Chapuz masivo
//	if (red + 100 > blue) {
//		Serial.println("blue");
//	}
//	else if (blue > green) {
//		Serial.println("yellow");
//	}
//	else {
//		Serial.println("red");
//	}
//	digitalWrite(S0, LOW);
//	digitalWrite(S1, LOW);
//	digitalWrite(OE, HIGH);
//}

ColorSensor::ColorSensor(byte oe, byte s0, byte s1, byte out, byte s2, byte s3)
{
	this->oe = oe;
	this->s0 = s0;
	this->s1 = s1;
	this->out = out;
	this->s2 = s2;
	this->s3 = s3;

	pinMode(s0, OUTPUT);
	pinMode(s1, OUTPUT);
	pinMode(s2, OUTPUT);
	pinMode(s3, OUTPUT);
	pinMode(oe, OUTPUT);
	pinMode(out, INPUT);

	digitalWrite(s0, LOW);
	digitalWrite(s1, LOW);
	digitalWrite(oe, HIGH);
}

byte ColorSensor::getColor()
{//TODO arreglar la cantidad de tiempo que espera ca color
	digitalWrite(oe, LOW);
	digitalWrite(s0, LOW);
	digitalWrite(s1, HIGH);
	delayMicroseconds(100);

	unsigned long pulseWidth = 0;

	unsigned long red = 0;
	unsigned long green = 0;
	unsigned long blue = 0;
	//Red
	digitalWrite(s2, LOW);
	digitalWrite(s3, LOW);
	delayMicroseconds(100);
	red = pulseIn(out, HIGH, 5000);

	//Green
	digitalWrite(s2, HIGH);
	digitalWrite(s3, HIGH);
	delayMicroseconds(100);
	green = pulseIn(out, HIGH, 2500);


	//Blue
	digitalWrite(s2, LOW);
	digitalWrite(s3, HIGH);
	delayMicroseconds(100);
	blue = pulseIn(out, HIGH, 3750);

	byte result;
	//Chapuz masivo //Tal
	if (red > 200 && green > 300 && blue > 300 &&
		red < 900 && green < 900 && blue < 900) {
		result = WHITE;
	}
	else if (red > 100 && red < 1000) {
		result = RED;
	}
	else if (blue > 100 && blue < 1000) {
		result = BLUE;
	}
	else {
		result = BLACK;
	}
	digitalWrite(s0, LOW);
	digitalWrite(s1, LOW);
	digitalWrite(oe, HIGH);

	/*Serial.print("red: ");
	Serial.print(red);
	Serial.print(" green: ");
	Serial.print(green);
	Serial.print(" blue: ");
	Serial.println(blue);*/

	return result;
}

