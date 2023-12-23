package com.moderneinstein.web.vertx.braceservice;

import com.moderneinstein.web.vertx.braceservice.TestClient4 ; 
import com.moderneinstein.web.vertx.braceservice.TestClient3 ; 

import org.junit.jupiter.api.Test ; 

import io.vertx.core.buffer.Buffer ; 
import io.vertx.core.json.JsonObject ; 
import io.vertx.core.Future ; 
import io.vertx.core.Handler ; 
import io.vertx.core.json.JsonArray ; 

import java.util.List ; 
import java.util.ArrayList ;
import java.util.Vector ; 

import java.net.http.HttpClient ; 
import java.net.http.HttpResponse ;
import java.net.http.HttpRequest ; 

public class TestCaller3 {

    public static BraceUser[] users = new BraceUser[]{
         new  BraceUser("kenzellgreywood@gmail.com","kenzell","password","address1") 
        , new BraceUser("newpaulowei@gmail.com","paulowei","password","address2") 
         , new BraceUser("pauloweita@gmail.com","oweipaul","password","address3")} ;  
    public static String BASES = new String("http://localhost/8050") ;  

        /*JsonObject voltage = new JsonObject().put("email",current.getEmail())  ; 
            voltage.put("username",current.getUsername())  ; 
            voltage.put("password",current.getPassword())   ; */
    

  public static List<String> tokens = new Vector<String>() ;    

       @Test   
    public void Test1() throws Exception {
         int height = users.length ; 
         for(int cr=0;cr<height;cr++ ){
            BraceUser current =   users[cr] ; 
            JsonObject temps = Serializers.serialiseUser(current) ; 
            String[] options = {"PUT",BASES.concat("/services/users/register"),temps.toString()} ;
            TestClient4.main(options) ; 
          }      
    } 
    /*String third = temps.toString() ; 
            String second =  BASES.concat("/services/users/register") ; 
            String first = "GET" ;  */
   // @Test
    public void Test2() throws Exception {
        for(int  re=0;re<users.length;re++ ){
            BraceUser voltage = users[re]  ;
            JsonObject jsons5 = Serializers.serialiseUser(voltage) ;  
            JsonObject objects = new JsonObject().put ("user",jsons5) ;  
            String[] options = new String[]{"GET",BASES.concat("/services/users/login/method"),jsons5.toString()} ; 
            HttpResponse<String> resps = TestClient3.collect(options) ; // TestClient3.main(options) ; 
            JsonObject gains = new JsonObject(resps.body()) ; 
            tokens .add(gains.getString("token")) ; 
        }  
        System.out.println (tokens.toString()) ;
    } 
    //  @Test // JsonObject jsons4 = Serializers.serialiseUser(brace)  ;
    public void Test3() throws Exception{
         for(int yt=0;yt<users.length;yt++){
            BraceUser brace =  users[yt] ; 
            JsonObject jsons4 = Serializers.serialiseUser(brace)  ;
            JsonObject options = new JsonObject().put("version","2.2") ; 
            JsonObject wrapper = new JsonObject().put("user",jsons4).put("options",options) ;  
            String[] inserts = new String[]{"POST",BASES.concat("/services/users/editoptions"),wrapper.toString()} ; 
            HttpResponse<String> response= TestClient3.collect(inserts)    ; 
            JsonObject created = new JsonObject(response.body()) ; 
            System.out.println(created.toString()) ;
         }
    }
    
    
}
