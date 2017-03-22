package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String user = request.getParameter("uname");
		String pwd = request.getParameter("pass");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String sex = request.getParameter("gender");
		String DOB = request.getParameter("DOB");
		System.out.println(user + " " + pwd + " " + fname + " " + lname + " "
				+ email + " " + sex + " " + DOB);
		DbConnection obj = new DbConnection();
		Connection conn = obj.createConnection();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = null;
			String check = "Select * from users where username='" + user + "'";
			rs = st.executeQuery(check);
			System.out.println("checking duplicate values");

			if (rs.next() == false) {
				System.out.println("insert into users(username,First_Name,Last_Name,passwd,Email,Gender,DOB) values ('"+ user+
						"','"+ fname+ "','"+ lname+ "','"+ pwd+ "','"+ email+ "','"+ sex+ "','"+ DOB + "')");
				int i = st.executeUpdate("insert into users(username,First_Name,Last_Name,passwd,Email,Gender,DOB) values ('"
								+ user+ "','"+ fname+ "','"+ lname+ "','"+ pwd+ "','"+ email+ "','"+ sex+ "','"+ DOB + "')");
				if (i > 0) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					System.out.println(session.getAttribute("user"));
					System.out.println("Session is created");
					response.sendRedirect("room.jsp");
					System.out.print("Registration Successfull!"
							+ "<a href='index.jsp'>Go to Login</a>");
				}
			} else {
				System.out.println("Username exists already");
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
