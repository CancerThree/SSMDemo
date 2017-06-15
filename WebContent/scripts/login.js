/**
 * 隐藏表单错误提示label
 * @param {obejct} ele 错误的input元素，通过该元素找其第一个兄弟label元素并隐藏
 * @return Void
 */
function hide_error_info(ele) {
    // var error_labels = document.getElementById
    var error_label = $("#" + ele).next(".error");
    if (error_label.css("visibility") == "visible") {
        error_label.css("visibility", "hidden");
    }
}

function check_name(checking_str, error_ele) {
    var error_str = "";
    var check_res = true;
    if (checking_str == null || checking_str == "") {
        check_res = false;
        error_str = "请输入姓名";
    } else if (checking_str.length > 20) {
        check_res = false;
        error_str = "用户名不能超过20个字符";
    }
    if (!check_res) {
        error_ele.innerHTML = error_str;
    }
    return check_res;
}

function check_phone_num(checking_str, error_ele) {
    var error_str = "";
    var check_res = true;
    var pattern = /^(1+\d{10})$/;
    if (checking_str == null || checking_str == "") {
        check_res = false;
        error_str = "请输入号码";
    } else if (!pattern.exec(checking_str)) {
        check_res = false;
        error_str = "请输入正确的手机号码";
    }
    if (!check_res) {
        error_ele.innerHTML = error_str;
    }
    return check_res;
}

function check_pwd(checking_str, error_ele) {
    var error_str = "";
    var check_res = true;
    if (checking_str == null || checking_str == "") {
        check_res = false;
        error_str = "请输入密码";
    } else if (checking_str.length < 6 || checking_str.length > 20) {
        check_res = false;
        error_str = "密码长度在6~20之间";
    }
    if (!check_res) {
        error_ele.innerHTML = error_str;
    }
    return check_res;
}

function check_email(checking_str, error_ele) {
    var pattern = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    var error_str = "";
    var check_res = true;
    if (checking_str == null || checking_str == "") {
        check_res = false;
        error_str = "请输入邮箱";
    } else if (!pattern.exec(checking_str)) {
        error_str = "请输入正确的邮箱";
        check_res = false;
    }
    return check_res;
}

function check_account(checking_ele, label_ele) {
    var check_res = true;
    check_res = check_phone_num(checking_ele, label_ele);
    if (!check_res) {
        if (!check_email(checking_ele, label_ele)) {
            label_ele.innerHTML = "请输入正确的邮箱或者手机号";
        }
    }
    return check_res;
}

/**
 * 检查input元素输入是否合法，并在label中显示错误提示信息
 * @param {object} checking_ele 待检查元素
 * @param {obejct} label_ele    提示错误信息的label
 * @return {boolean}  true/false 合法/不合法
 */
function check_valid(checking_ele, label_ele) {
    var check_functions = {
        "username": check_name,
        "phone_num": check_phone_num,
        "pwd": check_pwd,
        "email": check_email,
    };
    if (typeof(check_functions[checking_ele.name]) != "undefined") {
        console.log(checking_ele.value);
        return check_functions[checking_ele.name](checking_ele.value, label_ele);
    }
    return false;
}

/**
 * 检查表单是否填写正确
 * @param {string} sign_type 表单类别：登录/注册(signin/signup)
 * @return boolean
 */
function check_sign_info(sign_type) {
    var error_node;
    var is_valid = true;
    var val_arry;
    if (sign_type == "signup") {
        val_arry = $("#view-signup").find("input");
        error_node = $("#view-signup").find(".error");
    } else if (sign_type == "signin") {
        val_arry = $("view-signin").find("input");
        error_node = $("view-signin").find(".error");
    }
    for (var i = 0; i < val_arry.length; i++) {
        if ((val_arry[i]).type == "hidden") {
            continue;
        }
        if (!check_valid(val_arry[i], error_node[i])) {
            is_valid = false;
            if ($(error_node[i]).css("visibility") == "hidden") {
                $(error_node[i]).css("visibility", "visible");
            }
        }
    }
    return is_valid;
}

/**
 * 切换登录/注册表单
 * @param {boolean} is_signup 是否切换到注册表单
 */
function login_tab_btn_click(is_signup) {
    var signup_btn = $("#signup");
    var signin_btn = $("#signin");
    var slider = $("#tab_slider_bar");
    var signup_view = $("#view-signup");
    var signin_view = $("#view-signin");
    if (is_signup === signup_btn.hasClass("active")) {
        return;
    }
    if (is_signup) {
        signin_btn.toggleClass("active");
        signup_btn.toggleClass("active");
        signup_view.show();
        signin_view.hide();
        slider.css("left", "0");
    } else {
        signin_btn.toggleClass("active");
        signup_btn.toggleClass("active");
        signup_view.hide();
        signin_view.show();
        slider.css("left", "50%");
    }
}

/**
 * 显示在按钮上方的图片（盖住后面的元素）
 * @param {object} event 
 * @param {object} ele 
 */
function showQRCode(event, ele) {
    var showQRCode = $("#QRCode-card");
    var showBtn = $("#show-QRCode-btn");
    if (showQRCode.css("display") == "none") {
        showQRCode.show();
        showBtn.text("关闭二维码");
        (event || window.event).cancelBubble = true;
    }
}

/**
 * 阻止消息冒泡
 * @param {object} event 
 */
function stop_event_bubble(event) {
    (event || window.event).cancelBubble = true;
}

/**
 * 注册页面上的点击相应函数
 */
document.onclick = function() {
    var showQRCode = $("#QRCode-card");
    if (showQRCode.css("display") == "block") {
        showQRCode.hide();
        $("#show-QRCode-btn").text("下载知乎App");
    }
}

function register() {
    if (check_sign_info('signup')) {
        $.post("/ZHWebApp/Regist",
            $("#register_form").serialize(),
            function(data) {
                if (data.errorCode == 0) {
                    location.href = "/ZHWebApp/explore/selfIndex";
                } else {
                    alert("errorCode" + data.errorCode);
                }
            }
        );
    }
}

function login() {
    if (check_sign_info('signin')) {
        $.post("/ZHWebApp/Login",
            $("#login_form").serialize(),
            function(data) {
                if (data.errorCode == 0) {
                    location.href = "/ZHWebApp/explore/selfIndex";
                } else {
                    alert("errorCOde" + data.errorInfo);
                }
            }
        );
    }
}