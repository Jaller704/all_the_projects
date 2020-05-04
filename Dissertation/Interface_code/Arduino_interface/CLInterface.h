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

	static void interfaceLoop(ArduinoInterface*);

};

