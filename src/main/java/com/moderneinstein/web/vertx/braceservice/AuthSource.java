package com.moderneinstein.web.vertx.braceservice ; 
 
 import  io.vertx.core.Handler   ; 
 import io.vertx.ext.auth.jwt.JWTAuth ;  
 import io.vertx.ext.auth.jwt.JWTAuthOptions ; 
 import io.vertx.ext.auth.KeyStoreOptions ;  
import io.vertx.core.Vertx ; 
 import io.vertx.ext.auth.JWTOptions ; 

import java.util.ArrayList ; 


public class AuthSource  {


    public static JWTAuth  central   = null  ;   // C:\\Users\\201204040\\Documents\\Centre\\Codebases\\user-login-system\\target\\
    private static String PASSWORD=   new String("password")  ;  
    private static String PATHS = new String("user-login.keystore") ;     
    public static String ISSUER =  "User-Login-System" ;    
     private static String[] ALGORITHMS = {"RS256","HS256"} ;  
    public static String SUBJECT  = "User-Login-System" ; 
     private static int MINUTES[] = {50,60,70 }   ;  
    /*
    JWTAuthOptions pubsec = 
    new JWTAuthOptions(). 
    addPubSecKey( 
        new PubSecKeyOptions()
        .setSymmetric( boolean symmetry) ;
        .setBuffer( String buffers)
        .setPublicKey(String public ) 
        .setPrivateKey(String private)  
    ) ;*/

    public static  JWTOptions createJWTOptions( ){
         JWTOptions options = new JWTOptions( ) ;   
        options.setExpiresInMinutes(MINUTES[1]) ;   
        options.setIssuer(ISSUER)  ;   
        options.setAlgorithm( ALGORITHMS[1]) ;    
       // options.setSubject( ) ;   
        options.setIgnoreExpiration(false) ;   
        return options ; 
    }
    public static JWTAuthOptions createAuthOptions(){
        JWTAuthOptions options = new JWTAuthOptions() ;
        options.setKeyStore(
            new KeyStoreOptions().
            setPath(PATHS).setPassword(PASSWORD) ) ;   
        JWTOptions jwtoptions =   createJWTOptions( ) ;  
     //   jwtoptions.setAudience(new ArrayList<String>( )) ; 
         options.setJWTOptions(jwtoptions) ; 
        return  options ; 
    }   
    public  static JWTAuth deriveAuth(Vertx context){
        if(central==null){ 
            JWTAuthOptions options =  createAuthOptions() ; 
            central = JWTAuth.create (context,options) ; 
        } 
        return central ; 
    }
}