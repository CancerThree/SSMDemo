<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cmb.zh.domain.UserInfo"%>

<script>
                function saveSelfIntro() {
                    var inputs = $(".inputs").find("input");
                    for (var i = 0; i < inputs.length; i++) {
                        if ($(inputs[i]).val().length > 100) {
                            alert($(inputs[i]).prev("label").text() + "\n字符长度不能超过100");
                            return;
                        }
                    }

                    $.post("<%=request.getContextPath()%>/myInfo/mySettings/saveSelfInfo",
						$("#selfIntro").serialize(), function(data) {
						if (data.errorCode == 0) {
							alert("保存成功");
						} else {
							alert("errorCode" + data.errorInfo);
						}
					});
	}
</script>

<form id="selfIntro"
	action="<%=request.getContextPath()%>/myInfo/mySettings/saveSelfInfo"
	class="login-info" onsubmit="return false;" autocomplete="off">
    <input type="hidden" value="${user.userid}" />
    <dl class="form-group">
        <dt>
            <label>签名</label>
        </dt>
        <dd>
            <input class="form-control" type="text" name="selfintro" placeholder="个性签名，介绍你自己" value="${userInfo.selfintro}" />
        </dd>
    </dl>
    <dl class="form-group">
        <dt>
            <label>公司</label>
            </dt>
        <dd>
            <input class="form-control" type="text" name="company" placeholder="所属公司" value="${userInfo.company}" />
            <!--<textarea></textarea>-->
        </dd>
    </dl>
    <dl class="form-group">
        <dt>
            <label>标签</label>
            </dt>
        <dd>
            <input class="form-control" type="text" name="tag" placeholder="标签，以分号分隔，例如：C++；音视频；JAVA" value="${userInfo.tag}" />
            <!--<textarea></textarea>-->
        </dd>
    </dl>

    <button class="btn submit-btn" onclick="saveSelfIntro()">保存</button>
</form>