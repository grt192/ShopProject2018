# 1 "c:\\Users\\Bid\\Documents\\grt_stuff\\ShopProject2018\\arduino\\frc\\src\\grt-Serial\\grt-Serial.ino"
# 1 "c:\\Users\\Bid\\Documents\\grt_stuff\\ShopProject2018\\arduino\\frc\\src\\grt-Serial\\grt-Serial.ino"
# 2 "c:\\Users\\Bid\\Documents\\grt_stuff\\ShopProject2018\\arduino\\frc\\src\\grt-Serial\\grt-Serial.ino" 2

void setup()
{
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.write("hello");

  pinMode(13, 0x1);

  digitalWrite(13, 0x1);
  delay(1000);
  digitalWrite(13, 0x0);
  delay(1000);
}

String message = "";

void loop()
{
  // put your main code here, to run repeatedly:

  if (Serial.available())
  {
    Serial.print(Serial.readString());
    digitalWrite(13, 0x1);
  }
}
