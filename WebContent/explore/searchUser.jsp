<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.cmb.zh.domain.User" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="java.util.HashMap" %>
        <%@ page import="com.cmb.zh.domain.UserInfo" %>
        <%@ page import="java.util.Iterator" %>
        <%@ page import="java.util.Map.Entry" %>
                <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                <html class="no-js">

                <head>
                    <meta charset="utf-8">
                    <title>招呼 - ${user.username}</title>
                    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/login.css">
                    <link rel="stylesheet" href="<%= request.getContextPath() %>/style/explore.css">
                    <script src="<%=request.getContextPath()%>/scripts/jquery-3.2.1.min.js"></script>

                </head>

                <body>
                    <jsp:include page="indexpage/header.jsp" flush="true"></jsp:include>

                    <div id="selfIndexBody" class="selfbody view-signup">
						<jsp:include page="indexpage/userList.jsp" flush="true"></jsp:include>
                    </div>
                </body>

                </html>