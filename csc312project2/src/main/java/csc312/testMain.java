package csc312;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class testMain {

	
	

	@Test
	public void test500() throws IOException {
		try {
			Main main = new ();
			String z = main.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z99");
		}	
		catch(IOException e){
			assertEquals("java.io.IOException: Internal Server Error",e.toString());
		}
	}
	
	@Test
	public void test403() throws IOException {
		try {
			Main main = new Main();
			String z = main.getURL("https://wordfinder-001.appspot.com/wordfinder?game=1&pos=Z88");
		}	
		catch(IOException e){
			assertEquals("java.io.IOException: Forbidden",e.toString());
		}
	}

	@Test
	public void testAll() throws IOException {
		Main main;
//		main = new Main();
//		main.gameNum = 2;
//		main.makeBoard();
//		// Populates priority queue
//		main.getHmap();
//		main.solve();
		for (int i=1; i<4; i++) {
			main = new Main();
			main.gameNum = i;
			main.makeBoard();
			// Populates priority queue
			main.getHmap();
			main.solve();
		}
	}
	
	
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
