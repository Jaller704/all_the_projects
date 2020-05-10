#include <Stepper.h>
#include <Servo.h>
#include <SoftwareSerial.h>
#include <SerialCommand.h>
#include <Encoder.h>

#define elbow_tlimit_pin 0
#define elbow_blimit_pin 2
#define hume_rlimit_pin 12
#define hume_llimit_pin 7
#define elbow_step_pin 13
#define shoulder_step_pin 4
#define hume_step_pin 8
#define elbow_direct_pin 6
#define shoulder_direct_pin 9
#define hume_direct_pin 10


Servo fore_servo;
Servo twrist_servo;
Servo bwrist_servo;
Servo lwrist_servo;
Servo rwrist_servo;

Encoder shoulder_enc(1, 3);
//Not enough pins for more encoders on the Uno I have currently
//Encoder elbow_enc(13,12);
//Encoder hume_enc(13,12);

SerialCommand cmd;

bool zeroed = false;

void runConfig();
void rotateForearm();
void getForearmRotation();
void rotateHumerus();
void getHumerusRotation();
void pivotElbow();
void getElbowPosition();
void pivotShoulder();
void getShoulderPosition();
void pivotWristHorz();
void getWristHorzPosition();
void pivotWristVert();
void getWristVertPosition();

void setup() {
  fore_servo.attach(5);
  //Don't actually possess these servos
  //twrist_servo.attach(6);
  //bwrist_servo.attach(9);
  //lwrist_servo.attach(10);
  //rwrist_servo.attach(11);

  pinMode(elbow_step_pin, OUTPUT);
  pinMode(shoulder_step_pin, OUTPUT);
  pinMode(hume_step_pin, OUTPUT);

  pinMode(elbow_direct_pin, OUTPUT);
  pinMode(shoulder_direct_pin, OUTPUT);
  pinMode(hume_direct_pin, OUTPUT);

  pinMode(elbow_tlimit_pin, INPUT_PULLUP);
  pinMode(elbow_blimit_pin, INPUT_PULLUP);
  pinMode(hume_rlimit_pin, INPUT_PULLUP);
  pinMode(hume_llimit_pin, INPUT_PULLUP);
  
  Serial.begin(9600);
  //empty loop that stays true until the serial port is ready
  while(!Serial);

  cmd.addCommand("CONFIG", runConfig);

  cmd.addCommand("ROT_FORE", rotateForearm);
  cmd.addCommand("GET_FORE_ROT", getForearmRotation);

  cmd.addCommand("ROT_HUME", rotateHumerus);
  cmd.addCommand("GET_HUME_ROT", getHumerusRotation);

  cmd.addCommand("PIV_ELBW", pivotElbow);
  cmd.addCommand("GET_ELBW_POS", getElbowPosition);

  cmd.addCommand("PIV_SHLD", pivotShoulder);
  cmd.addCommand("GET_SHLD_POS", getShoulderPosition);

  cmd.addCommand("PIV_WRTH", pivotWristHorz);
  cmd.addCommand("GET_WRTH_POS", getWristHorzPosition);

  cmd.addCommand("PIV_WRTV", pivotWristVert);
  cmd.addCommand("GET_WRTV_POS", getWristVertPosition); 
}

bool elbow_limit_hit = false;
bool hume_limit_hit = false;

void loop() {
  if (!zeroed) {
    //move each motor until their resepctive limit switches are hit
    //Serial.println("ELBOW LIMIT NOT HIT");
    if(digitalRead(elbow_blimit_pin) == HIGH && !elbow_limit_hit) {
      //Serial.println("ELBOW LIMIT NOT HIT");
      digitalWrite(elbow_direct_pin, LOW);
      digitalWrite(elbow_step_pin, HIGH);
    } else if (digitalRead(elbow_blimit_pin) == LOW){
      elbow_limit_hit = true;
      //Serial.println("ELBOW LIMIT HIT");
    }
    //Serial.println("HUME LIMIT NOT HIT");
    if(digitalRead(hume_rlimit_pin) == HIGH && !hume_limit_hit && elbow_limit_hit) {
      //Serial.println("HUME LIMIT NOT HIT");
      digitalWrite(hume_direct_pin,LOW);
      digitalWrite(hume_step_pin, HIGH);
    } else if (digitalRead(hume_rlimit_pin) == LOW){
      hume_limit_hit = true;
      //Serial.println("HUME LIMIT HIT");
    }
 //Serial.println("NOT ZEROED");
 //Serial.flush();
    if(hume_limit_hit && elbow_limit_hit) {
      fore_servo.write(0);
      zeroed = true;
      Serial.println("ZEROED");
      Serial.flush();
    }
    
  } else {
    //readPositions();
    //check if there are commands available 
    if(Serial.available() > 0) {
      //SerialCommand calls the function from this class that corresponds to what is read
      cmd.readSerial();
    }
  }
}
  
