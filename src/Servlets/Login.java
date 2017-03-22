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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("Username");
		String pwd = request.getParameter("Password");
		// request.setAttribute(arg0, arg1)
		DbConnection db = new DbConnection();
		Connection conn = db.createConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("select * from users where username='" + user
					+ "' and passwd='" + pwd + "'");
			if (rs.next()) {
				// out.println("welcome " + userid);
				// out.println("<a href='logout.jsp'>Log out</a>");
				System.out.println(user);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				System.out.println(session.getAttribute("user"));
				System.out.println("Session is created");
				response.sendRedirect("room.jsp");
			} else {
				System.out.println("Invalid password"
						+ "try again</a>");
				response.sendRedirect("front.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
