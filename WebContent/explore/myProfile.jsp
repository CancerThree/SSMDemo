<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.cmb.zh.domain.UserInfo" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html class="no-js">

        <head>
            <meta charset="utf-8">
            <title>招呼 - ${user.username}</title>
            <link rel="stylesheet" href="<%= request.getContextPath() %>/style/explore.css">
            <script src="<%= request.getContextPath() %>/scripts/jquery-3.2.1.min.js"></script>
            <style type="text/css">
.left-box {
    float: left;
    width: 25%;
    padding-right: 24px;
    box-sizing: border-box;
}

.left-box nav.menu {
    margin-bottom: 15px;
    list-style: outside none none;
    background-color: #FFF;
    border: 1px solid #D1D5DA;
    border-radius: 3px;
}

.left-box a {
    display: block;
}

.left-box a.menu-item {
    position: relative;
    display: block;
    padding: 8px 10px;
    border-bottom: 1px solid #E1E4E8;
    text-decoration: none;
}

.menu-item:active,
.menu-item:hover {
    text-decoration: none !important;
    background-color: #F6F8FA;
}

.left-box a.selected-item {
    font-weight: 600;
    color: #24292E;
    cursor: default;
    background-color: #FFF;
}

a.selected-item::before {
    position: absolute;
    top: 0px;
    bottom: 0px;
    left: 0px;
    width: 2px;
    content: "";
    background-color: #E36209;
}

.left-box nav.menu h3.menu-heading {
    display: block;
    padding: 8px 10px;
    margin-top: 0px;
    margin-bottom: 0px;
    font-size: 13px;
    font-weight: 600;
    line-height: 20px;
    color: #586069;
    background-color: #F3F5F8;
    border-bottom: 1px solid #E1E4E8;
}
            </style>
            
<script>
        function getPage(ele) {
            var href = $(ele).attr('href');
            changeNav(ele);
            console.log(href);

            $.ajax({
                url: href,
                type: "GET",
                data: {},
                success: function(res) {
                    $("#setting-right-box").html(res);
                },
                error: err => {
                    console.log(err);
                    alert(err);
                }
            });
        }

        function changeNav(ele) {
            var siblings = $(ele).siblings('.selected-item');
            if (siblings.length != 0) {
                $(siblings).toggleClass('selected-item');
                $(ele).toggleClass('selected-item');
            }
        }

        $(function() {
            getPage(('#my-settings'));
        });
    </script>
        </head>

        <body>
            <jsp:include page="indexpage/header.jsp" flush="true"></jsp:include>

            <div id="selfIndexBody" class="selfbody view-signup">
                <div class="left-box">
    				<nav class="menu">
        				<h3 class="menu-heading">用户设置</h3>
        				<a class="menu-item selected-item" id="my-settings" onclick="getPage(this);return false;" href="<%= request.getContextPath() %>/myInfo/mySettings">个人设置</a>
        				<a class="menu-item" href="<%= request.getContextPath() %>/myInfo/myFollowers" onclick="getPage(this);return false;">Followers</a>
        				<a class="menu-item" href="<%= request.getContextPath() %>/myInfo/myFollowings" onclick="getPage(this);return false;">Following</a>
    				</nav>
				</div>
				<div id="setting-right-box" style="float: left; width: 75%;">
				</div>
            </div>
        </body>

        </html>