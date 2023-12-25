package com.moderneinstein.web.vertx.braceservice ;

import java.util.ArrayList ; 
import java.util.Set ; 
import java.util.Objects ; 
import java.util.Arrays ; 
import java.util.Iterator ; 

import io.vertx.ext.mongo.MongoClientUpdateResult ; 
import io.vertx.ext.mongo.MongoClient ;  
import io.vertx.ext.mongo.MongoClientDeleteResult  ; 

import io.vertx.core.Handler ;
import io.vertx.core.Promise ;
import io.vertx.core.Vertx ;

import io.vertx.core.Handler ;
import io.vertx.core.AsyncResult ;
import io.vertx.core.Future ;  
import io.vertx.core.json.JsonObject ; 

import io.vertx.ext.auth.HashingAlgorithm ;
import io.vertx.ext.auth.HashingStrategy   ; 

import java.util.Map ;
import java.util.TreeMap ;
import java.util.HashMap ; 

import java.util.Collection ; 
import java.util.List ; 
import java.util.Vector ; 
import java.util.LinkedList ;


public class UserServices  {

    public MongoClient central  ; 
    public static String bases = new String("Users6"); //("Users4")  ;   
    public Vertx context ;      
     private static HashingStrategy strategy  ;   
     private static String[] methods = new String[] {"sha512","pbkdf2"} ;  //HashingString
     private static String hashSalt = new String("HashingSaltHashingString") ;  
     private String Algorithm  ; 
     private String HashingSalt ;
   //  new String("HashingStringHashingStringHashreagertdhrewgrsewgawrgthjzehtrsWRetsezRets
   //zhREtbwfraethsrjerewGrbaethsrsvgfweadfgbbgrzdgbzshrtegthtgrhsgzdfsdfggweqergsewrgfhgtf") ;  
     private Map<String,String> mapper ; 

