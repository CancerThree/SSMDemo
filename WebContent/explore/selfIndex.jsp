<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.cmb.zh.domain.UserInfo" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html class="no-js">

        <head>
            <meta charset="utf-8">
            <title>招呼 - ${user.username}</title>

            <link rel="stylesheet" href="../style/login.css">
            <link rel="stylesheet" href="../style/explore.css">
            <script src="../scripts/jquery-3.2.1.min.js"></script>
            <script>
                function saveSelfIntro() {
                    var inputs = $(".inputs").find("input");
                    for (var i = 0; i < inputs.length; i++) {
                        if ($(inputs[i]).val().length > 100) {
                            alert($(inputs[i]).prev("label").text() + "\n字符长度不能超过100");
                            return;
                        }
                    }

                    $.post("<%=request.getContextPath()%>/explore/selfIndex/saveSelfInfo",
                        $("#selfIntro").serialize(),
                        function(data) {
                            if (data.errorCode == 0) {
                                alert("保存成功");
                            } else {
                                alert("errorCOde" + data.errorInfo);
                            }
                        });
                }
            </script>
        </head>

        <body>
            <jsp:include page="indexpage/header.jsp" flush="true"></jsp:include>

            <div id="selfIndexBody" class="selfbody view-signup">
                <form id="selfIntro" action="<%=request.getContextPath()%>/explore/selfIndex/saveSelfInfo" class="login-info" onsubmit="return false;" autocomplete="off">
                    <input type="hidden" name="userid" value="${user.userid}" />
                    <div class="inputs">
                        <label class="view-signup taglabel">签名</label>
                        <input type="text" name="selfintro" placeholder="个性签名，介绍你自己" value="${userInfo.selfintro}" />
                    </div>
                    <div class="inputs">
                        <label class="view-signup taglabel">公司</label>
                        <input type="text" name="company" placeholder="所属公司" value="${userInfo.company}" />
                    </div>
                    <div class="inputs">
                        <label class="view-signup taglabel">标签</label>
                        <div class="tags">
                            <%
							UserInfo usrInfo = (UserInfo)request.getAttribute("userInfo");
							if (usrInfo != null) {
								String tags = usrInfo.getTag();
								if (tags != null && tags.length() > 0) {
									String transTags = tags.replace("；", ";");
									String[] tag = transTags.split(";");
									for (String str : tag) {
										%>
                                <span class="tag"><%=str%></span>
                                <%
									}
								}
							}
							%>
                        </div>
                        <input type="text" name="tag" placeholder="标签，以分号分隔，例如：C++；音视频；JAVA" value="${userInfo.tag}" />
                    </div>
                    <button onclick="saveSelfIntro()">保存</button>
                </form>
            </div>
        </body>

        </html>