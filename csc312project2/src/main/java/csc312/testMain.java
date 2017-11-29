package csc312;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;


public class testMain {
	
	public String getURL( String url) throws IOException {
		//char a = 5;
		HttpURLConnection urlConnection = null ;
		try {
			URL myURL = new URL( url );
		
			urlConnection = (HttpURLConnection) myURL.openConnection();
			
			if (urlConnection.getResponseCode() != 200) {
				//return getURL(url);
			}
			InputStream  is = urlConnection.getInputStream();
			StringBuffer output = new StringBuffer();
			int c;
			while ( (c = is.read() ) != -1 ) {
				output.append((char)c );
			}
			is.close();
			return output.toString();
		} catch (IOException e) {
			String i = urlConnection.getResponseMessage();
			throw new IOException(i);
		} catch (Exception e) {
		}
		return null;
	}
	
	@Test
	public void testWords() throws IOException {
		String z = getURL("http://localhost:8082/words");
		// Should be 20 words separated by newline characters.
		// Each would should be three letters.
		assertEquals("tux\n" + 
				"six\n" + 
				"yuk\n" + 
				"ick\n" + 
				"kaf\n" + 
				"kep\n" + 
				"bee\n" + 
				"kef\n" + 
				"hyp\n" + 
				"kab\n" + 
				"gyp\n" + 
				"tax\n" + 
				"cup\n" + 
				"and\n" + 
				"xis\n" + 
				"fub\n" + 
				"kip\n" + 
				"gym\n" + 
				"guv\n" + 
				"sox\n",z);
	}
	
	@Test
	public void testNewContest() throws IOException {
		String z = getURL("http://localhost:8082/newcontest");
		assertTrue(Integer.valueOf(z) > 0);
		assertTrue(Integer.valueOf(z) <= 1000);
	}
	
