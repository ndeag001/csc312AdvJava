package csc312;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class testproject1 {

	
	

	@Test
	public void test500() throws IOException {
		try {
			project1 proj1 = new project1();
			String z = proj1.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z99");
		}	
		catch(IOException e){
			assertEquals("java.io.IOException: Internal Server Error",e.toString());
		}
	}
	
	@Test
	public void test403() throws IOException {
		try {
			project1 proj1 = new project1();
			String z = proj1.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z88");
		}	
		catch(IOException e){
			assertEquals("java.io.IOException: Forbidden",e.toString());
		}
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
//	public void testLetterProbabilities() throws IOException {
//		
//		project1 proj1 = new project1();
//		
//		// Set wordsHashMap.
//		// Set letterProbaHashMap.
//		proj1.getHmap();
//		
//		//System.out.println("The letter probabilities in the game are:\n"+proj1.letterProbaHashMap);
//				
//	}
	
//	@Test
//	public void testMakeBoard() {
//		project1 proj1 = new project1();
//		proj1.makeBoard();
//		//System.out.println("The board is:\n"+proj1.board);
//	}
//	
	
}
