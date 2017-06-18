<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <header class="top">
        <div class="nav">
            <a class="logo" href="<%=request.getContextPath()%>"></a>
            <div class="self">${user.username} ${user.userid}</div>
            <div class="navtag">
                <ul class="navul">
                    <li class="topnav"><a href="#">首页</a></li>
                    <li class="topnav"><a href="<%= request.getContextPath()%>/myInfo/ ">我的</a></li>
                </ul>
            </div>
            <div class="top-search">
            	<form id="search-user" method="get" action="<%=request.getContextPath()%>/searchUser">
					<input class="search-top" placeholder="搜索用户" type="text" name="name"/>
					<button type="submit"  class="search">搜索</button>
				</form>
            </div>
        </div>
    </header>