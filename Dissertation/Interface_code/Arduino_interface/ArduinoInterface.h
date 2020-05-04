#pragma once

#include "SerialClass.h"
#include "ArduinoCommand.h"

class ArduinoInterface {
public:
	/*
	* Constructor that runs configuration and sets the serial port link.
	*/
	ArduinoInterface(unique_ptr<Serial> link);

	/*
	* Asks the arduino for its list of commands and sets up a list of
	* available ones.
	*/
	void config();

	/*
	* Writes the command to the serial console, returns true if the command
	* is safe and succesfully writes to the serial port.
	*/
	bool write(ArduinoCommand& c);

	/*
	* Reads a set amount of characters from the serial port, returns the 
	* read string or an error message.
	*/
	string read();

	/*
	* Writes a command and reads the response given, returns the response 
	* or an error message.
	*/
	string writeRead(ArduinoCommand& c);


private:
	/*
	* Checks if the given command is within the list of valid commands. 
	*/
	bool safeCommand(ArduinoCommand& c);

	vector<string> valid_commands;
	unique_ptr<Serial> link;
	char read_buffer[11];
};

