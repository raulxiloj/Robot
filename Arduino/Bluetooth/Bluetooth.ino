/*  
 *  IMPORTANTE
 *  Configurar los baudios del monitor serie en 9600
*/

void setup(){
  Serial.begin(9600);
  Serial2.begin(38400);
  Serial2.println("AT");
}

void loop(){
  while(Serial2.available()){
    /*Recibe la solicitud por parte del celular*/
    Serial.println(Serial2.readString());
    /*Aqui en vez de hola#, enviar la cadena que se recibira en el celular*/
    Serial2.write("hola#");
    }

    while(Serial2.available()){
    //Serial2.write(Serial.read());
    Serial2.write("hola#");
    }
    delay(50);
  }
