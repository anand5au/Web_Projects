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
import javax.servlet.http.HttpSession;

import stackoverflow.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<User> users = (List<User>)getServletContext().getAttribute("users");
		boolean isUserAuthenticated=false;
    	User userAuthenticated=null;
    	String username = request.getParameter("username");
    	String password=request.getParameter("password");
		try
	    {
	        String url = "jdbc:mysql://localhost/cs320stu08";
	        //Connection c = DriverManager.getConnection( url, "root","root" );
	        Connection c = DriverManager.getConnection( url, "cs320stu08","3jy!Z#!w" );
	        PreparedStatement stmt = c.prepareStatement("insert into users values(?,?)");
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        stmt.execute();
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
	    users.add(new User(username,password));
	    getServletContext().setAttribute( "users", users );
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
