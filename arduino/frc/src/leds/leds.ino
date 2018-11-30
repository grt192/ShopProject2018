#include <Adafruit_NeoPixel.h>

#define PIN 6

#define STRIPLEN 10

// Parameter 1 = number of pixels in strip
// Parameter 2 = pin number (most are valid)
// Parameter 3 = pixel type flags, add together as needed:
//   NEO_KHZ800  800 KHz bitstream (most NeoPixel products w/WS2812 LEDs)
//   NEO_KHZ400  400 KHz (classic 'v1' (not v2) FLORA pixels, WS2811 drivers)
//   NEO_GRB     Pixels are wired for GRB bitstream (most NeoPixel products)
//   NEO_RGB     Pixels are wired for RGB bitstream (v1 FLORA pixels, not v2)
Adafruit_NeoPixel strip = Adafruit_NeoPixel(13, PIN, NEO_GRB + NEO_KHZ400);

double voltage;

String message;
char key;
String value;

int voltageRangeMin = 0;
int voltageRangeMax = 5;

int leftSize = 11;
int leftMin = voltageRangeMax;
int leftRangeMax = voltageRangeMax + leftSize;
int leftMiddle = leftMin + leftSize/2;

int rightSize = 11;
int rightMin = leftRangeMax;
int rightRangeMax = leftRangeMax + rightSize;
int rightMiddle = rightMin + rightSize/2;

void setup()
{
    Serial.begin(9600);
    //Serial.setTimeout(2000);
    Serial.write("hello");

    pinMode(13, OUTPUT);

    strip.begin();
    strip.clear();
    strip.show();
}

void loop()
{
    // put your main code here, to run repeatedly:
    if (Serial.available())
    {
        unsigned long startTime = micros();
        
        message = Serial.readStringUntil('\n');

        key = message.charAt(0);

        value = message.substring(1);

        switch(key){
          case 'v':
            voltageSetStrip(value.toDouble());
          default:
            break;
        }
        
        Serial.println((micros() - startTime));
    }
}

void rightSetStrip(double power){
  for(int i = rightMin; i < rightRangeMax; i++){
    strip.setPixelColor(i, 0, 0, 0);
  }
  
  if(power > 0){
    for(int i = rightMiddle; i < rightRangeMax; i++){
      strip.setPixelColor(i, 255, 0, 0);
    }
  }else if(power < 0){
    for(int i = rightMin; i <= rightMiddle; i++){
      strip.setPixelColor(i, 255, 0, 0);
    }
  }

  strip.show();  
}

void leftSetStrip(double power){
  for(int i = leftMin; i < leftRangeMax; i++){
    strip.setPixelColor(i, 0, 0, 0);
  }
  
  if(power > 0){
    for(int i = leftMiddle; i < leftRangeMax; i++){
      strip.setPixelColor(i, 255, 0, 0);
    }
  }else if(power < 0){
    for(int i = leftMin; i <=leftMiddle; i++){
      strip.setPixelColor(i, 255, 0, 0);
    }
  }

  strip.show();  
}

void voltageSetStrip(double value){
  double red = voltageToColor(0, voltage);
  double green = voltageToColor(1, voltage);

  //Serial.println(red);
  //Serial.println(green);

  digitalWrite(13, LOW);

  for (int i = voltageRangeMin; i < voltageRangeMax; i++)
  {
      strip.setPixelColor(i, red, green, 0);          
  }

  strip.show();
}

double voltageToColor(int color, double voltage)
{
  double scale = clip((voltage - 7.0)/6.0);
  
  if(color == 0){
    //red
    return clip( (255.0 - (255.0 * 2 * (scale - 0.5))), 0.0, 255.0);
  }else if(color == 1){
    return clip( (255.0 * 2.0 * scale), 0.0, 255.0 );
  }else{
    return 0;
  }
}

double clip(double input){
    return (input > 1.0) ? 1.0 : ((input < 0.0) ? 0.0 : input);
}

double clip(double input, double min, double max){
    return (input > max) ? max : ((input < min) ? min : input);
}
