package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.sql.*;
import java.io.*;

/**
 * Servlet implementation class retrieve
 */
@WebServlet("/retrieve")
public class retrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("abc");
		int p=Integer.parseInt(op);
		System.out.println(p);
		System.out.println("Retrive Image Example!");
		Connection con = null;
		try{
			 DbConnection obj = new DbConnection();
			    Connection conn=obj.createConnection();
			    Statement stmt;
			    stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select Img from photos where Img_ID="+p);
			int i = 0;
			while (rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				byte[] img=rs.getBytes("Img");
				OutputStream f = new ByteOutputStream();
				i++;
				int c = 0;
				while ((c = in.read()) > -1) {
					//System.out.println("above");
					response.getOutputStream().write(c);
					//System.out.println("below");
				}
				f.close();
				in.close();
				System.out.println("Successfull");
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub{
	}
			
}
