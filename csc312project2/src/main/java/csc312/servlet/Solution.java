package csc312.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csc312.Main;

public class Solution extends HttpServlet {

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
        //out.write("satisfactisdfasdfon".getBytes());
    	//http status: https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html 2xx (ok), 3xx(moved, ...), 4xx(client error), 5xx(server error)
    	//
    	//the browser will react based on the status code such as: to display the content, redirect to the alternate url, display an error page
    	resp.setStatus( HttpServletResponse.SC_OK);
    	//Integer ID = csc312.Main.arrayContestId.remove(0);
    	//Integer S = csc312.Main.arrayContestId.size();
//    	HttpSession mySession = req.getSession();
    	
    	//HttpSession session = request.getSession(false);

//    	Long x = mySession.getCreationTime();
    	Long y = System.currentTimeMillis();
    	Integer strGame = Integer.valueOf(req.getParameter("game"));
        String strContest = req.getParameter("contest");
        String strSolution = req.getParameter("solution");
    	Long x = csc312.Main.contestIDHashMap.get(Integer.valueOf(strContest));
    	Long gameTime = (y-x)/1000;
    	if (strGame != 1 && strGame != 2 && strGame != 3) {resp.setStatus( HttpServletResponse.SC_BAD_REQUEST);}
    	else if (x == null ) {resp.setStatus( HttpServletResponse.SC_BAD_REQUEST);}
    	
    	else if ( gameTime > 120) {
    		resp.setStatus( HttpServletResponse.SC_GONE);
    	}else {
    		//out.write(strGame.toString().getBytes());
    		//
        	//using the outputstream, you can write your output
        	//based on the mimetype, different encoding may be required
        	//
            // http://localhost:8081/contest?game=1&pos=a1&contest=765
            //out.write(ID.toString().getBytes());
            //out.write("satisfaction".getBytes());
            //out.write(x.toString().getBytes());
            //out.write("\n".getBytes());
            //out.write(S.toString().getBytes());
            
            if (strGame == 1 && strSolution.equals(csc312.Main.Solution1)) {
            	out.write(csc312.Main.contestIDNumRequestsHashMap.get(Integer.valueOf(strContest)).toString().getBytes());
            	resp.setStatus(HttpServletResponse.SC_OK);
            	addTopScore(gameTime);

            }
            
            else if(strGame == 2 && strSolution.equals(csc312.Main.Solution2)) {
            	out.write(csc312.Main.contestIDNumRequestsHashMap.get(Integer.valueOf(strContest)).toString().getBytes());
            	resp.setStatus(HttpServletResponse.SC_OK);
            	addTopScore(gameTime);

            }
            else if(strGame == 3 && strSolution.equals(csc312.Main.Solution3)) {
            	out.write(csc312.Main.contestIDNumRequestsHashMap.get(Integer.valueOf(strContest)).toString().getBytes());
            	resp.setStatus(HttpServletResponse.SC_OK);
            	addTopScore(gameTime);

            }
            else {resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);}
         
            
            out.flush();
            
            
            out.close();
    			
    			
    	}
    }
    public void addTopScore(Long gameTime) throws IOException {
    	
    	csc312.Main.topScore.add(gameTime);
    	csc312.Main.topScore.sort(new TopScoreComparator());
    	
    	if(csc312.Main.topScore.size()>=6) {
    		csc312.Main.topScore.remove(5);
    	}
    	
    	String filePath = new File("").getAbsolutePath();
    	
        FileWriter r = new FileWriter(filePath + "/src/main/topscore.txt");
        StringBuilder b = new StringBuilder();
        for( Long s : csc312.Main.topScore) {
            b.append( s);
            b.append( "\n");
            
        }
        r.write(b.toString().trim());
        r.close();
        
    	//Collections.sort(csc312.Main.topScore, new csc312.Main.TopScoreComparator());
    	
    }
    public class TopScoreComparator implements Comparator<Long> {
		@Override
	    public int compare(Long p1, Long p2)
	    {
			if (p1<p2) {
				return -1;
			}else if (p1>p2) {
				return 1;
			}else {
				return 0;
			}
	    }
	}
}
    	