	@Test
	public void testBoardLetters() throws IOException {
		HashMap<String, Character> boardHashMap1 = new HashMap<String, Character>();
		HashMap<String, Character> boardHashMap2 = new HashMap<String, Character>();
		HashMap<String, Character> boardHashMap3 = new HashMap<String, Character>();
		boardHashMap1.put("a1",'A');            //                  a b c d e
		boardHashMap1.put("b1",'B');			//  board 1 is   1  A B C t D
		boardHashMap1.put("c1",'C');			//        		 2  E F G a H
		boardHashMap1.put("d1",'t');			//               3  I J K x L
		boardHashMap1.put("e1",'D');			//				 4  M N O P Q
		boardHashMap1.put("a2",'E');			//               5  R S T U V
		boardHashMap1.put("b2",'F');
		boardHashMap1.put("c2",'G');
		boardHashMap1.put("d2",'a');
		boardHashMap1.put("e2",'H');
		boardHashMap1.put("a3",'I');
		boardHashMap1.put("b3",'J');
		boardHashMap1.put("c3",'K');
		boardHashMap1.put("d3",'x');
		boardHashMap1.put("e3",'L');
		boardHashMap1.put("a4",'M');
		boardHashMap1.put("b4",'N');
		boardHashMap1.put("c4",'O');
		boardHashMap1.put("d4",'P');
		boardHashMap1.put("e4",'Q');
		boardHashMap1.put("a5",'R');
		boardHashMap1.put("b5",'S');
		boardHashMap1.put("c5",'T');
		boardHashMap1.put("d5",'U');
		boardHashMap1.put("e5",'V');
		
		boardHashMap2.put("a1",'A');
		boardHashMap2.put("b1",'B');			//  board 2 is     A B C W D
		boardHashMap2.put("c1",'C');			//        		   E F G X H
		boardHashMap2.put("d1",'W');			//                 I J K Y L
		boardHashMap2.put("e1",'D');			//				   M b e e Q
		boardHashMap2.put("a2",'E');			//                 R S T U V
		boardHashMap2.put("b2",'F');
		boardHashMap2.put("c2",'G');
		boardHashMap2.put("d2",'X');
		boardHashMap2.put("e2",'H');
		boardHashMap2.put("a3",'I');
		boardHashMap2.put("b3",'J');
		boardHashMap2.put("c3",'K');
		boardHashMap2.put("d3",'Y');
		boardHashMap2.put("e3",'L');
		boardHashMap2.put("a4",'M');
		boardHashMap2.put("b4",'b');
		boardHashMap2.put("c4",'e');
		boardHashMap2.put("d4",'e');
		boardHashMap2.put("e4",'Q');
		boardHashMap2.put("a5",'R');
		boardHashMap2.put("b5",'S');
		boardHashMap2.put("c5",'T');
		boardHashMap2.put("d5",'U');
		boardHashMap2.put("e5",'V');
		
		boardHashMap3.put("a1",'A');
		boardHashMap3.put("b1",'B');			//  board 3 is     A B C W D
		boardHashMap3.put("c1",'C');			//        		   E F G X H
		boardHashMap3.put("d1",'W');			//                 I J K Y L
		boardHashMap3.put("e1",'D');			//				   M N O P Q
		boardHashMap3.put("a2",'E');			//                 R S t u x
		boardHashMap3.put("b2",'F');
		boardHashMap3.put("c2",'G');
		boardHashMap3.put("d2",'X');
		boardHashMap3.put("e2",'H');
		boardHashMap3.put("a3",'I');
		boardHashMap3.put("b3",'J');
		boardHashMap3.put("c3",'K');
		boardHashMap3.put("d3",'Y');
		boardHashMap3.put("e3",'L');
		boardHashMap3.put("a4",'M');
		boardHashMap3.put("b4",'N');
		boardHashMap3.put("c4",'O');
		boardHashMap3.put("d4",'P');
		boardHashMap3.put("e4",'Q');
		boardHashMap3.put("a5",'R');
		boardHashMap3.put("b5",'S');
		boardHashMap3.put("c5",'t');
		boardHashMap3.put("d5",'u');
		boardHashMap3.put("e5",'x');
		
		ArrayList<HashMap> hmList = new ArrayList();
		hmList.add(boardHashMap1);
		hmList.add(boardHashMap2);
		hmList.add(boardHashMap3);
		
		String z = getURL("http://localhost:8082/newcontest");
		Integer gameCount = 1;
		for (HashMap hm : hmList) {
			Iterator<Entry<String,Character>> iterator = hm.entrySet().iterator();
			while (iterator.hasNext()) {
				HashMap.Entry<String,Character> entry = (HashMap.Entry<String,Character>) iterator.next();
				System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue());
				String x = getURL("http://localhost:8082/contest?contest="+z+"&game="+gameCount.toString()+"&pos="+entry.getKey());
				assertEquals(x, entry.getValue().toString());
			}
			gameCount += 1;
		}
	}
	
	@Test
	public void testContestTimeout() throws IOException, InterruptedException {
		String z = getURL("http://localhost:8082/newcontest");//id
		Thread.sleep(120000);
		try {
			String x = getURL("http://localhost:8082/contest?contest="+z+"&game=1&pos=a1");
		} catch (Exception e) {
			System.out.println(e.toString());
			assertEquals("java.io.IOException: Gone",e.toString());
		}
	}
	

	@Test
	public void testSolution() throws IOException {
		String z = getURL("http://localhost:8082/newcontest");
	
		String x1 = getURL("http://localhost:8082/solution?contest="+z+"&game=1&solution=tax");
		z = getURL("http://localhost:8082/newcontest");
		String x2 = getURL("http://localhost:8082/solution?contest="+z+"&game=2&solution=bee");
		z = getURL("http://localhost:8082/newcontest");
		String x3 = getURL("http://localhost:8082/solution?contest="+z+"&game=3&solution=tux");
		
		assertEquals("0",x1);
		assertEquals("0",x2);
		assertEquals("0",x3);
		
		try {
			x1 = getURL("http://localhost:8082/solution?contest="+z+"&game=1&solution=tux");
		} catch(Exception e) {
			assertEquals("java.io.IOException: Bad Request",e.toString());
		}
		try {
			x1 = getURL("http://localhost:8082/solution?contest="+z+"&game=2&solution=tax");
		} catch(Exception e) {
			assertEquals("java.io.IOException: Bad Request",e.toString());
		}
		try {
			x1 = getURL("http://localhost:8082/solution?contest="+z+"&game=3&solution=bee");
		} catch(Exception e) {
			assertEquals("java.io.IOException: Bad Request",e.toString());
		}
	}
	
//	@Test
//	public void test500() throws IOException {
//		try {
//			Main main = new ();
//			String z = main.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z99");
//		}	
//		catch(IOException e){
//			assertEquals("java.io.IOException: Internal Server Error",e.toString());
//		}
//	}
//	
//	@Test
//	public void test403() throws IOException {
//		try {
//			Main main = new Main();
//			String z = main.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z88");
//		}	
//		catch(IOException e){
//			assertEquals("java.io.IOException: Forbidden",e.toString());
//		}
//	}
//
//	@Test
//	public void testAll() throws IOException {
//		Main main;
////		main = new Main();
////		main.gameNum = 2;
////		main.makeBoard();
////		// Populates priority queue
////		main.getHmap();
////		main.solve();
//		for (int i=1; i<4; i++) {
//			main = new Main();
//			main.gameNum = i;
//			main.makeBoard();
//			// Populates priority queue
//			main.getHmap();
//			main.solve();
//		}
//	}
	
	
//	@Test
//	public void testLetterProbabilities() throws IOException {
//		
//		Main main = new Main();
//		
//		// Set wordsHashMap.
//		// Set letterProbaHashMap.
//		main.getHmap();
//		
//		//System.out.println("The letter probabilities in the game are:\n"+main.letterProbaHashMap);
//				
//	}
	
//	@Test
//	public void testMakeBoard() {
//		Main main = new Main();
//		main.makeBoard();
//		//System.out.println("The board is:\n"+main.board);
//	}
//	
	
}
