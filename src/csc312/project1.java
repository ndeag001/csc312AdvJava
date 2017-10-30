package csc312;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class project1 {

	//you must implement the function to retrieve the content of a specific URL at https://wordfinder-001.appspot.com/wordfinder
	//
	//be aware that at random  the  ResponseCode may be SC_INTERNAL_SERVER_ERROR  or SC_INTERNAL_SERVER_ERROR instead of SC_OK
	//
	public String getURL( String url) throws IOException {
		//char a = 5;
		try {
			URL myURL = new URL( url );
		
			HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
		
			System.out.println( "requestMethod:"  + urlConnection.getRequestMethod() );
			System.out.println( "responseCode:"  + urlConnection.getResponseCode()); //404 !!, 200 -> OK
			System.out.println( "message:"  + urlConnection.getResponseMessage()); 
		
			InputStream  is = urlConnection.getInputStream();
				
			StringBuffer output = new StringBuffer();
			int c;
			  HashMap<String, String> hmap = new HashMap<String, String>();
			while ( (c = is.read() ) != -1 ) {
				if ((char) c == '\n'){
					
					System.out.println("new Line");
					hmap.put(output.toString().trim(), null);
					output= new StringBuffer();
				}else {
					output.append((char)c );
				
			} 
			}
		
			is.close();
//			a = output.toString().charAt(0);
			System.out.println(hmap);
			
			
	
		} catch (MalformedURLException e) {
		    return null;
		} catch (IOException e) {
			return null;
		
		}
//		return null;
		return null;
		
	}
}
