<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import ="javax.servlet.http.HttpSession" %>
<title>Your Account</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css" >

</head>
<body>
<%   HttpSession s = request.getSession();
    String res = "like";
	String user = s.getAttribute("user").toString();
	s.setAttribute("res", res);%>

<div id="header">
<h1>HOME</h1>
</div>

<div id="nav">
<ul>
  <li><a id="first" href="#" >Upload Image</a></li>
  <li><a href="retrieve.jsp">View Images</a></li>
  <li><a href="edit.jsp">Settings</a></li>
  <li><a href="https://www.instagram.com/">About</a></li>
  <li><a href="logout.jsp">Logout</a></li>
</ul>
</div>
<div id="upload" hidden>
			<h2>Upload Image</h2>
		    <form action="upload" method="post" enctype="multipart/form-data">
		    	<input type="file" name= "photo" >
		    	<input type="text" name="caption" >
		    	<input type="submit" value="Submit"> 
	    	</form>
	    </div>
<div>
	<h2>Searching people</h2>
	<form action="take" method="post">
		<input type="text" name="name" >
	</form>
</div>
<script src="${pageContext.request.contextPath}/css/jquery.js"></script>
<script src="${pageContext.request.contextPath}/css/lightbox.js"></script>

<script>
	$('#first').click(function(e) {
    $('#upload').lightbox_me({
        centered: true, 
		overlayCSS:	{
						background: 'silver', 
						opacity: .4
					},
        onLoad: function() { 
          //  $('#upload').find('input:second').focus()
            }
        });
    e.preventDefault();
});
 
</script>
</body>
</html>