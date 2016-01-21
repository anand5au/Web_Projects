package stackoverflow.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stackoverflow.User;

import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init(ServletConfig config) throws ServletException 
    {
    	super.init(config);
    }
    
    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher("login.jsp").forward(request, response);        
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {        
		List<User> users=new ArrayList<User>();
    	boolean isUserAuthenticated=false;
    	User userAuthenticated=null;
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	
    	String sql = "select * from users where username = '" + username
        + "' and password = '" + password + "'";

	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/stackoverflow";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "stackoverflow","password" );
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( sql );
	        while( rs.next() )
	            users.add( new User(rs.getString( "username" ),rs.getString( "password" )));
	        c.close();
	        getServletContext().setAttribute( "users", users );
	    }
	    catch( Exception e )
	    {
	        throw new ServletException( e );
	    }
    	
    	for(User user:users)
    	{
    		if(user!=null)
    		{
    			if(user.getUsername().equals(username) && user.getPassword().equals(password))
    			{
    				isUserAuthenticated=true;
    				userAuthenticated=user;
    				break;
    			}
    		}
    	}
    	
    	if(isUserAuthenticated)
    	{
    		HttpSession session=request.getSession();
    		session.setAttribute("user", userAuthenticated);
    		response.sendRedirect("QuestionList");
    	}
    	else
    	{
    		response.sendRedirect("login");
    	}
    }
}
