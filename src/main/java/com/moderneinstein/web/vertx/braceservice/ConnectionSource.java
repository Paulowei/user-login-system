package  com.moderneinstein.web.vertx.braceservice ; 

import io.vertx.ext.mongo.MongoClient ;
import io.vertx.core.Handler  ;  
import io.vertx.core.Vertx  ; 
import io.vertx.core.json.JsonObject   ;
import io.vertx.core.Future ;  
import io.vertx.core.AsyncResult ; 

import java.lang.Throwable ; 
import java.lang.Thread ; 
import java.lang.Exception  ; 

public class ConnectionSource{
    // "UserLogin4","mongodb://localhost/Library4?connectTimeoutMS=120000
    private static MongoClient cores = null ; 
    private static Vertx  point = null ;   
    public static String MongoOptions[] =  {"UserLogin4","mongodb://localhost/UserLogin4?connectTimeoutMS=120000"} ; 
    public static JsonObject createParts (){
        JsonObject  portions = new JsonObject() ;   
        portions.put("connection_string",MongoOptions[1]) ; 
        portions.put("db_name",MongoOptions[0]) ; 
        portions.put("useObjectId",true) ; 
         return portions ;  
    }
    public static  void configure(Vertx prior){
        point = prior   ;   
    }    
    //    cores = MongoClient.createShared(point,cores) ; 
    public static MongoClient deriveClient(Vertx previous){  
        configure(previous) ;
        if(cores==null){
              JsonObject objects = createParts() ; 
            cores = MongoClient.createShared(point,objects) ; } 
        return cores ; 
    }    
    //   configure(previous)  ;
    public static MongoClient deriveSource(Vertx context,String collection){
         configure(context)  ;
        if (cores==null){
            JsonObject created =  createParts()  ;
            cores =  MongoClient.createShared( context,created) ; 
           Future<Void> future4 =  cores.createCollection(collection) ; 
            future4.onSuccess(
                new Handler<Void>(){
                    @Override 
                    public void handle(Void voided){

                    }
                }
            ) ; 
            future4.onFailure(
                new Handler<Throwable>(){
                    @Override 
                    public void handle(Throwable thrown){

                    }
                }
            ) ;
        }  
        return  cores ; 
    }
}