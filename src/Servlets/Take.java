package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class Take
 */
@WebServlet("/take")
public class Take extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Take() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DbConnection obj = new DbConnection();
		Connection conn = obj.createConnection();
		HttpSession s = request.getSession();
		String user = s.getAttribute("user").toString();
		boolean u = false;
		String n1 = request.getParameter("name");
		Statement st;
		char b;
		int i = 0;
		System.out.println(n1);
		
			 i = n1.indexOf(' ', i+1);
			 String k  = n1.substring(0, i);
			 String c =  n1.substring(i+1);
			 k=k.substring(0,1).toUpperCase()+k.substring(1).toLowerCase();
			 c=c.substring(0,1).toUpperCase()+c.substring(1).toLowerCase();
		try {
		st = conn.createStatement();
		ResultSet rs = null;
		String check = "Select First_Name,Last_Name from users where LOWER(First_Name)=LOWER('"+k+"')and LOWER(Last_Name)=LOWER('"+c+"')";
		rs = st.executeQuery(check);
		//System.out.println(rs.next());
		while(rs.next())
		{
			String fname = rs.getString("First_Name");
			String lname = rs.getString("Last_Name");
			System.out.println(fname+lname);
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	}

}
