package csc312.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csc312.Main;

public class Contest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//
	// HttpServletRequest : information about the request
	//
	// HttpServletResponse : what will be the response
	//
	// workflow: https://jorosjavajams.wordpress.com/servlet-workflow/
	//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	//http status: https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html 2xx (ok), 3xx(moved, ...), 4xx(client error), 5xx(server error)
    	//
    	//the browser will react based on the status code such as: to display the content, redirect to the alternate url, display an error page
    	resp.setStatus( HttpServletResponse.SC_OK);
    	
    	//
    	//using the outputstream, you can write your output
    	//based on the mimetype, different encoding may be required
    	//
        ServletOutputStream out = resp.getOutputStream();
        //Random rand = new Random();
        //int x = 1+rand.nextInt(Main.arrayContestId.length);
        //out.write(Main.boardHashMap1.toString().getBytes());
        //out.write(Main.arrayContestId[x].toString().getBytes());
        
        //Main.arrayContestId[x] 
        //int[] n = new int[Main.arrayContestId.length - 1];
        //System.arraycopy(Main.arrayContestId, 0, n, 0, x );
        //System.arraycopy(Main.arrayContestId, x+1, n, x, Main.arrayContestId.length - x-1);
        //return n;
        
        //Main.arrayContestId
        
        out.flush();
        out.close();
    }
}