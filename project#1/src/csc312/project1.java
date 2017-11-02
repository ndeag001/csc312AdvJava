package csc312;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;


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
		
//			System.out.println( "requestMethod:"  + urlConnection.getRequestMethod() );
//			System.out.println( "responseCode:"  + urlConnection.getResponseCode()); //404 !!, 200 -> OK
//			System.out.println( "message:"  + urlConnection.getResponseMessage()); 
//		
			InputStream  is = urlConnection.getInputStream();
				
			StringBuffer output = new StringBuffer();
			
				
			int c;
			 
			while ( (c = is.read() ) != -1 ) {
				
					output.append((char)c );
			}
			
		
			is.close();
		//	System.out.println(output.toString());
			return output.toString();
//			System.out.println(hmap);
//			return hmap;
			
			
	
		} catch (MalformedURLException e) {
		    return null;
		} catch (IOException e) {
			return null;
		
		}
//		return null;
		
		
	}
	public HashMap<String, String> getHmap() throws IOException {
		String content = getURL( "https://wordfinder-001.appspot.com/word.txt" );
		HashMap<String, String> hmap = new HashMap<String, String>();
			StringTokenizer tokenizer = new StringTokenizer( content, "\n" );
		while ( tokenizer.hasMoreTokens() ) {
			hmap.put(tokenizer.nextToken().trim(), null);
			//values.add( tokenizer.nextToken() );
		}
		//System.out.println(hmap);
		return null;
	}

	public Character getLetter(int row,char column) {
		
		return null;
	}
	public Boolean Game ( Integer gameNum ) throws IOException {
		// Two arrays, each containing 5 string buffers.
		StringBuffer[] column = new StringBuffer[5];
		StringBuffer[] rowSB = new StringBuffer[5];
		ArrayList<String> checks = new ArrayList<String>();
		String colLetters = "abcde";
		HashMap<String, String> hmap = getHmap();
		int a = 0;
		while(a<22) {
			a++;
			outerloop:
				
			for (int i=1;i<6;i=i+2) { // 12345
				for (int j=1;j<6;j=j+2) {  //"abcde":
					checks.add(String.format("%2d,%2d",i,j));
					
					
					
				}
				
				
			}
		
			
//					Character L = getBoardLetter(gameNum, i, colLetters.charAt(j));
//					// Append letter to the respective string buffers.
//					column[j].append(L);
//					rowSB[i].append(L);
//					if (Character.isUpperCase(L)){break outerloop;}
//					else {
//						L = getBoardLetter(gameNum, i, colLetters.charAt(j+2));
//						if (Character.isUpperCase(L)){break outerloop;}
//					}
					
//				w = test_success(cols[col], hmap)
//				if w:
//					do_success(w);
//					return; // End
//				rowSB[i].append(L);
//				w = test_success(rows[row], hmap);
//				if w:
//					do_success(w);
//					return; // End
			}
		System.out.println(checks);
		return null;
		}
		// Still here? Then no word has been found!
		// Print fail string or something.
//	}

	public Character getBoardLetter(Integer gameNum, int row, char col) throws IOException {
		
		String v = getURL("https://wordfinder-001.appspot.com/wordfinder?game="+gameNum+"&pos="+col+row);
		
		return v.charAt(0);
	}

	public String test_success(String string_to_test,HashMap<String, String> hmap) {
		// Test last three letters of the string against HashMap.
		// Return the word if found, otherwise return *null*.
		
		// The word has to have at least three letters in it.	
		if (string_to_test.length() < 3) { 
			return null;
		}
		return null;
	        // Check if string is in hmap here.
	}
}

