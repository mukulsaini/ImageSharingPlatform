package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Follow
 */
@WebServlet("/follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Follow Button Example!");
		HttpSession s = request.getSession();
		String user = s.getAttribute("user").toString();
		System.out.println(user);
		String follow = request.getParameter("follow");
		System.out.println(follow);
		PrintWriter out = response.getWriter();
		
		
		// TODO Auto-generated method stub
		//System.out.println("Like Button Example!");
		
		
		
		//String q="Update photos Set Likes=Likes+1 where Img_ID=rs.getInt('ID')";
	//	String q4="Update photos Set Likes=Likes-1 where Img_ID=rs.getInt('ID')";
	//	String q3="DELETE FROM photos WHERE Img_ID=rs.getInt('ID') and user_Liked=";
		String q1="insert into follows(username,user_followers)values(?,?)";
		String res="follow";
		
		Connection con = null;
		try{
			 	DbConnection obj = new DbConnection();
			    Connection conn=obj.createConnection();
			    Statement stmt;
			    stmt=conn.createStatement();
			    ResultSet rs = stmt.executeQuery("Select * from follows where username='"+follow+"'and user_followers='"+user+"'");
			    if(rs.first()== false)
			    {
			    	System.out.println("The user hasn't followed it yet");
			    	PreparedStatement statement = conn.prepareStatement(q1);
			    	statement.setString(1,follow);
					statement.setString(2,user);
			    	int row = statement.executeUpdate();
					if (row > 0) {
						String message="Changes done and saved into database1";
						PreparedStatement stat = conn.prepareStatement("Update users Set No_of_followers=No_of_followers+1 where username='"+follow+"'");
						int r= stat.executeUpdate();
						if(r>0){
							System.out.println(message);
						}
					}
				}
			    else
			    {	System.out.println("The user has already liked it ");
			    	PreparedStatement statement = conn.prepareStatement("DELETE FROM follows WHERE username='"+follow+"'and user_followers='"+user+"'");
			    	int row = statement.executeUpdate();
					if (row > 0) {
						String message="Changes done and saved into database";
						PreparedStatement stat = conn.prepareStatement("Update users Set No_of_followers=No_of_followers-1 where username='"+follow+"'");
						int r= stat.executeUpdate();
						if(r>0){
							System.out.println(message);
						}
					}
				}
			    
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
