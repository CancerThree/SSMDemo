<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.cmb.zh.domain.UserInfo"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.cmb.zh.domain.User"%>
<%@ page import="java.math.BigDecimal"%>

<script>
function toggleFollow(ele){
	var followUrl = "";
	if ($(ele).html().trim() === "Follow") {
		$(ele).html("UnFollow");
		followUrl = "followUser";
	} else {
		$(ele).html("Follow");
		followUrl = "unfollowUser";
	}
	
	$.ajax({
		url:"<%= request.getContextPath() %>/myInfo/" + followUrl,
		data:{userid:parseInt($(ele).siblings("#userid")[0].value)}, //$("#followUser").serialize(),
		type:"POST",
		success: function(errorCode) {
			if (errorCode.errorCode === 0) {
				alert("成功");
				//location.reload();
			} else {
				alert("失败\n" + errorCode.errorInfo);
			}
		},
		error: function(err) {
			alert("失败" + err);
			console.log(err);
		}
	});
}
</script>

<%
	HashMap<User, UserInfo> users = (HashMap<User, UserInfo>) request.getAttribute("users");
	HashMap<BigDecimal, Integer> isFollow = (HashMap<BigDecimal, Integer>) request.getAttribute("isFollow");
	if (users == null || users.isEmpty()) {
%><h2>没有相关用户</h2>
<%
	} else {
		//Iterator ite = users.entrySet().iterator();
		for (Entry<User, UserInfo> entry : users.entrySet()) {
			User userKey = entry.getKey();
			UserInfo userInfo = entry.getValue();
%>
<div class="admin-user-block">
	<div class="title">
		<h2><%=userKey.getUsername()%></h2>
	</div>
	<div class="follow">
		<form id="followUser" onsubmit="return false;">
			<input type="hidden" id="userid" name="userid" value=<%=userKey.getUserid()%> >
			<%
			Integer follow = isFollow.get(userKey.getUserid());
			%>
			<button onclick="toggleFollow(this)" >
			<% 
			if (follow == null || follow == 0){
				%>Follow<%
			} else {
			%>
				UnFollow
			<% 
			}
			%>
			</button>
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
						String[] tags = tag.split(";|；");
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
<div class="tags"></div></div>
<%
			}
		}
	}
%>