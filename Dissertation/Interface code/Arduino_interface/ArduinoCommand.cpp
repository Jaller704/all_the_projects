#include "ArduinoCommand.h"

ArduinoCommand::ArduinoCommand(string raw_command) {
	//Find the index of the first letter in the string, this should be the first letter of the command name
	size_t first_letter = raw_command.find_first_not_of("\t\n\v\f\r ");
	//Find where the command name ends by looking for the first bit of whitespace after the first letter
	size_t first_space = raw_command.find_first_of("\t\n\v\f\r ", first_letter);
	
	//Pull this name out of the string and set the command_name
	this->command_name = raw_command.substr(first_letter, (first_letter - (first_space - 1)));

	//Find where the first parameter starts
	size_t first_param = raw_command.find_first_not_of("\t\n\v\f\r ", first_space);

	string temp_string = raw_command.substr(first_param);

	vector<string> params(1);
	//Split the parameters up into individual strings
	stringSplit(temp_string, params, ' ');

	//Convert the strings into integers and copy into the command_params vector
	for (string i : params) {
		this->command_params.emplace_back(stoi(i));
	}

	//Recombine the name and parameters to create a string fomatted for the serial console
	stringstream ss;
	ss << command_name;
	for (int i : command_params) {
		ss << " " << i;
	}
	ss << "\r\n";

	this->full_command.reset(new string(ss.str()));

}

ArduinoCommand::ArduinoCommand(string name, vector<int> params) {
	//Find the index of the first letter in the string, this should be the first letter of the command name
	size_t first_letter = name.find_first_not_of("\t\n\v\f\r ");
	//Find where the command name ends by looking for the first bit of whitespace after the first letter
	size_t first_space = name.find_first_of("\t\n\v\f\r ", first_letter);

	//Pull this name out of the string and set the command_name
	this->command_name = name.substr(first_letter, (first_letter - (first_space - 1)));

	//Do a deep copy of the input parameters for robustness
	for (int i : params) {
		this->command_params.emplace_back(i);
	}

	//Combine the name and parameters to create a string fomatted for the serial console
	stringstream ss;
	ss << command_name;
	for (int i : command_params) {
		ss << " " << i;
	}
	ss << "\r\n";

	this->full_command.reset(new string(ss.str()));
}

string ArduinoCommand::getSerialCommand() {
	return *full_command;
}

string ArduinoCommand::getName() {
	return command_name;
}

void ArduinoCommand::stringSplit(const string& input, vector<string>& split_string, char delim = ' ') {
	stringstream ss(input);
	string token;
	while (getline(ss, token, delim)) {
		split_string.emplace_back(token);
	}
}
