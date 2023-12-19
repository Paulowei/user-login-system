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


public class TestClient{
	
	public HttpRequest request ;
	public HttpClient client ; 
	public  HttpResponse<String> response ; 
	public static String useful = new String("http://localhost:8080/permutations/?range=4") ; 
	public static String prevail = new String("GET") ;
	public static HttpRequest.BodyPublisher publish = HttpRequest.BodyPublishers.ofString("") ; 
	public static String worker  = new String("") ;
	public static String colons = new String(" : ") ; 
	public static void configure(TestClient tested, String[] args) throws Exception{
		if(args.length!=3){args= new String[]{prevail,useful,worker}; }
		 HttpClient.Builder built = HttpClient.newBuilder()  ; 
		built.version(HttpClient.Version.HTTP_2) ; 
		built.connectTimeout(Duration.ofMillis(2000));
		tested.client = built.build() ; 
		HttpRequest.Builder build = HttpRequest.newBuilder() ;
		publish =  HttpRequest.BodyPublishers.ofString(args[2]) ;
		build.uri(new URI(args[1])) ; 
 		build.version(HttpClient.Version.HTTP_2) ;
		build.method(args[0],publish)  ;
		//build.GET() ;
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
		TestClient tests = new TestClient() ; 
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

