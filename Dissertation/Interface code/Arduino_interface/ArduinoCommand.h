#pragma once

#include <string>
#include <sstream>
#include <vector>

using namespace std;
class ArduinoCommand
{
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
	* Return the full_command which has been formatted for the serial console.
	*/
	string getSerialCommand();
private:
	/*
	* Splits theinput string based on a delimiter and puts the contents 
	* into a string vector.
	*/
	void stringSplit(const string& input, vector<string>& split_string, char delim = ' ');

	string command_name;
	vector<int> command_params;
	unique_ptr<string> full_command;


};

