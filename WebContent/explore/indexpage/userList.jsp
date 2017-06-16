<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.cmb.zh.domain.UserInfo"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.cmb.zh.domain.User"%>
<%
	HashMap<User, UserInfo> users = (HashMap<User, UserInfo>) request.getAttribute("users");
	if (users == null || users.isEmpty()) {
%><h2>没有相关用户</h2>
<%
	} else {
		//Iterator ite = users.entrySet().iterator();
		for (Entry<User, UserInfo> entry : users.entrySet()) {
			User user = entry.getKey();
			UserInfo userInfo = entry.getValue();
%>
<div class="admin-user-block">
	<div class="title">
		<h2><%=user.getUsername()%></h2>
	</div>
	<div class="follow">
		<form>
			<input type="hidden" id="article_id" value=<%=user.getUserid()%>>
			<button>Follow</button>
		</form>
	</div>
	<div class="introdction">
		<%
			if (userInfo != null) {
		%>
		<p><%=userInfo.getSelfintro()%></p>
	</div>
	<div class="tags">
		<%
				String tag = userInfo.getTag();
					if (tag != null && tag.length() > 0) {
						String[] tags = tag.split(";");
						for (int i = 0; i < tags.length; i++) {
		%>
		<span class="tag"><%=tags[i]%></span>
		<%
						}
					}
		%>
	</div>
</div>
	<%
			} else {
	%>
</div>
<div class="tags"></div>
<%
			}
		}
	}
%>