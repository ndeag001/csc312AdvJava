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
import javax.servlet.http.HttpSession;

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
    	ServletOutputStream out = resp.getOutputStream();
    	//http status: https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html 2xx (ok), 3xx(moved, ...), 4xx(client error), 5xx(server error)
    	//
    	//the browser will react based on the status code such as: to display the content, redirect to the alternate url, display an error page
    	resp.setStatus( HttpServletResponse.SC_OK);

    	Long y = System.currentTimeMillis();
    	Integer strGame = Integer.valueOf(req.getParameter("game"));
        String strPos = req.getParameter("pos");
        String strContest = req.getParameter("contest");
    	Long x = csc312.Main.contestIDHashMap.get(Integer.valueOf(strContest));
    	if (strGame != 1 && strGame != 2 && strGame != 3) {resp.setStatus( HttpServletResponse.SC_BAD_REQUEST);}
    	else if (csc312.Main.boardHashMap1.get(strPos) == null ) {resp.setStatus( HttpServletResponse.SC_BAD_REQUEST);}
    	else if (x == null ) {resp.setStatus( HttpServletResponse.SC_BAD_REQUEST);}
    	
    	else if ( x + 120000 < y) {
    		resp.setStatus( HttpServletResponse.SC_GONE);
    	}else {
    		//out.write(strGame.toString().getBytes());
    		//
        	//using the outputstream, you can write your output
        	//based on the mimetype, different encoding may be required
        	//
            // http://localhost:8081/contest?game=1&pos=a1&contest=765
            //out.write(ID.toString().getBytes());
            //out.write("\n".getBytes());
            //out.write(x.toString().getBytes());
            //out.write("\n".getBytes());
            //out.write(S.toString().getBytes());
            
            if (strGame == 1) {
            	out.write(csc312.Main.boardHashMap1.get(strPos).toString().getBytes());
            }
            else if(strGame == 2) {
            	out.write(csc312.Main.boardHashMap2.get(strPos).toString().getBytes());
            }
            else { // It's game 3.
            	out.write(csc312.Main.boardHashMap3.get(strPos).toString().getBytes());
            }
            
            Integer z = csc312.Main.contestIDNumRequestsHashMap.get(Integer.valueOf(strContest));
        	csc312.Main.contestIDNumRequestsHashMap.put(Integer.valueOf(strContest),z + 1);
 
         
            
            out.flush();
            
            
            out.close();
    			
    			
    	}
    }
}
    	
