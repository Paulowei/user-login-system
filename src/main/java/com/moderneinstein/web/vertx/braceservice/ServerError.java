
package com.moderneinstein.web.vertx.braceservice ; 

import java.lang.Throwable ; 
import java.lang.Exception ; 
import java.util.Objects ;
import java.lang.StackTraceElement ; 

public class ServerError extends Exception{

    private String  internal  ; 
    
    public ServerError(String  verse ){ 
        super(verse) ;   
        this.internal = new String(verse) ; 
    }
    @Override 
    public String toString(){  
        String upper = super.toString() ;   
        String combine = upper.concat (new String("\n"))   ;
        upper= upper.concat(this.internal ) ;
        return  upper   ;    //  = = //this.internal ; 
    }
    
}
