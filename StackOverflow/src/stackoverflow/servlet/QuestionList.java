package stackoverflow.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.AnswerList;
import stackoverflow.QuestionEntry;
import stackoverflow.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;
import javax.servlet.ServletConfig;

@WebServlet("/QuestionList")
public class QuestionList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public QuestionList() 
    {
        super();
    }
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );
        List<QuestionEntry> entries = new ArrayList<QuestionEntry>();
        String[] tags = null;
        String sql = "select * from questions";
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
	    catch( Exception e )
	    {
	        throw new ServletException( e );
	    }
	    getServletContext().setAttribute( "entries", entries );
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
				
		getServletContext().setAttribute("ansentries", ansentries);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	Map<String,Integer> tagMap = new TreeMap<String, Integer>();
    	
    	List<User> users = request.getSession().getAttribute("user")==null?new ArrayList<User>():(List<User>)getServletContext().getAttribute("users");
   		request.getServletContext().setAttribute("users",users);
    	List<QuestionEntry> entries = (List<QuestionEntry>) getServletContext().getAttribute( "entries" );
    	for(QuestionEntry entry:entries)
    	{
    		for(String tag:entry.getTagList())
    		{
    			Integer count = tagMap.get(tag);
    			count = (count==null)?1:count+1;
    			tagMap.put(tag,count);
    		}
    	}
    	tagMap.remove("");
    	ArrayList as = new ArrayList( tagMap.entrySet() );  
        
        Collections.sort( as , new Comparator() {  
            public int compare( Object o1 , Object o2 )  
            {  
                Map.Entry e1 = (Map.Entry)o1 ;  
                Map.Entry e2 = (Map.Entry)o2 ;  
                Integer first = (Integer)e1.getValue();  
                Integer second = (Integer)e2.getValue();  
                return second.compareTo( first );  
            }  
        });  
        Iterator i = as.iterator();  
        while ( i.hasNext() )  
        {  
            System.out.println( (Map.Entry)i.next() );  
        } 
    	request.getServletContext().setAttribute("tagStat", as);
    	request.getRequestDispatcher("/QuestionList.jsp").forward(request, response);
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}



