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

   
public class CentralVerticle extends AbstractVerticle {

    public HttpServer server ;   
    public int  PORT = 8050 ;  
    public String HOST = "http://localhost" ; 
    public Router router ; 
    public void  endpoints(){
         UserEndpoint endpoint4 = new UserEndpoint(vertx,router,true) ;
        
    }     

    public HttpServerOptions CreateOptions( ){
        HttpServerOptions options = new HttpServerOptions() ;     
        options.setPort(PORT) ;  
        options.setHost(HOST) ;     
        options.setSsl(false) ;    
        options.setUseAlpn(false) ;    
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
