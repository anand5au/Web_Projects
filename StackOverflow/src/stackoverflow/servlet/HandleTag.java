
package stackoverflow.servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stackoverflow.QuestionEntry;


@WebServlet("/HandleTag")
public class HandleTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HandleTag() 
    {
        super();
        
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<QuestionEntry> entries = (List<QuestionEntry>) getServletContext().getAttribute( "entries" );
		ArrayList<QuestionEntry> tagEntries = new ArrayList<QuestionEntry>(); 
		for(  int i=entries.size()-1 ; i >= 0  ; --i )
        {
        	QuestionEntry entry = entries.get(i);
        	if(entry.getTagList().contains(request.getParameter("tagName")))
        	{
        		tagEntries.add(entry);
        	}
        }
		getServletContext().setAttribute("tagEntries", tagEntries);
		request.getRequestDispatcher("/HandleTag.jsp").forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
