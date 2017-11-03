package csc312;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;


public class project1 {
	// To hold calculated letter probabilities.
	// Example:
	// letterProbaHashMap[ "a" ] = 0.0927835
	// letterProbaHashMap[ "b" ] = 0.02749141
	public HashMap<Character, Float> letterProbaHashMap = new HashMap<Character, Float>();
	// To hold all of the possible words.
	public HashMap<String, String> wordsHashMap = new HashMap<String,String>();
	
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
	/**
	 * This function does the initial word things for a game.
	 * 
	 * Sets the private class hashmap letterProbaHashMap.
	 * Sets the private class hashmap wordsHashMap.
	 * 
	 * @return void
	 * @throws IOException
	 */
	public void getHmap() throws IOException {
		String content = getURL( "https://wordfinder-001.appspot.com/word.txt" );
		StringTokenizer tokenizer = new StringTokenizer( content, "\n" );
		while ( tokenizer.hasMoreTokens() ) {
			String tk = tokenizer.nextToken().trim();
			String oldKey = wordsHashMap.put(tk, null);
			// Put returns:
			// The previous value associated with key,
			// or null if there was no mapping for key.
			// (A null return can also indicate that the map previously associated null with key.)
			if (oldKey != null) {
				// If oldKey is not null then this word is a duplicate!
				// In which case, do not go through the counting letter steps below.
				// Counting the same word again would increase the letter
				// probabilities associated with that word.
				continue;
			}
			// Add letters of the word to probabilites hashmap.
			wordDictProbaAddLetters(tk);
		}
		// Calculate probabilities.
		wordDictProbaCalculate();
		
		//return null;
	}
	/**
	 * Updates letterProbaHashMap so that each letter represents the pct% of total letters. 
	 */
	public void wordDictProbaCalculate() {
		// Calculate probabilities. Note: Each word has three letters.
		int totalLetters = wordsHashMap.size() * 3;
		for (Entry<Character, Float> entry : letterProbaHashMap.entrySet()) {
		    Character key = entry.getKey();
		    Float value = entry.getValue();
		    letterProbaHashMap.put(key, value / totalLetters);
		}
	}
	public void wordDictProbaAddLetters(String word) {
		// Add letters to letter probability hash map.
		// There are typically three letters per word.
		for (int i=0; i<word.length(); i++) {
			Character l = word.charAt(i);
			// putIfAbsent:
			// If the specified key is not already associated with
			// a value (or is mapped to null) associates it with the
			// given value and returns null, else returns the current value.
			Float val = letterProbaHashMap.putIfAbsent(l, (float) 1);
			if (val != null) {
				Float newVal = val+1;
				letterProbaHashMap.put(l, newVal);
			}
		}
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
		//HashMap<String, String> hmap = getHmap();
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

