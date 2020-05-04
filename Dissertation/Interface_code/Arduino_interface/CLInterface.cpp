#include "CLInterface.h"

int main() {

	ArduinoInterface* a_interface = new ArduinoInterface(CLInterface::selectSerialPort());
	cout << "Beginning the interface loop" << endl;
	CLInterface::interfaceLoop(a_interface);
	return 0;
}

string inputTrim(string raw_input) {
	//Find the index of the first letter in the string
	size_t first_letter = raw_input.find_first_not_of("\t\n\v\f\r ");
	//Find where the command name ends by looking for the first bit of whitespace after the first letter
	size_t line_break = raw_input.find_first_of("\t\n\v\f\r", first_letter);

	if (line_break == string::npos) {
		raw_input = raw_input.substr(first_letter);
	}
	else {
		raw_input = raw_input.substr(first_letter, (line_break - first_letter));
	}
	return raw_input;
}

unique_ptr<Serial> CLInterface::selectSerialPort() {
	string input_port_str;
	int input_port;
	stringstream  port_name_str;
	cout << "Enter the COM port number that the Arduino is connected to (usually 14):" << endl;
	getline(cin, input_port_str);

	//Takes just the integer from the input 
	stringstream(input_port_str) >> input_port;


	port_name_str << "\\\\.\\COM" << input_port;
	
	const std::string& tmp = port_name_str.str();
	const char* port_name = tmp.c_str();
	return unique_ptr<Serial>(new Serial(port_name));
}

void CLInterface::interfaceLoop(ArduinoInterface* a_interface) {
	string raw_input;

	cout << "Interface loop ready" << endl;

	cout << "Input END to stop the loop and close the program" << endl;

	cout << "Enter command: ";

	getline(cin, raw_input);

	string input = inputTrim(raw_input);

	while (input != "END") {
		
		ArduinoCommand command = ArduinoCommand(input);

		cout << a_interface->writeRead(command) << endl;

		getline(cin, raw_input);

		input = inputTrim(raw_input);
	}
}

