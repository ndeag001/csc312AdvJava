package csc312;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

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
	public void testAll() throws IOException {
		
		// 1 geturl, gethmap()
		// variable hmap_board
		// 2 while(word not found) geturl(letters),  hmap_board.add(letter)
		
		
		project1 proj1 = new project1();
		
		
		HashMap<String, String> content1 = proj1.getHmap();
		
		
		assertEquals( new Character('c'), proj1.Game(1) );
		
	
	
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
