package com.moderneinstein.web.vertx.braceservice ; 
 
 import  io.vertx.core.Handler   ; 
 import io.vertx.ext.auth.jwt.JWTAuth ;  
 import io.vertx.ext.auth.jwt.JWTAuthOptions ; 
 import io.vertx.ext.auth.KeyStoreOptions ;  
import io.vertx.core.Vertx ; 
 import io.vertx.ext.auth.JWTOptions ; 

import java.util.ArrayList ; 
import java.util.Properties ; 
import java.util.List  ;   

public class AuthSource  {


    public static JWTAuth  central   = null  ;   // C:\\Users\\201204040\\Documents\\Centre\\Codebases\\user-login-system\\target\\
    private static String PASSWORD=   new String("password")  ;  
    private static String PATHS = new String("target/user-login.keystore") ;     
    public static String ISSUER =  "User-Login-System" ;    
     private static String[] ALGORITHMS = {"RS256","HS256"} ;  
    public static String SUBJECT  = "User-Login-System" ; 
     private static int MINUTES[] = {50,60,70 }   ;   
     private static String Algorithm ; 
     public static  int  DURATION = 60 ; 
     public static  Properties properties ;    
     public static boolean IgnoreExpiration ;  
     public static String Subjective  ; 
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
    public static void SetProperties(){ 
        properties =  CentralVerticle.properties ; 
        ISSUER = properties.getProperty("jwt-issuer") ;    
          PATHS = properties.getProperty("keystore-path") ;   
        PASSWORD = properties.getProperty("keystore-password") ; 
      IgnoreExpiration = Boolean.parseBoolean(properties.getProperty("jwt-ignore-expiration")) ;  
        String  timing =  properties.getProperty("jwt-minutes-expiration") ; 
        if(timing!=null){
        DURATION = Integer.parseInt(timing) ; }
        Algorithm = properties.getProperty("jwt-algorithm") ;   
        Subjective = properties.getProperty("jwt-subject")  ;
    }  
    // properties.getProperty("")  ; 
    // CentralVerticle.properties.getProperty("jwt-algorithm")
    public static  JWTOptions createJWTOptions( ){
         JWTOptions options = new JWTOptions( ) ;   
        options.setExpiresInMinutes(DURATION) ; //(MINUTES[1]) ;   
        options.setIssuer(ISSUER) ; //(ISSUER)  ;   
        options.setAlgorithm(Algorithm) ; //( ALGORITHMS[1]) ;    
        options.setSubject(Subjective ) ;   
        options.setIgnoreExpiration(IgnoreExpiration) ;   
        return options ; 
    } 
    //      //   jwtoptions.setAudience(new ArrayList<String>( )) ;  
    // CentralVerticle.properties.getProperty("keystore-password")
    //CentralVerticle.properties.getProperty("keystore-password")
    //CentralVerticle.properties.getProperty("keystore-path")
    public static JWTAuthOptions createAuthOptions(){
        JWTAuthOptions options = new JWTAuthOptions() ;
        options.setKeyStore(
            new KeyStoreOptions().
            setPath(PATHS).
            setPassword(PASSWORD) ) ;   
        JWTOptions jwtoptions =   createJWTOptions( ) ;  
         options.setJWTOptions(jwtoptions) ; 
        return  options ; 
    }   
    public  static JWTAuth deriveAuth(Vertx context){ 
        SetProperties() ; 
        if(central==null){   
            JWTAuthOptions options =  createAuthOptions() ; 
            central = JWTAuth.create (context,options) ; 
        } 
        return central ; 
    }
}