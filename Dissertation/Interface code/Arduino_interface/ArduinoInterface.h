#pragma once

#include "SerialClass.h"
#include "ArduinoCommand.h"

class ArduinoInterface {
public:
	ArduinoInterface(Serial link);

	void config();
	bool write(ArduinoCommand c);
	string read(int timeout = 1000);
	string writeRead(ArduinoCommand c);


private:
	bool safeCommand(ArduinoCommand c);

	vector<string> valid_commands;
	Serial link;
};

