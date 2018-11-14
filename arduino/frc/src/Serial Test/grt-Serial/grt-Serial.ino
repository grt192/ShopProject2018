void setup()
{
  // put your setup code here, to run once:

  pinMode(13, OUTPUT);

  digitalWrite(13, HIGH);
  delay(1000);
  digitalWrite(13, LOW);
  delay(1000);
}

String message = "";

void loop()
{
  // put your main code here, to run repeatedly:

  if (Serial.available())
  {
    Serial.print(Serial.readString());
    digitalWrite(13, HIGH);
  }
}
