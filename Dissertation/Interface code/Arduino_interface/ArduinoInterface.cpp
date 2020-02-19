#include "ArduinoInterface.h"
#include <iostream>

ArduinoInterface::ArduinoInterface(Serial link) : link(link){
	config();
}

void ArduinoInterface::config() {
	cout << "Getting commands from arduino..." << endl;

	ArduinoCommand config = ArduinoCommand("CONFIG");
	string s = config.getSerialCommand();


	if (link.WriteData(s.c_str(), s.length())) {

		bool commandsAvailable = true;
		string received;
		int attempt = 1;

		while (commandsAvailable) {
			received = read();
			/*if (attempt < 11) {*/
				if (strncmp(received.c_str(), "", 1)) {
					commandsAvailable = false;
				}
				/*else if (strncmp(received.c_str(), "failed read", 11)) {
					cout << "Failed reading, trying again" << endl;
					cout << "Attempt #" << attempt << endl;
					attempt++;
				}*/
				else {
					valid_commands.emplace_back(received);
				}
			}
			/*else {
				commandsAvailable = false;
				cout << "Too many read errors"
			}
		}*/

		
	}
	else {
		cout << "Link comm error, config failed" << endl;
		cout << "Is link still connected? " << link.IsConnected() <<endl;
	}
}

bool ArduinoInterface::write(ArduinoCommand c) {
	return false;
}

string ArduinoInterface::read(int timeout = 1000) {
	
	return string();
}

string ArduinoInterface::writeRead(ArduinoCommand c) {
	return string();
}

bool ArduinoInterface::safeCommand(ArduinoCommand c) {
	return false;
}
