<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="Servlets.DbConnection"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.lang.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search people here</title>
</head>
<body>
 <%		DbConnection obj = new DbConnection();
		Connection conn = obj.createConnection();
	HttpSession s = request.getSession();
	String user = s.getAttribute("user").toString();
	Statement st;
	try {
		st = conn.createStatement();
		ResultSet rs = null;
		String check = "Select First_Name,Last_Name from users";
		rs = st.executeQuery(check);
		while(rs.next()==true)
		{
			String fname = rs.getString("First_Name");
			String lname = rs.getString("Last_Name");
			String fv= fname.substring(0,1).toUpperCase()+fname.substring(1);
			System.out.println(fv+" "+lname);
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
		%>
</body>
</html>