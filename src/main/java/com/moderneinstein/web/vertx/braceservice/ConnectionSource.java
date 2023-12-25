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
    // ,"mongodb+srv://Library4:TreeNode@cluster0.ffzof1d.mongodb.net/"} ;  
    //mongodb+srv://Library4:<password>@cluster0.ffzof1d.mongodb.net/ // 
    // mongodb+srv://<username>:<password>@cluster4.f9nwgne.mongodb.net/  //
    // "mongodb+srv://<username>:<password>@cluster4.f9nwgne.mongodb.net/?retryWrites=true&w=majority"
    //User4:TreeNode@cluster4.f9nwgne.mongodb.net +srv  :  // Certain@cluster4.  
    // "mongodb+srv://cluster4.vzrprp0.mongodb.net/?authSource=%24external&authMechanism=MONGODB-X509&retryWrites=true&w=majority" 
    //"mongodb+srv://<username>:<password>@cluster5.nip9d3w.mongodb.net/?retryWrites=true&w=majority";
    private static MongoClient cores = null ; 
    private static Vertx  point = null ;   
    public static String MongoOptions[] =  {"UserLogin4","mongodb://localhost/UserLogin4?connectTimeoutMS=120000",
    "mongodb+srv://Library4:TreeNode@cluster0.ffzof1d.mongodb.net/",
    "mongodb+srv://user4:TreeNode@cluster4.f9nwgne.mongodb.net/"
,"mongodb+srv://User4:TreeNode@cluster4.f9nwgne.mongodb.net/?retryWrites=true&w=majority"
,"mongodb+srv://cluster4.vzrprp0.mongodb.net/?authSource=%24external&authMechanism=MONGODB-X509&retryWrites=true&w=majority"
,"mongodb+srv://User4:TreeNode@cluster4.vzrprp0.mongodb.net/?retryWrites=true&w=majority"
,"mongodb+srv://User5:TreeNode@cluster5.nip9d3w.mongodb.net/?retryWrites=true&w=majority"
,"mongodb+srv://cluster5.nip9d3w.mongodb.net/?authSource=%24external&authMechanism=MONGODB-X509&retryWrites=true&w=majority"} ; 
    public static JsonObject createParts (){
        JsonObject  portions = new JsonObject() ;     
        String sample = CentralVerticle.properties.getProperty("mongodb-connection-string") ;
        portions.put("connection_string",sample) ; 
        portions.put("db_name",MongoOptions[0]) ; 
        portions.put("useObjectId",true) ; 
         return portions ;  
    } 
    // MongoOptions[7]// CentralVerticle.properties.getProperty (sample)
    public static  void configure(Vertx prior){
        point = prior   ;   
    }    
    //    cores = MongoClient.createShared(point,cores) ; 
    public static MongoClient deriveClient(Vertx previous){  
        configure(previous) ;
        if(cores==null){
              JsonObject objects = createParts() ; 
            cores = MongoClient.create(point,objects) ;  } //createShared(point,objects) ; } 
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
                        System.out.println("Collection created") ; 
                        System.out.println("Client created") ;
                     }
                }
            ) ; 
            future4.onFailure(
                new Handler<Throwable>(){
                    @Override 
                    public void handle(Throwable thrown){  
                        System.out.println(505) ; 
                        System.out.println(thrown.toString()) ;
                    }
                }
            ) ;  
        }  
        return  cores ; 
    }
}