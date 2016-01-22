package stackoverflow.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.servlet.http.HttpSession;

import stackoverflow.AnswerList;

@WebServlet("/QuestionDisplay")
public class QuestionDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuestionDisplay() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<AnswerList> ansentries = new ArrayList<AnswerList>();
		try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("select * from answers");
	        while(rs.next())
	        {
	        	ansentries.add(new AnswerList(rs.getInt("id"),rs.getString("answer"), rs.getString("postedby"), rs.getString("posttime"), rs.getString("title"),rs.getInt("pvote"),rs.getInt("nvote"),rs.getString("accepted")));
	        }
	    }
	    catch(Exception e)
	    {
	    	throw new ServletException( e );
	    }
				
		request.getServletContext().setAttribute("ansentries", ansentries);
		request.getRequestDispatcher("/QuestionDisplay.jsp").forward(request,response);
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session=request.getSession();
		if(session.getAttribute("user")==null)
		{
			response.sendRedirect("login");
		}
		List<AnswerList> ansentries = (List<AnswerList>)getServletContext().getAttribute("ansentries");
		DateFormat formatter;
		formatter=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        PreparedStatement stmt = c.prepareStatement("insert into answers values(?,?,?,?,?,?,?,?)");
	        stmt.setInt(1,ansentries.size()+1);
	        stmt.setString(2,request.getParameter("anstitle"));
	        stmt.setString(3,request.getParameter("answer"));
	        stmt.setString(4,request.getParameter("postby"));
	        stmt.setString(5,formatter.format(new java.util.Date()));
	        stmt.setInt(6,0);
	        stmt.setInt(7,0);
	        stmt.setString(8,"no");
	        stmt.execute();
	        c.close();
	    }
	    catch( Exception e )
	    {
	        throw new ServletException( e );
	    }
	    ansentries = new ArrayList<AnswerList>();
		try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery("select * from answers");
	        while(rs.next())
	        {
	        	ansentries.add(new AnswerList(rs.getInt("id"),rs.getString("answer"), rs.getString("postedby"), rs.getString("posttime"), rs.getString("title"),rs.getInt("pvote"),rs.getInt("nvote"),rs.getString("accepted")));
	        }
	    }
	    catch(Exception e)
	    {
	    	throw new ServletException( e );
	    }
				
		getServletContext().setAttribute("ansentries", ansentries);
        response.sendRedirect( "QuestionList" );
	}

}
