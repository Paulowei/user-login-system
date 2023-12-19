package com.moderneinstein.web.vertx.braceservice ;


import io.vertx.core.buffer.Buffer  ; 
import io.vertx.core.json.JsonObject ;
import io.vertx.ext.auth.User;
import io.vertx.core.json.Json ;

import io.vertx.core.Future;
import io.vertx.core.Handler ;  
import io.vertx.core.http.HttpServerRequest ;
 import io.vertx.core.http.HttpServerResponse ;

import io.vertx.ext.auth.jwt.JWTAuth ; 
import io.vertx.ext.auth.User ; 
import io.vertx.core.AsyncResult ; 


import java.util.Vector ;
import java.util.ArrayList ; 
import java.io.Serializable ; 

import java.util.Map ;
import java.util.HashMap; 
import java.util.Iterator ; 
import java.util.Collection ; 


public class Tools{

	public static JsonObject combine(JsonObject gamma,JsonObject delta){
		JsonObject  zeta =  (JsonObject)gamma.copy() ; //clone() ; //  new JsonObject(gamma) ;  
		Map<String,Object> mapper = delta. getMap() ; //toMap() ; 
		Iterator<Map.Entry<String,Object>> iterator 
		=  mapper.entrySet().iterator() ; 
		while(iterator.hasNext ()){
			Map.Entry<String,Object> entry = iterator.next () ;
			if(zeta.containsKey(entry.getKey())){continue ; } 
		    zeta.put(entry.getKey(),entry.getValue()) ;
	}	 
	return zeta ; 
	}  
	
	public static JsonObject detach(JsonObject theta,JsonObject sigma){
		JsonObject  omega =  theta.copy() ;  // new JsonObject(theta) ;   
		Map<String,Object> tracks =  sigma.getMap() ; 
		Iterator<Map.Entry<String,Object>> iterator 
		= tracks.entrySet().iterator() ; //theta.getMap(). ; 
		while(iterator.hasNext()){
			Map.Entry<String,Object> point = iterator.next() ; 
			if(omega.containsKey(point.getKey())){  
				omega.remove(point.getKey()) ;  
				}
			}
			return omega ; 
 	}
	// Json   
	public static void HandleResponse(HttpServerResponse response,Object body,String message,boolean success,boolean chunked,boolean terminate){  
		response.setChunked(chunked) ;   
		JsonObject parts = new JsonObject( ).put("success",success ) ;  
		parts.put("message",message) ; 
		parts.put ("payload",body) ;
		response.write(parts.toString()) ; 
		if(terminate==true){response.end( ) ; }  
	}  
	public  static Collection<JsonObject> filter(String value,Collection<JsonObject> serial){
		Iterator<JsonObject> iterator = serial.iterator() ; 
		Collection<JsonObject> collector = new ArrayList<JsonObject>() ; 
		while(iterator.hasNext()){
			JsonObject parts =iterator.next().copy() ;
			if( parts.containsKey(value))
			{Object temps = parts.remove(value) ;} 
			collector.add (parts) ; 
		}
		return collector ; 
	}  
	     public static void  test(JsonObject objects,JWTAuth jwts){
            Future<User> futures5 =  jwts.authenticate(objects)  ;
            futures5.onSuccess( 
                new Handler<User>(){
                    @Override
                    public void  handle(User user4){
                        System.out.println(user4.toString() ) ; 
                    }
                }
            ) ;  
            futures5.onFailure( 
                new Handler<Throwable>(){
                    @Override 
                    public void handle(Throwable thrown){
                        System.out.println (thrown.toString( )) ; 
                    }
                }
             ) ;  
//return static ;   
        } 
}
