package csc312;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class project1 {
	public static void main() {
		
			
		String		domainName	  =  " https://wordfinder-001.appspot.com/word.txt";

		try  { 

			String result = project1.listURL( domainName );
			System.out.println( result );
			
		} catch ( Exception e ) {
			//fail("we got an exception");
		}
	}
		
	
	public static String listURL(String url) throws Exception {
		
		//p. 736

		URL myURL = new URL( url );
		
		//what is in an URL: https://docs.oracle.com/javase/7/docs/api/java/net/URL.html
		
		
		//URL connection, to access remote resource
		
		//HttpURLConnection 
		
		HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
		
		System.out.println( "requestMethod:"  + urlConnection.getRequestMethod() );
		
		System.out.println( "responseCode:"  + urlConnection.getResponseCode()); //404 !!, 200 -> ok
		
		System.out.println( "message:"  + urlConnection.getResponseMessage()); 
		
		
		InputStream  is = urlConnection.getInputStream();
		
		//all communication in socket are done in 'byte', java is unicode for String/char
		
		StringBuffer output = new StringBuffer();
		int c;
		while ( (c = is.read() ) != -1 ) {
			output.append((char)c );
		}
		
		is.close();
	
		return output.toString();
	
}
}
