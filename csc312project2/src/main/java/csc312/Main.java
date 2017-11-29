package csc312;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import csc312.servlet.NewContest;
import csc312.servlet.Solution;
import csc312.servlet.TopScore;
import csc312.servlet.Contest;
import csc312.servlet.GoodBye;
import csc312.servlet.HelloWorld;
import csc312.servlet.Words;

public class Main {

	public static HashMap<String, Character> boardHashMap1 = new HashMap<String, Character>();
	public static HashMap<String, Character> boardHashMap2 = new HashMap<String, Character>();
	public static HashMap<String, Character> boardHashMap3 = new HashMap<String, Character>();
	public static HashMap<Integer, Long> contestIDHashMap = new HashMap<Integer, Long>();
	public static HashMap<Integer, Integer> contestIDNumRequestsHashMap = new HashMap<Integer, Integer>();
	//public static Comparator<Long> c = new TopScoreComparator();
	public static ArrayList<Long> topScore = new ArrayList<Long>(5);
	public static String Solution1 = "tax";
	public static String Solution2 = "bee";
	public static String Solution3 = "tux";
	
	
	//public static Integer arrayContestId [] = new Integer[1000];
	public static ArrayList<Integer> arrayContestId = new ArrayList<Integer>(1000);
	// public static allGames[contestId] = <Game>
	
	//int [] arrayContestId = new int[1000];
	
	/*  Servlet: to handle dynamic request in java
	 *   
	 *   IDE are already integrated to generate project for dynamic contact, such as Eclipse IDE for Java EE Developers 
	 *   instead of Eclipse IDE for Java Developers
	 *   
	 *   tomcat, which is an implementation of the serlvet api, is commonly integrated with IDE
	 *   
	 *   what we will use, is an embedded version of tomcat, it is packaged in your code, but you have less external configuration elements
	 *   
	 *   servlet: most commonly you will write specilization of HttpServlet, which allows you, to handle http request
	 *   
	 *   What are common request: GET (clicking on an URL), POST (submit in a form) 
	 *   
	 *   others have become more popular with REST/JSON: DELETE, PUT
	 *   
	 *   all requests are mapped to their counterpart functions: https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html
	 *   public static String Solution1 = "tax";
	 *   2 principals interface: ServletRequest (information on the request), ServletResponse (responses to be sent)
	 *   
	 *   most will use a framework such as Spring MVC, based on a URL, what to do, control the processing, and what to show the user 
	 */
	
	public static void generateBoards() {
		
		
	}
	
	public static void main(String[] args)
    		  throws LifecycleException, InterruptedException, ServletException, NumberFormatException, IOException {
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

		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(8082);
	    
	    //https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	    for (int a = 1; a < 1001; a++) {
	    	arrayContestId.add(a);
	    }
	    Collections.shuffle(arrayContestId);
	    
	    String filePath = new File("").getAbsolutePath();
	    BufferedReader r = new BufferedReader(new FileReader(filePath + "/src/main/topscore.txt"));                        
        String line = null;         
        while ((line = r.readLine()) != null)
        {
        	topScore.add(Long.valueOf(line.trim()));
        	  
        }
        r.close();
          	//out.write(line.getBytes());
          	//out.write("\n".getBytes());
          
	    
	    
	    //System.out.println(arrayContestId);
	    //System.out.println("hello");
	    
	    //
	    //if it was not the embedded, it would be configured using annotation (latest version), 
	    //before it was in a xml class : https://javatutorial.net/servlet-annotation-example
	    //
	    Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
	
	    Tomcat.addServlet(ctx, "hello", new HelloWorld() );
	    Tomcat.addServlet(ctx, "bye", new GoodBye() );
	    Tomcat.addServlet(ctx, "words", new Words() );
	    Tomcat.addServlet(ctx, "newcontest", new NewContest() );
	    Tomcat.addServlet(ctx, "contest", new Contest() );
	    Tomcat.addServlet(ctx, "solution", new Solution() );
	    Tomcat.addServlet(ctx, "topscore", new TopScore() );
		    
	    //1st parameter, is what url are handled by this serlvet, 2nd parameter, 
	    //the name of the servlet handling it
	    ctx.addServletMapping("/hello", "hello");
	    ctx.addServletMapping("/goodbye", "bye");
	    ctx.addServletMapping("/words", "words");
	    ctx.addServletMapping("/newcontest", "newcontest");
	    ctx.addServletMapping("/contest", "contest");
	    ctx.addServletMapping("/solution", "solution");
	    ctx.addServletMapping("/topscore", "topscore");
	    
	    // Servlet starts at e.g.
	    // http://localhost:8082/words
	    tomcat.start();
	    tomcat.getServer().await();
	    
	    // "djsiv\ndibox\njviso\JdkbI\n"
//	    h[2]["e"] = "K"
//	    h[2] = "e"
//	    h["2e"] = "K"
	   
	}
	
}