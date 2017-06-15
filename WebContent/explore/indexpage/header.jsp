<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <!--<header class="am-topbar am-topbar-fixed-top">
        <div class="am-container">
            <h1 class="am-topbar-brand">
                <a href="#">招呼</a>
            </h1>
            <div class="am-collapse am-topbar-collapse" id="collapse-head">
                <ul class="am-nav am-nav-pills am-topbar-nav">
                    <li class="am-active"><a href="#">首页</a></li>
                    <li><a href="#">项目</a></li>
                    <li class="am-dropdown" data-am-dropdown>
                        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">下拉菜单</span></a>
                        <ul class="am-dropdown-content">
                            <li class="am-dropdown-header">标题</li>
                            <li><a href="#">默认</a></li>
                            <li><a href="#">基础设置</a></li>
                            <li><a href="#">文字排版</a></li>
                            <li><a href="#">网格系统</a></li>
                        </ul>
                    </li>
                </ul>

                <div class="am-topbar-right">
                    <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm">注册</button>
                </div>
            </div>
        </div>
    </header>-->
    <script>
    	function searchUser(){
    		$.ajax({
    			type: "GET",
    			url: "<%=request.getContextPath()%>/searchUser",
    			data: $("#search-user").serialize(),
    			dataType:"html",
    			success: function(res){
    				console.log(res);
    				$("#selfIndexBody").html(res);
    			},
    			error: function(err){
    				console.log(err);
    				alert(err);
    			}
    		});
    	}
    </script>
    
    <header class="top">
        <div class="nav">
            <a class="logo" href="<%=request.getContextPath()%>"></a>
            <div class="self">${user.username} ${user.userid}</div>
            <div class="navtag">
                <ul class="navul">
                    <li class="topnav">首页</li>
                    <li class="topnav">我的</li>
                </ul>
            </div>
            <div class="top-search">
            	<form id="search-user" onsubmit="return false;">
					<input class="search-top" placeholder="搜索用户" type="text" name="name"/>
				</form>
				<button onclick="searchUser()" class="search">搜索</button>
            </div>
        </div>
    </header>