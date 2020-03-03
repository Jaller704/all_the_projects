#include <Stepper.h>
#include <Servo.h>
#include <SoftwareSerial.h>
#include <SerialCommand.h>

Servo fore_servo
Servo twrist_servo
Servo bwrist_servo
Servo lwrist_servo
Servo rwrist_servo
Stepper elbow_step
Stepper shoulder_step
Stepper hume_step


void setup() {
  fore_servo.attach();
  twrist_servo.attach();
  bwrist_servo.attach();
  lwrist_servo.attach();
  rwrist_servo.attach();
  

}

void loop() {
  // put your main code here, to run repeatedly:

}
