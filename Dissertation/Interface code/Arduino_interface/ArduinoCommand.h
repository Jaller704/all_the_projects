#pragma once

#include <string>
#include <sstream>
#include <vector>

using namespace std;
class ArduinoCommand
{
public:
	ArduinoCommand(string raw_command);
	ArduinoCommand(string command_name, int command_params[]);

	string getSerialCommand();
private:
	void setCommandName(string command_name);
	void setCommandParams(int command_params[]);
	void stringSplit(const string& str, vector<string>& split_string, char delim = ' ');
	string command_name;
	int command_params[];


};

