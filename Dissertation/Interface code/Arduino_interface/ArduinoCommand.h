#pragma once

#include <string>
#include <sstream>
#include <vector>

using namespace std;
class ArduinoCommand {
public:
	/*
	* Takes in raw string representation of the entire command
	* and splits it into the name and parameters, along with trimming 
	* out any whitespace before or after the name.
	*/
	ArduinoCommand(string raw_command);

	/*
	* Takes in the command name and the parameters, trims the whitespace 
	* out of the name string.
	*/
	ArduinoCommand(string name, vector<int> params);

	/*
	* Copy constructor.
	*/
	ArduinoCommand(const ArduinoCommand& c) {
		this->command_name = c.command_name;
		copy(c.command_params.begin(), c.command_params.end(), back_inserter(this->command_params));
		string t = *c.full_command;
		this->full_command.reset(&t);

	}

	/*
	* Return the full_command which has been formatted for the serial console.
	*/
	string getSerialCommand();

	/*
	* Return the name of the command.
	*/
	string getName();
private:
	/*
	* Splits the input string based on a delimiter and puts the contents 
	* into a string vector.
	*/
	void stringSplit(const string& input, vector<string>& split_string, char delim);

	string command_name;
	vector<int> command_params;
	unique_ptr<string> full_command;


};

