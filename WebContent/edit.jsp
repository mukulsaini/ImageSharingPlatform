<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="Servlets.DbConnection"%>
<%@ page import ="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>



body {
    background-image: url("http://www.hdwalley.com/wp-content/uploads/2015/10/white_background_best_2019_hd_backgrounds.jpg");
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: #f1f1f1;
}

li a {
    display: block;
    color: #000;
    padding: 8px 0 8px 16px;
    text-decoration: none;
}

li a.active {
    
    background-color: #0B0B3B;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
div.img {
    margin: 5px;
    border: 1px solid #ccc;
    float: left;
    width: 180px;

}

div.img:hover {
    border: 1px solid #777;
}

div.img img {
    width: 100%;
    height: 180px;
}

div.desc {
    padding: 15px;
    text-align: center;
}

form{
    display : inline-block ;
    margin-bottom: 800px;
    margin-right: 150px;
    margin-left: 250px;

}
#header {
    opacity: 0.2;
    background-color:#115EA2;
    height:40px;
    width:100%;
    color:black;
    font-family: "Lucida Console";
    font-size: 40px;
}

#footer {
    position:fixed;
    width:100%;
    bottom:0;
    height:35px;
    background-color: #115EA2;

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  DbConnection obj = new DbConnection();
	Connection conn = obj.createConnection();
	HttpSession s = request.getSession();
	String user = s.getAttribute("user").toString();
	Statement st;
	try {
		st = conn.createStatement();
		ResultSet rs = null;
		String check = "Select * from users where username='" + user + "'";
		rs = st.executeQuery(check);
		if(rs==null)
		{
			System.out.println("No such user exists");
		}
		else
		{
			String fname = rs.getString("First_Name");
			String lname = rs.getString("Last_Name");
			String pass = rs.getString("passwd");
			String email = rs.getString("Email");
			String sex = rs.getString("Gender");
			String dob = rs.getString("DOB");
			
			ArrayList<String> temp = new ArrayList<String>();
			Iterator<String> i ;
			while(rs.next())
			{
				%>
			<img src="retrieve?abc=<%=rs.getString("user")%>" height="400" width="40%">
	<% 		}
		}
		} catch(Exception e)
		{
			System.out.println("No Connection");
			System.out.println(e.getMessage());
		}%>

<div id="header">
<p align="center" style="color: black">Shareware
</div>


<div class="img">
  <a target="_blank" href="a.jpg">
    <img src="a.jpg" alt="a" width="300" height="200">
  </a>
 
  <div class="desc"><p class="form-actions"><input type="submit" class="button-green" value="Edit Display Picture" /></p></div>
</div>
<ul>
  <li><a class="active" href="#home">Edit Profile</a></li>
  <li><a href="room.jsp">Home</a></li>
  <li><a href="#contact">Settings</a></li>
  <li><a href="logout.jsp">Logout</a></li>
</ul>


<form  method="POST" accept-charset="utf-8" class="adjacent bordered" style= margin: 100px 150px 100px 80px; >



    
   <input type="hidden" name="csrfmiddlewaretoken" value="1c944ef57b670e6f07b38f647375191e"/>  
    <p name="first_name_section" class="form-text">
        <label for="first_name">Name</label>
        <span><input name="first_name" autocorrect="off" value="" maxlength="30" type="text" id="first_name" /></span>
    </p>
    
    
    
    
    <p name="email_section" class="form-text">
        <label for="email">Email</label>
        <span><input type="email" name="email" value="sdgupta.gupta9@gmail.com" id="email" /></span>
    </p>
    
    
    
    
    <p name="username_section" class="form-text">
        <label for="username">Username</label>
        <span><input name="username" maxlength="30" autocapitalize="off" autocorrect="off" type="text" id="username" value="samypro1" /></span>
    </p>  
    <p name="gender_section" class="form-select">
        <label for="gender">Sex</label>
        <span><select name="gender" id="gender">
<option value="3">--------</option>
<option value="1" selected="selected">Male</option>
<option value="2">Female</option>
</select></span>
    </p>
    <p class="form-actions"><input type="submit" class="button-green" value="Submit" /></p>

</form>

 
</body>
</html>