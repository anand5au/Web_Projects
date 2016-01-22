package stackoverflow.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.AnswerList;

@WebServlet("/ChangeAnswerState")
public class ChangeAnswerState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeAnswerState() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
	    {
			Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        PreparedStatement stmt = c.prepareStatement("update answers set accepted=? where id=? and title=?");
	        stmt.setString(1,"yes");
	        stmt.setInt(2,Integer.parseInt(request.getParameter("aid")));
	        stmt.setString(3, request.getParameter("atitle"));
	        stmt.execute();
	        stmt = c.prepareStatement("update answers set accepted=? where id!=? and title=?");
	        stmt.setString(1,"no");
	        stmt.setInt(2,Integer.parseInt(request.getParameter("aid")));
	        stmt.setString(3, request.getParameter("atitle"));
	        stmt.execute();
	        c.close();
	    }
	    catch( Exception e )
	    {
	        throw new ServletException( e );
	    }
	    List<AnswerList> ansentries = new ArrayList<AnswerList>();
	    String sql = "select * from answers;";
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( sql );
	        while( rs.next() )
	        {
	        	ansentries.add(new AnswerList(rs.getInt("id"),rs.getString("answer"), rs.getString("postedby"), rs.getString("posttime"), rs.getString("title"),rs.getInt("pvote"),rs.getInt("nvote"),rs.getString("accepted")));
	        }
	        c.close();
	    }
	    catch( Exception e )
	    {
	        throw new ServletException( e );
	    }
	    getServletContext().setAttribute( "ansentries", ansentries );
        response.sendRedirect( "QuestionList" );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
