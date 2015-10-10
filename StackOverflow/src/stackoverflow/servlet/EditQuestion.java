package stackoverflow.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.QuestionEntry;

@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditQuestion() 
	{
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int qno = Integer.parseInt(request.getParameter("id"));
		List<QuestionEntry> entries = (List<QuestionEntry>)getServletContext().getAttribute("entries");
		for(QuestionEntry entry:entries)
		{
			if(entry.getQuestionNumber()==qno)
			{
				request.getServletContext().setAttribute("editentry", entry);
				String tags="";
				for(String tag:entry.getTagList())
					tags = tags+" "+tag;
				request.getServletContext().setAttribute("tags", tags);
			}
		}
		request.getRequestDispatcher("/EditQuestion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
	    {
	        String url = "jdbc:mysql://localhost/cs320stu08";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "cs320stu08","3jy!Z#!w" );
	        PreparedStatement stmt = c.prepareStatement("update questions set title=?,question=?,taglist=? where id=?");
	        stmt.setString(1,request.getParameter("title"));
	        stmt.setString(2,request.getParameter("question"));
	        stmt.setString(3,request.getParameter("tag"));
	        stmt.setInt(4,Integer.parseInt(request.getParameter("qno")));
	        stmt.execute();
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
	    List<QuestionEntry> entries = new ArrayList<QuestionEntry>();
        String[] tags = null;
        String sql = "select * from questions;";
	    try
	    {
	        String url = "jdbc:mysql://localhost/cs320stu08";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "cs320stu08","3jy!Z#!w" );
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( sql );
	        while( rs.next() )
	        {
	        	tags = rs.getString("taglist").split(" ");
	            System.out.println(tags.length);
	            ArrayList<String> tag = new ArrayList<String>();
	            for(int i=0;i<tags.length;i++)
	            {
	            	tag.add(tags[i]);
	            }
	            System.out.println(tag);
	    		entries.add( new QuestionEntry( rs.getString("question"),rs.getString("title"), rs.getString("postedby") ,rs.getString("posttime"),tag,rs.getInt("id")) );
	    		
	        }
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
	    System.out.println(entries);
	    getServletContext().setAttribute( "entries", entries );
        
       
        response.sendRedirect( "QuestionList" );
    }
}
