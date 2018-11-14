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
    Serial.write("hello");

    strip.begin();
    strip.clear();
    strip.show();
}

void loop()
{
    // put your main code here, to run repeatedly:
    if (Serial.available())
    {
        voltage = Serial.readString().toDouble();//String.toDouble(Serial.readString());

        double red = voltageToColor("red", voltage);
        double green = voltageToColor("green", voltage);

        Serial.println(red);
        Serial.println(green);

        for (int i = 0; i < STRIPLEN; i++)
        {
            strip.setPixelColor(i, red, green, 0);
            strip.show();
        }
    }
}

double voltageToColor(String color, double voltage)
{
    double scale = clip((voltage - 7.0)/6.0);

    //Scale using hsl (Hue saturation brightness) 0-120 100% 50% pattern
    double redValue = clip( (255.0 - (255.0 * 2 * (scale - 0.5))), 0.0, 255.0);
    double greenValue = clip( (255.0 * 2.0 * scale), 0.0, 255.0 );
    

    if (color == "red")
    {
        return redValue;
    }
    else if (color == "green")
    {
        return greenValue;
    }
    else
    {
        return 0.0;
    }
}

double clip(double input){
    return (input > 1.0) ? 1.0 : ((input < 0.0) ? 0.0 : input);
}

double clip(double input, double min, double max){
    return (input > max) ? max : ((input < min) ? min : input);
}
