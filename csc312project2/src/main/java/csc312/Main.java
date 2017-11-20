package csc312;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import csc312.servlet.Contest;
import csc312.servlet.GoodBye;
import csc312.servlet.HelloWorld;
import csc312.servlet.Words;

public class Main {

	public static HashMap<String, Character> boardHashMap1 = new HashMap<String, Character>();
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
	 *   
	 *   2 principals interface: ServletRequest (information on the request), ServletResponse (responses to be sent)
	 *   
	 *   most will use a framework such as Spring MVC, based on a URL, what to do, control the processing, and what to show the user 
	 */
	
	public static void main(String[] args)
    		  throws LifecycleException, InterruptedException, ServletException {

		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(8082);
	    
	    //https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
	    for (int a = 1; a < 1001; a++) {
	    	arrayContestId.add(a);
	    }
	    Collections.shuffle(arrayContestId);
	    
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
	    Tomcat.addServlet(ctx, "newcontest", new Contest() );
		    
	    //1st parameter, is what url are handled by this serlvet, 2nd parameter, 
	    //the name of the servlet handling it
	    ctx.addServletMapping("/hello", "hello");
	    ctx.addServletMapping("/goodbye", "bye");
	    ctx.addServletMapping("/words", "words");
	    ctx.addServletMapping("/newcontest", "newcontest");
	    
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