package com.moderneinstein.web.vertx.braceservice  ;  


import  io.vertx.ext.web.RoutingContext ;
import io.vertx.ext.web.Router ; 
import io.vertx.core.Future ; 
import io.vertx.core.AsyncResult ; 

import  io.vertx.core.Handler ; 
import io.vertx.core.Vertx ; 
import io.vertx.ext.web.Route ; 
import io.vertx.core.json.JsonObject ; 
import io.vertx.core.json.JsonArray ; 


import io.vertx.core.buffer.Buffer ; 
import io.vertx.core.http.HttpMethod ;
import io.vertx.ext.auth.User ; 
import io.vertx.ext.auth.authentication.AuthenticationProvider ; 
import io.vertx.ext.auth.jwt.JWTAuth ; 

import java.util.Map ; 
import java.util.Set ; 
import java.util.List ; 


public class  UserEndpoint1{


    public  Vertx context ; 
    private UserServices services ; 
     public  Router router ; 
     private JWTAuth jwts  ;   
    public static String[] lines = {"The request was successful","The request was not successful","Error parsing Json"} ;
    public  void noticeRoutes (Handler<Void> handles){
         new Thread(){
           @Override 
           public void run(){
             serveRoutes1( ) ;   
             serveRoutes2( ) ;  
             serveRoutes3( ) ;  
          //   serveRoutes4( ); 
          //    serveRoutes5( ) ;  
         //     serveRoutes6( )  ; 
         //   serveRoutes7( ) ;   
          //  serveRoutes8() ;   
          //  serveRoutes9()  ;
            serveRoutes4() ;  
            serveRoutes5() ; 
             Void voided = null ; 
            handles.handle(voided) ; 
           } 
         }.start() ; 
    }

