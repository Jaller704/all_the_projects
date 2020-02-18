#include "ArduinoCommand.h"

ArduinoCommand::ArduinoCommand(string raw_command) {
	//Find the index of the first letter in the string, this should be the first letter of the command name
	size_t first_letter = raw_command.find_first_not_of("\t\n\v\f\r ");
	//Find where the command name ends by looking for the first bit of whitespace after the first letter
	size_t first_space = raw_command.find_first_of("\t\n\v\f\r ", first_letter);
	
	//Pull this name out of the string and set the command_name
	setCommandName(raw_command.substr(first_letter, (first_letter - (first_space - 1))));

	//Find where the first parameter starts
	size_t first_param = raw_command.find_first_not_of("\t\n\v\f\r ", first_space);

	string temp_string = raw_command.substr(first_param);

	vector<string> params(1);
	stringSplit(temp_string, params, ' ');
	int temp_params[params.size];

	for (string i : params) {
		temp_params[i] = stoi(i);
	}

}

ArduinoCommand::ArduinoCommand(string command_name, int command_params[]) {

}

string ArduinoCommand::getSerialCommand() {
	return string();
}

void ArduinoCommand::setCommandName(string command_name) {
}

void ArduinoCommand::setCommandParams(int command_params[]) {
}

void ArduinoCommand::stringSplit(const string& str, vector<string>& split_string, char delim = ' ') {
	stringstream ss(str);
	string token;
	while (getline(ss, token, delim)) {
		split_string.emplace_back(token);
	}
}
