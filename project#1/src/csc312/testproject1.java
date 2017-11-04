package csc312;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Test;

public class testproject1 {

	
	@Test
	public void testValidURL() throws IOException {
		
		project1 proj1 = new project1();
				
		assertEquals( new Character('c'), proj1.getURL( "https://wordfinder-001.appspot.com/word.txt" ) );
		
	}
	

	@Test
	public void testHmap() throws IOException {
		
		project1 proj1 = new project1();
		
		String content = proj1.getURL( "https://wordfinder-001.appspot.com/word.txt" );
		

		assertEquals( new Character('c'), proj1.getURL( "https://wordfinder-001.appspot.com/word.txt" ) );
		
	}
	
	@Test
	public void testLetterProbabilities() throws IOException {
		
		project1 proj1 = new project1();
		
		// Set wordsHashMap.
		// Set letterProbaHashMap.
		proj1.getHmap();
		
		System.out.println("The letter probabilities in the game are:\n"+proj1.letterProbaHashMap);
				
	}
	
	@Test
	public void testMakeBoard() {
		
		project1 proj1 = new project1();
		proj1.makeBoard();
		System.out.println("The board is:\n"+proj1.board);
	}
	
	@Test
	public void testAll() throws IOException {
		project1 proj1;
//		proj1 = new project1();
//		proj1.gameNum = 2;
//		proj1.makeBoard();
//		// Populates priority queue
//		proj1.getHmap();
//		proj1.solve();
		for (int i=1; i<4; i++) {
			proj1 = new project1();
			proj1.gameNum = i;
			proj1.makeBoard();
			// Populates priority queue
			proj1.getHmap();
			proj1.solve();
		}
	}
//	@Test
//	public void testServerError() throws IOException {
//		
//		project1 proj1 = new project1();
//				
//		assertEquals( null, proj1.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z99" ) );
//		
//
//	}
//	
//	@Test
//	public void testServerForbidden() throws IOException {
//		
//		project1 proj1 = new project1();
//				
//		assertEquals( null, proj1.getURL( "https://wordfinder-001.appspot.com/wordfinder?game=2&pos=Z88" ) );
//		
//	
//	}
//	
//	@Test
//	public void testInvalidDomain() throws IOException {
//		
//		project1 proj1 = new project1();
//				
//		assertEquals( null, proj1.getURL( "https://aninvaliddomainname.com" ) );
//		
//	
//	}
//	
//
}
