#include "ArduinoInterface.h"
#include <iostream>

ArduinoInterface::ArduinoInterface(Serial link) : link(link){
	config();
}

void ArduinoInterface::config() {
	cout << "Getting commands from arduino..." << endl;

	//Send a command to the arduino to get it to return all of its commands to the serial console
	ArduinoCommand config = ArduinoCommand("CONFIG");
	string s = config.getSerialCommand();

	//Check that the write is a success before we can do anything
	if (link.WriteData(s.c_str(), s.length())) {

		bool commandsAvailable = true;
		string received;
		int attempt = 1;

		while (commandsAvailable) {
			received = read();
			/*if (attempt < 11) {*/
				//Check if we've ran out of commands
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
		cout << "Is link still connected? " << link.IsConnected() << endl;
	}
}

bool ArduinoInterface::write(ArduinoCommand c) {
	string command = c.getSerialCommand();
	if (safeCommand(c)) {
		if (link.WriteData(command.c_str(), command.length())) {
			return true;
		}
		else {
			cout << "Link comm error, command write failed" << endl;
			cout << "Is link still connected? " << link.IsConnected() << endl;
			return false;
		}
	}
	else {
		cout << "Invalid command: " << command << endl;
		return false;
	}
	return false;
}

string ArduinoInterface::read() {
	int bytes_read = link.ReadData(read_buffer, 20);
	string read_string = read_buffer;
	
	if (bytes_read < 11 && bytes_read > 0) {
		cout << "Incomplete read" << endl;
		cout << "Read string was: " << read_string << endl;
	}
	else if (bytes_read == 0){
		cout << "There was an error or nothing to read "
			<< "(so there was probably an error)" << endl;
	}
	else {
		return read_string;
	}
	return "Unknown error";
	
}

string ArduinoInterface::writeRead(ArduinoCommand c) {
	if (write(c)) {
		return read();
	} 

	return "writeRead failed";
}

bool ArduinoInterface::safeCommand(ArduinoCommand c) {
	string name = c.getName();
	
	for (string i : valid_commands) {
		if (strcmp(i.c_str(), name.c_str())) {
			return true;
		}
	}

	return false;
}