    public void activate(){
        Handler<Void>  handlers = new Handler<Void>(){
            @Override 
            public void handle (Void voided){
                //
            }
        } ;   
       noticeRoutes(handlers) ;
    } 
  /*  public void  activate(Handler<Void> callback){
        noticeRoutes(handlers) ; 
    } */
   public UserEndpoint1(Vertx environ,Router lanes,boolean begin){
    services = new UserServices(environ)  ;
    context = environ   ;  
    router =   lanes ;  // Router.router(environ) ;  
    if(begin==true){
        activate() ;   } 
    jwts =  AuthSource.deriveAuth(context ) ;  
   }  
// services.saveUser(braces).onSuccess( );   
// public void handle(BraceUser users){   
    //      new Handler<BraceUser>(){ 
         //   routing.response().send(users) ;  //(alters) ;  
                       //      System.out.println(44) ;   
                       //  System.out.println(jsons.toString()) ; 
            /*          System.out.println( buffers.toString( )) ; 
                          //  if(buffers!=null ){ return  ; } */
   public void serveRoutes1(){
        Route paths1 = router.route()  ;
    paths1.method(HttpMethod.PUT) ;    
    paths1.path("/services/users/register") ; 
    paths1.handler(   
        new Handler<RoutingContext>(){ 
            @Override  
            public void handle(RoutingContext  routing){
                Future<Buffer> futures3 =  routing.request().body() ;   
                futures3.onSuccess(   
                    new Handler<Buffer>(){
                        @Override   
                        public void handle(Buffer buffers){ 
                            JsonObject jsons = buffers.toJsonObject()  ;  
                         //   BraceUser braces = Serializers.serialise(jsons) ; 
                            services.saveUserCrypt(jsons).onSuccess(
                                new Handler<JsonObject>(){
                                    @Override 
                                    public void handle(JsonObject users){
                                        Tools.HandleResponse (routing.response(),users.copy(),lines[0],true,true,true)  ;
                                    }
                                }
                            ).onFailure(
                                new Handler<Throwable>( ){
                                    @Override 
                                    public void handle(Throwable throwable){
                                        Tools.HandleResponse(routing.response(),new JsonObject( ),throwable.toString(),false,true,true) ;
                                    }
                                }
                            ) ;
                        }
                    }
                     ).onFailure ( 
                        new Handler<Throwable>(){
                            @Override  
                            public void handle(Throwable throwable){
                                Tools.HandleResponse(routing.response(),new JsonObject(),throwable.toString( ),false,true,true) ;
                            }
                        }
                     ) ; 
            }
        }
    ) ;
}    
/*     //   JsonObject  alters = Utilities.serialiseUser(users)   
                                        routing.response().setChunked(true) ;    
                                        routing.response().write(users) ;  //(alters) ;  
                                        routing.response ().end() ;  */
//  String  jwtAuth =  jwts.generateToken(jackson) ;   
/*   context.response().setChunked(true) ;
        context.response().write(bearer.toString())  ;
        context.response( ).end() ;   */  
        //   JsonObject bearer = new JsonObject().put("bearer",generated ).put("success",true) ;   
        //         ///  System.out.println (generated)  ; // javax.sql.rowset.serial ; 
public void serveRoutes2( ){
    Route paths2 = router.route()  ;
    paths2.method (HttpMethod.GET) ; 
    paths2.path("/services/users/login/:method") ; 
    paths2.handler (
        new Handler<RoutingContext>(){
            @Override 
            public void handle(RoutingContext context){  
                String trials = context.pathParams().get(":method") ;
                  context.request( ).body(  
                    new Handler<AsyncResult<Buffer>>() {
                        @Override 
                        public void handle(AsyncResult<Buffer> pending1){
                             if(pending1.succeeded()){
                                Buffer buffs = pending1.result( ) ;
                                JsonObject jsons = buffs.toJsonObject()  ;  
                             //   if(jsons!=null){System.out.println(jsons.toString( )) ;  return  ; }
                                Future<JsonObject> found =  services.findUserCrypt( jsons); 
                                found.onComplete(
                                    new Handler<AsyncResult<JsonObject>>(){
                                        @Override 
                                        public void handle(AsyncResult<JsonObject> pending2){
                                            if(pending2.succeeded()){ 
                                                JsonObject jackson = pending2.result()  ; 
                                                String  generated =  jwts.generateToken(jackson) ;  
                                                JsonObject bearer = new JsonObject().put("token",generated ).put("success",true) ;   
                                                Tools.test(bearer,jwts)  ;
                                                Tools.HandleResponse(context.response(),bearer,lines[0],true,true,true) ;
                                                }else{
                                                    Tools.HandleResponse(context.response(),new JsonObject(),pending2.cause().toString(),false,true,true) ;
                                                }    }  
                                                   }  ) ;    }else{ 
                                                    Tools.HandleResponse (context.response (), new JsonObject(),pending1.cause().toString(),false,true,true) ;
                                                   }   }
                    } ) ; 
                  } }
                            ) ;   }      
           //  if(jackson!=null){System.out.println(jackson.toString( )) ; 
        //     System.out.println("Result Fullfilled ") ;return ; }
    //  Future<JsonObject> possible = services.editUserOptions
    //(input.getString("email"),inpi) ; //  context.response().setChunked(true) ;  
 
