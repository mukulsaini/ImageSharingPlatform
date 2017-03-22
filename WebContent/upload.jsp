<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="Servlets.DbConnection"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.sql.*"%>

<%
	HttpSession s = request.getSession();

	String user = null;

	if (s.getAttribute("user") != null) {
		user = s.getAttribute("user").toString();
		System.out.println("************" + user);
	} else {
		System.out.println("***$$$$****" + s.getAttribute("user"));
		response.sendRedirect("front.jsp");
	}
	DbConnection db = new DbConnection();
	Connection conn = db.createConnection();
	Statement st;
	try {
		st = conn.createStatement();
		ResultSet rs = null;
		String check = "Select * from users where username='" + user
				+ "'";
		rs = st.executeQuery(check);
	} catch (Exception e) {

	}
	//
	//String res = s.getAttribute("flag").toString();
	//if(res=="1"){
	//	System.out.println("Image uploaded Successfully");
	//name =" Image is uploaded succssfully";
	//	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Example</title>
</head>
<body>
	<!-- <p></p> -->
	<div>
		<a href="retrieve.jsp"><%=user%></p>
	</div>


	<div id="first">
		<form action="upload" method="post" enctype="multipart/form-data">
			<input type="file" name="photo">
			 <input type="text" name="caption"> 
			 <input type="submit" value="Submit">
		</form>
	</div>
</body>
</html>
