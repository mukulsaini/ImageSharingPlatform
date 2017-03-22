package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

/**
 * Servlet implementation class Like
 */
@WebServlet("/like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Like() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Like Button Example!");
		HttpSession s = request.getSession();
		String user = s.getAttribute("user").toString();
		System.out.println(user);
		int id = Integer.parseInt(request.getParameter("imgId"));
		System.out.println(id);
		PrintWriter out = response.getWriter();
		
		
		// TODO Auto-generated method stub
		//System.out.println("Like Button Example!");
		
		
		
		//String q="Update photos Set Likes=Likes+1 where Img_ID=rs.getInt('ID')";
	//	String q4="Update photos Set Likes=Likes-1 where Img_ID=rs.getInt('ID')";
	//	String q3="DELETE FROM photos WHERE Img_ID=rs.getInt('ID') and user_Liked=";
		String q1="insert into likes(ID,user_Liked)values(?,?)";
		String res="Like";
		
		Connection con = null;
		try{
			 	DbConnection obj = new DbConnection();
			    Connection conn=obj.createConnection();
			    Statement stmt;
			    stmt=conn.createStatement();
			    ResultSet rs = stmt.executeQuery("Select * from likes where ID='"+id+"'and user_Liked='"+user+"'");
			    if(rs.first()== false)
			    {
			    	System.out.println("The user hasn't liked it yet");
			    	PreparedStatement statement = conn.prepareStatement(q1);
			    	statement.setInt(1,id);
					statement.setString(2,user);
			    	int row = statement.executeUpdate();
					if (row > 0) {
						String message="Changes done and saved into database1";
						PreparedStatement stat = conn.prepareStatement("Update photos Set Likes=Likes+1 where Img_ID='"+id+"'");
						
						int r= stat.executeUpdate();
						if(r>0){
							System.out.println(message);
						
						}
					}
					
					
				}
			    else
			    {	System.out.println("The user has already liked it ");
			    	PreparedStatement statement = conn.prepareStatement("DELETE FROM likes WHERE ID='"+id+"'and user_Liked='"+user+"'");
			    	int row = statement.executeUpdate();
					if (row > 0) {
						String message="Changes done and saved into database";
						PreparedStatement stat = conn.prepareStatement("Update photos Set Likes=Likes-1 where Img_ID='"+id+"'");
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
