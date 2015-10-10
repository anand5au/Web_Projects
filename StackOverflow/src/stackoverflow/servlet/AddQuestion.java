package stackoverflow.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.QuestionEntry;

@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddQuestion()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	if(request.getSession().getAttribute("user")==null)
		{
			response.sendRedirect("login");
			return;
		}
    	request.getRequestDispatcher("/AddQuestion.jsp").forward(request, response);
		
    }

    @SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        List<QuestionEntry> entries = (List<QuestionEntry>) getServletContext().getAttribute( "entries" );
        DateFormat formatter;
		formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        try
	    {
	        String url = "jdbc:mysql://localhost/cs320stu08";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "cs320stu08","3jy!Z#!w" );
	        PreparedStatement stmt = c.prepareStatement("insert into questions values(?,?,?,?,?,?)");
	        stmt.setInt(1,entries.size()+1);
	        stmt.setString(2,request.getParameter("title"));
	        stmt.setString(3,request.getParameter("question"));
	        stmt.setString(4,request.getParameter("tag"));
	        stmt.setString(5,request.getParameter("postedby"));
	        stmt.setString(6,formatter.format(new java.util.Date()));
	        stmt.execute();
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
        String[] tags = null;
        String sql = "select * from questions where id="+(entries.size()+1)+";";
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
	            ArrayList<String> tag = new ArrayList<String>();
	            for(int i=0;i<tags.length;i++)
	            {
	            	tag.add(tags[i]);
	            }
	    		entries.add( new QuestionEntry( rs.getString("question"),rs.getString("title"), rs.getString("postedby") ,rs.getString("posttime"),tag,rs.getInt("id")) );
	    		
	        }
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
	    getServletContext().setAttribute( "entries", entries );
        
       
        response.sendRedirect( "QuestionList" );
    }
}
