package Servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Date;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 16177215)
// upload file's size up to 16MB
public class upload extends HttpServlet {

	// database connection settings
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		// String firstName = request.getParameter("firstName");
		
		HttpSession s = request.getSession();
		System.out.println(s.getAttribute("user"));
		String username = s.getAttribute("user").toString();

		String Name = request.getParameter("user");
		String cap = request.getParameter("caption");
		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
		String flag = "0";
		// Connection conn = null; // connection to the database
		String message = null; // message will be sent back to client

		try {
			// connects to the database
			DbConnection db = new DbConnection();
			Connection conn = db.createConnection();
			// constructs SQL statement
			String sql = "INSERT INTO photos(Img,Upload_time,Caption,Username) values (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(4, username);
			Date date = new Date();
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(date));

			statement.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			statement.setString(3, cap);
			System.out.println("The image is ready \n\n");

			if (inputStream != null) {
				System.out.println("Entered");
				// fetches input stream of the upload file for the blob column
				// statement.setBlob(3, inputStream);
				statement.setBlob(1, inputStream);
			}
			// sends the statement to the database server
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "File uploaded and saved into database";
				flag = "1";
				response.sendRedirect("retrieve.jsp");
			}
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		}

		// sets the message in request scope
		request.setAttribute("Message", message);
		s = request.getSession();
		s.setAttribute("flag", flag);
		System.out.println(s.getAttribute("flag"));
		flag = "0";
		// forwards to the message page
		// getServletContext().getRequestDispatcher("/Message.jsp").forward(request,
		// response);
	}
}
