#include "ArduinoInterface.h"
#include <iostream>

ArduinoInterface::ArduinoInterface(unique_ptr<Serial> link) : link(std::move(link)){
	config();
}

void ArduinoInterface::config() {
	string current_out = read();

	while (current_out != "ZEROED\r\n") {
		cout << "Waiting for zeroing to finish" << endl;
		//cout << current_out << endl;
		Sleep(100);
		current_out = read();
	}
	
	cout << "Getting commands from arduino..." << endl;

	//Send a command to the arduino to get it to return all of its commands to the serial console
	ArduinoCommand config_command = ArduinoCommand("CONFIG");
	string s = config_command.getSerialCommand();

	//Check that the write is a success before we can do anything
	if (link->WriteData(s.c_str(), s.length()) && link->IsConnected()) {

		bool commands_available = true;
		string received;

		bool setters = true;
		
		int command_count = 1;
		Sleep(1000);
		while (commands_available) {
			if (setters) {
				link->ReadData(read_buffer, 10);
				received = read_buffer;
			}
			else {
				if ((!link->ReadData(read_buffer, 14) == 0)) {
					received = read_buffer;
				}
				else {
					received = "";
				}
			}

			//Check if we've ran out of commands
			if (received == "") {
				commands_available = false;
			}
			else {
				cout << received;
				valid_commands.emplace_back(received);
			}

			command_count++;

			if (command_count > 6) {
				setters = false;
			}
		}
		
	}
	else {
		cout << "Link comm error, config failed" << endl;
		cout << "Is link still connected? " << link->IsConnected() << endl;
	}
}

bool ArduinoInterface::write(ArduinoCommand& c) {
	string command = c.getSerialCommand();
	
	if (safeCommand(c)) {
		if (link->WriteData(command.c_str(), command.length())) {
			return true;
		}
		else {
			cout << "Link comm error, command write failed" << endl;
			cout << "Is link still connected? " << link->IsConnected() << endl;
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
	memset(read_buffer, 0, sizeof(read_buffer));
	int bytes_read = link->ReadData(read_buffer, 20);
	string read_string = read_buffer;
	
	if (bytes_read < 5 && bytes_read > 0) {
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

string ArduinoInterface::writeRead(ArduinoCommand& c) {
	if (write(c)) {
		Sleep(100);
		return read();
	} 

	return "writeRead failed";
}

bool ArduinoInterface::safeCommand(ArduinoCommand& c) {
	string name = c.getName();
	
	for (string i : valid_commands) {
		if (name == i.substr(0, i.length() - 2)) {
			return true;
		}
	}

	return false;
}
