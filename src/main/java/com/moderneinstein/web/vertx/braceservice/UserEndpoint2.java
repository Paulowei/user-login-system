package com.moderneinstein.web.vertx.braceservice;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router; 
import io.vertx.ext.web.Route ;

import java.util.Map;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future ;
import io.vertx.core.Handler;
import io.vertx.ext.auth.User ;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest ;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext ; 



public class UserEndpoint2 {
     public  Vertx context ; 
    private UserServices services ; 
     public  Router router ; 
     private JWTAuth jwts  ;     
     public static String[] lines = {"The request was successful","The request was not successful","Error parsing Json"} ;
     public  void noticeRoutes (Handler<Void> handles){
        new Thread(){
          @Override 
          public void run(){
        //    serveRoutes1( ) ;   
         //   serveRoutes2( ) ;  
        //    serveRoutes3( ) ;  
            serveRoutes1( ); 
             serveRoutes2( ) ;  
             serveRoutes3( )  ; 
           serveRoutes4( ) ;   
       //    serveRoutes8() ;   
           serveRoutes5()  ;
        //   serveRoutes10() ; 
        //  serveRoutes6() ;  
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
     public UserEndpoint2(Vertx environ,Router lanes,boolean begin){
            services = new UserServices(environ)  ;
            context = environ   ;  
            router =   lanes ;  // Router.router(environ) ;  
            if(begin==true){
                activate() ;   } 
            jwts =  AuthSource.deriveAuth(context ) ;  
     }
       public void serveRoutes1(){
        Route paths9 =  router.route()  ; 
        paths9.method(HttpMethod.POST) ; 
        paths9.path("/services/users/update") ; 
        paths9.handler(
            new Handler<RoutingContext>(){
                @Override 
                public void handle(RoutingContext intent){
                    intent.request().body().onFailure(
                        new Handler<Throwable>(){
                            @Override 
                            public void handle(Throwable error){
                                 Tools.HandleResponse(intent.response(),new JsonObject(),error.toString(),false,true,true) ; 
                            }
                        } 
                    ).onSuccess(
                        new Handler<Buffer>(){
                            @Override 
                            public void handle(Buffer buffers){ 
                                JsonObject jsons3 = buffers.toJsonObject() ;
                                Future<User> futures5 =  jwts.authenticate(jsons3) ;
                                 futures5.onComplete(
                                    new Handler<AsyncResult<User>>(){
                                        @Override 
                                        public void handle(AsyncResult<User> asyncs4){
                                            if(asyncs4.failed()){
                                              Tools.HandleResponse(intent.response(),new JsonObject(),asyncs4.cause().toString(),false,true,true) ; 
                                            }else{  
                                                JsonObject types = asyncs4.result().attributes() ; // jsons3.getJsonObject("user")
                                                Future<JsonObject> futures6 = services.editUserCrypt(types.getJsonObject("accessToken"),jsons3.getString("types"),jsons3.getString("value")) ;  
                                                futures6.onComplete( 
                                                    new Handler<AsyncResult<JsonObject>>(){
                                                        @Override 
                                                        public void handle(AsyncResult<JsonObject> pending4){ 
                                                            if(pending4.failed()){
                                                    Tools.HandleResponse(intent.response(),new JsonObject(),pending4.cause().toString(),false,true,true) ;            
                                                            }else{
                                                            Tools.HandleResponse(intent.response(),pending4.result(),lines[0],true,true,true) ;  }
                                                        }
                                                    }
                                                );
                                            }
                                        }
                                    }
                                 ) ; 
                            }
                        }
                    ) ; 
                } }  ) ;  
    }    
     public void serveRoutes2( ){
        Route paths7 =  router.route() ;   
        paths7.method(HttpMethod.DELETE) ; 
        paths7.path(new String("/services/users/deleteuser/:email")) ; 
        paths7.handler( 
            new Handler<RoutingContext>(){
                @Override 
                public void handle(RoutingContext reach ){ 
                    Map<String,String> mapper = reach.pathParams() ;  
                    reach.request().body( ).onFailure( 
                        new Handler<Throwable>(){
                            @Override 
                            public void handle(Throwable thrown){
                  Tools.HandleResponse(reach.response(), new JsonObject( ), thrown.toString( ),false,true,true) ;  
                            }
                        }
                    ).
                    onSuccess( 
                        new Handler<Buffer>(){
                            @Override 
                            public void  handle(Buffer buffs){  
                                JsonObject  jsons5 = buffs.toJsonObject( ) ; 
                    jwts.authenticate(jsons5).onComplete(
                        new Handler<AsyncResult<User>>( ){
                            @Override 
                            public void handle( AsyncResult<User> asyncs){    
                        JsonObject jsons4 = asyncs.result().attributes() ;
                    Future<JsonObject> updates =   services.deleteUser(jsons4.getJsonObject("accessToken")) ;//(jsons5.getJsonObject("user")) ; 
                     //(jsons5.getString("email"))  ;  //(mapper.get("email")) ; 
                    updates.onSuccess(
                         new Handler<JsonObject>(){
                            @Override 
                            public void handle(JsonObject jsons2){
                                Tools.HandleResponse( reach.response(),jsons2,lines[0],true,true,true) ;
                            }  }   ) ;
                    updates.onFailure(
                        new Handler<Throwable>(){
                            @Override 
                            public void handle(Throwable thrown ){
                                Tools.HandleResponse(reach.response (),new JsonObject(),thrown.toString(),false,true,true) ;
                            }   }   ) ;
       }  }  ) ; 
         }
        } ); 
             }  }
               ) ;  }   
               public void serveRoutes3(){
                Route route6 = router.route() ;
                route6.method(HttpMethod.POST) ; 
                route6.path("/services/users/edituser/:email/:types/:value") ;  
                route6.handler(
                    new Handler<RoutingContext>(){
                        @Override 
                        public void handle(RoutingContext context){
                            Map<String,String> params = context.pathParams( ) ; 
                            context.request( ).body().onSuccess( 
                                new Handler<Buffer>( ){
                                @Override 
                                public void handle(Buffer body){ 
                                JsonObject  jsons4 = body.toJsonObject() ;  
                        jwts.authenticate(jsons4).onComplete(
                            new Handler<AsyncResult<User>>( ){
                                @Override 
                                public void handle (AsyncResult<User> depend4){ 
                                    if(depend4.succeeded()){  
                            JsonObject dues =  depend4.result().attributes().getJsonObject("accessToken")  ; /// params.get("email")
                     Future<JsonObject> updates = services.editUserByEmail(dues.getString("email"),params.get("types"),params.get("value")) ;  
                            updates.onComplete(
                                new Handler<AsyncResult<JsonObject>>(){
                                    @Override 
                                    public void handle(AsyncResult<JsonObject> pending4){
                                         if(pending4.succeeded()){ Tools.HandleResponse(context.response(),pending4.result (),lines[0],true,true,true) ; }   
                                         if(pending4.failed()){Tools.HandleResponse(context.response(),new JsonObject(),pending4.cause().toString(),false,true,true) ;  }  
                                    }    
                                }
                             ) ; 
                            }else{Tools.HandleResponse(context.response( ),new JsonObject( ),depend4.cause().toString( ),false,true,true) ;  }   }    }) ; } })
                             .onFailure(
                                new Handler<Throwable>(){ 
                                    @Override 
                                    public void handle(Throwable errors){ 
                                        Tools.HandleResponse(context.response(),new JsonObject(),errors.toString( ),false ,true,true ) ;
                                    }
                                }) ; 
                        }
                    }
                ) ;
                }    
                public void serveRoutes4(){
                    Route route4 = router.route() ; 
                    route4.path("/services/users/editoptions") ; 
                    route4.method(HttpMethod.POST) ; 
                    route4.handler(
                        new Handler<RoutingContext>(){
                            @Override 
                            public void handle(RoutingContext context){
                             context.request().body().onSuccess(
                                new Handler<Buffer>(){
                                    @Override 
                                    public void handle(Buffer buffer){
                                         JsonObject jsons3 =  buffer.toJsonObject() ;
                                         Future<User> futures2 = jwts.authenticate(jsons3) ; 
                                         futures2.onComplete(
                                            new Handler<AsyncResult<User>>(){
                                                @Override 
                                                public void  handle(AsyncResult<User> pending3){           
                                                    if(pending3.succeeded()){   // getJsonObject("user")      
                                                System.out.println(pending3.result().principal())  ;        
                                             //   System.out.println(pending3.result().attributes()) ; 
                                                System.out.println(pending3.result().subject()) ;  //jsons3.getJsonObject("user") ; 
                                                JsonObject recent = pending3.result().attributes()     ;    
                                        Future<JsonObject> futures3 = services.editUserOptions(recent.getJsonObject("accessToken"),jsons3.getJsonObject("options")) ;
                                                futures3.onSuccess(new Handler<JsonObject>(){           
                                                    @Override       
                                                    public void handle(JsonObject jsons4)    
                                                    {Tools.HandleResponse(context.response(),jsons4,lines[0],true,true,true) ;}
                                                }).onFailure(
                                                    new Handler<Throwable>(){
                                                        @Override 
                                                        public void handle(Throwable thrown)
                                                        {Tools.HandleResponse(context.response( ),new JsonObject(),thrown.toString (),true,true,true  ) ; }  }  ) ;
                                                    }else{Tools.HandleResponse(context.response( ),new JsonObject(),pending3.cause().toString(),false,true,true)  ;   }
                                                }
                                            }
                                         ) ;
                                    }
                                }
                             )  ;         
                            }
                        }
                    )  ;
                }   
                public void serveRoutes5(){
                    Route paths5 = router.route( ) ; 
                    paths5.method(HttpMethod.DELETE) ;  
                    paths5.path("/services/users/deleteoptions") ; 
                    paths5.handler (
                        new  Handler<RoutingContext>( ){
                            @Override 
                            public void handle(RoutingContext routing){
                                Future<Buffer> futures2 = routing.request().body() ;   
                               //    System.out.println(55) ;
                                futures2.onComplete(new Handler<AsyncResult<Buffer>>(){
                                    @Override 
                                    public void  handle(AsyncResult<Buffer> asyncs2){
                                        if(asyncs2.succeeded()){
                                            JsonObject jsons4 = asyncs2.result().toJsonObject() ;    
                                            jwts.authenticate(jsons4).onSuccess(
                                                new Handler<User>( ){
                                                    @Override 
                                                    public void handle(User users3){  
                                                  //      System.out.println(jsons4.getJsonObject("options")) ;  
                                                  JsonObject created = users3.attributes().getJsonObject("accessToken") ; //jsons4.getJsonObject("user"),
                                                        Future<JsonObject> confirms = services.deleteUserOptions(created,jsons4.getJsonObject("options")) ; 
                                                        confirms.onFailure(
                                                            new Handler<Throwable>(){
                                                                @Override 
                                                                public void handle(Throwable thrown )
                                                                {Tools.HandleResponse(routing.response(), new JsonObject( ),thrown.toString(),false,true,true) ; }
                                                            }
                                                        ).onSuccess(
                                                            new  Handler<JsonObject>(){
                                                                @Override 
                                                                public void handle(JsonObject objects){
                                                  Tools.HandleResponse(routing.response(),objects.copy(),lines[0],true,true,true) ;   }
                                                            } )   ;  }
                                                }
                                            ).onFailure(new Handler<Throwable>(){
                                                @Override 
                                                public void handle(Throwable error){Tools.HandleResponse(routing.response(),new JsonObject(),error.toString(),false,true,true) ;}
                                            }) ; 
                                        }else{Tools.HandleResponse (routing.response( ),new JsonObject(),lines[2],false,true ,true) ;}
                                    }
                                }) ; 
                            }
                        }
                    ) ;
                }   
            }
