<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cmb.zh.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
	for(int i=0; i<users.size(); i++){
		User user = users.get(i);
		%>
		<h1><%= user.getUsername()%></h1>
		<%
	}
%>