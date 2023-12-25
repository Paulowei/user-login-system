package com.moderneinstein.web.vertx.braceservice ;

import io.vertx.core.AbstractVerticle;
  
import io.vertx.core.http.HttpServer ;
import io.vertx.core.http.HttpClient ; 
import io.vertx.ext.web.Router ; 
import io.vertx.core.Future ;
import io.vertx.core.Handler ;
import io.vertx.core.Vertx  ;
import io.vertx.core.AsyncResult ;  
import io.vertx.core.Promise  ; 
 import io.vertx.core.http.HttpServerOptions ; 

 import java.io.IOException ; 
 import java.util.Properties ;

import org.apache.commons.collections4.SetValuedMap;

import java.io.File ;  
 import java.util.Hashtable ; 
 import java.io.InputStream  ;
import java.util.HashMap ;
import java.io.FileInputStream   ;
  //  HashTable ; 
   
public class CentralVerticle extends AbstractVerticle {

    public HttpServer server ;   
    public int  PORT = 8050 ;  
    public String HOST = "http://localhost" ; 
    public Router router ;  
    public static Properties properties ; 
    public static String filePath = "server.properties" ;

    //   FileInputStream
    public FileInputStream deriveStream (){  
        FileInputStream stream = null ;
        try{
        File files =new File(filePath) ;
         stream = new FileInputStream(files)  ; 
        return  stream  ; 
        }catch(IOException except){
            System.out.println(except.toString()) ;
        }
         return stream  ; 
    }
    //  Properties properties =new Properties() ;
    public void SetProperties(){
         properties =new Properties() ; 
         try{
        FileInputStream input = deriveStream() ; 
        properties.load(input) ;  
         }catch(IOException except){

         }
    }
    public void  endpoints(){
         UserEndpoint1 endpoint4 = new UserEndpoint1(vertx,router,true) ;
        UserEndpoint2  endpoint5 =  new UserEndpoint2(vertx,router, true) ; 
    }     

    public HttpServerOptions CreateOptions( ){
        HttpServerOptions options = new HttpServerOptions() ;     
        options.setPort(Integer.parseInt(properties.getProperty("server-port"))) ; //(PORT) ;  
        options.setHost(properties.getProperty("server-host")) ; //(HOST) ;     
        options.setSsl(Boolean.parseBoolean(properties.getProperty("set-ssl"))) ; //false) ;    
        options.setUseAlpn(Boolean.parseBoolean(properties.getProperty("use-alpn"))) ;//(false) ;    
        return options ;     
    }
    public  void orientServer(){ 
        server.exceptionHandler(
            new Handler<Throwable>(){
                @Override 
                public void  handle(Throwable thrown){
                // TO - IMPLEMENT ; 
                }  }   ) ;  
        server.requestHandler(router ) ; 
        server.listen(PORT  , 
        new Handler<AsyncResult<HttpServer>>(){
            @Override 
            public void handle(AsyncResult<HttpServer> lanes){
                if(lanes.succeeded()){
                    // TO - IMPLEMENT ;  
                    System.out.println("Server is listening") ; 
                }else{ }  }   }) ;    
    } 
    
    @Override
    public void start() {
        vertx =  Vertx.vertx() ;   
        SetProperties() ; 
        HttpServerOptions options = CreateOptions() ; 
         server = vertx.createHttpServer( options ) ;     
         router = Router.router(vertx) ;    
        endpoints() ; 
         orientServer() ; 
        
    }
    /*  server.listen(port ,
        new Handler<AsyncResult<HttpServer>>(){
            @Override
            public void handle(AsyncResult<HttpServer> pending){
                if(pending.suceeded()){
                     
                }
            }
        }) ; */
}
