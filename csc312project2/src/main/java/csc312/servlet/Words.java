package csc312.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Words extends HttpServlet {

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
        
        String filePath = new File("").getAbsolutePath();
        BufferedReader r = new BufferedReader(new FileReader(filePath + "/src/main/word.txt"));
        try
        {                           
            String line = null;         
            while ((line = r.readLine()) != null)
            {
            	out.write(line.getBytes());
            	out.write("\n".getBytes());
            }               
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }               
        finally
        {
            r.close();
        }
        out.flush();
        out.close();
    }
}