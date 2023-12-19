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


public class  UserEndpoint{


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
             serveRoutes4( ); 
              serveRoutes5( ) ;  
              serveRoutes6( )  ; 
            serveRoutes7( ) ;   
            serveRoutes8() ;   
            serveRoutes9()  ;
            serveRoutes10() ; 
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
   public UserEndpoint(Vertx environ,Router lanes,boolean begin){
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
                                        if(pending.succeeded()){
                                Future<JsonObject> possible = services.editUserOptions(input.getJsonObject("user"),input.getJsonObject("options")) ; 
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
                            Future<JsonObject> futures3 = services.editUserOptions(jsons3.getJsonObject("user"),jsons3.getJsonObject("options")) ;
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
                                            Future<JsonObject> confirms = services.deleteUserOptions(jsons4.getJsonObject("user"),jsons4.getJsonObject("options")) ; 
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
    // Tools.HandleResponse(context.response(),pending.result (),"The request was successful",true,true,true) ; 
    //  route6.path("/services/users/edituser/:email/:types/:value") ;  
    public void serveRoutes6(){
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
         Future<JsonObject> updates = services.editUserByEmail(params.get("email"),params.get("types"),params.get("value")) ;  
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
    // :method/:value  // ,params.get("method"),params.get("value")  // "The request was successful"
    //  Tools.HandleResponse(reach.response (),new JsonObject(),"There was an error",false,true,true) ;
    public void serveRoutes7( ){
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
                    Future<JsonObject> updates =   services.deleteUser(jsons5.getJsonObject("user")) ; 
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
    public void serveRoutes8(){
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
    public void serveRoutes9(){
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
                                                Future<JsonObject> futures6 = services.editUserCrypt(jsons3.getJsonObject("user"),jsons3.getString("type"),jsons3.getString("value")) ;  
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
    
        public void serveRoutes10(){
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
