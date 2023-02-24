<%@page import="ip.dto.Message"%>
<%@page import="ip.beans.MessagesBean"%>
<%@page import="ip.services.MailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="userBean" class="ip.beans.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="messagesBean" class="ip.beans.MessagesBean" scope="session"></jsp:useBean>

<%
if(!(userBean.isLoggedIn())) response.sendRedirect("Login.jsp");
%>

<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
		String content = request.getParameter("content");
		String receiver = request.getParameter("receiver");
		
		if(MailService.getMailService().sendMail(receiver, content)) {
			session.setAttribute("notification", "Poruka je uspješno poslana!");
		} else {
			session.setAttribute("notification", "Greška prilikom slanja maila!");
		}
	} else {
		if (request.getParameter("id") != null) {
			session.setAttribute("notification", "");
			Message message = messagesBean.getMessageById(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("receiver", message.getMail());
		}
		else {
			response.sendRedirect("Messages.jsp");
		}
	}
%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Odgovor</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rowdies:wght@300&display=swap" rel="stylesheet">
	<link href="styles/Style.css" rel="stylesheet">
</head>
<body>
	<form method="POST" action="Response.jsp">
		<div class="mb-3">
		  <label for="sender" class="form-label">Pošiljalac</label>
		  <input type="text" class="form-control" name="sender" id="sender" value="CustomerSupportIP" readonly>
		</div>
		<div class="mb-3">
		  <label for="receiver" class="form-label">Primalac</label>
		  <input type="text" class="form-control" name="receiver" id="receiver" value="<%=session.getAttribute("receiver")%>" readonly>
		</div>
		<div class="mb-3">
		  <label for="receiver" class="form-label">Sadržaj</label>
		  <textarea class="form-control" name="content" id="content"></textarea>
		</div>
		<p class="errorMessage"><%=session.getAttribute("notification").toString()%></p>
		<div class="d-grid gap-2 col-6 mx-auto">
  			<button class="btn btn-warning btn-lg" type="submit" name="submit">Pošalji odgovor</button>
  			<a class="text-dark btn btn-link" href="Messages.jsp">Vrati se na prethodnu stranu...</a>
		</div>
	</form>

</body>
</html>