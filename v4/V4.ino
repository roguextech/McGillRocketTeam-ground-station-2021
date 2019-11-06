#include <SimpleKalmanFilter.h>

//https://github.com/denyssene/SimpleKalmanFilter

#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BNO055.h>
#include <utility/imumaths.h>

/* This driver reads raw data from the BNO055
   Connections
   ===========
   Connect SCL to analog 5
   Connect SDA to analog 4
   Connect VDD to 3.3V DC
   Connect GROUND to common ground
   =======
*/


#define BNO055_SAMPLERATE_DELAY_MS (100)

Adafruit_BNO055 bno = Adafruit_BNO055(-1, 0x28);
SimpleKalmanFilter ACXFilter(0.01, 0.01, 0.01);

void setup(void)
{
  Serial.begin(115200);
 
  
  if(!bno.begin())
  {
    
    Serial.print("Ooops, no BNO055 detected ... Check your wiring or I2C ADDR!");
    while(1);
  }

  delay(1000);

  
}




  double a1=0;
  double s= 0;
  double FAX = 0;
  double combacc = 0;






void loop(void)
{
  
  imu::Vector<3> euler = bno.getVector(Adafruit_BNO055::VECTOR_LINEARACCEL);
  

  

    a1 = euler.x();
    FAX = ACXFilter.updateEstimate(a1);
    
    combacc = combacc + FAX;
    s=0.5*0.1*(combacc);
    


    Serial.print("\t SX: ");
    Serial.println(s);
    



  delay(BNO055_SAMPLERATE_DELAY_MS);
}