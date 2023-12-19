package com.moderneinstein.web.vertx.braceservice  ;

import  java.util.Set   ; 
import java.util.Map  ; 
import java.util.TreeMap;  
import java.util.List ;

import java.util.Objects ;
import java.util.Arrays ; 
import java.io.Serializable ;  
import java.util.HashMap ; 

import java.io.Serializable ;   

import io.vertx.core.json.JsonObject ; 
import io.vertx.core.buffer.Buffer  ; 


public class Serializers  implements  Serializable  {
     
    public static BraceUser deserialiseUser_(JsonObject source){       
        BraceUser checks = new  BraceUser(new String(""),new String(""),new String("")) ;
        String  username= source.getString("username") ;   
        String password = source.getString("password") ;    
        String  email = source.getString("email")  ;    
       // if(source.containsKey("address")){
        String  address = source.getString("address") ;     
        Map<String,Object> mapper = source.getJsonObject("options").getMap() ;  
        if(mapper==null){mapper = new HashMap<String,Object>() ; }
        BraceUser braces = new  BraceUser(username,email,password,address)  ;  
        braces.setOptions(mapper)  ;
        return braces ; 
    }           
    // checks.setUsername("") ;   checks.setUsername(source.getUsername())
    public static BraceUser deserialiseUser(JsonObject source){
        BraceUser checks = new BraceUser (new String(""),new String(""),new String(""))  ; 
        if(source.containsKey("username")){
            checks.setUsername(source.getString("username") ) ;  } 
        if(source.containsKey("email")){
            checks.setEmail(source.getString("email")) ; } 
        if( source.containsKey("password")){
            checks.setPassword(source.getString("password") ) ; }  
        if (source.containsKey("address")){
            checks.setAddress(source.getString("address")) ; } 
        if(source.containsKey("options")){
            checks.setOptions(source.getJsonObject("options").getMap()) ;
        }  
        return checks ; 
     }
    // source.getOptions()
    public static JsonObject serialiseUser(BraceUser source){
        JsonObject  message = new JsonObject() ;   
        if(source.getAddress()!=null){
        message.put("address",source.getAddress()) ; }
        if(source.getEmail()!=null){
        message.put("email",source.getEmail()) ; }
        if (source.getPassword()!=null){
        message.put("password",source.getPassword()) ; }
        if(source.getUsername()!=null){
        message.put("username",source.getUsername()) ;  }
        if(source.getOptions()!=null){
        message.put("options",new JsonObject(source.getOptions())) ;  }
        return  message ;
    }       

}     