    /*         central.createCollection(bases).onSuccess(
            new Handler<Void>(){
                @Override 
                public void handle(Void voided){
                    //  
                    System.out.println("Client Created") ;
                }
            } */  
       //  this.central = ConnectionSource.deriveClient(parent,bases) ;  // source ;  
    public UserServices (Vertx  parent){
        this.central = ConnectionSource.deriveSource(parent,bases) ;  // source ;     
         this.context = parent ;       
        strategy = HashingStrategy.load( ) ;   
        HashingSalt =   CentralVerticle.properties.getProperty("hashing-salt"); 
        Algorithm =  CentralVerticle.properties.getProperty("hashing-algorithm") ;

      //  System.out.println(33) ; 

      //   );
    }
    //        JsonObject primaries = new JsonObject().put("email",toFind.getEmail())  ;  
    //    Promise<JsonObject> premise)  //  void  //  ,Promise<Boolean> premise
    public Future<Boolean>  ensureNotFound(JsonObject toFind){ 
        JsonObject primaries =  toFind.copy() ; 
         // toFind.clone() ; //new JsonObject().put("email",toFind.getEmail())  ; 
          Future<List<JsonObject>> futures =  central.find(bases,primaries) ;  
          Promise<Boolean> promises4 = Promise.promise() ; 
        futures.onSuccess(
            new  Handler<List<JsonObject>>(){
                @Override 
                public void handle(List<JsonObject> jsons){
                    if(jsons!=null&&jsons.size ()>0){
                     //   premise.fail(new  ServerError("The credentials were found previously")) ;
                         promises4 .complete(true ) ;  }
                    if(jsons.size()==0){promises4.complete(false) ;}
                }
            }  
        ) ; 
        futures.onFailure(
            new Handler<Throwable>(){
                @Override 
                public void handle(Throwable error){
                 promises4.fail(error) ; ///   premise.fail (error) ; 
                }
            }
        ) ;  
             return promises4.future()  ;  
    }  
   // public Stream<Map.Entry<String,Object>> stream(){} ; 
  //  public Iterator<Map.Entry<String,Object>> iterator(){}  ;
 //   public <F> JsonObject mapFrom(F  verse){} ; 
 //   public <C> C mapTo(Class<C> templates){} ;   
 // public Future<BraceUser> saveUser(BraceUser voltage){  //   
    //,promises ) ; // plans) ;  //     new Handler<AsyncResult<JsonObject>>()
      //  if(!plans.future().failed()){return plans.future() ; } plans.    
          //  Promise<Boolean>  promises = Promise.promise() ;   //   promises 
     public Future<JsonObject> saveUser(JsonObject voltage){
        JsonObject initial = voltage.copy() ;   // voltage.clone( ) ; 
       Promise<JsonObject> plans = Promise.promise() ;  
        Future<Boolean> futures5 =  ensureNotFound(initial ) ; 
         futures5.onComplete(     
        new Handler<AsyncResult<Boolean>>(){
            @Override 
            public void handle(AsyncResult<Boolean> causes){
            if(causes.failed()){
                  plans.fail(causes.cause() )  ;
                  return ;     }    
        if(causes.result()==true ){
            plans.fail(new ServerError("The Details were found previously")) ; 
            return   ; }
         central.save(bases,initial).onComplete(
            new Handler<AsyncResult<String>>(){
                @Override 
                public void handle(AsyncResult<String> pending){
                    if(pending.succeeded()){
                        JsonObject altered =  new JsonObject().put("$set", new JsonObject().put("idenity",pending.result()))  ; 
                        central.updateCollection(bases,initial,altered).onSuccess(
                            new Handler<MongoClientUpdateResult>(){
                                @Override 
                                public void handle(MongoClientUpdateResult result){
                                     initial.put("identity",pending.result()) ; 
                                     plans.complete(initial) ; 
                                } }   ) ; } }  } ) ; }} ) ;  
         return plans.future( ) ;  
    }    
    //   plans.succeed(Utilities.deSerialiseUser(initial)) ; 
    /* JsonObject initial =Serializers.serialise(voltage) ; 
       Promise<BraceUser> plans = Promise.promise() ; 
        ensureNotFound(new JsonObject().put("email",voltage.getEmail()),plans) ;  
        ensureNotFound(new JsonObject().put("username"),voltage.getUserName(),plans) ;
        JsonObject source  JsonObject jsons =  source.clone () ;   */
    public Future<JsonObject>  findUserById(String identity){
            Promise<JsonObject>  frames = Promise.promise() ; 
             JsonObject jsons =  new JsonObject().put("identity",identity) ; 
            central.find(bases, jsons,
            new Handler<AsyncResult<List<JsonObject>>>(){
                @Override
                public void  handle(AsyncResult<List<JsonObject>> pending){
                    if(pending.succeeded()){
                        frames.complete(pending.result().get(0)) ;
                    }else{frames.fail(pending.cause()) ; }
                }
            }) ;   
           return  frames.future() ; 
    }
    public Future<JsonObject> updateUser(String  email,JsonObject changes){
        JsonObject interest = new JsonObject().put("email",email) ;
        JsonObject  delta = new JsonObject().put("$set",changes.copy()) ;  
        Promise<JsonObject> promise4 = Promise.promise() ; 
       Future<MongoClientUpdateResult> yields = central.updateCollection(bases,interest,delta) ;  
    yields.onComplete(
        new Handler<AsyncResult<MongoClientUpdateResult>>(){
            @Override 
            public void handle(AsyncResult<MongoClientUpdateResult> asyncs){
                 if(asyncs.failed()){
                    promise4.fail(asyncs.cause()) ; 
                 }else{
                    JsonObject objects =  asyncs.result().toJson() ;
                    promise4.complete(objects) ;  }  }  }  ) ;   
    return   promise4.future() ; 
    }
    public Future<List<JsonObject>> findUsersByUserName(String username){
        JsonObject similar = new JsonObject().put("username",username) ; 
         Promise<List<JsonObject>> unknown = Promise.promise () ;
         Future<List<JsonObject>> futures = central.find(bases,similar) ; 
        futures.onSuccess(new Handler<List<JsonObject>>(){
            @Override 
            public void handle(List<JsonObject> listed){
                Collection<JsonObject> collected =   Tools.filter("password",listed) ;
                unknown.complete(new LinkedList<JsonObject>(listed)) ;   }  } ) ; 
        futures.onFailure(new Handler<Throwable>( ){
            @Override 
            public void handle(Throwable thrown){
                unknown.fail(thrown ) ; } }) ; 
        return  unknown.future( ) ; 
    }     
    //      //    objects.remove("password") ; 
    public Future<JsonObject> findUserByEmail(String email){
            JsonObject input =   new JsonObject().put("email",email) ; //email.clone() ;
            Promise<JsonObject> premise = Promise.promise() ;
            Future<List<JsonObject>> found = central.find(bases,input) ;
            found.onSuccess ( 
                new Handler<List<JsonObject>>(){
                    @Override 
                    public void handle(List<JsonObject> objects){     
                        if(objects.size()>=0){
                            JsonObject present = objects.get(0) ; 
                            present.remove("password")  ;
                          premise.complete(present) ; //  premise.complete(objects.get(0)) ;
                    } }}) ; 
            found.onFailure(
                new Handler<Throwable>(){
                    @Override 
                    public void handle(Throwable thrown){
                        premise.fail(thrown) ; } } ) ; 
            return premise.future() ; 
    }     
    //      //      promises.complete(asyncs.result( )) ;     
    // strategy.verify(gotten.getString("password") 
    // ,similar.getString ("password") )        System.out.println(55) ;  
    //   System.out.println(33) ;        System.out.println(66) ;   
    //         System.out.println("Correct") ;  // System.out.println(22) ; 
    public Future<JsonObject> findUserCrypt(JsonObject special){
        JsonObject similar =  special.copy() ;  //special.clone() ;
        Promise<JsonObject> promises = Promise.promise( ) ; 
        JsonObject attempt = new JsonObject( ).put("email",similar.getString("email")) ; 
      //  System.out.println(special.toString( )) ; 
        central.find(bases,attempt).onComplete(  //similar,
         new Handler<AsyncResult<List<JsonObject>>>(){
            @Override 
            public void handle( AsyncResult<List<JsonObject>> asyncs){  
                if(asyncs.succeeded ()&&asyncs.result( ).size()>0){  
              JsonObject gotten = asyncs.result().get(0) ;     
              boolean cases = strategy.verify (gotten.getString("password"),similar.getString("password")) ;
              if(cases==true){  
               gotten.remove("password") ;  
               //   JsonObject other = Tools.combine(similar,gotten)  ;  other.remove("password") ;
                 promises.complete(gotten) ;   // (similar) ;
              }else{ promises.fail(new ServerError(new String("User not found"))) ; }
                }else{    
                promises.fail(asyncs.cause()) ; }   }  }) ;  
         return promises.future( )  ; 
    }     
    //  JsonObject others =  // input.clone().put ("message",pending.result()) ;    
    // AsyncResult<MongoClientUpdateResult> asyncs  
          //  System.out.println(input.toString()) ; //               //,plans) ; 
          //  if(plans.future().failed()){return  plans.future() ; }  
          // String  hashString = strategy.hash(methods[1],mapper,hashSalt,password) ;  
    public Future<JsonObject> saveUserCrypt(JsonObject  input){  
            String  password = input.getString("password") ;   
            String  hashString = strategy.hash(Algorithm,mapper,HashingSalt,password) ;  
            JsonObject clones =  input.copy() ;  clones.remove("password") ;   
            clones.put("password",hashString) ;     
            Promise<JsonObject> plans = Promise.promise () ;    
            ensureNotFound(new JsonObject().put("email",input.getString("email"))).onComplete(
                new Handler<AsyncResult<Boolean>>(){
                    @Override 
                    public void handle(AsyncResult<Boolean> pending4){
                if(pending4.failed()){
                    plans.fail(pending4.cause()) ; 
                    return  ;   }  
                if(pending4.result()==true){  // ("Credentials not found previously")
                 plans.fail(new ServerError("Credentials were found previously")) ;
                return    ;      }
            Future<String> ahead  =  central.save(bases,clones ).onComplete(
                new Handler<AsyncResult<String>>(){
                    @Override
                    public void handle(AsyncResult<String> pending){
                        if(pending.succeeded()){
                            JsonObject nexts =   new JsonObject().put("identity",pending.result())  ; 
                            central.updateCollection(bases,clones,new JsonObject().put("$set",nexts),
                            new Handler<AsyncResult<MongoClientUpdateResult>>(){
                                @Override 
                                public void handle(AsyncResult<MongoClientUpdateResult> pending2){
                                    if(pending2.succeeded()){
                                        JsonObject goals = input.copy().put("identity",pending.result( )) ; 
                                        goals.remove("password") ; plans.complete(goals) ; 
                                    }else{plans.fail(pending2.cause())  ;   }
                                }
                            }) ; 
                          //   plans.succeed(others) ; 
                        }else{plans.fail(pending.cause()) ;  }  }  } ) ;  }}); 
            return plans.future() ; 
     }       
     //  JsonObject nexts =  clones.copy().put("identity",pending.result())  ; 
     public static JsonObject combine(JsonObject jsons4,JsonObject jsons5){
			Iterator<Map.Entry<String,Object>> iterator = jsons5.iterator() ; 
			JsonObject created = jsons4.copy() ;  // new JsonObject(jsons4) ;
			while(iterator.hasNext()){
				Map.Entry<String,Object> parts = iterator.next() ; 
				if(created.containsKey(parts.getKey())){continue ;  }  
				created.put(parts.getKey(),parts.getValue()) ; 
				}   
				return created ; 
 		 }   
 		 /* Object objects =  jsons1.getObject("options") ; 
			 Map<String,Object> mappers =  (Map<String,Object>)objects; */
	public Future<JsonObject> editUserOptions(JsonObject user,JsonObject options){
	JsonObject clones1 = new JsonObject().put("email",user.getString("email")) ; 
	 Promise<JsonObject> promises = Promise.promise();   
     //   System.out.println(44) ; 
	 Future<JsonObject> found  =  findUserByEmail(user.getString("email")) ;  // findUserCrypt( user )  ; //  central.findOne (bases,clones1) ;  
	 found.onSuccess(
	 new Handler<JsonObject>(){
		 @Override 
		 public void handle(JsonObject jsons1){
			JsonObject mains = jsons1.getJsonObject("options", new JsonObject( )) ; 
			JsonObject altered = Tools.combine(mains,options.copy()) ; 
			JsonObject changes = new JsonObject().put("$set",new JsonObject().put("options",altered))  ;   
			central.updateCollection(bases,clones1,changes,
			new Handler<AsyncResult<MongoClientUpdateResult>>(){
				@Override 
				public void handle(AsyncResult<MongoClientUpdateResult> pending3){
					if(pending3.succeeded( )){
					  promises.complete(new JsonObject().put("message",pending3.result().toJson()));
					}else{promises.fail(pending3.cause()) ;}}}) ;
			  }}).onFailure( 
                new Handler<Throwable>( ){
                    @Override 
                    public void  handle (Throwable thrown){
                        promises.fail(thrown) ;
                    }
                }
              ) ;   
			  return  promises.future() ; 
		} 
        // String email,JsonObject remove  
              //    System.out.println(previous.toString()) ;   
         //    System.out.println(asyncs.result().toString()) ;   
             //   System.out.println(44) ; 	// searches.findOne(bases,searches, 
        	//	JsonObject searches  = new JsonObject( ).put("email",email) ; 
		public Future<JsonObject> deleteUserOptions(JsonObject user,JsonObject remove ){  
			Promise<JsonObject> futures = Promise.promise() ;  
           Future<JsonObject> found =  findUserByEmail(user.getString("email"))  ; // findUserCrypt(user)  ;    
			found.onComplete( new Handler<AsyncResult<JsonObject>>(){
				 @Override 
				 public void handle(AsyncResult<JsonObject> asyncs){
					 if( asyncs.succeeded()){   
						 JsonObject previous =  asyncs.result().getJsonObject("options",new JsonObject()) ;   
						 JsonObject  created = Tools.detach(previous,remove.copy()) ;   System.out.println(created.toString()) ; 
						 JsonObject queries = new JsonObject().put("$set",new JsonObject().put("options",created)); 
						 central.updateCollection(bases,asyncs.result(),queries).onComplete(
						 new Handler<AsyncResult<MongoClientUpdateResult>>(){
							@Override 
							public void handle(AsyncResult<MongoClientUpdateResult> pending3){
								if(pending3.succeeded()){
									   futures.complete( pending3.result().toJson() )  ;
									}else{futures.fail(pending3.cause()) ;} }}) ;  
					 }else{futures.fail(asyncs.cause()) ; }} } ) ;
					 return futures.future() ; 
			}
        public Future<JsonObject> editUserUsername(String email,JsonObject changes){
            JsonObject jsons3 = new JsonObject().put("email",email )  ; 
            Promise<JsonObject> promise4 = Promise.promise(  ); 
            JsonObject lanes =  new JsonObject().put("$set",new JsonObject().put("username",changes.getString("username"))) ;  
            central.updateCollection(bases,jsons3,lanes,
            new Handler<AsyncResult<MongoClientUpdateResult>>(){
                @Override 
                public void handle (AsyncResult<MongoClientUpdateResult> pending2){
                    if(pending2.succeeded()){
                        promise4.complete( pending2.result().toJson( )) ; 
                 }else{promise4.fail(pending2.cause( )) ;}
                }
            }) ;  
            return  promise4.future( ) ;  
        }     
        //  JsonObject delta = new JsonObject().put("$set",new JsonObject( ).put("address"),alters.getString("address")) ; 
       // public Future<JsonObject>  editUser(String email,JsonObject alters){ 
        public Future<JsonObject> editUserByEmail(String email,String types,String value){
            JsonObject jsons2 =  new JsonObject( ).put("email",email)  ; 
            JsonObject changes= new JsonObject().put("$set",new JsonObject().put(types,value)) ;
            Promise<JsonObject> premise3 = Promise.promise()  ; 
            Future<MongoClientUpdateResult> futures4 =  central.updateCollection(bases,jsons2,changes ) ;  
            futures4.onFailure( 
                new  Handler<Throwable>(){
                    @Override 
                    public void  handle(Throwable error){
                        premise3.fail(error) ; 
                    }   }  )
                    .onSuccess(
                        new Handler<MongoClientUpdateResult>( ){
                            @Override 
                            public void handle(MongoClientUpdateResult results){
                                premise3.complete(results.toJson()) ; }} ) ;  
            return premise3.future () ;
        }
      //  public Future<JsonObject> deleteUser(String email ){      
        //   handle (AsyncResult<JsonObject> asyncs5)  
        //  source.copy(); // new JsonObject( ).put("email" , email )  ;  
        //findUserCrypt(source) 
        public Future<JsonObject>  deleteUser (JsonObject source){
            Promise<JsonObject>  promises1  = Promise.promise( ) ; 
            JsonObject deleted  = new JsonObject().put("email",source.getString("email")) ;
            findUserByEmail(source.getString("email")).onComplete(
                new Handler<AsyncResult<JsonObject>>(){
                    @Override 
                    public void handle (AsyncResult<JsonObject>  pending5){
                    if(pending5.failed()){
                        promises1.fail(pending5.cause( )) ; 
                    }else{
            Future<MongoClientDeleteResult> futures2 =  central.removeDocument(bases,deleted) ; 
            futures2.onFailure(
                new Handler<Throwable>( ){
                    @Override  
                    public void handle(Throwable thrown){
                 promises1.fail(thrown) ; }  } ).onSuccess(
                new Handler<MongoClientDeleteResult>(){
                    @Override 
                    public void handle(MongoClientDeleteResult results3){
                        promises1.complete(results3.toJson()) ;  } }) ;  
        } }}) ; 
            return  promises1.future() ;  
        }    
        public Future<List<JsonObject>> findAllUsers(){
            Promise<List<JsonObject>> promise5 = Promise.promise ()  ; 
            JsonObject jsons4 =  new JsonObject( ) ;
            Future<List<JsonObject>> future3 =  central.find(bases,jsons4) ; 
            future3.onFailure(
                new Handler<Throwable>( ){
                    @Override 
                    public void handle(Throwable  boomerang){
                        promise5.fail(boomerang) ;
                    }
                }
            ).onSuccess( 
                new Handler<List<JsonObject>>( ){
                    @Override 
                    public void handle(List<JsonObject> listed){  
                        Collection<JsonObject> collected = Tools.filter(new String("password"),listed) ;
                        promise5.complete(new Vector<JsonObject>(collected)) ; 
                    }
                }
            ) ; 
            return   promise5.future() ;
        }  
        public Future<List<JsonObject>> findUserByDetail(String detail,String value){
            Promise<List<JsonObject>> promise5 = Promise.promise() ; 
            JsonObject check = new JsonObject().put(detail,value) ; 
            central.find(bases,check,
            new Handler<AsyncResult<List<JsonObject>>>(){
                @Override 
                public void  handle(AsyncResult<List<JsonObject>> pending3){
                    if(pending3.succeeded()){
                         Collection<JsonObject> filtered = Tools.filter("password",pending3.result()) ; 
                        promise5.complete(new Vector<JsonObject>(filtered)) ; 
                    }else{      
                        promise5.fail(pending3.cause()) ;
                    } } } )  ;  
                return promise5.future() ; 
        }   
    public Future<JsonObject> editUserCrypt(JsonObject user,String key,String value){
          Future<JsonObject> futures3 =  findUserByEmail( user.getString("email")) ; // findUserCrypt(user) ; 
         JsonObject changes = new JsonObject().put("$set",new JsonObject().put(key, value)) ; 
         Promise<JsonObject> promise3 = Promise.promise()  ;
        futures3.onComplete(
            new Handler<AsyncResult<JsonObject>>(){
                @Override 
                public void handle(AsyncResult<JsonObject> asyncs4){
                     if(asyncs4.succeeded()){
                      Future<MongoClientUpdateResult> futures4 = central.updateCollection(bases,asyncs4.result(),changes) ; 
                    futures4.onFailure(
                        new Handler<Throwable>(){
                            @Override 
                            public void handle(Throwable error){
                                promise3.fail(error) ;} } )
                                .onSuccess(new Handler<MongoClientUpdateResult>(){
                                    @Override  
                                    public void handle(MongoClientUpdateResult  update4){  
                                        System.out.println(update4.toJson().toString()) ; 
                                        promise3.complete(update4.toJson()) ;
                                    }
                                }) ;  
                     }else{promise3.fail(asyncs4.cause().toString()) ;}
                }
            })   ;
            return  promise3.future() ; //  futures3  ;
    }
      //  public Future<JsonObject> updateUser
}