    public void serveRoutes3(){ 
        Route route3 = router.route() ; 
        route3.path("/services/users/append")  ;
        route3.method(HttpMethod.POST) ; 
        route3.handler( 
            new Handler<RoutingContext>( ){
                @Override 
                public void handle(RoutingContext routing){
                     routing.request().body().onSuccess( 
                        new Handler<Buffer>(){
                            @Override 
                            public void handle(Buffer buffer){
                                JsonObject input = buffer.toJsonObject() ;  
                                Future<User> futures =   jwts.authenticate(input) ; 
                                futures.onComplete(
                                    new Handler<AsyncResult<User>>(){
                                        @Override 
                                        public void handle(AsyncResult<User> pending){  
                                        if(pending.succeeded()){    // input.getJsonObject("user")
                                JsonObject jsons5 = pending.result().attributes() ; 
                                Future<JsonObject> possible = services.editUserOptions(jsons5.getJsonObject("accessToken"),input.getJsonObject("options")) ; 
                                possible.onSuccess (
                                    new Handler<JsonObject>(){
                                        @Override 
                                        public void handle( JsonObject prefer){
                                            Tools.HandleResponse(routing.response(),new JsonObject(prefer.getMap()),lines[0],true,true,true) ;
                                        }  } ).onFailure(
                                    new Handler<Throwable>( ){
                                        @Override 
                                        public void handle( Throwable thrown){
                                       //  context.response().setChunked(true) ; 
                                       Tools.HandleResponse(routing.response(),new JsonObject( ),thrown.toString(),false,true,true)  ; 
                                        }
                                    }
                                ) ; 
                            }} }) ; 
                            }
                        }
                    ) ;
                }
            }
        ) ; 
    }   

    // Tools.HandleResponse(context.response(),pending.result (),"The request was successful",true,true,true) ; 
    //  route6.path("/services/users/edituser/:email/:types/:value") ;  

    // :method/:value  // ,params.get("method"),params.get("value")  // "The request was successful"
    //  Tools.HandleResponse(reach.response (),new JsonObject(),"There was an error",false,true,true) ;


    public void serveRoutes4(){
        Route route8 =  router.route() ;
        route8.method(HttpMethod.GET) ; 
        route8.path(new String("/services/users/find/all")) ; 
        route8.handler(
            new Handler<RoutingContext>( ){
                @Override 
                public void handle( RoutingContext active ){
                    Future<List<JsonObject>> future4 =  services.findAllUsers() ;
                    future4.onSuccess (
                        new Handler<List<JsonObject>>( ){
                            @Override 
                            public void handle(List<JsonObject> serial){
                              //  JsonArray arrays = new JsonArray(serial) ; 
                                Tools.HandleResponse (active.response(),serial,lines[0],true,true,true) ;
                            }
                        }
                    ).onFailure( 
                        new Handler<Throwable>(){
                            @Override 
                            public void handle(Throwable error){
                                Tools.HandleResponse( active.response(),new JsonObject( ),error.toString(),false,true,true)  ;
                            }  } ) ; 
                        } } ) ;  }  
        public void serveRoutes5(){
            Route route10 = router.route() ;  // try_resources ;   
             route10.path("/services/users/find/detail")  ; 
             route10.method(HttpMethod.GET) ;  
             route10.handler(new Handler<RoutingContext>(){
                @Override 
                public void handle(RoutingContext realms){
                    realms.request().body(
                        new Handler<AsyncResult<Buffer>>(){
                            @Override 
                            public void handle(AsyncResult<Buffer> asyncs5){
                                if(asyncs5.failed()){
                                    Tools.HandleResponse(realms.response(),new JsonObject(),asyncs5.cause().toString(),false,true,true)  ;
                                }else{
                                    JsonObject jsons4 =   asyncs5.result().toJsonObject()  ;
                                     Future<List<JsonObject>> futures5 =  services.findUserByDetail(jsons4.getString("detail"),jsons4.getString("value")) ; 
                                     futures5.onSuccess(
                                        new Handler<List<JsonObject>>(){
                                            @Override 
                                            public  void handle(List<JsonObject> listed){
                                                 Tools.HandleResponse(realms.response( ),listed,lines[0],true,true,true) ;
                                 } }) ; 
                                 futures5.onFailure(new Handler<Throwable>(){
                                    @Override 
                                    public void handle(Throwable thrown){
                                        Tools.HandleResponse(realms.response(),new JsonObject(),thrown.toString(),false,true,true) ;
                                    }
                                 }) ; 
                                }
                            }
                        }
                    ) ;
                }
             }) ; 
        }   }
