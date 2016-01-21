package stackoverflow.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.AnswerList;


@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VoteServlet() 
	{
        super();
    }
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<AnswerList> ansentries = (List<AnswerList>) getServletContext().getAttribute( "ansentries" ); 
        int answerId = Integer.parseInt(request.getParameter("answerId"));
        String vtype = request.getParameter("vtype");
        
        for(int i=0; i<ansentries.size(); i++)
        {
        	if(answerId==ansentries.get(i).getAnswerId())
        	{
        		if(vtype.equalsIgnoreCase("positive"))
        		{
        			try
        		    {
        		    	Class.forName("com.mysql.jdbc.Driver");
        		        String url = "jdbc:mysql://localhost/stackoverflow";
        		        //Connection c = DriverManager.getConnection( url, "root","root" );
        		        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
        		        PreparedStatement stmt = c.prepareStatement("update answers set pvote=? where id = ?");
        		        stmt.setInt(1,ansentries.get(i).getPositiveVoteCount()+1);
        		        stmt.setInt(2,ansentries.get(i).getAnswerId());
        		        stmt.execute();
        		        c.close();
        		    }
        		    catch( Exception e )
        		    {
        		        throw new ServletException( e );
        		    }
        			ansentries.get(i).setPositiveVoteCount(ansentries.get(i).getPositiveVoteCount()+1);
        		}
        		else if(vtype.equalsIgnoreCase("negative"))
        		{
        			try
        		    {
        		    	Class.forName("com.mysql.jdbc.Driver");
        		        String url = "jdbc:mysql://localhost/stackoverflow";
        		        //Connection c = DriverManager.getConnection( url, "root","root" );
        		        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
        		        PreparedStatement stmt = c.prepareStatement("update answers set nvote=? where id = ?");
        		        stmt.setInt(1,ansentries.get(i).getNegativeVoteCount()+1);
        		        stmt.setInt(2,ansentries.get(i).getAnswerId());
        		        stmt.execute();
        		        c.close();
        		    }
        		    catch( Exception e )
        		    {
        		        throw new ServletException( e );
        		    }
        			ansentries.get(i).setNegativeVoteCount(ansentries.get(i).getNegativeVoteCount()+1);
        		}
        	}
        }
        getServletContext().setAttribute("ansentries", ansentries);
        response.sendRedirect("QuestionList");
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
