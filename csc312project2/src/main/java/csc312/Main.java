package csc312;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import csc312.servlet.GoodBye;
import csc312.servlet.HelloWorld;

public class Main {

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
	    tomcat.setPort(8080);
	
	    //
	    //if it was not the embedded, it would be configured using annotation (latest version), 
	    //before it was in a xml class : https://javatutorial.net/servlet-annotation-example
	    //
	    Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
	
	    Tomcat.addServlet(ctx, "hello", new HelloWorld() );
	    Tomcat.addServlet(ctx, "bye", new GoodBye() );
		    
	    //1st parameter, is what url are handled by this serlvet, 2nd parameter, 
	    //the name of the servlet handling it
	    ctx.addServletMapping("/hello", "hello");
	    ctx.addServletMapping("/goodbye", "bye");
	    tomcat.start();
	    tomcat.getServer().await();
	}
	
}