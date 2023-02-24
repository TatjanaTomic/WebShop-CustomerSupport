<%@page import="ip.dto.Message"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ip.beans.MessagesBean"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="userBean" class="ip.beans.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="messagesBean" class="ip.beans.MessagesBean" scope="session"></jsp:useBean>
    
<%
	if(!(userBean.isLoggedIn())) response.sendRedirect("Login.jsp");
%>

<%
	String action = request.getParameter("action");

	List<Message> messages = new ArrayList<>();
	
	if(action == null || "viewAllMessages".equals(action))  {
		messages = messagesBean.getAllMessages();
	}
	else if("viewUnreadMessages".equals(action)){
		messages = messagesBean.getUnreadMessages();
	}
	else if("updateMessage".equals(action) && (request.getParameter("id")!=null)) {
		messagesBean.markAsRead(Integer.parseInt(request.getParameter("id")));
		messages = messagesBean.getAllMessages();
	}
	else if("search".equals(action) && (request.getParameter("content") != null)){
		messages = messagesBean.getAllMessagesWithContent(request.getParameter("content"));
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
	<link href="styles/MessagesStyle.css" rel="stylesheet">
	<script src="scripts/Script.js"></script>
</head>
<body onload="init()">
	<header>
	   	<div class="d-flex flex-column flex-md-row align-items-center pb-3 border-bottom my-header-1">
      		<h3>CustomerSupportIP</h3>

      		<nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
        		<span class="me-3 py-2 text-dark text-decoration-none">Operater: <%=userBean.getFirstName()%> <%=userBean.getLastName()%></span>
        		<a class="btn btn-outline-secondary" href="Logout.jsp">Odjavi se</a>
      		</nav>
    	</div>
    	
    	<nav class="navbar navbar-expand-lg navbar-light bg-light my-header-2">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="btn btn-light" href="Messages.jsp?action=viewAllMessages">Sve poruke</a>
		        </li>
		        <li class="nav-item">
		          <a class="btn btn-light" href="Messages.jsp?action=viewUnreadMessages">Neporočitane poruke</a>
		        </li>
		    </ul>
		    <form class="d-flex" method="POST" action="Messages.jsp?action=search">
		        <input class="form-control me-2" type="search" placeholder="Pretraži sve poruke" aria-label="Search" id="content" name="content" required>
		        <button class="btn btn-outline-primary" type="submit">Pretraži</button>
		    </form>
		</nav>
	</header>
	
	<div class="content">
		<table class="table table-hover my-table" id="messages-table">
			<thead>
			    <tr>
			      <th scope="col">Id</th>
			      <th scope="col">Pošiljalac</th>
			      <th scope="col">Sadržaj poruke</th>
			      <th scope="col">Vrijeme prijema</th>
			      <th scope="col">Označi kao pročitano</th>
			      <th scope="col">Odgovori</th>
			    </tr>
			</thead>
			<tbody id="messages-table-content">
			<%
				for(Message m : messages) {
					
					String styleClass = "unreadMessage";
					if(m.isRead()) {
						styleClass = "readMessage";
					}
					
					out.println("<tr><th scope=\"row\">" + m.getId() + "</th>");
					out.println("<td class=\"" + styleClass + "\">" + m.getUsername() + "</td>");
					out.println("<td class=\"" + styleClass + "\">" + m.getContent() + "</td>");
					out.println("<td class=\"" + styleClass + "\">" + m.getDateTime() + "</td>");
					if(m.isRead()) {
						out.println("<td><button type=\"button\" class=\"btn btn-outline-warning\" disabled>Označi kao pročitano</button></td>");
					}
					else {
						out.println("<td><a class=\"btn btn-warning\" href=\"Messages.jsp?action=updateMessage&id=" + m.getId() + "\">Označi kao pročitano</a></td>");
					}
					out.println("<td><a class=\"btn btn-outline-success\" href=\"Response.jsp?id=" + m.getId() + "\">Odgovori</a></td></tr>");
				}
				
			%>
			</tbody>
		</table>
		
	</div>
</body>
</html>