void runConfig() {
  Serial.println("ROT_FORE");
  Serial.println("ROT_HUME");
  Serial.println("PIV_ELBW");
  Serial.println("PIV_SHLD");
  Serial.println("PIV_WRTH"); //NOT IMPLEMENTED
  Serial.println("PIV_WRTV"); //NOT IMPLEMENTED
  Serial.println("GET_FORE_ROT");
  Serial.println("GET_HUME_ROT"); //NOT IMPLEMENTED
  Serial.println("GET_ELBW_POS"); //NOT IMPLEMENTED
  Serial.println("GET_SHLD_POS"); //NOT IMPLEMENTED
  Serial.println("GET_WRTH_POS"); //NOT IMPLEMENTED
  Serial.println("GET_WRTV_POS"); //NOT IMPLEMENTED
  Serial.flush(); 
}

// Forearm rotation uses a servo 
void rotateForearm() {
  char *arg = cmd.next();
  int value = String(arg).toInt();

  fore_servo.write(value);

  Serial.println("FORE_SET " + String(arg));
  Serial.flush();
}

void getForearmRotation() {
  Serial.println("FORE_ROT " + String(fore_servo.read()));
  Serial.flush();
}

// Humerus rotation uses a stepper
void rotateHumerus() {
  char *arg = cmd.next();
  int value = String(arg).toInt();

  if(value >= 0) {
    for(int i = 0; i <= value; i++){
      digitalWrite(hume_direct_pin,HIGH);
      digitalWrite(hume_step_pin, HIGH);
    }
  } else {
    for(int i = 0; i > value; i++){
      digitalWrite(hume_direct_pin,LOW);
      digitalWrite(hume_step_pin, HIGH);
    }
  }

  Serial.println("HUME_SET " + String(arg));
  Serial.flush();
}

void getHumerusRotation(){
  // Ran out of pins for encoders so no encoder object created
  /*Serial.println("HUME_ROT" + String(hume_enc.read()));
    Serial.flush(); */

  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void pivotElbow(){
  char *arg = cmd.next();
  int value = String(arg).toInt();

  if(value >= 0) {
    for(int i = 0; i <= value; i++){
      digitalWrite(elbow_direct_pin,HIGH);
      digitalWrite(elbow_step_pin, HIGH);
    }
  } else {
    for(int i = 0; i > value; i++){
      digitalWrite(elbow_direct_pin,LOW);
      digitalWrite(elbow_step_pin, HIGH);
    }
  }
  Serial.println("ELBW_SET " + String(arg));
  Serial.flush();
}

void getElbowPosition(){
  // Ran out of pins for encoders so no encoder object created
  /*Serial.println("ELBW_PIV" + String(elbow_enc.read()));
    Serial.flush();*/

  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void pivotShoulder(){
  char *arg = cmd.next();
  int value = String(arg).toInt();

  if(value >= 0) {
    for(int i = 0; i <= value; i++){
      digitalWrite(shoulder_direct_pin,HIGH);
      digitalWrite(shoulder_step_pin, HIGH);
    }
  } else {
    for(int i = 0; i > value; i++){
      digitalWrite(shoulder_direct_pin,LOW);
      digitalWrite(shoulder_step_pin, HIGH);
    }
  }
  Serial.println("SHLD_SET " + String(arg));
  Serial.flush();
}

void getShoulderPosition(){
  // No encoder to read from
  /*Serial.println("SHLD_PIV " + String(shoulder_enc.read()));
  Serial.flush();*/
  
  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void pivotWristHorz() {
  /*char *arg = cmd.next();
  int value = String(arg).toInt();
  
  if(value < 0) { // Rotating left
    if(value < -90) {
      value = 90;
    } else {
      value = abs(value)
    }
    lwrist_servo.write(value);
  }

  Serial.println("FORE_SET " + String(arg));
  Serial.flush();*/

  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void getWristHorzPosition() {
  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void pivotWristVert(){
  Serial.println("INVALID FUNCTION");
  Serial.flush();
}

void getWristVertPosition(){
  Serial.println("INVALID FUNCTION");
  Serial.flush();
}
