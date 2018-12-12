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
        
        double voltage = Serial.readStringUntil('\n').toDouble();
                
        double red = voltageToColor(0, voltage);
        double green = voltageToColor(1, voltage);

        //Serial.println(red);
        //Serial.println(green);

        digitalWrite(13, LOW);

        for (int i = 0; i < STRIPLEN; i++)
        {
            strip.setPixelColor(i, red, green, 0);          
        }

        strip.show();
        Serial.println((micros() - startTime));
    }
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
