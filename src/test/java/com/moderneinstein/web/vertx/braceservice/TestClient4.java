package com.moderneinstein.web.vertx.braceservice  ;
import java.net.http.HttpClient ; 
import java.net.http.HttpRequest ; 
import java.net.http.HttpResponse ; 
//import java.net.http.BodyHandlers  ; 
import java.net.http.HttpHeaders ; 
import java.net.URI ; 
import java.time.Duration ;
import java.nio.file.Path ; 
import java.util.Map ; 
import java.util.List ; 
import java.util.Vector ; 
import java.util.ArrayList  ; 

//Use these strings to send API requests to the main server ; 
//"http://localhost:8040/salesman/absolute/4/R/C/"
public class TestClient4{
	

	public HttpRequest request ;
	public HttpClient client ; 
	public  HttpResponse<String> response ; 
	public static String useful = new String("http://localhost:8040/salesman/absolute/4/R/C/") ; 
	public static String prevail = new String("GET") ;
	public static HttpRequest.BodyPublisher publish = HttpRequest.BodyPublishers.ofString("7C6C9C4R5C8C1C8R6C4C3C7R9C2C7C8") ; 
	public static String worker  = new String("7C6C9C4R5C8C1C8R6C4C3C7R9C2C7C8") ;
	public static String colons = new String(" : ") ;   
	// 	publish =  HttpRequest.BodyPublishers.ofString(args[2]) ; 
	// 	build.method(args[0],publish)  ; 	//build.GET() ;
	public static void configure(TestClient4 tested, String[] args) throws Exception{
		if(args.length!=3){args= new String[]{prevail,useful,worker}; }
		 HttpClient.Builder built = HttpClient.newBuilder()  ; 
		built.version(HttpClient.Version.HTTP_2) ; 
		built.connectTimeout(Duration.ofMillis(6000));
		tested.client = built.build() ; 
		HttpRequest.Builder build = HttpRequest.newBuilder() ;
		build.uri(new URI(args[1])) ; 
 		build.version(HttpClient.Version.HTTP_2) ;
		build.method(args[0],HttpRequest.BodyPublishers.ofString(args[2])) ;
		tested.request = build.build() ; 
		}
		public static void checkHeaders(HttpResponse<String> derived){
			if(derived==null){return ;} 
			HttpHeaders headers = derived.headers() ;
			List<String> values = new Vector<String>( headers.map().keySet()) ; 
			Map<String,List<String>> collect = headers.map() ; 
			for(int fd=0;fd<values.size();fd++){
				String token = values.get(fd) ; 
				List<String> associate =  collect.get(token) ; 
				System.out.print(token.concat(colons)) ; 
				System.out.print("[") ;
				for(int vc=0;vc<associate.size();vc++){
					String taken = associate.get(vc) ; 
					System.out.print(taken.concat(" , ")) ;
					}
				System.out.println("]")  ;
				}  	
			}
	public static void main(String[] args ) throws Exception {
		TestClient4 tests = new TestClient4() ; 
		configure(tests,args) ; 
		tests.response = tests.client.send(tests.request , HttpResponse.BodyHandlers.ofString() ) ;
		String taken = tests.response.body() ; 
		int codes = tests.response.statusCode( ) ; 
		System.out.println(taken)  ; 
		System.out.println(codes) ; 
		 checkHeaders(tests.response) ; 
		}  
		//List<String> listed = headers.allValues() ; 
	} 	
	//7C6C9C4R5C8C1C8R6C4C3C7R9C2C7C8
    // }
