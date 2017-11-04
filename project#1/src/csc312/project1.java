package csc312;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;


public class project1 {
	// To hold calculated letter probabilities.
	// Example:
	// letterProbaHashMap[ "a" ] = 0.0927835
	// letterProbaHashMap[ "b" ] = 0.02749141
	public HashMap<Character, Float> letterProbaHashMap = new HashMap<Character, Float>();
	// To hold all of the possible words.
	public HashMap<String, String> wordsHashMap = new HashMap<String,String>();
	// To hold all iterations of words.
	public HashMap<String, String> wordPartsHashMap = new HashMap<String,String>();
	// To hold board.
	public ArrayList<BoardPosition>[][] board = new ArrayList[5][5];
	// For priority queue to decide which position to reveal.
	public Comparator<BoardPosition> c = new BoardComparator();
	public PriorityQueue<BoardPosition> boardPositionsQueue = new PriorityQueue<BoardPosition>(10, c);
	// To be updated by caller (default=1).
	public int gameNum;
	// To hold all BoardWordCombos
	public ArrayList<BoardWordCombo> boardWordCombos = new ArrayList<BoardWordCombo>();
	// To hold boolean game over.
	public Boolean solved = false;

	//you must implement the function to retrieve the content of a specific URL at https://wordfinder-001.appspot.com/wordfinder
	//
	//be aware that at random  the  ResponseCode may be SC_INTERNAL_SERVER_ERROR  or SC_INTERNAL_SERVER_ERROR instead of SC_OK
	//
	public String getURL( String url) {
		//char a = 5;
		try {
			URL myURL = new URL( url );
		
			HttpURLConnection urlConnection = (HttpURLConnection) myURL.openConnection();
			
			// Set timeout to 4 seconds
			// Then quit and try again.
			urlConnection.setConnectTimeout(4000);
			urlConnection.setReadTimeout(4000);
			if (urlConnection.getResponseCode() != 200) {
				return getURL(url);
			}
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
			
			
	
//		} catch (MalformedURLException e) {
//		    return null;
//		} catch (IOException e) {
//			return null;
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(url);
			return getURL(url);
		}
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
	public void getHmap() {
		String content = getURL( "https://wordfinder-001.appspot.com/word.txt" );
		StringTokenizer tokenizer = new StringTokenizer( content, "\n" );
		while ( tokenizer.hasMoreTokens() ) {
			String tk = tokenizer.nextToken().trim();
			String oldKey = wordsHashMap.put(tk, null);
			wordPartsAdd(tk); // Add word to wordPartsHashMap. 
			// wordsHashMap.put returns:
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
		//System.out.println("There are "+wordPartsHashMap.size()+" possible word combinations");
	}

	/**
	 * Updates wordPartsHashMap to add new word combos.
	 * 
	 * Make the parts of word hashmap.
	 * Enables ability to check if parts of words are viable, e.g. "j _ _".
	 * Ex:
	 *  j _ _
	 *  j o _
	 *  j o b
	 *  _ o _
	 *  _ o b
	 *  _ _ b
	 *  j _ b
	 *  Note: Strings beginning with w.charAt(0) must begin instead with "".
	 */
	public void wordPartsAdd(String w) {
		// One letter
		wordPartsHashMap.put(""+w.charAt(0) + "__", null);
		wordPartsHashMap.put("_" + w.charAt(1) + "_", null);
		wordPartsHashMap.put("__" + w.charAt(2), null);
		
		// Two letters
		wordPartsHashMap.put(""+w.charAt(0) + w.charAt(1) + "_", null);
		wordPartsHashMap.put(""+w.charAt(0) + "_" + w.charAt(2), null);
		wordPartsHashMap.put("_" + w.charAt(1) + w.charAt(2), null);
		wordPartsHashMap.put(""+w.charAt(0) + "_" + w.charAt(2), null);
		
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
	/**
	 * Comparator class for the priority queue.
	 * 
	 * This will need to check the number of combinations
	 * that the board position has available.
	 */
	public class BoardComparator implements Comparator<BoardPosition> {
		@Override
	    public int compare(BoardPosition p1, BoardPosition p2)
	    {
			// Sort numeric descending.
	        if (p1.numWordCombos > p2.numWordCombos) {
	        	return -1;
	        } else if (p1.numWordCombos < p2.numWordCombos) {
	        	return 1;
	        } else { // Tied
	        	return 0;
	        }
	    }
	}
	public class BoardWordCombo {
		public int x;
		public int y;
		public int length = 3; // Default game word size.
		public String direction;
		// Three letters
		public ArrayList<BoardPosition> letters = new ArrayList<BoardPosition>();
		// Class constructor
		BoardWordCombo (BoardPosition bp1, BoardPosition bp2, BoardPosition bp3, int xx, int yy, String dir) {
			letters.add(bp1);
			letters.add(bp2);
			letters.add(bp3);
			x = xx;
			y = yy;
			direction = dir;
		}
		public Boolean legitimateWord() {
			String wordToCheck = "" + letters.get(0).getLetter() + letters.get(1).getLetter() + letters.get(2).getLetter();
			if (wordsHashMap.containsKey(wordToCheck)) {
				// Found solution.
				quitGame(wordToCheck, letters.get(0).getBoardPos(), letters.get(2).getBoardPos());
				return true;
			} else if (wordPartsHashMap.containsKey(wordToCheck)) {
				return true;
			} else {
				return false;
			}
		}
		/**
		 * 
		 * @return true to bump last remaining letter.
		 */
		public BoardPosition numLettersRemaining() {
			int x = 0;
			BoardPosition tmp = null;
			for (BoardPosition i : letters) {
				if (i.getLetter() == '_') {
					x+=1;
					tmp = i;
				}
			}
			if (x == 1) {
				String w = "" + letters.get(0).getLetter() + letters.get(1).getLetter() + letters.get(2).getLetter();
				System.out.println("Found 2/3 of a match:" +w);
				tmp.bumpPosInQueue = true;
				return tmp;
			}
			return null;
		}
	}
	public void quitGame(String word, String s, String e) {
		System.out.println("game"+gameNum+" word:"+word+" location:"+s+"-"+e);
		solved = true;
	}
	/**
	 * 
	 */
	public class BoardPosition {
		public int x; // row
		public int y; // column
		public char column_char;
		public Character letter;
		public int numWordCombos;
		public ArrayList<BoardWordCombo> boardWordCombos = new ArrayList<BoardWordCombo>();
		public Boolean bumpPosInQueue = false; // When two letters are found and this is the missing letter.
		// Class constructor
		BoardPosition (int row, int column) {
			x = row;
			y = column;
			column_char = "abcde".charAt(column);
			letter = '_'; // Initialize all position to underscore ("_") 
			initializeNumWordCombos();
		}
		public String getBoardPos() {
			return "" + (x+1) + column_char;
		}
		public void process() {
			letter = getBoardLetter(gameNum, x+1, column_char);
			System.out.println("Got: "+letter);
			// Process board word combos. Remove from BoardPositions.
			List<BoardWordCombo> found = new ArrayList<BoardWordCombo>();
			// Maintain a set of "dirty" board positions which
			// will need to be re-ordered in the priority queue.
			Set<BoardPosition> dirty = new LinkedHashSet<BoardPosition>();
			for (BoardWordCombo bwc : boardWordCombos) {
				if (!bwc.legitimateWord()) {
					for (BoardPosition bp : bwc.letters ) {
						if (bp == this) {
							// Collect and remove
							found.add(bwc);
							continue; // Do this one after loop is complete.
						}
						dirty.add(bp);
						bp.boardWordCombos.remove(bwc);
					}
					continue;
				}
				// Legitimate word.
				// Test 2/3 completed and re-sort if so.
				BoardPosition bp = bwc.numLettersRemaining();
				if (bp != null)
					dirty.add(bp);
			}
			// Remove from this instance of BoardPosition.
			boardWordCombos.removeAll(found);
			
			// Update priority value for board positions.
			for (BoardPosition bp : dirty) {
				bp.updateNumWordCombos();
			}
			updateNumWordCombos();
			
			// Remove from queue and re-add the dirty board positions to update their priority.
			// TODO: Consider better implementation.
			// 		 Java priority queue does not support (nor optimize) updating priorities.
			boardPositionsQueue.removeAll(dirty);
			boardPositionsQueue.addAll(dirty);
			
			// Remove last queue element and re-add to get queue to put top element on top again.
			BoardPosition test = new BoardPosition(0,0);
			test.numWordCombos = 999999;
			boardPositionsQueue.add(test);
			boardPositionsQueue.remove();
			
		}
		public void addBWCs(BoardWordCombo bwc) {
			boardWordCombos.add(bwc);
		}
		public Character getLetter() {
			return letter;
		}
		public void updateNumWordCombos() {
			numWordCombos = boardWordCombos.size();
			if (bumpPosInQueue) {
				// Question: How much to bump up?
				// Answer: There was a negligible difference when
				//		   increasing priority queue score anywhere from 2-4 points.
				// Conclusion: May not be useful.
				numWordCombos += 4;
				
			}
		}
		/**
		 * Initialize num word combos based on x, y position.
		 * 
		 * 2 3 4 3 2
		 * 3 4 5 4 3
		 * 4 5 6 5 4
		 * 3 4 5 4 3
		 * 2 3 4 3 2
		 */
		public void initializeNumWordCombos() {
			int n = 0;
			if (x == 0) {
				n += 1;
			} else if (x == 1) {
				n += 2;
			} else if (x == 2) {
				n += 3;
			} else if (x == 3) {
				n += 2;
			} else if (x == 4) {
				n += 1;
			}
			if (y == 0) {
				n += 1;
			} else if (y == 1) {
				n += 2;
			} else if (y == 2) {
				n += 3;
			} else if (y == 3) {
				n += 2;
			} else if (y == 4) {
				n += 1;
			}
			numWordCombos = n;
		}
	}
	public void showBoard() {
		for (int i=0;i<5;i++) { // 12345
			StringBuffer col = new StringBuffer();
			for (int j=0;j<5;j++) {
				BoardPosition bp = board[i][j].get(0);
				col.append(bp.letter+""+bp.numWordCombos+" ");
			}
			System.out.println(col);
		}
		System.out.println("\n");
	}
	public void makeBoard() {
		for (int i=0;i<5;i++) { // 12345
			for (int j=0;j<5;j++) {  // "abcde":
				board[i][j] = new ArrayList<BoardPosition>();
				BoardPosition n = new BoardPosition(i, j);
				board[i][j].add(n); // Initialize to a null string.
				// Add to queue.
				boardPositionsQueue.add(n);
			}
		}
		
		// Add all possible BoardWordCombos.
		// There are down BWCs starting as far down as row 2.
		// There are across BWCs starting as far to the right as column c.
		for (int i=0;i<5;i++) { // 12345 // row
			for (int j=0;j<5;j++) {  // "abcde" // column
				
				// Down 
				// For col 0-4, row 0-2, add down-facing combos. 
				if (i < 3) {
					BoardWordCombo bwc1 = new BoardWordCombo(
							board[i][j].get(0), board[i+1][j].get(0), board[i+2][j].get(0),
							i, j, "down");
					// Add global variable.
					boardWordCombos.add(bwc1);
					// Notify BoardPosition elements of their inclusion in BWCs.
					board[i][j].get(0).addBWCs(bwc1);
					board[i+1][j].get(0).addBWCs(bwc1);
					board[i+2][j].get(0).addBWCs(bwc1);
				}
				
				// Across
				// For row 0-4, col 0-2, add across-facing combos. 
				if (j < 3) {
					BoardWordCombo bwc2 = new BoardWordCombo(
							board[i][j].get(0), board[i][j+1].get(0), board[i][j+2].get(0),
							i, j, "across");
					// Add global variable.
					boardWordCombos.add(bwc2);
					// Notify BoardPosition elements of their inclusion in BWCs.
					board[i][j].get(0).addBWCs(bwc2);
					board[i][j+1].get(0).addBWCs(bwc2);
					board[i][j+2].get(0).addBWCs(bwc2);
				}
			}
		}
		// At this point board positions could calculate their own NumWordCombos score.
		// 
		// Trace
		//BoardPosition top = boardPositionsQueue.element();
		//System.out.println("Top of queue is:" + top.x +","+ top.y +" "+ top.numWordCombos);
	}
	public void solve() {
		showBoard();
		while (boardPositionsQueue.size() > 0 && !solved) {
			//showBoard();
			
			BoardPosition top = boardPositionsQueue.poll();
			if (top != null && top.boardWordCombos.size() > 0) {
				System.out.println("Chose:"+top.x+","+top.y);
				System.out.println("Having #BWCs: "+top.boardWordCombos.size());
				top.process();
				
				showBoard();
			} else {
				break;
			}
		}
		
		// Get a letter
//		try {
//			showBoard();
//			
//			BoardPosition top = boardPositionsQueue.poll();
//			System.out.println("Chose:"+top.x+","+top.y);
//			System.out.println("Having #BWCs: "+top.boardWordCombos.size());
//			top.process();
//			
//			showBoard();
//			
////			for (BoardPosition i : boardPositionsQueue) {
////				System.out.println("bpq: "+i.x +","+ i.y+","+i.boardWordCombos.size());
////			}
//			
//			// Try next
//			top = boardPositionsQueue.poll();
//			System.out.println("Chose:"+top.x+","+top.y);
//			System.out.println("Having #BWCs: "+top.boardWordCombos.size());
//			top.process();
//			
//			showBoard();
//			
//			// Try next
//			top = boardPositionsQueue.poll();
//			System.out.println("Chose:"+top.x+","+top.y);
//			System.out.println("Having #BWCs: "+top.boardWordCombos.size());
//			top.process();
//			
//			showBoard();
//			
//			
//		} catch (Exception e) { // empty queue
//		
//		} 
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

	public Character getBoardLetter(int gameNum, int row, char col) {
		
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

