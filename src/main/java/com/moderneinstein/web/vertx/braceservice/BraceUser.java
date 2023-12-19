
package com.moderneinstein.web.vertx.braceservice ;

import java.util.Arrays ;
import java.util.Vector ; 
import java.util.Set ;
import java.util.Map ;
import  java.util.TreeMap ;  
import java.util.HashMap ;



public class BraceUser{
    
        private String password  ; 
        private String username ; 
        private String address  ; 
        private String email  ; 
        private Map<String,Object> options ; 

        public static final String BLANK =  new String("") ;
        public BraceUser(String email_,String username_,String password_){
            this.username = new String(username_) ; 
            this.email = new String(email_) ; 
            this.password = new String(password_) ;   
            this.address = new String(BLANK) ;  
            this.options = new TreeMap<String,Object>(); 
        } 
        public BraceUser(String  email_,String password_){
            this.email = new String(email_) ; 
            this.password= new String(password_)  ;    
            this.address = new String(BLANK) ;  
            this.username = new String(BLANK) ;   
            this.options =new HashMap<String,Object>() ; 
        } 

        public BraceUser(String email_,String  username_,String  password_,String address_){
             this.email =  new String(email_) ; 
            this.username = new String(username_) ; 
            this.password = new String(password_) ; 
            this.address  =  new String(address_) ;   
            this.options = new TreeMap<String,Object>() ; 
        } 
        public String getEmail(){
            return  this.email ; 
        } 
        public String getAddress(){
            return this.address  ; 
        } 
        public String getPassword(){
            return this.password ; 
        } 
        public String getUsername(){
            return this.username ; 
        } 
        public  void setUsername(String input){
            this.username = new String(input) ;  
        } 
        public void  setPassword(String input){
            this.password = new String(input)  ;
        } 
        public void setAddress(String input){
            this.address = new String(input) ; 
        }
        public void setEmail(String input){
            this.email = new String(input) ; 
        } 
        public void setOptions(Map<String,Object> input){
             this.options = new HashMap<String,Object> (input) ; 
        }
        public Map<String,Object> getOptions( ){
            return this.options ;  
        }
}
