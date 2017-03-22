<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="Servlets.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retrieval of Image from database</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style2.css" >

</head>
<body>
	<%
	 HttpSession s = request.getSession();
	System.out.println(s.getAttribute("user"));
	String username = s.getAttribute("user").toString();
	String r = s.getAttribute("res").toString();
	System.out.println("*******"+s.getAttribute("res"));
	%>
	<p>
		<a href="profile.jsp"><%=username %> </a>
	</p>
	<% 
	DbConnection obj = new DbConnection();
	Connection con=obj.createConnection();
try{
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("Select * from photos where Username='"+username+"'");
	String q="Update photos Set Likes=Likes+1 where Img_ID=rs.getInt('ID')";
	String q4="Update photos Set Likes=Likes-1 where Img_ID=rs.getInt('ID')";
	String q1="insert into likes(ID,user_Liked)values(?,?)";
	String q3="DELETE FROM photos WHERE Img_ID=rs.getInt('ID') and user_Liked=username";
	//statement.setInt(1,rs.getInt("ID"));
	//statement.setDate(2,username);
	String q2="Select * from likes where ID=rs.getInt('ID') and user_Liked=username";
	ResultSet rs1 = null;
	Statement stmt1 = con.createStatement();
	ArrayList<String> temp = new ArrayList<String>();
	Iterator<String> i ;
	
	while(rs.next())
	{
		String check1 = "Like";
		String q5 = "Select * from likes where User_Liked = '"+ username+"' and ID = "+rs.getInt("Img_ID")+"";
		
		rs1= stmt1.executeQuery(q5);
		if( rs1.first()== true){
			 check1 = "Unlike";
		}
		
	%>
	<div class="wrapper">
		<div class="image-wrapper">
			<img src="retrieve?abc=<%=rs.getInt("Img_ID")%>" >
			
			<div id="nolikes">
				<%= rs.getInt("Likes")%> likes
			</div>
			<a href="#" class="like-active" id="like<%=rs.getInt("Img_ID")%>"><%=check1 %></a>
			
		</div>
	</div>
	<script type="text/javascript">
	var like = document.getElementById("like<%=rs.getInt("Img_ID")%>");
	
	
	like.onclick= function(e){
		
	e.preventDefault();
	
		
		var xhr =new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log(xhr.responseText);
			
				if(document.getElementById("like<%=rs.getInt("Img_ID")%>").innerHTML ==  "Like"){
					
					document.getElementById("like<%=rs.getInt("Img_ID")%>").innerHTML = "Unlike";
				}else if(document.getElementById("like<%=rs.getInt("Img_ID")%>").innerHTML ==  "Unlike"){
					
					document.getElementById("like<%=rs.getInt("Img_ID")%>").innerHTML = "Like";
				}
				
				
			}
		};
		xhr.open("GET", "like?user=<%=username%>&imgId=<%=rs.getInt("Img_ID") %>", true);
		xhr.send();
	
}
	</script>
	
	<%
	}
}
catch(Exception e)
{
	System.out.println(e);
}
         %>
</body>
</html>