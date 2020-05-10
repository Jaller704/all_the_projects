#pragma once

#include "ArduinoInterface.h"
#include "ArduinoCommand.h"
#include "SerialClass.h"
#include <iostream>
#include <sstream>

using namespace std;
class CLInterface
{
public:
	/*
	* Takes in a user prompt and sets up a Serial link to the port entered 
	*/
	static unique_ptr<Serial> selectSerialPort();

	/*
	* Begins an interface loop to continuously take in commands and match 
	* the loop control on the Arduino
	*/
	static void interfaceLoop(ArduinoInterface*);

};

